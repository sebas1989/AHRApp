package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 23/09/15.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.R;

public class CustomGrid extends BaseAdapter{

    private Context mContext;
    //private final int[] title;
    ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    LayoutInflater inflater;

    public CustomGrid(Context c, ArrayList<Bitmap> bitmaps){
        mContext = c;
        this.bitmaps = bitmaps;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        grid = new View(mContext);
        grid = inflater.inflate(R.layout.single_grid_ecards_item, null);
        ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
        imageView.setImageBitmap(bitmaps.get(position));
        return grid;
    }

}
