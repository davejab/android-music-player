package davejab.musicplayer.models;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Albums;

public class Album extends Item{

    // Static

    private static Uri EXTERNAL_URI = Albums.EXTERNAL_CONTENT_URI;
    private static String[] PROJECTION = {
            Albums._ID,
            Albums.ALBUM,
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
            this.selection = Albums.ARTIST + " = " + item.getId();
        }
    }

    @Override
    public Item cursorToItem(Cursor cursor) {
        Album album = new Album();
        album.setId(cursor.getLong(cursor.getColumnIndex(getProjection()[0])));
        album.setAlbum(cursor.getString(cursor.getColumnIndex(getProjection()[1])));
        return album;
    }

    private String album;

    public String getAlbum(){
        return this.album;
    }
    public void setAlbum(String album){
        this.album = album;
    }

}
