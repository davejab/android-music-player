package davejab.musicplayer.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.fragments.ArtistFragment;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.models.Artist;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LibraryActivity extends FragmentActivity {

    private static String[] PERMISSIONS = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        // TODO Move this elsewhere
        // Get required permissions
        requestPermissions(PERMISSIONS, 0);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_host, ArtistFragment.getArtistFragment(Library.getLibrary(getContentResolver()).getCurrentList()))
                // Add this transaction to the back stack
                .addToBackStack(null)
                .commit();


    }


}
