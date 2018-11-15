package davejab.musicplayer.activities.views;

import android.app.Activity;
import android.content.Context;
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

    private Context context;

    // App bar
    private TextView txtSongTitle;
    private TextView txtArtist;

    // Album art
    private ImageView imgAlbumArt;

    // Controls
    private ImageButton btnPlay;
    private ImageButton btnRepeat;
    private ImageButton btnShuffle;
    private SeekBar seekBarProgress;
    private TextView txtCurrentDuration;
    private TextView txtTotalDuration;

    private Player player;
    private Handler progressHandler;

    public PlayerView(Activity activity, Player player){
        Log.i(TAG, "constructor called");

        ImageButton btnNext;
        ImageButton btnPrevious;

        // Set views
        // TODO getters & setters?
        this.btnPlay = activity.findViewById(R.id.btnPlay);
        btnNext = activity.findViewById(R.id.btnNext);
        btnPrevious = activity.findViewById(R.id.btnPrevious);
        this.btnRepeat = activity.findViewById(R.id.btnRepeat);
        this.btnShuffle = activity.findViewById(R.id.btnShuffle);
        this.imgAlbumArt = activity.findViewById(R.id.songAlbumArt);
        this.seekBarProgress = activity.findViewById(R.id.songProgressBar);
        this.txtSongTitle = activity.findViewById(R.id.txt_song_title);
        this.txtArtist = activity.findViewById(R.id.txt_artist);
        this.txtCurrentDuration = activity.findViewById(R.id.songCurrentDurationLabel);
        this.txtTotalDuration = activity.findViewById(R.id.songTotalDurationLabel);

        this.seekBarProgress.setOnSeekBarChangeListener(this);

        this.context = activity.getApplicationContext();
        this.player = player;
        this.progressHandler = new Handler();

        this.btnPlay.setOnClickListener(new View.OnClickListener() {
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

        this.btnRepeat.setOnClickListener(new View.OnClickListener() {
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

        this.btnShuffle.setOnClickListener(new View.OnClickListener() {
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
        removeCallbacks();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        removeCallbacks();
        getPlayer().seek(Time.progressToTimer(seekBar.getProgress(), (int) getPlayer().getTotalDuration()));
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
        if (song.getAlbumArt()!= null) {
            imgAlbumArt.setImageURI(Uri.parse(song.getAlbumArt()));
        } else {
            imgAlbumArt.setImageDrawable(this.context.getDrawable(R.drawable.img_nocover));
        }
        updateProgressBar();
    }

    private void removeCallbacks(){
        this.progressHandler.removeCallbacks(this.progressUpdater);
    }

    private void updateProgressBar() {
        this.progressHandler.postDelayed(this.progressUpdater, 100);
    }

    private Runnable progressUpdater = new Runnable() {
        public void run() {
            long totalDuration = getPlayer().getTotalDuration();
            long currentDuration = getPlayer().getCurrentDuration();
            txtTotalDuration.setText(Time.milliSecondsToTimer(totalDuration));
            txtCurrentDuration.setText(Time.milliSecondsToTimer(currentDuration));
            int progress = Time.getProgressPercentage(currentDuration, totalDuration);
            seekBarProgress.setProgress(progress);
            updateProgressBar();
        }
    };

    private Player getPlayer(){
        return this.player;
    }

}
