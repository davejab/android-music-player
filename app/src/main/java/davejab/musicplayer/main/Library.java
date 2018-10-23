package davejab.musicplayer.main;

import android.content.ContentResolver;

import java.util.List;
import java.util.Stack;

import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;

public class Library {

    private static Library LIBRARY = null;

    private Item currentItem;
    private List<Item> currentList;
    private Stack<List<Item>> history;

    private Library(ContentResolver contentResolver){
        setCurrentItem(new Artist(contentResolver));
        setCurrentList(getCurrentItem().toList());
        setHistory(new Stack<List<Item>>());
    }

    public static Library getLibrary(ContentResolver contentResolver){
        if (LIBRARY == null){
            LIBRARY = new Library(contentResolver);
        }
        return LIBRARY;
    }

    public List<Item> getNextList(int index){
        Item item = getCurrentList().get(index);
        Item newItem;
        if (item instanceof Album){
            newItem = new Song(getCurrentItem().getContentResolver());
        }else if (item instanceof Artist){
            newItem = new Album(getCurrentItem().getContentResolver());
        }else{
            return getCurrentList();
        }
        setCurrentItem(newItem);
        getCurrentItem().setItemSelection(item);
        getHistory().push(getCurrentList());
        setCurrentList(getCurrentItem().toList());
        return getCurrentList();
    }

    public List<Item> getLastList() {
        if (!getHistory().empty()){
            List<Item> list = getHistory().pop();
            setCurrentList(list);
        }
        return getCurrentList();
    }

    public Item getCurrentItem(){
        return this.currentItem;
    }
    public List<Item> getCurrentList(){
        return this.currentList;
    }
    private Stack<List<Item>> getHistory(){
        return this.history;
    }
    private void setCurrentItem(Item currentItem){
        this.currentItem = currentItem;
    }
    private void setCurrentList(List<Item> currentList){
        this.currentList = currentList;
    }
    private void setHistory(Stack<List<Item>> history){
        this.history = history;
    }

}
