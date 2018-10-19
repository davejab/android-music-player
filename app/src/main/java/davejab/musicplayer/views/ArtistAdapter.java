package davejab.musicplayer.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import davejab.musicplayer.R;
import davejab.musicplayer.models.Artist;
import davejab.musicplayer.models.Item;

public class ArtistAdapter extends ArrayAdapter<Item> implements View.OnClickListener{

    // Static

    // TODO
    private static int RESOURCE = R.layout.list_item_artist;

    private static int getResource(){
        return RESOURCE;
    }

    // Instance

    private List<Item> artistList;

    public ArtistAdapter(Context context, List<Item> artistList) {
        super(context, getResource(), artistList);
        setArtistList(artistList);
    }

    private static class ViewHolder {
        TextView txtArtist;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Artist artist = (Artist) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(getResource(), parent, false);
            viewHolder.txtArtist = convertView.findViewById(R.id.artist);
            //result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            //result=convertView;
        }

        viewHolder.txtArtist.setText(artist.getArtist());
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }



    private List<Item> getArtistList(){
        return this.artistList;
    }
    private void setArtistList(List<Item> artistList){
        this.artistList = artistList;
    }

}
