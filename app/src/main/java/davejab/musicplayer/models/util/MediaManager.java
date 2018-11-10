package davejab.musicplayer.models.util;

import android.net.Uri;
import android.database.Cursor;
import android.content.ContentResolver;
import android.util.Log;

/**
 * Query manager for the android content resolver. Primarily used for querying the MediaStore.
 * Currently only allows you to retrieve information.
 *
 * TODO:
 * -    add functionality for editing items
 * -    add functionality for deleting items
 *
 * @author davejab
 */
public class MediaManager {

    private static final String TAG = "MediaManager";

    private ContentResolver contentResolver;

    public MediaManager(ContentResolver contentResolver){
        setContentResolver(contentResolver);
    }

    /**
     * Queries the content resolver and returns its cursor.
     * @param query the query that will be run into the content resolver
     * @return the resulting cursor from the query
     * @see Cursor
     */
    public Cursor query(Query query){
        Cursor cursor = getContentResolver().query(
                query.getExternalUri(),
                query.getProjection(),
                query.getSelection(),
                null,
                query.getOrder());
        if (cursor == null){
            // TODO
            Log.w(TAG, "cursor is null");
            return null;
        }
        if (cursor.getCount() <= 0){
            // TODO
            Log.w(TAG, "query returned empty cursor");
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
