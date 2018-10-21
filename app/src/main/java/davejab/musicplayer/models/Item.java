package davejab.musicplayer.models;

import android.content.ContentResolver;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import davejab.musicplayer.models.util.MediaManager;

public abstract class Item implements MediaManager.Query {

    private long id;
    private MediaManager mediaManager;
    private String selection = null;
    private String order = null;

    protected Item(ContentResolver contentResolver){
        setMediaManager(new MediaManager(contentResolver));
    }

    public abstract void setItemSelection(Item item);
    protected abstract Item cursorToItem(Cursor cursor);

    public ContentResolver getContentResolver(){
        return getMediaManager().getContentResolver();
    }

    private List<Item> cursorToList(Cursor cursor){
        List<Item> items = new ArrayList<Item>();
        while(cursor.moveToNext()) {
            items.add(cursorToItem(cursor));
        }
        return items;
    }

    public List<Item> toList(){
        Cursor cursor = getMediaManager().query(this);
        return cursorToList(cursor);
    }

    public long getId(){
        return this.id;
    }
    private MediaManager getMediaManager(){
        return this.mediaManager;
    }
    public String getSelection(){
        return this.selection;
    }
    public String getOrder(){
        return this.order;
    }
    protected void setId(long id){
        this.id = id;
    }
    private void setMediaManager(MediaManager mediaManager){
        this.mediaManager = mediaManager;
    }
    protected void setSelection(String selection){
        this.selection = selection;
    }
    protected void setOrder(String order){
        this.order = order;
    }

}
