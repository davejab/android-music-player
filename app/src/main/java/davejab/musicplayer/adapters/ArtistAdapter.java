package davejab.musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.LibraryActivity;
import davejab.musicplayer.activities.fragments.AlbumFragment;
import davejab.musicplayer.activities.fragments.ItemFragment;
import davejab.musicplayer.models.Album;
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
        viewHolder.bindItem(getItems().get(i));
    }

    private class ViewHolder extends ItemAdapter.ViewHolder {

        TextView txtArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtArtist = itemView.findViewById(R.id.artist);
        }

        @Override
        public void bindItem(Item item) {
            setItem(item);
            this.txtArtist.setText(((Artist) getItem()).getArtist());
        }

        @Override
        public void onClick(View v) {
            // TODO
            Album albums = new Album(getContext().getContentResolver());
            albums.setItemSelection(getItem());
            List<Item> items = albums.toList();
            ((LibraryActivity) getContext()).switchFragment(AlbumFragment.getAlbumFragment(items));
        }
    }

}
