package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.adapters.ArtistAdapter;
import davejab.musicplayer.models.Item;

public class ArtistFragment extends ItemFragment {

    public static Fragment getArtistFragment(List<Item> items){
        ArtistFragment fragment = new ArtistFragment();
        fragment.setItems(items);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRecyclerView((RecyclerView) view.findViewById(R.id.recyclerView));

        ArtistAdapter adapter = new ArtistAdapter(getContext(), getItems());
        getRecyclerView().setAdapter(adapter);
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
