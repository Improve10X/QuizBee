package com.example.quizbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quizbee.databinding.ActivityQuestionsBinding;
import com.example.quizbee.modelclass.Question;
import com.example.quizbee.modelclass.QuizBee;
import com.example.quizbee.network.QuizBeeApi;
import com.example.quizbee.network.QuizBeeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity extends AppCompatActivity {

    private List<QuizBee> quizBeeList = new ArrayList<>();
    private QuestionsAdapter questionsAdapter;
    private QuizBeeApiService quizBeeApiService;
    private ActivityQuestionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpApi();
        fetchQuizDetails();
        setUpQuestionsAdapter();
        setUpQuestionsRv();
    }

    private void setUpApi() {
        QuizBeeApi quizBeeApi = new QuizBeeApi();
        quizBeeApiService = quizBeeApi.createQuizBeeService();
    }

    private void getQuestion(Question question) {
        binding.questionTxt.setText(question.getQuestion());
        binding.radioButton1Rb.setText(question.getAnswers().get(0));
        binding.radioButton2Rb.setText(question.getAnswers().get(1));
        binding.radioButton3Rb.setText(question.getAnswers().get(2));
        binding.radioButton4Rb.setText(question.getAnswers().get(3));
    }

    private void fetchQuizDetails() {
        Call<List<QuizBee>> call = quizBeeApiService.fetchQuizItems();
        call.enqueue(new Callback<List<QuizBee>>() {
            @Override
            public void onResponse(Call<List<QuizBee>> call, Response<List<QuizBee>> response) {
                Toast.makeText(QuestionsActivity.this, "FetchItems successfully", Toast.LENGTH_SHORT).show();
                List<QuizBee> quizBees = response.body();
                questionsAdapter.setUpData(quizBees);
                getQuestion(quizBees.get(0).getQuestionsList().get(0));

            }

            @Override
            public void onFailure(Call<List<QuizBee>> call, Throwable t) {
                Toast.makeText(QuestionsActivity.this, "Failed To Load Items", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpQuestionsRv() {
        binding.questionRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.questionRv.setAdapter(questionsAdapter);
    }

    private void setUpQuestionsAdapter() {
        questionsAdapter = new QuestionsAdapter();
        questionsAdapter.setUpData(quizBeeList);
    }
}