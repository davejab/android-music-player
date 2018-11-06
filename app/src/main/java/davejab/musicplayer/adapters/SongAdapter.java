package davejab.musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
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
        Song song = (Song) getItems().get(i);
        ((ViewHolder) viewHolder).txtTitle.setText(song.getTitle());
    }

    private class ViewHolder extends ItemAdapter.ViewHolder implements View.OnClickListener{

        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.artist);
        }

        @Override
        public void onClick(View v) {
            // TODO
        }
    }

}
