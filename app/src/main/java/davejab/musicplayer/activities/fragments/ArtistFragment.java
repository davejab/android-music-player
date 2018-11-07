package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.LibraryActivity;
import davejab.musicplayer.adapters.ArtistAdapter;
import davejab.musicplayer.models.Item;

public class ArtistFragment extends ItemFragment {

    public static Fragment getArtistFragment(List<Item> items){
        ArtistFragment artistFragment = new ArtistFragment();
        artistFragment.setItems(items);
        return artistFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArtistAdapter adapter = new ArtistAdapter(getContext(), getItems());
        getRecyclerView().setAdapter(adapter);
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        ((LibraryActivity) getContext()).setAppBarText(getString(R.string.library_artists));
    }

}
