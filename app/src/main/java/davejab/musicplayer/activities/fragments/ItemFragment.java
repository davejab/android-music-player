package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import davejab.musicplayer.R;
import davejab.musicplayer.models.Item;

public abstract class ItemFragment extends Fragment {

    private Item item;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.recyclerView = view.findViewById(R.id.recyclerView);
    }

    public Item getItem(){
        return this.item;
    }

    public RecyclerView getRecyclerView(){
        return this.recyclerView;
    }

    public void setItem(Item item){
        this.item = item;
    }

}
