package com.example.demo.service;

import com.example.demo.dto.NetFlowDataChart;
import com.example.demo.model.NetFlowData;
import com.example.demo.util.SaveResultToJsonFile;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NetFlowService {

    public <T> List<T> loadObjectList(Class<T> type, File file) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File fileTemp = file;
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(fileTemp);
            return readValues.readAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
    public List<NetFlowData> getFilteredList(List<NetFlowData> list, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return list.stream()
                .filter(dates -> dates.getDate().isAfter(dateFrom)&& dates.getDate().isBefore(dateTo))
                .collect(Collectors.toList());
    }

    public Map<String,Integer> getTop10DestinationAddress(List<NetFlowData> dataList) {
        Map<String,Integer> result = new TreeMap<>();

        List<String> destinationAddressList = getDestinationAddressList(dataList);

        destinationAddressList.forEach(data ->
                result.put(data,
                        Collections.frequency(destinationAddressList,data)));

         return getTop(result,10);
    }

    public Map<String,Integer> getTop10SourceAddress(List<NetFlowData> dataList) {
        Map<String,Integer> result = new TreeMap<>();

        List<String> sourceList = getSourceList(dataList);

        sourceList.forEach(data ->
                result.put(data, Collections.frequency(sourceList,data)));
        return getTop(result,10);
    }

    public Map<String,Integer> getTop3ProtocolNumber(List<NetFlowData> dataList) {
        Map<String,Integer> result = new TreeMap<>();
        List<String> protocolNumberList = getProtocolNumberList(dataList);

        protocolNumberList.forEach(data ->
                result.put(data, Collections.frequency(protocolNumberList,data)));

        return getTop(result,3);
    }

    public Map<String,Integer> getTop10ApplicationName(List<NetFlowData> dataList) {
        Map<String,Integer> result = new TreeMap<>();
        List<String> applicationNameList = getApplicationNameList(dataList);

        applicationNameList.forEach(data -> result.put(data,Collections.frequency(applicationNameList,data)));
        return getTop(result,10);
    }


    public String getMinAndMaxDateToString(List<NetFlowData> dataList) {
        List<LocalDateTime> list = getLocalDateTimeList(dataList);
        Collections.sort(list);
        return "Min: " + list.get(0).format(DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy")) +
                ", Max: " +list.get(list.size()-1).format(DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy"));
    }

    public void saveChartToJson(Map<String,Integer> top10DestinationAddress,
                                Map<String,Integer> top10SourceAddress,
                                Map<String,Integer> top3ProtocolNumber,
                                Map<String,Integer> top1010ApplicationName) {

        NetFlowDataChart dataChart = new NetFlowDataChart();

        dataChart.setTop10DestinationAddress(top10DestinationAddress);
        dataChart.setTop10SourceAddress(top10SourceAddress);
        dataChart.setTop3ProtocolNumber(top3ProtocolNumber);
        dataChart.setTop1010ApplicationName(top1010ApplicationName);

        SaveResultToJsonFile.saveData(dataChart);
    }

    private List<LocalDateTime> getLocalDateTimeList(List<NetFlowData> dataList) {
        return dataList
                .stream()
                .map(NetFlowData::getDate)
                .collect(Collectors.toList());
    }


    private List<String> getApplicationNameList(List<NetFlowData> dataList) {
        return dataList
                .stream()
                .map(NetFlowData::getApplicationName)
                .collect(Collectors.toList());

    }

    private List<String> getProtocolNumberList(List<NetFlowData> dataList) {
        return dataList
                .stream()
                .map(NetFlowData::getProtocolNumber)
                .collect(Collectors.toList());
    }

    private List<String> getSourceList (List<NetFlowData> dataList) {
        return dataList
                .stream()
                .map(NetFlowData::getSourceAddress)
                .collect(Collectors.toList());
    }

    private List<String> getDestinationAddressList(List<NetFlowData> dataList) {
        return dataList
                .stream()
                .map(NetFlowData::getDestinationAddress)
                .collect(Collectors.toList());
    }

    private Map<String,Integer> getTop(Map<String,Integer> result,int top) {
        return result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(top)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}