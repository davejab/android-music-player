package davejab.musicplayer.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.activities.fragments.SongFragment;
import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Item;
import davejab.musicplayer.models.Song;

public class AlbumAdapter extends ItemAdapter {

    public AlbumAdapter(Context context, List<Item> items) {
        super(context, items);
        setItemLayout(R.layout.list_item_album);
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

        TextView txtAlbum;
        ImageView imgAlbum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtAlbum = itemView.findViewById(R.id.album);
            this.imgAlbum = itemView.findViewById(R.id.albumArt);
        }

        @Override
        public void bindItem(Item item) {
            super.bindItem(item);
            Album album = (Album) item;
            this.txtAlbum.setText(album.getAlbum());
            if (album.getAlbumArt()!= null) {
                this.imgAlbum.setImageURI(Uri.parse(album.getAlbumArt()));
            } else {
                this.imgAlbum.setImageDrawable(getActivity().getDrawable(R.drawable.img_nocover));
            }
        }

        @Override
        public void onClick(View v) {
            Song songs = new Song(getActivity().getContentResolver());
            songs.setItemSelection(getItem());
            getActivity().switchFragment(SongFragment.getSongFragment(songs));
        }
    }

}
