package com.example.quizbee;

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
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        Question question = quizBees.get(position);
        holder.binding.numberTxt.setText(String.valueOf(position + 1));
        holder.binding.getRoot().setOnClickListener(v -> {
            onItemActionListener.onClick(question);
        });
    }

    @Override
    public int getItemCount() {
        return quizBees.size();
    }
}
