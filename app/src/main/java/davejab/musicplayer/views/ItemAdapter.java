package davejab.musicplayer.views;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

import davejab.musicplayer.models.Item;

public abstract class ItemAdapter extends ArrayAdapter<Item> implements View.OnClickListener{

    private List<Item> itemList;
    private int resource;

    protected ItemAdapter(Context context, int resource, List<Item> itemList) {
        super(context, resource, itemList);
        setItemList(itemList);
        setResource(resource);
    }

    private List<Item> getItemList(){
        return this.itemList;
    }
    protected int getResource(){
        return this.resource;
    }
    private void setItemList(List<Item> itemList){
        this.itemList = itemList;
    }
    private void setResource(int resource){
        this.resource = resource;
    }

}
