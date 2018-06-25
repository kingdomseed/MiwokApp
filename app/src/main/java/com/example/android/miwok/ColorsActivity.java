/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_activity);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("noir", "black", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("blanc", "white", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("vert", "green", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("jaune", "yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("rouge", "red", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("brun", "brun", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gris", "gray", R.drawable.color_gray, R.raw.color_gray));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }
}
