package in.kb.controller;

import in.kb.entity.CitizenPlan;
import in.kb.request.SearchRequest;
import in.kb.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/pdf")
    public void pdfExport(HttpServletResponse response) throws Exception{
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition","attachment;filename=plans.pdf");
        service.exportPdf(response);
    }

    @GetMapping("/excel")
    public void excelExport(HttpServletResponse response) throws Exception{
        //To tell browser data out is coming in the excel(octet-stream) format
        response.setContentType("application/octet-stream");
        //When you click on the excel, excel will be down in the browser
        response.addHeader("Content-Disposition","attachment;filename=plans.xls");
        service.exportExcel(response);

    }


    @PostMapping("/search")
    public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model){
        System.out.println(search);

        List<CitizenPlan> plans = service.search(search);
        model.addAttribute("plans",plans);
        init(model);
        return "index";
    }

    /*
    This method is used to load index page
    @Param model
    @return String
     */
    @GetMapping("/")
    public String indexPage(Model model){
        /*
        SearchRequest searchObj = new SearchRequest();
        model.addAttribute("search",searchObj);
        this can be written as model.addAttribute("search",new SearchRequest());
        */
        model.addAttribute("search", new SearchRequest());
        init(model);
        return "index";
    }

    private void init(Model model) {
//        model.addAttribute("search", new SearchRequest());
        model.addAttribute("names",service.getPlanNames());
        model.addAttribute("status",service.getPlanStatuses());
    }

}
