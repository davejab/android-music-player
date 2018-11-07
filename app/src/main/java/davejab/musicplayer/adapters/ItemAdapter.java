package davejab.musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import davejab.musicplayer.models.Item;

public abstract class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    private int itemLayout;

    protected ItemAdapter(Context context, List<Item> items){
        setContext(context);
        setItems(items);
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    protected LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(getContext());
    }

    protected Context getContext(){
        return this.context;
    }
    public List<Item> getItems(){
        return this.items;
    }
    protected int getItemLayout(){
        return this.itemLayout;
    }
    private void setContext(Context context){
        this.context = context;
    }
    private void setItems(List<Item> items){
        this.items = items;
    }
    protected void setItemLayout(int itemLayout){
        this.itemLayout = itemLayout;
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected Item item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void bindItem(Item item){
            setItem(item);
        }

        protected Item getItem(){
            return this.item;
        }
        protected void setItem(Item item){
            this.item = item;
        }

    }
}
