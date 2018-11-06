package davejab.musicplayer.activities.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import davejab.musicplayer.models.Item;

public abstract class ItemFragment extends Fragment {

    private List<Item> items;
    private RecyclerView recyclerView;

    public List<Item> getItems(){
        return this.items;
    }
    public RecyclerView getRecyclerView(){
        return this.recyclerView;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }
    public void setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

}
