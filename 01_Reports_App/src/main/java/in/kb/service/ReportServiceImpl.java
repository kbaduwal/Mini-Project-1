package in.kb.service;

import in.kb.entity.CitizenPlan;
import in.kb.repo.CitizenPlanRepository;
import in.kb.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<CitizenPlan> searchResult = planRepo.findAll();
        System.out.println("\n Search is happening some issue with JSP \n"+searchResult);
        return planRepo.findAll();
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
