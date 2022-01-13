package io.chetan.coronatracker.controllers;

import io.chetan.coronatracker.models.LocationStats;
import io.chetan.coronatracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats=coronaVirusDataService.getAllStats();
        int totalReportedCases= allStats.stream().mapToInt(stat -> stat.getTotalLatestCases()).sum();
        int totalNewCases= allStats.stream().mapToInt(stat -> stat.getYesterdayCases()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalNewCases",totalNewCases);
        model.addAttribute("totalReportedCases",totalReportedCases);
        return "home";
    }
}
