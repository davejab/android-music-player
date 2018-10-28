package davejab.musicplayer.views;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Item;

public class AlbumAdapter extends ItemAdapter implements View.OnClickListener{

    public AlbumAdapter(Context context, List<Item> albumList) {
        super(context, R.layout.list_item_album, albumList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        Album album = (Album) getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(getResource(), parent, false);
            viewHolder.txtAlbum = convertView.findViewById(R.id.album);
            viewHolder.imgAlbum = convertView.findViewById(R.id.albumArt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtAlbum.setText(album.getAlbum());
        viewHolder.imgAlbum.setImageURI(Uri.parse(album.getAlbumArt()));
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    private static class ViewHolder {
        TextView txtAlbum;
        ImageView imgAlbum;
    }

}
