package com.cybertek.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


    @Setter
    @Getter
    @ToString

    @JsonIgnoreProperties(value = "id", allowGetters = true)
    public class Spartan {
        //getter setter
        //toString
        private int id;
        private String name;
        private String gender;
        private long phone;

    }
