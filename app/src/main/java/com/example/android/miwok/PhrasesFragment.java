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
public class PhrasesFragment extends Fragment {

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

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list_activity, container, false);

        words = new ArrayList<>();
        words.add(new Word("zero", "zero", R.raw.phrase_are_you_coming));
        words.add(new Word("un", "one", R.raw.phrase_come_here));
        words.add(new Word("deux", "two", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("trois", "three", R.raw.phrase_im_coming));
        words.add(new Word("quatre", "four", R.raw.phrase_im_feeling_good));
        words.add(new Word("cinq", "five", R.raw.phrase_lets_go));
        words.add(new Word("six", "six", R.raw.phrase_my_name_is));
        words.add(new Word("sept", "seven", R.raw.phrase_what_is_your_name));
        words.add(new Word("huit", "eight", R.raw.phrase_where_are_you_going));
        words.add(new Word("neuf", "nine", R.raw.phrase_yes_im_coming));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

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
