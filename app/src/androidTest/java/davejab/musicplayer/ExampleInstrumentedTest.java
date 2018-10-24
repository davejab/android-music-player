package davejab.musicplayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import davejab.musicplayer.main.Library;
import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;
import davejab.musicplayer.models.util.MediaManager;

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





        long aid = 123;


        Artist artist = new Artist(appContext.getContentResolver());
        List<Item> artists = artist.toList();

        for (Item i : artists) {
            Artist a = (Artist) i;
            if (a.getArtist().equals("10cc")){
                aid = a.getId();
                Log.d("DABRA", a.getId()+"");
            }
        }




        Uri uri = MediaStore.Audio.Artists.Albums.getContentUri("external", aid);

        Log.d("DABRA", uri.toString()+"");
        Log.d("DABRA", MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI.toString()+"");
        Cursor cursor = appContext.getContentResolver().query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);

        while(cursor.moveToNext()) {
            Log.d("DABRA", cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))+"");
        }







//





        //Library lib = Library.getLibrary(appContext.getContentResolver());


//        Artist artist = new Artist(appContext.getContentResolver());
//        List<Item> artists = artist.toList();
//
//        for (Item i : artists) {
//            Artist a = (Artist) i;
//            //Log.d("DABRA", a.getArtist() + " - ");
//            if (a.getArtist().equals("Toro Y Moi")){
//                artist = a;
//            }
//        }
//
//
//
//
//        Album album = new Album(appContext.getContentResolver());
//        album.setItemSelection(artist);
//        List<Item> albums = album.toList();
//
//        for (Item i : albums) {
//            Album a = (Album) i;
//            if (a.getAlbum().equals("Anything In Return")){
//                Log.d("DABRA", a.getAlbum() + " - " + a.getArtist() + " - " + a.getAlbumArt());
//                album = a;
//            }
//        }
//
//        Song song = new Song(appContext.getContentResolver());
//        song.setItemSelection(album);
//        List<Item> songs = song.toList();
//
//        for (Item i : songs) {
//            Song a = (Song) i;
//            Log.d("DABRA", a.getTitle() + " - " + a.getArtist() + " - " + a.getAlbumArt());
//        }








//        MediaManager msm = new MediaManager(appContext.getContentResolver());
//        Library lib = Library.getLibrary(appContext.getContentResolver());

//        List<Item> artists = lib.getCurrentList();
//        Artist artist = new Artist();
//        for (Item i : artists) {
//            Artist a = (Artist) i;
//
//            if (a.getArtist().equals("Kanye West")){
//                artist = a;
//            }
//        }
//
//        List<Item> albums = lib.getNextList(artist);
//        Album album = new Album();
//        for (Item i : albums) {
//            Album a = (Album) i;
//            Log.d("DABRA", a.getAlbum());
//            if (a.getAlbum().equals("My Beautiful Dark Twisted Fantasy")){
//                album = a;
//            }
//        }
//
//        //lib.getLastList();
//        for (Item i : lib.getLastList()) {
//            Artist a = (Artist) i;
//
//            if (a.getArtist().equals("Kanye West")){
//                Log.d("DABRA", a.getArtist());
//            }
//        }

//        Song song = new Song();
//        song.setItemSelection(album);
//        List<Item> songs = msm.getList(song);
//        for (Item i : songs) {
//            Song a = (Song) i;
//            Log.d("DABRA", a.getTitle()+"");
//        }


    }
}
