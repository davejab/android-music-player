package davejab.musicplayer.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.navigation.Navigation;
import davejab.musicplayer.R;
import davejab.musicplayer.activities.LibraryActivity;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.views.ArtistAdapter;

public class ArtistFragment extends ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("DABRA", "");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("DABRA", "");
        ArtistAdapter adapter = new ArtistAdapter(getActivity(), Library.getLibrary(getActivity().getContentResolver()).getItemList());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("DABRA", "");
        ArtistAdapter adapter = new ArtistAdapter(getActivity(), Library.getLibrary(getActivity().getContentResolver()).getItemList());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Library lib = Library.getLibrary(getActivity().getContentResolver());
        lib.getNextList(lib.getItemList().get(position));
        Navigation.findNavController(view).navigate(R.id.action_artistFragment_to_albumFragment);

//        ((LibraryActivity) getActivity()).getMenuStatePagerAdapter().addFragment(new AlbumFragment());
//        ((LibraryActivity) getActivity()).getMenuStatePagerAdapter().notifyDataSetChanged();
//        ((LibraryActivity) getActivity()).getViewPager().setCurrentItem(2);
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

}
