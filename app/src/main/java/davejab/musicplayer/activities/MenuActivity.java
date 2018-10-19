package davejab.musicplayer.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.main.Library;
import davejab.musicplayer.main.MediaManager;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;
import davejab.musicplayer.views.ArtistAdapter;

public class MenuActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get required permissions
        requestPermissions(MediaManager.getPermissions(), 0);

        Library lib = Library.getLibrary(getContentResolver());

        Intent intent = getIntent();
        int songIndex = intent.getIntExtra("songIndex", -1);

        if (songIndex >= 0){
            // If song index is present we advance through the menu
            lib.getNextList(lib.getItemList().get(songIndex));
        }
        final List<Item> list = lib.getItemList();

        // TODO Create custom adapters
        // setListAdapter(item.getAdapter(this, list));
        setListAdapter(new ArtistAdapter(this, list));

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO there is a better way to do this
                Class clss = MenuActivity.class;
                if (list.get(position) instanceof Song) {
                    clss = PlayerActivity.class;
                }
                Intent intent = new Intent(getApplicationContext(), clss);
                intent.putExtra("songIndex", position);
                startActivity(intent);
            }
        });
    }
}
