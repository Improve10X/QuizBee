package com.example.quizbee;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbee.databinding.QuestionsItemBinding;
import com.example.quizbee.modelclass.QuizBee;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder> {

    private List<QuizBee> quizBees;

    void setUpData(List<QuizBee> quizBees) {
        this.quizBees = quizBees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsItemBinding questionItemBinding = QuestionsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(questionItemBinding);
        return questionsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        QuizBee quizBee = quizBees.get(position);
        holder.binding.numberTxt.setText(String.valueOf(position + 1));

    }

    @Override
    public int getItemCount() {
        return quizBees.size();
    }
}
