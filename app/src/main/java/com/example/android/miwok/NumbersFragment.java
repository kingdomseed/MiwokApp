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

import java.util.ArrayList;

public class NumbersFragment extends Fragment {

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

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list_activity, container, false);

        words = new ArrayList<>();
        words.add(new Word("un", "one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("deux", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("trois", "three", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("quatre", "four", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("cinq", "five", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("sept", "seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("huit", "eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("neuf", "nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("dix", "ten", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

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
