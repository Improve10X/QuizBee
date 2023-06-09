package com.example.quizbee;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbee.databinding.QuestionsItemBinding;
import com.example.quizbee.modelclass.Question;
import com.example.quizbee.modelclass.QuizBee;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder> {

    private List<Question> quizBees;

    public int currentQuestionPosition = 0;
    private OnItemActionListener onItemActionListener;
    void setUpData(List<Question> quizBees) {
        this.quizBees = quizBees;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsItemBinding questionItemBinding = QuestionsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(questionItemBinding);
        return questionsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Question question = quizBees.get(position);
        holder.binding.numberTxt.setText(String.valueOf(question.getNumber()));
        holder.binding.getRoot().setOnClickListener(v -> {
            currentQuestionPosition = position;
            notifyDataSetChanged();
            onItemActionListener.onClick(question);
        });
        if (currentQuestionPosition == position ) {
            holder.binding.numberTxt.setTextColor(Color.parseColor("#D61D1D"));
        } else {
            holder.binding.numberTxt.setTextColor(Color.parseColor("#050505"));
        }
    }

    @Override
    public int getItemCount() {
        return quizBees.size();
    }
}
