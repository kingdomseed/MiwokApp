package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter {

    public WordAdapter(Context context, List<Word> words)
    {
        super(context, R.layout.language_list_layout, words);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.language_list_layout, parent, false);
        }

        Word currentWord = (Word) getItem(position);

        TextView frenchTranslationTextView = (TextView) listItemView.findViewById(R.id.word);
        frenchTranslationTextView.setText(currentWord.getFrenchTranslation());

        TextView englishTranslationTextView = (TextView) listItemView.findViewById(R.id.translation);
        englishTranslationTextView.setText(currentWord.getDefaultTranslation());

        return listItemView;
    }
}
