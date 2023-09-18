package com.pandasdroid.rvexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pandasdroid.rvexample.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    AdapterTitles adapterTitles;

    ArrayList<MainModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        MainModel m1 = new MainModel();
        m1.title = "1. Vector";

        //SubTitlesModel1
        SubTitlesModel sm = new SubTitlesModel();
        m1.list.add(sm);
        sm.title = "1." + (1);
        // A static list of meaningful words
        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(
                "apple", "banana", "carrot", "dog", "elephant",
                "flower", "guitar", "happiness", "island", "jazz", "book", "pencil", "laptop", "mouse", "cats", "dogs"
        ));

        sm.listTitles = wordsList;

        //SubTitlesModel1
        SubTitlesModel sm2 = new SubTitlesModel();
        m1.list.add(sm2);
        sm2.title = "1." + (2);
        sm2.listTitles = wordsList;

        list.add(m1);
        list.add(m1);
        list.add(m1);
        list.add(m1);

        adapterTitles = new AdapterTitles(list);
        binding.rvTitles.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.rvTitles.setAdapter(adapterTitles);
        binding.rvTitles.setHasFixedSize(true);
        binding.rvTitles.setItemViewCacheSize(list.size() + 1);
    }

    public static ArrayList<String> generateRandomWordList() {
        ArrayList<String> wordList = new ArrayList<>();
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 20; i++) { // Generate 20 random words
            int wordLength = random.nextInt(9) + 4; // Random length from 4 to 12
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < wordLength; j++) {
                char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
                word.append(randomChar);
            }

            int count = random.nextInt(5) + 2; // Random count from 2 to 6
            for (int j = 0; j < count; j++) {
                wordList.add(word.toString());
            }
        }

        return wordList;
    }
}