package com.example.demo.controller;

import com.example.demo.model.NetFlowData;
import com.example.demo.service.NetFlowService;
import com.example.demo.util.MultipartFileToFileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {

    @Autowired
    private NetFlowService netFlowService;

    private List<NetFlowData> netFlowDataList;

    @GetMapping({"/", ""})
    public String welcome() {
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartfile) {
        if (multipartfile.isEmpty()) {
            return "redirect:/";
        }

        File file = MultipartFileToFileConverter.convertToFile(multipartfile);

        netFlowDataList = netFlowService.loadObjectList(NetFlowData.class, file);

        return "redirect:/process";
    }

    @GetMapping("/process")
    public String processFile(@RequestParam(value = "timeFrom", required = false) String timeFrom,
                              @RequestParam(value = "timeTo", required = false) String timeTo,
                              Model model) {

        if (netFlowDataList == null) {
            return "index";
        }

        model.addAttribute("rage", netFlowService.getMinAndMaxDateToString(netFlowDataList));

        if(timeFrom!= null && timeTo != null) {
            List<NetFlowData> filteredList = netFlowService
                    .getFilteredList(
                            netFlowDataList,
                            LocalDateTime.parse(timeFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
                            LocalDateTime.parse(timeTo, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

            Map<String, Integer> top10DestinationAddress =
                    netFlowService.getTop10DestinationAddress(filteredList);
            model.addAttribute("top10DestinationAddress",top10DestinationAddress);

            Map<String, Integer> top10SourceAddress = netFlowService.getTop10SourceAddress(filteredList);
            model.addAttribute("top10SourceAddress",top10SourceAddress);

            Map<String, Integer> top3ProtocolNumber = netFlowService.getTop3ProtocolNumber(filteredList);
            model.addAttribute("top3ProtocolNumber",top3ProtocolNumber);

            Map<String, Integer> top10ApplicationName = netFlowService.getTop10ApplicationName(filteredList);
            model.addAttribute("top10ApplicationName",top10ApplicationName);

            netFlowService.saveChartToJson(
                    top10DestinationAddress,
                    top10SourceAddress,
                    top3ProtocolNumber,
                    top10ApplicationName);
        }

        return "process";
    }
}