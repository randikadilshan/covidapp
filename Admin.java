package com.example.covidapp;

public class Admin {

    public String FullName, NIC_No,Lecture_ID,Email;

    public Admin(){

    }

    public Admin(String fullName, String nicno,String campusID,String email){

        this.FullName = fullName;
        this.NIC_No = nicno;
        this.Lecture_ID = campusID;
        this.Email = email;

    }
}
