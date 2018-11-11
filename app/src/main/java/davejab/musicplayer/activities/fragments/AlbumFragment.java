package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import davejab.musicplayer.activities.MainActivity;
import davejab.musicplayer.adapters.AlbumAdapter;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;

public class AlbumFragment extends ItemFragment {

    private static final int NUM_OF_COLUMNS = 2;

    public static Fragment getAlbumFragment(Item item){
        AlbumFragment albumFragment = new AlbumFragment();
        albumFragment.setItem(item);
        return albumFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AlbumAdapter adapter = new AlbumAdapter(getContext(), getItem().toList());
        getRecyclerView().setAdapter(adapter);
        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), NUM_OF_COLUMNS));
        ((MainActivity) getContext()).setAppBarText(((Artist) getItem()).getArtist());
    }

}
