package davejab.musicplayer.activities.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import davejab.musicplayer.R;
import davejab.musicplayer.main.Player;
import davejab.musicplayer.models.Song;
import davejab.musicplayer.util.Time;

/**
 * Container for all views relating to the music player
 *
 * @author davejab
 */
public class PlayerView implements SeekBar.OnSeekBarChangeListener, SlidingUpPanelLayout.PanelSlideListener {

    private static final String TAG = "PlayerView";

    private Context context;

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

    private ConstraintLayout toolbar;
    private View mPlayerCollapsed;
    private View mPlayerExpanded;

    private SlidingUpPanelLayout slidingPanelPlayer;

    public PlayerView(Activity activity, Player player){
        Log.i(TAG, "constructor called");

        // Set views
        ImageButton btnNext = activity.findViewById(R.id.btnNext);
        ImageButton btnPrevious = activity.findViewById(R.id.btnPrevious);
        this.btnPlay = activity.findViewById(R.id.btnPlay);
        this.btnRepeat = activity.findViewById(R.id.btnRepeat);
        this.btnShuffle = activity.findViewById(R.id.btnShuffle);
        this.imgAlbumArt = activity.findViewById(R.id.songAlbumArt);
        this.seekBarProgress = activity.findViewById(R.id.songProgressBar);
        this.txtCurrentDuration = activity.findViewById(R.id.songCurrentDurationLabel);
        this.txtTotalDuration = activity.findViewById(R.id.songTotalDurationLabel);

        // TODO clean this up
        this.slidingPanelPlayer = activity.findViewById(R.id.sliding_layout);
        this.toolbar = activity.findViewById(R.id.toolbar);
        this.mPlayerCollapsed = LayoutInflater.from(activity).inflate(R.layout.toolbar_player_collapsed, null);
        this.mPlayerExpanded = LayoutInflater.from(activity).inflate(R.layout.toolbar_player_expanded, null);
        this.mPlayerCollapsed.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
        this.mPlayerExpanded.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
        this.slidingPanelPlayer.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        this.slidingPanelPlayer.addPanelSlideListener(this);

        this.seekBarProgress.setOnSeekBarChangeListener(this);

        this.context = activity.getApplicationContext();
        this.player = player;
        this.progressHandler = new Handler();

        this.mPlayerCollapsed.findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPlayer().togglePause()){
                    btnPlay.setImageResource(R.drawable.btn_pause);
                    ((ImageButton) mPlayerCollapsed.findViewById(R.id.btnPlay)).setImageResource(R.drawable.btn_pause);
                } else {
                    btnPlay.setImageResource(R.drawable.btn_play);
                    ((ImageButton) mPlayerCollapsed.findViewById(R.id.btnPlay)).setImageResource(R.drawable.btn_play);
                }
            }
        });

        this.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "toggling pause");
                // Toggles pause and updates view
                if (getPlayer().togglePause()){
                    btnPlay.setImageResource(R.drawable.btn_pause);
                    ((ImageButton) mPlayerCollapsed.findViewById(R.id.btnPlay)).setImageResource(R.drawable.btn_pause);
                } else {
                    btnPlay.setImageResource(R.drawable.btn_play);
                    ((ImageButton) mPlayerCollapsed.findViewById(R.id.btnPlay)).setImageResource(R.drawable.btn_play);
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
        btnPlay.setImageResource(R.drawable.btn_pause);
        Drawable cover = this.context.getDrawable(R.drawable.img_nocover);
        if (song.getAlbumArt()!= null) {
            cover = Drawable.createFromPath(song.getAlbumArt());
        }
        imgAlbumArt.setImageDrawable(cover);
        ((ImageView) mPlayerCollapsed.findViewById(R.id.songAlbumArt)).setImageDrawable(cover);
        for (View v : new View[]{mPlayerExpanded, mPlayerCollapsed}){
            ((TextView) v.findViewById(R.id.txt_song_title)).setText(getPlayer().getCurrentSong().getTitle());
            ((TextView) v.findViewById(R.id.txt_artist)).setText(getPlayer().getCurrentSong().getArtist());
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
            int progress = Time.getProgressPercentage(currentDuration, totalDuration);
            txtTotalDuration.setText(Time.milliSecondsToTimer(totalDuration));
            txtCurrentDuration.setText(Time.milliSecondsToTimer(currentDuration));
            seekBarProgress.setProgress(progress);
            updateProgressBar();
        }
    };

    private Player getPlayer(){
        return this.player;
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        // Do nothing
        // TODO animate?
    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
        if (newState == SlidingUpPanelLayout.PanelState.EXPANDED){
            toolbar.removeAllViews();
            toolbar.addView(mPlayerExpanded);
        } else if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED){
            toolbar.removeAllViews();
            toolbar.addView(mPlayerCollapsed);
        }
    }

    public boolean isExpanded() {
        return this.slidingPanelPlayer.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED;
    }

    public void expand() {
        this.slidingPanelPlayer.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }

    public void collapse(){
        this.slidingPanelPlayer.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }
}
