package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import java.util.List;

import davejab.musicplayer.adapters.AlbumAdapter;
import davejab.musicplayer.models.Item;

public class AlbumFragment extends ItemFragment {

    private static final int NUM_OF_COLUMNS = 2;

    public static Fragment getAlbumFragment(List<Item> items){
        AlbumFragment albumFragment = new AlbumFragment();
        albumFragment.setItems(items);
        return albumFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AlbumAdapter adapter = new AlbumAdapter(getContext(), getItems());
        getRecyclerView().setAdapter(adapter);
        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), NUM_OF_COLUMNS));
        //getAppBarText().setText(""); // TODO
    }

}
