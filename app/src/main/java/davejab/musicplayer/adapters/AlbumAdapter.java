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
import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Item;

public class AlbumAdapter extends ItemAdapter {

    public AlbumAdapter(Context context, List<Item> items) {
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
        Album album = (Album) getItems().get(i);
        ((ViewHolder) viewHolder).txtAlbum.setText(album.getAlbum());
        ((ViewHolder) viewHolder).imgAlbum.setImageURI(Uri.parse(album.getAlbumArt()));
    }

    private class ViewHolder extends ItemAdapter.ViewHolder implements View.OnClickListener{

        TextView txtAlbum;
        ImageView imgAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAlbum = itemView.findViewById(R.id.album);
            imgAlbum = itemView.findViewById(R.id.albumArt);
        }

        @Override
        public void onClick(View v) {
            // TODO
        }
    }

}
