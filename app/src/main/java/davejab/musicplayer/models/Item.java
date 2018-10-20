package davejab.musicplayer.models;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Adapter;
import android.widget.ListAdapter;

import java.util.List;

public abstract class Item {

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

    public abstract void setSelection(Item item);

    public abstract Item cursorToItem(Cursor cursor);

}
