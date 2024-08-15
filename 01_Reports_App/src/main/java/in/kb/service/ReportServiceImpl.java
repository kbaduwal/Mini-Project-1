package in.kb.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import in.kb.entity.CitizenPlan;
import in.kb.repo.CitizenPlanRepository;
import in.kb.request.SearchRequest;
import in.kb.util.EmailUtil;
import in.kb.util.ExcelGenerator;
import in.kb.util.PdfGenerator;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CitizenPlanRepository planRepo;

    @Autowired
    private ExcelGenerator excelGenerator;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public List<String> getPlanNames() {
//        List<String> planNames = planRepo.getPlanNames();
//        return planNames;
        //Above things can be written as

        return planRepo.getPlanNames();
    }

    @Override
    public List<String> getPlanStatuses() {
        return planRepo.getPlanStatus();
    }

    @Override
    public List<CitizenPlan> search(SearchRequest request) {
        CitizenPlan entity = new CitizenPlan();

        if(null!=request.getPlanName() && !"".equals(request.getPlanName())){
            entity.setPlanName(request.getPlanName());
        }

        if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
            entity.setPlanStatus(request.getPlanStatus());
        }

        if(null!=request.getGender() && !"".equals(request.getGender())){
            entity.setGender(request.getGender());
        }

        if(null!=request.getStartDate() && !"".equals(request.getStartDate())){
          String  startDate= request.getStartDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            //convert String to LocalDate
            LocalDate localDate = LocalDate.parse(startDate, formatter);
            entity.setPlanStartDate(localDate);

        }

        if(null!=request.getEndDate() && !"".equals(request.getEndDate())){
            String  endDate= request.getEndDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            //convert String to LocalDate
            LocalDate localDate = LocalDate.parse(endDate, formatter);
            entity.setPlanEndDate(localDate);

        }

        return planRepo.findAll(Example.of(entity));
    }

    @Override
    public boolean exportExcel(HttpServletResponse response) throws Exception {

        File f = new File("Plans.xls");

        List<CitizenPlan> plans = planRepo.findAll();
        excelGenerator.generate(response,plans,f);

        String subject = "Text Mail Subject";
        String body = "<h1>Test mail body. </h1>";
        String to = "khakendrabaduwal1lh@gmail.com";

        emailUtil.sendMail(subject,body,to,f);
        f.delete();

        return true;
    }

    @Override
    public boolean exportPdf(HttpServletResponse response) throws Exception {
        File f = new File("Plans.pdf");

       List<CitizenPlan> plans = planRepo.findAll();
        pdfGenerator.generate(response,plans,f);

        String subject = "Text Mail Subject";
        String body = "<h1>Test mail body. </h1>";
        String to = "khakendrabaduwal1lh@gmail.com";

        emailUtil.sendMail(subject,body,to,f);
        f.delete();

        return true;
    }




}
