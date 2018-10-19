package davejab.musicplayer.models;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Albums;

public class Album extends Artist{

    // Static

    private static Uri EXTERNAL_URI = Albums.EXTERNAL_CONTENT_URI;
    private static String[] PROJECTION = {
            Albums._ID,
            Albums.ALBUM,
            Albums.ALBUM_KEY,
            Albums.ALBUM_ART,
            Albums.ARTIST
    };

    // Instance

    private String selection;
    private String order = Albums.DEFAULT_SORT_ORDER;

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
        if (item instanceof Artist) {
            Artist artist = (Artist) item;
            this.selection = Albums.ARTIST+" = '"+artist.getArtist()+"'";
        }
    }

    @Override
    public Item cursorToItem(Cursor cursor) {
        Album album = new Album();
        album.setId(cursor.getLong(cursor.getColumnIndex(getProjection()[0])));
        album.setAlbum(cursor.getString(cursor.getColumnIndex(getProjection()[1])));
        album.setAlbumKey(cursor.getString(cursor.getColumnIndex(getProjection()[2])));
        album.setAlbumArt(cursor.getString(cursor.getColumnIndex(getProjection()[3])));
        album.setArtist(cursor.getString(cursor.getColumnIndex(getProjection()[4])));
        return album;
    }

    private String album;
    private String albumArt;
    private String albumKey;

    public String getAlbum(){
        return this.album;
    }
    public String getAlbumArt(){
        return this.albumArt;
    }
    public String getAlbumKey(){
        return this.albumKey;
    }
    public void setAlbum(String album){
        this.album = album;
    }
    public void setAlbumArt(String albumArt){
        this.albumArt = albumArt;
    }
    public void setAlbumKey(String albumKey){
        this.albumKey = albumKey;
    }
}
