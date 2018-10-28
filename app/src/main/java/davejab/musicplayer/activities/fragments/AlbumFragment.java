package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.navigation.Navigation;
import davejab.musicplayer.R;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.views.AlbumAdapter;

public class AlbumFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView gridview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        AlbumAdapter adapter = new AlbumAdapter(getActivity(), Library.getLibrary(getActivity().getContentResolver()).getCurrentList());
        gridview = getView().findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Library lib = Library.getLibrary(getActivity().getContentResolver());
        lib.getNextList(position);
        Navigation.findNavController(view).navigate(R.id.action_albumFragment_to_songFragment);
    }


}
