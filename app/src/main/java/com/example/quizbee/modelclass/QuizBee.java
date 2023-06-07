package com.example.quizbee.modelclass;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuizBee {

    @SerializedName("_id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public ArrayList<Question> getQuestionsList() {
        return questionList;
    }

    public void setQuestionsList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    @SerializedName("module")
    public Module module;

   @SerializedName("questions")
   public ArrayList<Question> questionList;

}
