package davejab.musicplayer.models;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Albums;

public class Album extends Artist{

    private String album;
    private String albumArt;
    private String albumKey;

    public Album(ContentResolver contentResolver) {
        super(contentResolver);
        setOrder(Albums.DEFAULT_SORT_ORDER);
    }

    @Override
    public Uri getExternalUri() {
        return Albums.EXTERNAL_CONTENT_URI;
    }

    @Override
    public String[] getProjection() {
        return new String[] {
                Albums._ID,
                Albums.ALBUM,
                Albums.ALBUM_KEY,
                Albums.ALBUM_ART,
                Albums.ARTIST
        };
    }

    @Override
    public void setItemSelection(Item item) {
        if (item instanceof Artist) {
            Artist artist = (Artist) item;
            setSelection(Albums.ARTIST+" = '"+artist.getArtist()+"'");
        }
    }

    @Override
    public Album cursorToItem(Cursor cursor) {
        Album album = new Album(getContentResolver());
        album.setId(cursor.getLong(cursor.getColumnIndex(getProjection()[0])));
        album.setAlbum(cursor.getString(cursor.getColumnIndex(getProjection()[1])));
        album.setAlbumKey(cursor.getString(cursor.getColumnIndex(getProjection()[2])));
        album.setAlbumArt(cursor.getString(cursor.getColumnIndex(getProjection()[3])));
        album.setArtist(cursor.getString(cursor.getColumnIndex(getProjection()[4])));
        return album;
    }

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
