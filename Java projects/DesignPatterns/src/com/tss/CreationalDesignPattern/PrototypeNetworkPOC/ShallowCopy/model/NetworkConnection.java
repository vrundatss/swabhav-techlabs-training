package com.tss.CreationalDesignPattern.PrototypeNetworkPOC.ShallowCopy.model;

import java.util.ArrayList;
import java.util.List;

public class NetworkConnection implements Cloneable{
    private String ipAddress;
    private String data;

    private List<String> domains = new ArrayList<>();

    public void loadData() throws InterruptedException {
        this.data = "Loading very large and important data";

        domains.add("www.google.com");
        domains.add("\nhttps://tsm.swabhavtechlabs.com");
        domains.add("\nhttps://teams.microsoft.com");
        domains.add("\nhttps://mail.google.com");

        Thread.sleep(2000);
    }

    @Override
    public String toString() {
        return
                "Ip Address='" + ipAddress + '\'' +
                        ", Data='" + data + '\n' +
                        "Domains = \n" +  this.domains +
                        "----------------------------------------------\n"
                ;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

}
