package com.example.quizbee;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbee.databinding.QuestionsItemBinding;

public class QuestionsViewHolder extends RecyclerView.ViewHolder {

    QuestionsItemBinding binding;

    public QuestionsViewHolder(QuestionsItemBinding questionsItemBinding) {
        super(questionsItemBinding.getRoot());
        binding = questionsItemBinding;
    }
}
