package com.example.covidapp;

public class Quarentine {

        private String dquarentine;
        public String NowYouAreQuarentine;

        public Quarentine(){}

        public Quarentine(String NowYouAreQuarentine)
        {
            this.NowYouAreQuarentine = NowYouAreQuarentine;
        }


        public String getquarentine () {return dquarentine;}

        public void setquarentine (String quarentine) {NowYouAreQuarentine= quarentine;}
    }


