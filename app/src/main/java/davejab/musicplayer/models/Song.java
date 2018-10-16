package davejab.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;

public class Song extends Item{

    // Static

    private static String[] PROJECTION = {
            Media.TITLE,
            Media.ALBUM,
            Media.ARTIST,
            Media.DATA,
            Media.DURATION
    };
    public static String[] getProjection(){
        return PROJECTION;
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        public Song createFromParcel(Parcel parcel) {
            return new Song(parcel);
        }
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    // Instance

    public Song(){}

    public Song(Parcel parcel){
        setTitle(parcel.readString());
        setData(parcel.readString());
    }

    private String title;
    private String data;

    public String getTitle(){
        return this.title;
    }
    public String getData(){
        return this.data;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setData(String data){
        this.data = data;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getTitle());
        parcel.writeString(getData());
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
