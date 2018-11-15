package davejab.musicplayer.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.fragments.ArtistFragment;
import davejab.musicplayer.main.Player;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Allows user to browse music collection and play songs.
 *
 * @author davejab
 */
public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";

    // Defining required permissions for app
    private static String[] PERMISSIONS = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
    };

    private Player player;
    private TextView txtAppBar;
    private SlidingUpPanelLayout slidingPanelPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate called");

        // TODO Move this elsewhere
        // Request required permissions
        requestPermissions(PERMISSIONS, 0);

        // Set views
        setContentView(R.layout.activity_main);
        this.txtAppBar = findViewById(R.id.app_bar_title);
        this.slidingPanelPlayer = findViewById(R.id.sliding_layout);

        // Hide player on initial launch
        this.slidingPanelPlayer.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

        // Initialise player class
        this.player = new Player(this);

        // Create new artist fragment
        Fragment startingFragment = ArtistFragment.getArtistFragment(
                new Artist(getContentResolver()));

        // Replaces the fragment holder with our starting fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_host, startingFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        // If player is overlaying the library view, we want to collapse it before navigating back
        if (this.slidingPanelPlayer != null && this.slidingPanelPlayer.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
            this.slidingPanelPlayer.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    public void showPlayer(){
        if (this.slidingPanelPlayer != null) {
            // Fully expand the player layout
            this.slidingPanelPlayer.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }
    }

    /**
     * Changes the active fragment on the library view to what is provided. Also adds fragment
     * to the back stack.
     * @param nextFragment fragment used to perform the switch
     */
    public void switchFragment(Fragment nextFragment) {
        Log.i(TAG, "switchFragment called");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_host, nextFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Sets the text of the app bar
     * @param text text used to set the app bar
     */
    public void setAppBarText(String text){
        this.txtAppBar.setText(text);
    }

    public Player getPlayer(){
        return this.player;
    }

}
