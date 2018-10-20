package davejab.musicplayer.models;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Artists;
import android.widget.Adapter;
import android.widget.ListAdapter;

import java.util.List;

import davejab.musicplayer.views.ArtistAdapter;

public class Artist extends Item{

    // Static

    private static Uri EXTERNAL_URI = Artists.EXTERNAL_CONTENT_URI;
    private static String[] PROJECTION = {
            Artists._ID,
            Artists.ARTIST,
    };

    // Instance

    private String selection;
    private String order = Artists.DEFAULT_SORT_ORDER;

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

    }

    @Override
    public Item cursorToItem(Cursor cursor) {
        Artist artist = new Artist();
        artist.setId(cursor.getLong(cursor.getColumnIndex(getProjection()[0])));
        artist.setArtist(cursor.getString(cursor.getColumnIndex(getProjection()[1])));
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
