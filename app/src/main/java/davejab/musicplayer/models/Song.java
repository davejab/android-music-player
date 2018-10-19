package davejab.musicplayer.models;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

public class Song extends Album{

    // Static

    private static Uri EXTERNAL_URI = Media.EXTERNAL_CONTENT_URI;
    private static String[] PROJECTION = {
            Media._ID,
            Media.DATA,
            Media.TITLE,
            Media.DURATION,
            Media.ALBUM,
            Media.ARTIST
    };

    // Instance

    private String selection;
    private String order = Media.DEFAULT_SORT_ORDER;

    @Override
    public Uri getExternalUri() {
        return EXTERNAL_URI;
    }

    @Override
    public String[] getProjection() {
        return PROJECTION;
    }

    @Override
    public String getSelection() {
        return this.selection;
    }

    @Override
    public String getOrder() {
        return this.order;
    }

    @Override
    public void setSelection(Item item) {
        if (item instanceof Album){
            Album album = (Album) item;
            this.selection = Media.ALBUM+" = '"+album.getAlbum()+"'";
            this.order = Media.TRACK+" ASC";
        } else if (item instanceof Artist) {
            Artist artist = (Artist) item;
            this.selection = Media.ARTIST+" = '"+artist.getArtist()+"'";
        }
    }

    @Override
    public Item cursorToItem(Cursor cursor) {
        Song song = new Song();
        song.setId(cursor.getLong(cursor.getColumnIndex(getProjection()[0])));
        song.setData(cursor.getString(cursor.getColumnIndex(getProjection()[1])));
        song.setTitle(cursor.getString(cursor.getColumnIndex(getProjection()[2])));
        song.setDuration(cursor.getString(cursor.getColumnIndex(getProjection()[3])));
        song.setAlbum(cursor.getString(cursor.getColumnIndex(getProjection()[4])));
        song.setArtist(cursor.getString(cursor.getColumnIndex(getProjection()[5])));
        return song;
    }

    private String data;
    private String title;
    private String duration;

    public String getData(){
        return this.data;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDuration(){
        return this.duration;
    }
    public void setData(String data){
        this.data = data;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }
}
