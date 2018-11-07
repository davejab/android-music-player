package davejab.musicplayer.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.fragments.ArtistFragment;
import davejab.musicplayer.models.Artist;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LibraryActivity extends FragmentActivity {

    private TextView appBarText;

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

        this.appBarText = findViewById(R.id.app_bar_title);

        switchFragment(ArtistFragment.getArtistFragment(new Artist(getContentResolver()).toList()));
    }

    public void switchFragment(Fragment nextFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_host, nextFragment)
                .addToBackStack(null)
                .commit();
    }

    public void setAppBarText(String text){
        this.appBarText.setText(text);
    }

}
