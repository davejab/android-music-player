package davejab.musicplayer.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.PlayerActivity;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.views.SongAdapter;

public class SongFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        SongAdapter adapter = new SongAdapter(getActivity(), Library.getLibrary(getActivity().getContentResolver()).getCurrentList());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity().getApplicationContext(), PlayerActivity.class);
        intent.putExtra("songIndex", position);
        startActivity(intent);
    }

}
