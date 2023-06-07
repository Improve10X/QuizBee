package com.example.quizbee;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.quizbee.modelclass.QuizBee;
import com.example.quizbee.network.QuizBeeApi;
import com.example.quizbee.network.QuizBeeApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test

    public void fetchQuizBeeItems() throws IOException {
        QuizBeeApi quizBeeApiApi = new QuizBeeApi();
        QuizBeeApiService shopsApiService = quizBeeApiApi.createQuizBeeService();
        Call<List<QuizBee>> call = shopsApiService.fetchQuizItems();
        List<QuizBee> quiz = call.execute().body();
        assertNotNull(quiz);
        assertFalse(quiz.isEmpty());
        System.out.println(new Gson().toJson(quiz));
    }
}