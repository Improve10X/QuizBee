package com.example.quizbee;

import org.junit.Test;

import static org.junit.Assert.*;

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
        List<QuizBee> shops = call.execute().body();
        assertNotNull(shops);
        assertFalse(shops.isEmpty());
        System.out.println(new Gson().toJson(shops));
    }
}