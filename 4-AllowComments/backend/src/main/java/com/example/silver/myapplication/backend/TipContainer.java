package com.example.silver.myapplication.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class TipContainer {
    @Id String id;
    static final String defaultTipContainer = "default";
}
