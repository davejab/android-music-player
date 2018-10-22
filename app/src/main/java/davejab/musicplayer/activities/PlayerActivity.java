package davejab.musicplayer.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import davejab.musicplayer.R;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.main.Player;
import davejab.musicplayer.models.Song;
import davejab.musicplayer.util.Time;

public class PlayerActivity extends Activity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private ImageButton btnPlay;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private ImageButton btnRepeat;
    private ImageButton btnShuffle;
    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    // Media Player
//    private MediaPlayer mp;
//    private List<Song> songsList;
    // Handler to update UI timer, progress bar etc,.
    private Handler progressHandler = new Handler();
    //private SongsManager songManager;
    //private Time time;
    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds
    //private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();


    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // All player buttons
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
        btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);
        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        //songTitleLabel = (TextView) findViewById(R.id.songTitle);
        songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);

        songProgressBar.setOnSeekBarChangeListener(this);

        setPlayer(Player.getPlayer());
        getPlayer().setOnCompletionListener(this);

        Intent intent = getIntent();
        int position = intent.getIntExtra("songIndex", 0);

        getPlayer().setPlaylist(Library.getLibrary(getContentResolver()).getCurrentList());
        getPlayer().setCurrentSongIndex(position);
        playSong(getPlayer().getCurrentSong());


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                getPlayer().next();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                getPlayer().previous();
            }
        });

        btnRepeat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                if (isRepeat) {
//                    isRepeat = false;
//                    Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
//                    btnRepeat.setImageResource(R.drawable.btn_repeat);
//                } else {
//                    // make repeat to true
//                    isRepeat = true;
//                    Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
//                    // make shuffle to false
//                    isShuffle = false;
//                    //    btnRepeat.setImageResource(R.drawable.btn_repeat_focused);
//                    btnShuffle.setImageResource(R.drawable.btn_shuffle);
//                }
            }
        });

        btnShuffle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                if (isShuffle) {
//                    isShuffle = false;
//                    Toast.makeText(getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
//                    btnShuffle.setImageResource(R.drawable.btn_shuffle);
//                } else {
//                    // make repeat to true
//                    isShuffle = true;
//                    Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
//                    // make shuffle to false
//                    isRepeat = false;
//                    //    btnShuffle.setImageResource(R.drawable.btn_shuffle_focused);
//                    btnRepeat.setImageResource(R.drawable.btn_repeat);
//                }
            }
        });
    }

    public void playSong(Song song) {
        // Play song
        try {

            getPlayer().playSong(song);
            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);//////////

            // Updating progress ba/r
            updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        }
    }


    public void updateProgressBar() {
        progressHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
//                long totalDuration = getPlayer().getCurrentSong().getDuration();
//               long currentDuration = mp.getCurrentPosition();
//
//            // Displaying Total Duration time
//               songTotalDurationLabel.setText(time.milliSecondsToTimer(totalDuration));
//            //   // Displaying time completed playing
//              songCurrentDurationLabel.setText(time.milliSecondsToTimer(currentDuration));
//
//            // Updating progress bar
//            int progress = time.getProgressPercentage(currentDuration, totalDuration);
//            ///Log.d("Progress", ""+progress);
//            songProgressBar.setProgress(progress);
//
//            // Running this thread after 100 milliseconds
//            progressHandler.postDelayed(this, 100);
        }
    };

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        progressHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
//        progressHandler.removeCallbacks(mUpdateTimeTask);
//        int totalDuration = mp.getDuration();
//        int currentPosition = time.progressToTimer(seekBar.getProgress(), totalDuration);
//
//        // forward or backward to certain seconds
//        mp.seekTo(currentPosition);
//
//        // update timer progress again
//        updateProgressBar();
    }

    @Override
    public void onCompletion(MediaPlayer arg0) {

    }

    private Player getPlayer(){
        return this.player;
    }
    private void setPlayer(Player player){
        this.player = player;
    }
}

