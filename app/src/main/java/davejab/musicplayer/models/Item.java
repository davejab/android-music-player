package davejab.musicplayer.models;

import android.database.Cursor;
import android.net.Uri;

public abstract class Item implements Queryable{

    private long id;

    public long getId(){
        return this.id;
    }

    protected void setId(long id){
        this.id = id;
    }

    // interface
    public abstract Uri getExternalUri();
    public abstract String[] getProjection();
    public abstract String getSelection();
    public abstract String getOrder();

    public abstract Item cursorToItem(Cursor cursor);

}
