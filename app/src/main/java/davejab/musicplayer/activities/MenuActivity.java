package davejab.musicplayer.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.main.MediaStoreManager;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;

public class MenuActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get required permissions
        requestPermissions(MediaStoreManager.getPermissions(), 0);

        // Initialise media store manager
        MediaStoreManager msm = new MediaStoreManager(getContentResolver());
        // Get song list
        final List<Item> list = msm.getList(new Artist());

        // TODO delete this
        List<String> artists = new ArrayList<>();
        for (Item i : list){
            Artist a = (Artist) i;
            artists.add(a.getArtist());
        }

        // TODO Create custom adapter
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, artists));
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Pass selected song to the player
                //Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                //intent.putExtra("song", songlist.get(position));
                //startActivity(intent);
            }
        });
    }
}