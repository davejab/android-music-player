package davejab.musicplayer.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
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

public class MainActivity extends FragmentActivity {

    private TextView appBarText;
    private SlidingUpPanelLayout playerSlidingPane;

    private Player player;

    private static String[] PERMISSIONS = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO Move this elsewhere
        // Get required permissions
        requestPermissions(PERMISSIONS, 0);

        this.appBarText = findViewById(R.id.app_bar_title);

        playerSlidingPane = findViewById(R.id.sliding_layout);
        playerSlidingPane.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);


        // TODO needed?
        playerSlidingPane.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                // TODO needed?
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                // TODO needed?
            }
        });
        playerSlidingPane.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSlidingPane.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        switchFragment(ArtistFragment.getArtistFragment(new Artist(getContentResolver()).toList()));


        player = new Player(this);


    }

    public void play(List<Item> playlist, int position){
        player.setPlaylist(playlist);
        // TODO change player to take current index only
        player.setCurrentSongIndex(position);
        player.playSong(player.getCurrentSong());
        showPlayer();
    }

    public void showPlayer(){
        if (playerSlidingPane != null) {
            playerSlidingPane.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        }
    }

    public void switchFragment(Fragment nextFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_host, nextFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (playerSlidingPane != null &&
                (playerSlidingPane.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || playerSlidingPane.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            playerSlidingPane.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    public void setAppBarText(String text){
        this.appBarText.setText(text);
    }
}
