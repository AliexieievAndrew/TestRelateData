package com.example.demo.util;

import com.example.demo.dto.NetFlowDataChart;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public class SaveResultToJsonFile {

    public static void saveData(NetFlowDataChart dataChart) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File("data.txt"), dataChart);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("error message: %s",e.getMessage()));
        }
    }
}
