package davejab.musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;

public class ArtistAdapter extends ItemAdapter {

    public ArtistAdapter(Context context, List<Item> items) {
        super(context, items);
        setItemLayout(R.layout.list_item_artist);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = getLayoutInflater().inflate(getItemLayout(), viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i) {
        Artist artist = (Artist) getItems().get(i);
        ((ViewHolder) viewHolder).txtArtist.setText(artist.getArtist());
    }

    private class ViewHolder extends ItemAdapter.ViewHolder implements View.OnClickListener{

        TextView txtArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtArtist = itemView.findViewById(R.id.artist);
        }

        @Override
        public void onClick(View v) {
            // TODO
        }
    }

}
