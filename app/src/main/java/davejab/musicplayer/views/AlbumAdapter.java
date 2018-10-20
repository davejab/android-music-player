package davejab.musicplayer.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.models.Album;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;

public class AlbumAdapter extends ArrayAdapter<Item> implements View.OnClickListener{

    // Static

    // TODO
    private static int RESOURCE = R.layout.list_item_album;

    private static int getResource(){
        return RESOURCE;
    }

    // Instance

    private List<Item> albumList;

    public AlbumAdapter(Context context, List<Item> albumList) {
        super(context, getResource(), albumList);
        setAlbumList(albumList);
    }

    private static class ViewHolder {
        TextView txtAlbum;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Album album = (Album) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(getResource(), parent, false);
            viewHolder.txtAlbum = convertView.findViewById(R.id.album);
            //result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            //result=convertView;
        }

        viewHolder.txtAlbum.setText(album.getAlbum());
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    private List<Item> getAlbumList(){
        return this.albumList;
    }
    private void setAlbumList(List<Item> albumList){
        this.albumList = albumList;
    }

}
