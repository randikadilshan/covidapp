package com.example.covidapp;

public class Vaccine {


    private String dvaccine;
    public String DidYouGetAVaciine;

    public Vaccine(){}

    public Vaccine(String DidYouGetAVaccine)
    {
        this.DidYouGetAVaciine = DidYouGetAVaccine;
    }


    public String getvaccine () {return dvaccine;}

    public void setvaccine (String vaccine) {DidYouGetAVaciine= vaccine;}
}
