package davejab.musicplayer.main;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import davejab.musicplayer.models.Item;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MediaManager {

    // Static

    private static String[] PERMISSIONS = {
            READ_EXTERNAL_STORAGE
    };
    public static String[] getPermissions(){
        return PERMISSIONS;
    }

    // Instance

    private ContentResolver contentResolver;

    public MediaManager(ContentResolver contentResolver){
        setContentResolver(contentResolver);
    }

    public List<Item> getList(Item item){
        List<Item> items = new ArrayList<>();
        Cursor cursor = getCursor(
                item.getExternalUri(),
                item.getProjection(),
                item.getSelection(),
                item.getOrder()
        );
        while(cursor.moveToNext()) {
            items.add(item.cursorToItem(cursor));
        }
        return items;
    }

    private Cursor getCursor(Uri externalContent, String[] projection, String selection, String sortOrder){
        Cursor cursor = getContentResolver().query(externalContent, projection, selection, null, sortOrder);
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

    // Get & Set
    private void setContentResolver(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }
    private ContentResolver getContentResolver(){
        return this.contentResolver;
    }
}
