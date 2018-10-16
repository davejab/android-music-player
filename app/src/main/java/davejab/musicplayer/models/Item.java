package davejab.musicplayer.models;

import android.os.Parcelable;

public abstract class Item implements Parcelable {

    private String id;

    protected Item(){
        //setId(id);
    }

    public String getId(){
        return this.id;
    }

    protected void setId(String id){
        this.id = id;
    }

}
