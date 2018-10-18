package davejab.musicplayer.models;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Artists;

public class Artist extends Item{

    // Static

    private static Uri EXTERNAL_URI = Artists.EXTERNAL_CONTENT_URI;
    private static String[] PROJECTION = {
            Artists.ARTIST,
    };

    // Instance

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
        return null;
    }

    @Override
    public String getOrder() {
        return null;
    }

    @Override
    public Item cursorToItem(Cursor cursor) {
        Artist artist = new Artist();
        artist.setArtist(cursor.getString(cursor.getColumnIndex(getProjection()[0])));
        return artist;
    }

    private String artist;

    public String getArtist(){
        return this.artist;
    }
    public void setArtist(String artist){
        this.artist = artist;
    }


}
