package davejab.musicplayer.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.models.Song;
import davejab.musicplayer.models.Item;

public class SongAdapter extends ItemAdapter implements View.OnClickListener{

    public SongAdapter(Context context, List<Item> albumList) {
        super(context, R.layout.list_item_song, albumList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        Song album = (Song) getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(getResource(), parent, false);
            viewHolder.txtTitle = convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtTitle.setText(album.getTitle());
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    private static class ViewHolder {
        TextView txtTitle;
    }

}
