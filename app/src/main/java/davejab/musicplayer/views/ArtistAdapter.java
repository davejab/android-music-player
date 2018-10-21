package davejab.musicplayer.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;

public class ArtistAdapter extends ItemAdapter implements View.OnClickListener{

    public ArtistAdapter(Context context, List<Item> artistList) {
        super(context, R.layout.list_item_artist, artistList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        Artist artist = (Artist) getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(getResource(), parent, false);
            viewHolder.txtArtist = convertView.findViewById(R.id.artist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtArtist.setText(artist.getArtist());
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    private static class ViewHolder {
        TextView txtArtist;
    }

}
