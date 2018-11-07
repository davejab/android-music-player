package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import davejab.musicplayer.adapters.SongAdapter;
import davejab.musicplayer.models.Item;

public class SongFragment extends ItemFragment {

    public static Fragment getSongFragment(List<Item> items){
        SongFragment songFragment = new SongFragment();
        songFragment.setItems(items);
        return songFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SongAdapter adapter = new SongAdapter(getContext(), getItems());
        getRecyclerView().setAdapter(adapter);
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        //getAppBarText().setText(""); // TODO
    }

}
