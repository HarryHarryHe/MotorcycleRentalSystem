package com.uk.ncl.entity;

import com.uk.ncl.Tools;

import java.util.*;

public class License {
    private String licenseID;
    private static Set<String> licenseIDSet = new HashSet<>();
    private Client client;
    private Date issueDate;
    private boolean isFormal;

    public License() {
    }

    public String getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }

    public static Set<String> getLicenseIDSet() {
        return licenseIDSet;
    }

    public static void setLicenseIDSet(Set<String> licenseIDSet) {
        License.licenseIDSet = licenseIDSet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isFormal() {
        return isFormal;
    }

    public void setFormal(boolean formal) {
        isFormal = formal;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    private String genLicenseID() {
        StringJoiner licenseIDSj = new StringJoiner("-");
        String[] name = client.getName().split(" ");
        String firstName = name[0].substring(0, 1);
        String lastName = name[1].substring(0, 1);
        String[] issueDate = Tools.getFormatDate(this.issueDate).split("-");
        String issueDateYear = issueDate[0];
        String serNum = String.format("%02d", new Random().nextInt(100));
        licenseIDSj = licenseIDSj.add(firstName + lastName).add(issueDateYear).add(serNum);
        String licenseID = licenseIDSj.toString();
        while (licenseIDSet.contains(licenseID)) {
            genLicenseID();
        }
        return licenseID;
    }

    public static void main(String[] args) {
        License license = new License();
        String issueDateStr = "2000-03-21";
        String birthDateStr = "1980-05-04";
        Date issueDate = Tools.parseToDate(issueDateStr);
        Date birthDate = Tools.parseToDate(birthDateStr);
        license.setClient(new Client("Sihuo","Zhao",birthDate));
        license.setIssueDate(issueDate);
        String licenseID = license.genLicenseID();
        System.out.println(licenseID);
    }
}
