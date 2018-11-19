package davejab.musicplayer.main;

import android.app.Activity;
import android.media.MediaPlayer;

import java.util.List;
import java.util.Random;

import davejab.musicplayer.activities.views.PlayerView;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;

public class Player implements MediaPlayer.OnCompletionListener {

    private PlayerView playerView;

    private MediaPlayer mediaPlayer;

    private List<Item> playlist;
    private int currentSongIndex;

    private boolean shuffle;
    private boolean repeat;

    public Player(Activity activity){
        this.mediaPlayer = new MediaPlayer();
        this.mediaPlayer.setOnCompletionListener(this);
        this.playerView = new PlayerView(activity, this);
        this.shuffle = this.repeat = false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        next();
    }

    public void playList(List<Item> playlist, int position){
        this.playlist = playlist;
        playSong(position);
        getView().expand();
    }

    private void playSong(int songIndex){
        this.currentSongIndex = songIndex;
        playCurrentSong();
    }

    private void playCurrentSong(){
        try {
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(getCurrentSong().getData());
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
            this.playerView.setSongView(getCurrentSong());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next(){
        if(this.repeat){
            playCurrentSong();
        } else if(this.shuffle){
            playSong(new Random().nextInt((this.playlist.size() - 1) + 1));
        } else{
            if(this.currentSongIndex < (this.playlist.size() - 1)){
                playSong(this.currentSongIndex + 1);
            }else{
                playSong(0);
            }
        }
    }

    public void previous(){
        if(this.currentSongIndex > 0){
            playSong(this.currentSongIndex - 1);
        } else {
            playSong(this.playlist.size() - 1);
        }
    }

    public Song getCurrentSong(){
        return (Song) this.playlist.get(this.currentSongIndex);
    }

    public long getCurrentDuration(){
        return this.mediaPlayer.getCurrentPosition();
    }

    public long getTotalDuration(){
        return getCurrentSong().getDuration();
    }

    public boolean toggleShuffle(){
        if (this.shuffle) {
            this.shuffle = false;
        } else {
            this.shuffle = true;
        }
        return this.shuffle;
    }

    public boolean toggleRepeat(){
        if (this.repeat) {
            this.repeat = false;
        } else {
            this.repeat = true;
        }
        return this.repeat;
    }

    public boolean togglePause(){
        if(this.mediaPlayer == null) {
            return false;
        }
        if(this.mediaPlayer.isPlaying()){
            this.mediaPlayer.pause();
        }else{
            this.mediaPlayer.start();
        }
        return this.mediaPlayer.isPlaying();
    }

    public void seek(int position){
        this.mediaPlayer.seekTo(position);
    }

    public PlayerView getView() {
        return playerView;
    }
}
