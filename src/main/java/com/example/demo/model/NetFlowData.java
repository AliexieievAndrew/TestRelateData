package com.example.demo.model;

import com.example.demo.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NetFlowData {
    private static String LOCAL_DATE_PATTERN = "M/dd/yyyy h:mm:ss.SSSSSSSSS a";

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime date;
    private Long bytesIn;
    private Long bytesOut;
    private Long packetsIn;
    private Long packetsOut;
    private String applicationName;
    private String destinationAddress;
    private String protocolNumber;
    private String sourceAddress;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date =
                LocalDateTime.parse(date, DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN));
    }

    public Long getBytesIn() {
        return bytesIn;
    }

    public void setBytesIn(Long bytesIn) {
        this.bytesIn = bytesIn;
    }

    public Long getBytesOut() {
        return bytesOut;
    }

    public void setBytesOut(Long bytesOut) {
        this.bytesOut = bytesOut;
    }

    public Long getPacketsIn() {
        return packetsIn;
    }

    public void setPacketsIn(Long packetsIn) {
        this.packetsIn = packetsIn;
    }

    public Long getPacketsOut() {
        return packetsOut;
    }

    public void setPacketsOut(Long packetsOut) {
        this.packetsOut = packetsOut;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    @Override
    public String toString() {
        return "NetFlowData{" +
                "date=" + date +
                ", bytesIn=" + bytesIn +
                ", bytesOut=" + bytesOut +
                ", packetsIn=" + packetsIn +
                ", packetsOut=" + packetsOut +
                ", applicationName='" + applicationName + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", protocolNumber=" + protocolNumber +
                ", sourceAddress='" + sourceAddress + '\'' +
                '}';
    }
}