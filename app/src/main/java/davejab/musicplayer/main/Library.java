package davejab.musicplayer.main;

import android.content.ContentResolver;

import java.util.List;
import java.util.Stack;

import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;

public class Library {

    // Static

    private static Library LIBRARY = null;
    public static Library getLibrary(ContentResolver contentResolver){
        if (LIBRARY == null){
            LIBRARY = new Library(contentResolver);
        }
        return LIBRARY;
    }

    // Instance

    private Item currentItem;
    private List<Item> itemList;
    private Stack<List<Item>> history;
    private MediaManager mediaManager;

    private Library(ContentResolver contentResolver){
        setMediaManager(new MediaManager(contentResolver));
        setCurrentItem(new Artist());
        setItemList(getMediaManager().getList(getCurrentItem()));
        setHistory(new Stack<List<Item>>());
    }

    public List<Item> getNextList(Item item){
        Item newItem;
        if (item instanceof Album){
            newItem = new Song();
        }else if (item instanceof Artist){
            newItem = new Album();
        }else{
            return getItemList();
        }
        newItem.setSelection(item);
        setCurrentItem(newItem);
        getHistory().push(getItemList());
        setItemList(getMediaManager().getList(newItem));
        return getItemList();
    }

    public List<Item> getLastList() {
        List<Item> list = getHistory().pop();
        setItemList(list);
        return getItemList();
    }

    public Item getCurrentItem(){
        return this.currentItem;
    }
    public List<Item> getItemList(){
        return this.itemList;
    }
    private Stack<List<Item>> getHistory(){
        return this.history;
    }
    private MediaManager getMediaManager(){
        return this.mediaManager;
    }
    private void setCurrentItem(Item currentItem){
        this.currentItem = currentItem;
    }
    private void setItemList(List<Item> itemList){
        this.itemList = itemList;
    }
    private void setHistory(Stack<List<Item>> history){
        this.history = history;
    }
    private void setMediaManager(MediaManager mediaManager){
        this.mediaManager = mediaManager;
    }

}
