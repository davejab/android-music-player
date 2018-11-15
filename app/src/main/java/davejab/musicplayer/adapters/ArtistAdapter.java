package davejab.musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.fragments.AlbumFragment;
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
        viewHolder.bindItem(getItem(i));
    }

    private class ViewHolder extends ItemAdapter.ViewHolder {

        TextView txtArtist;
        TextView txtSongCount;
        TextView txtAlbumCount;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtArtist = itemView.findViewById(R.id.artist);
            this.txtSongCount = itemView.findViewById(R.id.song_count);
            this.txtAlbumCount = itemView.findViewById(R.id.album_count);
        }

        @Override
        public void bindItem(Item item) {
            super.bindItem(item);
            this.txtArtist.setText(((Artist) getItem()).getArtist());
            this.txtSongCount.setText(String.valueOf(((Artist) getItem()).getNumberOfSongs()));
            this.txtAlbumCount.setText(String.valueOf(((Artist) getItem()).getNumberOfAlbums()));
        }

        @Override
        public void onClick(View v) {
            Album albums = new Album(getActivity().getContentResolver());
            albums.setItemSelection(getItem());
            getActivity().switchFragment(AlbumFragment.getAlbumFragment(albums));
        }
    }

}
