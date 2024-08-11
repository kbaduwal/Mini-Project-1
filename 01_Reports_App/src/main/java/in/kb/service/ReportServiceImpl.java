package in.kb.service;

import in.kb.entity.CitizenPlan;
import in.kb.repo.CitizenPlanRepository;
import in.kb.request.SearchRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
    public boolean exportExcel() {
        return false;
    }

    @Override
    public boolean exportPdf() {
        return false;
    }
}
