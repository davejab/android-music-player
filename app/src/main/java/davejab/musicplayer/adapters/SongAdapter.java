package davejab.musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.MainActivity;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;

public class SongAdapter extends ItemAdapter {

    public SongAdapter(Context context, List<Item> items) {
        super(context, items);
        setItemLayout(R.layout.list_item_song);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = getLayoutInflater().inflate(getItemLayout(), viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindItem(getItems().get(i));
    }

    private class ViewHolder extends ItemAdapter.ViewHolder implements View.OnClickListener{

        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
        }

        @Override
        public void bindItem(Item item) {
            setItem(item);
            this.txtTitle.setText(((Song) getItem()).getTitle());
        }

        @Override
        public void onClick(View v) {
            ((MainActivity) getContext()).play(getItems(), getItems().indexOf(getItem()));
        }
    }

}
