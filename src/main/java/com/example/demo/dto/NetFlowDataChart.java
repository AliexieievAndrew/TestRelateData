package com.example.demo.dto;

import java.util.Map;

public class NetFlowDataChart {

    private Map<String,Integer> top10DestinationAddress;
    private Map<String,Integer> top10SourceAddress;
    private Map<String,Integer> top3ProtocolNumber;
    private Map<String,Integer> top1010ApplicationName;

    public Map<String, Integer> getTop10DestinationAddress() {
        return top10DestinationAddress;
    }

    public void setTop10DestinationAddress(Map<String, Integer> top10DestinationAddress) {
        this.top10DestinationAddress = top10DestinationAddress;
    }

    public Map<String, Integer> getTop10SourceAddress() {
        return top10SourceAddress;
    }

    public void setTop10SourceAddress(Map<String, Integer> top10SourceAddress) {
        this.top10SourceAddress = top10SourceAddress;
    }

    public Map<String, Integer> getTop3ProtocolNumber() {
        return top3ProtocolNumber;
    }

    public void setTop3ProtocolNumber(Map<String, Integer> top3ProtocolNumber) {
        this.top3ProtocolNumber = top3ProtocolNumber;
    }

    public Map<String, Integer> getTop1010ApplicationName() {
        return top1010ApplicationName;
    }

    public void setTop1010ApplicationName(Map<String, Integer> top1010ApplicationName) {
        this.top1010ApplicationName = top1010ApplicationName;
    }
}
