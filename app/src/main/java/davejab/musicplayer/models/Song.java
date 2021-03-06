package davejab.musicplayer.models;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;

/**
 * Song class for modeling songs from the android media store.
 *
 * @author davejab
 */
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
                Media.ARTIST,
                Media.DURATION,
        };
    }

    @Override
    public void setItemSelection(Item item) {
        if (item instanceof Album){
            Album album = (Album) item;
            mergeAlbum(album);
            // Filter songs by album
            setSelection(Media.ALBUM_KEY+" = '"+album.getAlbumKey()+"'");
            // Sort by track #
            setOrder(Media.TRACK+" ASC");
        } else if (item instanceof Artist) {
            Artist artist = (Artist) item;
            mergeArtist(artist);
            // Filter songs by artist
            setSelection(Media.ARTIST+" = '"+artist.getArtist()+"'");
        }
    }

    @Override
    public Song cursorToItem(Cursor cursor) {
        Song song = new Song(getContentResolver());
        song.mergeAlbum(this);
        song.setId(cursor.getLong(cursor.getColumnIndex(getProjection()[0])));
        song.setData(cursor.getString(cursor.getColumnIndex(getProjection()[1])));
        song.setTitle(cursor.getString(cursor.getColumnIndex(getProjection()[2])));
        song.setArtist(cursor.getString(cursor.getColumnIndex(getProjection()[3])));
        song.setDuration(cursor.getLong(cursor.getColumnIndex(getProjection()[4])));
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
    private void setDuration(long duration){
        this.duration = duration;
    }

}
