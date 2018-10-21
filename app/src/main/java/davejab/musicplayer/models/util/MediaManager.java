package davejab.musicplayer.models.util;

import android.net.Uri;
import android.database.Cursor;
import android.content.ContentResolver;

public class MediaManager {

    private ContentResolver contentResolver;

    public MediaManager(ContentResolver contentResolver){
        setContentResolver(contentResolver);
    }

    public Cursor query(Query query){
        Cursor cursor = getContentResolver().query(
                query.getExternalUri(),
                query.getProjection(),
                query.getSelection(),
                null,
                query.getOrder());
        if (cursor == null){
            // TODO
            return null;
        }
        if (cursor.getCount() <= 0){
            // TODO
            return null;
        }
        return cursor;
    }

    public ContentResolver getContentResolver(){
        return this.contentResolver;
    }
    private void setContentResolver(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    public interface Query{
        Uri getExternalUri();
        String[] getProjection();
        String getSelection();
        String getOrder();
    }

}
