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

    public License(Date issueDate, boolean isFormal) {
        this.issueDate = issueDate;
        this.isFormal = isFormal;
    }

    public License(String licenseID, Date issueDate, boolean isFormal) {
        this.licenseID = licenseID;
        this.issueDate = issueDate;
        this.isFormal = isFormal;
    }

    public License(String licenseID, Client client, Date issueDate, boolean isFormal) {
        this.licenseID = licenseID;
        this.client = client;
        this.issueDate = issueDate;
        this.isFormal = isFormal;
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

    public boolean getIsFormal() {
        return isFormal;
    }

    public void setIsFormal(boolean formal) {
        this.isFormal = formal;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Generate a unique driver's license ID based on initials, issue date and serial number
     * @return licenseID
     */
    public String genLicenseID() {
        //in order to build final ID
        StringJoiner licenseIDSj = new StringJoiner("-");
        String[] name = client.getName().split(" ");
        String firstName = name[0].substring(0, 1);
        String lastName = name[1].substring(0, 1);
        String[] issueDate = Tools.getFormatDate(this.issueDate).split("-");
        String issueDateYear = issueDate[0];
        String serNum = String.format("%02d", new Random().nextInt(100));
        licenseIDSj.add(firstName + lastName).add(issueDateYear).add(serNum);
        String licenseID = licenseIDSj.toString();
        while (licenseIDSet.contains(licenseID)) {
            genLicenseID();
        }
        return licenseID;
    }
}
