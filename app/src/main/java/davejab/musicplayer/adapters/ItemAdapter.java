package davejab.musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import davejab.musicplayer.activities.MainActivity;
import davejab.musicplayer.models.Item;

public abstract class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    private int itemLayout;

    ItemAdapter(Context context, List<Item> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(this.context);
    }

    protected MainActivity getActivity(){
        return ((MainActivity) this.context);
    }

    Item getItem(int id){
        return getItems().get(id);
    }

    List<Item> getItems(){
        return this.items;
    }

    int getItemLayout(){
        return this.itemLayout;
    }

    void setItemLayout(int itemLayout){
        this.itemLayout = itemLayout;
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected Item item;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void bindItem(Item item){
            this.item = item;
        }

        protected Item getItem(){
            return this.item;
        }

    }
}
