package in.kb.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import in.kb.entity.CitizenPlan;
import in.kb.repo.CitizenPlanRepository;
import in.kb.request.SearchRequest;
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


        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("plans-data");
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Citizen Name");
        headerRow.createCell(2).setCellValue("Plan Name");
        headerRow.createCell(3).setCellValue("Plan Status");
        headerRow.createCell(4).setCellValue("Plan Start Date");
        headerRow.createCell(5).setCellValue("Pan End Date");
        headerRow.createCell(6).setCellValue("Benefit Amt");

        List<CitizenPlan> records = planRepo.findAll();

        int dataRowIndex = 1;

        for(CitizenPlan plan: records){
            Row dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(plan.getCitizenId());
            dataRow.createCell(1).setCellValue(plan.getCitizenName());
            dataRow.createCell(2).setCellValue(plan.getPlanName());
            dataRow.createCell(3).setCellValue(plan.getPlanStatus());

            if(null != plan.getPlanStartDate()){
                dataRow.createCell(4).setCellValue(plan.getPlanStartDate()+"");
            }else {
                dataRow.createCell(4).setCellValue("N/A");
            }

            if(null != plan.getPlanEndDate()){
                dataRow.createCell(5).setCellValue(plan.getPlanEndDate()+"");
            }else {
                dataRow.createCell(5).setCellValue("N/A");
            }


            if(null != plan.getBenefitAmount()){
                dataRow.createCell(6).setCellValue(plan.getBenefitAmount());
            }else {
                dataRow.createCell(6).setCellValue("N/A");
            }

            dataRowIndex++;
        }

//        FileOutputStream fos = new FileOutputStream(new File("plan.xls"));
//        workbook.write(fos);
//        workbook.close();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return true;
    }

    @Override
    public boolean exportPdf(HttpServletResponse response) throws Exception {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Creating paragraph
        Paragraph paragraph = new Paragraph("Citizen Plan Info", fontTiltle);
        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(6);
        table.setSpacingBefore(5);


        table.addCell("Id");
        table.addCell("Citizen Name");
        table.addCell("Plan Name");
        table.addCell("Plan Status");
        table.addCell("Start Date");
        table.addCell("End Date");

        List<CitizenPlan> plans= planRepo.findAll();
        for (CitizenPlan plan : plans){
            table.addCell(String.valueOf(plan.getCitizenId()));
            table.addCell(plan.getCitizenName());
            table.addCell(plan.getPlanName());
            table.addCell(plan.getPlanStatus());
            table.addCell(plan.getPlanStartDate()+"");
            table.addCell(plan.getPlanEndDate()+"");
        }

        document.add(table);
        document.close();

        return true;
    }


}
