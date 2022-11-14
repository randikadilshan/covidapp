package com.example.covidapp;

public class User {

    public String FullName, NIC_No,CampusID,Email;

    public User(){

    }

    public User(String fullName, String nicno,String campusID,String Email){

        this.FullName = fullName;
        this.NIC_No = nicno;
        this.CampusID = campusID;
        this.Email = Email;


    }
}
