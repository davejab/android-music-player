package davejab.musicplayer.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import davejab.musicplayer.models.Song;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MediaStoreManager {

    // Static

    private static String[] PERMISSIONS = {
            READ_EXTERNAL_STORAGE
    };
    public static String[] getPermissions(){
        return PERMISSIONS;
    }

    // Instance

    private ContentResolver contentResolver;

    public MediaStoreManager(ContentResolver contentResolver){
        setContentResolver(contentResolver);

    }

    public List<Song> getSongsList(){
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                Song.getProjection(),
                selection,
                null,
                sortOrder);

        Song song;
        List<Song> songs = new ArrayList<Song>();
        if(cursor != null){
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()) {
                    song = new Song();
                    song.setTitle(cursor.getString(cursor.getColumnIndex(Audio.Media.TITLE)));
                    song.setData(cursor.getString(cursor.getColumnIndex(Audio.Media.DATA)));
                    songs.add(song);
                }
            }else{
                // TODO
            }
        }
        return songs;
    }

    // Get & Set
    private void setContentResolver(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }
    private ContentResolver getContentResolver(){
        return this.contentResolver;
    }
}
