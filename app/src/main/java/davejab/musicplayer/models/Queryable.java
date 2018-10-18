package davejab.musicplayer.models;

import android.database.Cursor;
import android.net.Uri;

public interface Queryable {

    Uri getExternalUri();
    String[] getProjection();
    String getSelection();
    String getOrder();

    Item cursorToItem(Cursor cursor);
}
