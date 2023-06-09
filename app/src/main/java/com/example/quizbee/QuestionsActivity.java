package com.example.quizbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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

    private List<Question> questions = new ArrayList<>();
    private QuestionsAdapter questionsAdapter;
    private QuizBeeApiService quizBeeApiService;
    private ActivityQuestionsBinding binding;

    private int currentQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpApi();
        fetchQuizDetails();
        setUpQuestionsAdapter();
        setUpQuestionsRv();
        handleNextBtn();
        handlePreviousBtn();
    }

    private void handlePreviousBtn() {
        binding.previousBtn.setOnClickListener(v -> {
            try {
                currentQuestionNumber = questionsAdapter.currentQuestionPosition;
                currentQuestionNumber--;
                Question question = questions.get(currentQuestionNumber);
                getQuestionData(question);
                questionsAdapter.currentQuestionPosition = currentQuestionNumber;
                questionsAdapter.notifyDataSetChanged();

            } catch (Exception e) {
                Toast.makeText(this, "There Is No Questions", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void handleNextBtn() {
        binding.nextBtn.setOnClickListener(v -> {
            currentQuestionNumber = questionsAdapter.currentQuestionPosition;
            currentQuestionNumber++;
            if (currentQuestionNumber == questions.size() - 1) {
                binding.nextBtn.setVisibility(View.GONE);
            } else {
                binding.nextBtn.setVisibility(View.VISIBLE);
            }
            Question question = questions.get(currentQuestionNumber);
            getQuestionData(question);
            questionsAdapter.currentQuestionPosition = currentQuestionNumber;
            questionsAdapter.notifyDataSetChanged();
        });
    }

    private void setUpApi() {
        QuizBeeApi quizBeeApi = new QuizBeeApi();
        quizBeeApiService = quizBeeApi.createQuizBeeService();
    }

    private void getQuestionData(Question question) {
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
                questionsAdapter.setUpData(quizBees.get(0).getQuestionsList());
                questions = quizBees.get(0).getQuestionsList();
                getQuestionData(questions.get(0));
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
        questionsAdapter.setUpData(questions);
        questionsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onClick(Question question) {
                getQuestionData(question);
            }
        });
    }
}