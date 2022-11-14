package com.example.covidapp;

public class Covid {

    private String CampusID,ContactNo;
    private String dcovid;
    public String DoYouHaveCovid;

    public Covid(){}

    public Covid(String DoYouHaveCovid)
    {
        this.DoYouHaveCovid = DoYouHaveCovid;
    }


    public String getName(){ return CampusID;}

    public void setName(String name) {CampusID = name;}


    public String getContactNo(){ return ContactNo;}

    public void setContactNo(String contactNo) {ContactNo = contactNo;}

    public String getcovid () {return dcovid;}

    public void setcovid (String covid) {DoYouHaveCovid= covid;}

}
