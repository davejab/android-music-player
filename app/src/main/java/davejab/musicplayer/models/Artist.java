package davejab.musicplayer.models;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Artists;

public class Artist extends Item{

    private String artist;

    public Artist(ContentResolver contentResolver) {
        super(contentResolver);
        setOrder(Artists.DEFAULT_SORT_ORDER);
    }

    @Override
    public Uri getExternalUri() {
        return Artists.EXTERNAL_CONTENT_URI;
    }

    @Override
    public String[] getProjection() {
        return new String[] {
                Artists._ID,
                Artists.ARTIST,
        };
    }

    @Override
    public void setItemSelection(Item item) {
        mergeItem(item);
    }

    @Override
    protected Artist cursorToItem(Cursor cursor) {
        Artist artist = new Artist(getContentResolver());
        artist.setId(cursor.getLong(cursor.getColumnIndex(getProjection()[0])));
        artist.setArtist(cursor.getString(cursor.getColumnIndex(getProjection()[1])));
        return artist;
    }

    protected void mergeArtist(Artist artist) {
        mergeItem(artist);
        setArtist(artist.getArtist());
    }

    public String getArtist(){
        return this.artist;
    }
    public void setArtist(String artist){
        this.artist = artist;
    }

}
