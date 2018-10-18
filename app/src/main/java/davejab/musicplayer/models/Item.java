package davejab.musicplayer.models;

import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;

public abstract class Item implements Queryable{

    private String id;

    protected Item(){
        //setId(id);
    }

    public String getId(){
        return this.id;
    }

    protected void setId(String id){
        this.id = id;
    }

    // interface
    public abstract Uri getExternalUri();
    public abstract String[] getProjection();
    public abstract String getSelection();
    public abstract String getOrder();

    public abstract Item cursorToItem(Cursor cursor);

}
