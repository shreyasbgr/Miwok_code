package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mplayer;

    MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<Word> numbers=new ArrayList<Word>();
        numbers.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        numbers.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        numbers.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        numbers.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        numbers.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        numbers.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        numbers.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        numbers.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        numbers.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        numbers.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter wa = new WordAdapter(this,numbers,R.color.category_family);

        ListView listview=(ListView) findViewById(R.id.list);
        listview.setAdapter(wa);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = numbers.get(i);
                releaseMediaPlayer();
                MediaPlayer mplayer = MediaPlayer.create(FamilyActivity.this,currentWord.getMusicfileId());
                mplayer.start();
                mplayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer()
    {
        if(mplayer!=null)
        {
            mplayer.release();
            mplayer=null;
        }
    }
}
