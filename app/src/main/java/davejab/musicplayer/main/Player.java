package davejab.musicplayer.main;

import android.app.Activity;
import android.media.MediaPlayer;

import java.io.IOException;
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

    private boolean shuffle = false;
    private boolean repeat = false;

    public Player(Activity activity){
        setMediaPlayer(new MediaPlayer());
        getMediaPlayer().setOnCompletionListener(this);
        setShuffle(false);
        setRepeat(false);
        setPlayerView(new PlayerView(activity, this));
    }

    public Song getCurrentSong(){
        Song currentSong = (Song) getPlaylist().get(getCurrentSongIndex());
        return currentSong;
    }

    public long getCurrentPosition(){
        return getMediaPlayer().getCurrentPosition();
    }

    private int getSongCount(){
        return getPlaylist().size();
    }

    private void getNextSong(){
        if(getRepeat()){
            return;
        } else if(getShuffle()){
            Random rand = new Random();
            setCurrentSongIndex(rand.nextInt((getSongCount() - 1) + 1));
        } else{
            if(getCurrentSongIndex() < (getSongCount() - 1)){
                setCurrentSongIndex(getCurrentSongIndex() + 1);
            }else{
                setCurrentSongIndex(0);
            }
        }
    }

    public void playSong(Song song){
        try {
            getMediaPlayer().reset();
            getMediaPlayer().setDataSource(song.getData());
            getMediaPlayer().prepare();
            getMediaPlayer().start();
            getPlayerView().setSongView(song);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void next(){
        getNextSong();
        playSong(getCurrentSong());
    }

    public void previous(){
        if(getCurrentSongIndex() > 0){
            setCurrentSongIndex(getCurrentSongIndex() - 1);
        } else {
            setCurrentSongIndex(getSongCount() - 1);
        }
        playSong(getCurrentSong());
    }

    public boolean toggleShuffle(){
        if (getShuffle()) {
            setShuffle(false);
        } else {
            setShuffle(true);
        }
        return getShuffle();
    }

    public boolean toggleRepeat(){
        if (getRepeat()) {
            setRepeat(false);
        } else {
            setRepeat(true);
        }
        return getRepeat();
    }

    public boolean togglePause(){
        if(getMediaPlayer().isPlaying()){
            if(getMediaPlayer() != null){
                getMediaPlayer().pause();
            }
        }else{
            if(getMediaPlayer() != null){
                getMediaPlayer().start();
            }
        }
        return getMediaPlayer().isPlaying();
    }

    public void seek(int position){
        getMediaPlayer().seekTo(position);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        getNextSong();
        playSong(getCurrentSong());
    }

    private MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }
    private PlayerView getPlayerView(){
        return this.playerView;
    }
    private List<Item> getPlaylist(){
        return this.playlist;
    }
    private int getCurrentSongIndex(){
        return this.currentSongIndex;
    }
    private boolean getShuffle(){
        return this.shuffle;
    }
    private boolean getRepeat(){
        return this.repeat;
    }
    private void setMediaPlayer(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }
    private void setPlayerView(PlayerView playerView){
        this.playerView = playerView;
    }
    public void setPlaylist(List<Item> playlist){
        this.playlist = playlist;
    }
    public void setCurrentSongIndex(int currentSongIndex){
        this.currentSongIndex = currentSongIndex;
    }
    private void setShuffle(boolean shuffle){
        this.shuffle = shuffle;
    }
    private void setRepeat(boolean repeat){
        this.repeat = repeat;
    }


}
