package davejab.musicplayer.activities.views;

import android.app.Activity;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import davejab.musicplayer.R;
import davejab.musicplayer.main.Player;
import davejab.musicplayer.models.Song;
import davejab.musicplayer.util.Time;

/**
 * Container for all views relating to the music player
 *
 * @author davejab
 */
public class PlayerView implements SeekBar.OnSeekBarChangeListener{

    private static final String TAG = "PlayerView";

    // App bar
    private TextView txtSongTitle;
    private TextView txtArtist;

    // Album art
    private ImageView imgAlbumArt;

    // Controls
    private ImageButton btnPlay;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private ImageButton btnRepeat;
    private ImageButton btnShuffle;
    private SeekBar seekBarProgress;
    private TextView txtCurrentDuration;
    private TextView txtTotalDuration;

    private Time time;
    private Player player;
    private Handler progressHandler;

    public PlayerView(Activity activity, Player player){
        Log.i(TAG, "constructor called");

        // Set views
        // TODO getters & setters?
        btnPlay = activity.findViewById(R.id.btnPlay);
        btnNext = activity.findViewById(R.id.btnNext);
        btnPrevious = activity.findViewById(R.id.btnPrevious);
        btnRepeat = activity.findViewById(R.id.btnRepeat);
        btnShuffle = activity.findViewById(R.id.btnShuffle);
        imgAlbumArt = activity.findViewById(R.id.songAlbumArt);
        seekBarProgress = activity.findViewById(R.id.songProgressBar);
        txtSongTitle = activity.findViewById(R.id.txt_song_title);
        txtArtist = activity.findViewById(R.id.txt_artist);
        txtCurrentDuration = activity.findViewById(R.id.songCurrentDurationLabel);
        txtTotalDuration = activity.findViewById(R.id.songTotalDurationLabel);

        seekBarProgress.setOnSeekBarChangeListener(this);

        setTime(new Time());
        setPlayer(player);
        setProgressHandler(new Handler());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "toggling pause");
                // Toggles pause and updates view
                if (getPlayer().togglePause()){
                    btnPlay.setImageResource(R.drawable.btn_pause);
                } else {
                    btnPlay.setImageResource(R.drawable.btn_play);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "playing next song");
                // Plays next song and updates view
                getPlayer().next();
                setSongView(getPlayer().getCurrentSong());
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "playing previous song");
                // Plays previous song and updates view
                getPlayer().previous();
                setSongView(getPlayer().getCurrentSong());
            }
        });

        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "toggling repeat");
                // Toggles repeat and updates view
                if (getPlayer().toggleRepeat()){
                    btnRepeat.setImageResource(R.drawable.img_repeat_white_24);
                } else {
                    btnRepeat.setImageResource(R.drawable.img_repeat_pressed);
                }
            }
        });

        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "toggling shuffle");
                // Toggles shuffle and updates view
                if (getPlayer().toggleShuffle()){
                    btnShuffle.setImageResource(R.drawable.img_shuffle_white_24);
                } else {
                    btnShuffle.setImageResource(R.drawable.img_shuffle_pressed);
                }
            }
        });

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
        // Do nothing
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        getProgressHandler().removeCallbacks(progressUpdater);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        getProgressHandler().removeCallbacks(progressUpdater);
        getPlayer().seek(getTime().progressToTimer(seekBar.getProgress(), (int) getPlayer().getTotalDuration()));
        updateProgressBar();
    }

    /**
     * Updates all relevant views with new song data.
     * @param song song used to update views with
     */
    public void setSongView(Song song){
        seekBarProgress.setProgress(0);
        seekBarProgress.setMax(100);
        txtSongTitle.setText(song.getTitle());
        txtArtist.setText(song.getArtist());
        btnPlay.setImageResource(R.drawable.btn_pause);
        imgAlbumArt.setImageURI(Uri.parse(song.getAlbumArt()));
        updateProgressBar();
    }

    private void updateProgressBar() {
        getProgressHandler().postDelayed(progressUpdater, 100);
    }

    private Runnable progressUpdater = new Runnable() {
        public void run() {
            long totalDuration = getPlayer().getTotalDuration();
            long currentDuration = getPlayer().getCurrentDuration();
            txtTotalDuration.setText(getTime().milliSecondsToTimer(totalDuration));
            txtCurrentDuration.setText(getTime().milliSecondsToTimer(currentDuration));
            int progress = getTime().getProgressPercentage(currentDuration, totalDuration);
            seekBarProgress.setProgress(progress);
            getProgressHandler().postDelayed(this, 100);
        }
    };

    private Handler getProgressHandler(){
        return this.progressHandler;
    }
    private Player getPlayer(){
        return this.player;
    }
    private Time getTime(){
        return this.time;
    }
    private void setPlayer(Player player){
        this.player = player;
    }
    private void setProgressHandler(Handler progressHandler){
        this.progressHandler = progressHandler;
    }
    private void setTime(Time time){
        this.time = time;
    }

}
