package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter {

    private int backgroundColorResourceID;
    private MediaPlayer mediaPlayer;

    public WordAdapter(Context context, List<Word> words, int backgroundColorResourceID) {
        super(context, R.layout.language_list_layout, words);
        this.backgroundColorResourceID = backgroundColorResourceID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;


        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.language_list_layout, parent, false);
        }

        View textContainer = listItemView.findViewById(R.id.linearLayoutForBackgroundColor);
        int color = ContextCompat.getColor(getContext(), backgroundColorResourceID);
        textContainer.setBackgroundColor(color);


        final Word currentWord = (Word) getItem(position);

        textContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getContext(), currentWord.getmSoundFileID());
                mediaPlayer.start();
            }
        });

        TextView frenchTranslationTextView = (TextView) listItemView.findViewById(R.id.word);
        frenchTranslationTextView.setText(currentWord.getFrenchTranslation());

        TextView englishTranslationTextView = (TextView) listItemView.findViewById(R.id.translation);
        englishTranslationTextView.setText(currentWord.getDefaultTranslation());

        ImageView pictureTranslationImageView = (ImageView) listItemView.findViewById(R.id.image);
        pictureTranslationImageView.setImageResource(currentWord.getmImageResourceID());

        if (pictureTranslationImageView.getDrawable() != null) {
            pictureTranslationImageView.setVisibility(ImageView.VISIBLE);
        } else {
            pictureTranslationImageView.setVisibility(ImageView.GONE);
        }

        return listItemView;
    }
}
