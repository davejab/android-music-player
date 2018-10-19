package davejab.musicplayer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import davejab.musicplayer.main.MediaManager;
import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("davejab.musicplayer", appContext.getPackageName());

        MediaManager msm = new MediaManager(appContext.getContentResolver());

        List<Item> artists = msm.getList(new Artist());
        Artist artist = new Artist();
        for (Item i : artists) {
            Artist a = (Artist) i;

            if (a.getArtist().equals("Kanye West")){
                artist = a;
            }
        }

        Album al = new Album();
        al.setSelection(artist);
        List<Item> albums = msm.getList(al);
        Album album = new Album();
        for (Item i : albums) {
            Album a = (Album) i;
            if (a.getAlbum().equals("My Beautiful Dark Twisted Fantasy")){
                album = a;
            }
        }
        Log.d("DABRA", album.getAlbum()+"");

        Song song = new Song();
        song.setSelection(album);
        List<Item> songs = msm.getList(song);
        for (Item i : songs) {
            Song a = (Song) i;
            Log.d("DABRA", a.getTitle()+"");
        }
    }
}
