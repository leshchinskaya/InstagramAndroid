package com.example.mariya.someandroidapp.models;

public class Images {
    private Standart_resolution standart_resolution;

    public Standart_resolution getStandart_resolution(){
        return standart_resolution;
    }

    public class Standart_resolution {
        private String url;
        public String getUrl (){
            return url;
        }
    }
}
