package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import davejab.musicplayer.R;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.views.ArtistAdapter;

public class ArtistFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Library lib = Library.getLibrary(getActivity().getContentResolver());
        ArtistAdapter adapter = new ArtistAdapter(getActivity(), lib.getCurrentList());

        TextView txtTitle = getActivity().findViewById(R.id.txt_title);
        txtTitle.setText("Artists");

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Library lib = Library.getLibrary(getActivity().getContentResolver());
        lib.getNextList(position);
        Navigation.findNavController(view).navigate(R.id.action_artistFragment_to_albumFragment);
    }

}
