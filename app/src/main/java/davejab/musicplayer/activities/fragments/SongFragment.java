package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import davejab.musicplayer.activities.MainActivity;
import davejab.musicplayer.adapters.SongAdapter;
import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Item;

public class SongFragment extends ItemFragment {

    public static Fragment getSongFragment(Item item){
        SongFragment songFragment = new SongFragment();
        songFragment.setItem(item);
        return songFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SongAdapter adapter = new SongAdapter(getContext(), getItem().toList());
        getRecyclerView().setAdapter(adapter);
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        ((MainActivity) getContext()).setAppBarText(((Album) getItem()).getAlbum());
    }

}
