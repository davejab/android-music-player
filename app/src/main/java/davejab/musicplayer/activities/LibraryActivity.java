package davejab.musicplayer.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import davejab.musicplayer.R;
import davejab.musicplayer.main.Library;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class LibraryActivity extends FragmentActivity {


    private static String[] PERMISSIONS = {
            READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // TODO Move this elsewhere
        // Get required permissions
        requestPermissions(PERMISSIONS, 0);
    }

    @Override
    public void onBackPressed() {
        Library.getLibrary(getContentResolver()).getLastList();
        super.onBackPressed();
    }

}
