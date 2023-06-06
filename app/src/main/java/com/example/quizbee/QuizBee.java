package com.example.quizbee;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizBee {

    private String id;

    @SerializedName("module")
    public Module module;

    @SerializedName("questions")
    public List<Questions> questionsList;

}
