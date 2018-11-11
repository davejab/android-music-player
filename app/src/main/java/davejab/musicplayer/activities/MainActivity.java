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
        setTxtAppBar((TextView) findViewById(R.id.app_bar_title));
        setSlidingPanelPlayer((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));

        // Hide player on initial launch
        getSlidingPanelPlayer().setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

        // Initialise player class
        setPlayer(new Player(this));

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
        if (getSlidingPanelPlayer() != null && getSlidingPanelPlayer().getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
            getSlidingPanelPlayer().setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    private void showPlayer(){
        if (getSlidingPanelPlayer() != null) {
            // Fully expand the player layout
            getSlidingPanelPlayer().setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }
    }

    /**
     * Sets the current playlist in the player class and proceeds to play the given position index
     * @param playlist new playlist of songs to be assigned to the player
     * @param position selected position index defines what song plays first
     */
    public void play(List<Item> playlist, int position){
        Log.i(TAG, "play called");
        getPlayer().setPlaylist(playlist);
        getPlayer().playSong(position);
        showPlayer();
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
        getTxtAppBar().setText(text);
    }

    private Player getPlayer(){
        return this.player;
    }
    private TextView getTxtAppBar(){
        return this.txtAppBar;
    }
    private SlidingUpPanelLayout getSlidingPanelPlayer(){
        return this.slidingPanelPlayer;
    }
    private void setPlayer(Player player){
        this.player = player;
    }
    private void setTxtAppBar(TextView txtAppBar){
        this.txtAppBar = txtAppBar;
    }
    private void setSlidingPanelPlayer(SlidingUpPanelLayout slidingPanelPlayer){
        this.slidingPanelPlayer = slidingPanelPlayer;
    }

}
