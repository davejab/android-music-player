package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import davejab.musicplayer.R;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.views.AlbumAdapter;

public class AlbumFragment extends ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        return view;
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        AlbumAdapter adapter = new AlbumAdapter(getActivity(), Library.getLibrary(getActivity().getContentResolver()).getCurrentList());
//        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);
//    }


    @Override
    public void onStart() {
        super.onStart();
        AlbumAdapter adapter = new AlbumAdapter(getActivity(), Library.getLibrary(getActivity().getContentResolver()).getCurrentList());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        AlbumAdapter adapter = new AlbumAdapter(getActivity(), Library.getLibrary(getActivity().getContentResolver()).getCurrentList());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }


}
