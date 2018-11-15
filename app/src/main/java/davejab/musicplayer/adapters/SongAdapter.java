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
import davejab.musicplayer.util.Time;

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
        viewHolder.bindItem(getItem(i));
    }

    private class ViewHolder extends ItemAdapter.ViewHolder implements View.OnClickListener{

        TextView txtTitle;
        TextView txtArtist;
        TextView txtDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtTitle = itemView.findViewById(R.id.title);
            this.txtArtist = itemView.findViewById(R.id.artist);
            this.txtDuration = itemView.findViewById(R.id.duration);
        }

        @Override
        public void bindItem(Item item) {
            super.bindItem(item);
            this.txtTitle.setText(((Song) getItem()).getTitle());
            this.txtArtist.setText(((Song) getItem()).getArtist());
            this.txtDuration.setText(Time.milliSecondsToTimer(((Song) getItem()).getDuration()));
        }

        @Override
        public void onClick(View v) {
            getActivity().getPlayer().playList(getItems(), getItems().indexOf(getItem()));
            getActivity().showPlayer();
        }
    }

}
