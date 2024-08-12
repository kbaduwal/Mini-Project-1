package in.kb.service;

import in.kb.entity.CitizenPlan;
import in.kb.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReportService {

    public List<String> getPlanNames();

    public List<String> getPlanStatuses();

    public List<CitizenPlan> search(SearchRequest request);

    public boolean exportExcel(HttpServletResponse response) throws Exception;

    public boolean exportPdf(HttpServletResponse response) throws Exception;

}
