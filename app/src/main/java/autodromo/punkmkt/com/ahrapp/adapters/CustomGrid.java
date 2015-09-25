package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 23/09/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import autodromo.punkmkt.com.ahrapp.R;

public class CustomGrid extends BaseAdapter{

    private Context mContext;
    //private final int[] title;
    private final int[] Imageid;
    LayoutInflater inflater;

    public CustomGrid(Context c, int[] Imageid){
        mContext = c;
        this.Imageid = Imageid;
        //this.title = title;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Imageid.length;
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

        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        grid = new View(mContext);
        grid = inflater.inflate(R.layout.single_grid_ecards_item, null);
        //TextView textView = (TextView) grid.findViewById(R.id.grid_text);
        //
        ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
        //textView.setText(title[position]);
        imageView.setImageResource(Imageid[position]);

        return grid;
    }

}
