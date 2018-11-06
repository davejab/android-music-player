package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import davejab.musicplayer.R;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.views.AlbumAdapter;

public class AlbumFragment extends Fragment {

    private GridView gridview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Library lib = Library.getLibrary(getActivity().getContentResolver());
        AlbumAdapter adapter = new AlbumAdapter(getActivity(), lib.getCurrentList());

        TextView txtTitle = getActivity().findViewById(R.id.txt_title);
        txtTitle.setText(((Artist) lib.getCurrentItem()).getArtist());

        gridview = getView().findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
    }



}
