package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private ArrayList<Word> words;
    private AudioManager am;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS ) {
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                }
            };


    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list_activity, container, false);
        words = new ArrayList<>();
        words.add(new Word("noir", "black", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("blanc", "white", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("vert", "green", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("jaune", "yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("rouge", "red", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("brun", "brun", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gris", "gray", R.drawable.color_gray, R.raw.color_gray));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getmSoundFileID());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {

        if (mediaPlayer != null) {

            mediaPlayer.release();

            mediaPlayer = null;

            am.abandonAudioFocus(afChangeListener);
        }
    }
}
