package in.kb.service;

import in.kb.entity.CitizenPlan;
import in.kb.request.SearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReportService {

    public List<String> getPlanNames();

    public List<String> getPlanStatuses();

    public List<CitizenPlan> search(SearchRequest request);

    public boolean exportExcel();

    public boolean exportPdf();

}
