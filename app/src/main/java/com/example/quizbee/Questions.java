package com.example.quizbee;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Questions {

    private Integer number;

    private String question;

    @SerializedName("answers")
    private List<String> answers;

    @SerializedName("correct_answer")
    private Integer correctAnswer;
}
