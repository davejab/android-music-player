package davejab.musicplayer.models;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;

public class Song extends Album{

    private String data;
    private String title;
    private long duration;

    public Song(ContentResolver contentResolver) {
        super(contentResolver);
        setOrder(Media.DEFAULT_SORT_ORDER);
    }

    @Override
    public Uri getExternalUri() {
        return Media.EXTERNAL_CONTENT_URI;
    }

    @Override
    public String[] getProjection() {
        return new String[] {
                Media._ID,
                Media.DATA,
                Media.TITLE,
                Media.DURATION,
                Media.ALBUM,
                Media.ARTIST,
        };
    }

    @Override
    public void setItemSelection(Item item) {
        if (item instanceof Album){
            Album album = (Album) item;
            setSelection(Media.ALBUM+" = '"+album.getAlbum()+"'");
            setOrder(Media.TRACK+" ASC");
            // TODO ditch this
            setAlbumArt(album.getAlbumArt());
        } else if (item instanceof Artist) {
            Artist artist = (Artist) item;
            setSelection(Media.ARTIST+" = '"+artist.getArtist()+"'");
        }
    }

    @Override
    public Song cursorToItem(Cursor cursor) {
        Song song = new Song(getContentResolver());
        song.setId(cursor.getLong(cursor.getColumnIndex(getProjection()[0])));
        song.setData(cursor.getString(cursor.getColumnIndex(getProjection()[1])));
        song.setTitle(cursor.getString(cursor.getColumnIndex(getProjection()[2])));
        song.setDuration(cursor.getLong(cursor.getColumnIndex(getProjection()[3])));
        song.setAlbum(cursor.getString(cursor.getColumnIndex(getProjection()[4])));
        song.setArtist(cursor.getString(cursor.getColumnIndex(getProjection()[5])));
        // TODO fix this
        song.setAlbumArt(getAlbumArt());
        return song;
    }

    public String getData(){
        return this.data;
    }
    public String getTitle(){
        return this.title;
    }
    public long getDuration(){
        return this.duration;
    }
    public void setData(String data){
        this.data = data;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDuration(long duration){
        this.duration = duration;
    }
}
