package davejab.musicplayer.activities.views;

import android.app.Activity;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import davejab.musicplayer.R;
import davejab.musicplayer.main.Player;
import davejab.musicplayer.models.Song;
import davejab.musicplayer.util.Time;

public class PlayerView implements SeekBar.OnSeekBarChangeListener{

    private ImageButton btnPlay;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private ImageButton btnRepeat;
    private ImageButton btnShuffle;
    private ImageView imgAlbumArt;
    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songArtistLabel;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    private Handler progressHandler;
    private Time time;

    private Player player;

    public PlayerView(Activity activity, Player player){

        btnPlay = activity.findViewById(R.id.btnPlay);
        btnNext = activity.findViewById(R.id.btnNext);
        btnPrevious = activity.findViewById(R.id.btnPrevious);
        btnRepeat = activity.findViewById(R.id.btnRepeat);
        btnShuffle = activity.findViewById(R.id.btnShuffle);
        imgAlbumArt = activity.findViewById(R.id.songAlbumArt);
        songProgressBar = activity.findViewById(R.id.songProgressBar);
        songTitleLabel = activity.findViewById(R.id.txt_song_title);
        songArtistLabel = activity.findViewById(R.id.txt_artist);
        songCurrentDurationLabel = activity.findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = activity.findViewById(R.id.songTotalDurationLabel);

        songProgressBar.setOnSeekBarChangeListener(this);

        setTime(new Time());
        setProgressHandler(new Handler());

        setPlayer(player);
        //Intent intent = getIntent();
        //int position = intent.getIntExtra("songIndex", 0);

        //getPlayer().setPlaylist(Library.getLibrary(getContentResolver()).getCurrentList());
        //getPlayer().setCurrentSongIndex(position);
        //playSong(getPlayer().getCurrentSong());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
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
                getPlayer().next();
                setSongView(getPlayer().getCurrentSong());
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                getPlayer().previous();
                setSongView(getPlayer().getCurrentSong());
            }
        });

        btnRepeat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
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
                if (getPlayer().toggleShuffle()){
                    btnShuffle.setImageResource(R.drawable.img_shuffle_white_24);
                } else {
                    btnShuffle.setImageResource(R.drawable.img_shuffle_pressed);
                }
            }
        });

    }

    public void updateProgressBar() {
        getProgressHandler().postDelayed(progressUpdater, 100);
    }

    private Runnable progressUpdater = new Runnable() {
        public void run() {
            long totalDuration = getPlayer().getCurrentSong().getDuration();
            long currentDuration = getPlayer().getCurrentPosition();
            songTotalDurationLabel.setText(getTime().milliSecondsToTimer(totalDuration));
            songCurrentDurationLabel.setText(getTime().milliSecondsToTimer(currentDuration));
            int progress = getTime().getProgressPercentage(currentDuration, totalDuration);
            songProgressBar.setProgress(progress);
            getProgressHandler().postDelayed(this, 100);
        }
    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        getProgressHandler().removeCallbacks(progressUpdater);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        getProgressHandler().removeCallbacks(progressUpdater);
        int totalDuration = (int) getPlayer().getCurrentSong().getDuration();
        int currentPosition = time.progressToTimer(seekBar.getProgress(), totalDuration);
        getPlayer().seek(currentPosition);
        updateProgressBar();
    }

    public void setSongView(Song song){
        songProgressBar.setProgress(0);
        songProgressBar.setMax(100);
        songTitleLabel.setText(song.getTitle());
        songArtistLabel.setText(song.getArtist());
        btnPlay.setImageResource(R.drawable.btn_pause);
        imgAlbumArt.setImageURI(Uri.parse(song.getAlbumArt()));
        updateProgressBar();
    }

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
