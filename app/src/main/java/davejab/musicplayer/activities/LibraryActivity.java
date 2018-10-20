package davejab.musicplayer.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.Navigation;
import davejab.musicplayer.R;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.main.MediaManager;

public class LibraryActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // TODO Move this elsewhere
        // Get required permissions
        requestPermissions(MediaManager.getPermissions(), 0);
    }

    @Override
    public void onBackPressed() {
        Library.getLibrary(getContentResolver()).getLastList();
        super.onBackPressed();
    }

}
