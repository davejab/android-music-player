package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.models.Item;

public abstract class ItemFragment extends Fragment {

    private List<Item> items;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView((RecyclerView) view.findViewById(R.id.recyclerView));
    }

    public List<Item> getItems(){
        return this.items;
    }
    public RecyclerView getRecyclerView(){
        return this.recyclerView;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }
    private void setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

}
