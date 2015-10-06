package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.PilotosDetalleActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.GalleryItem;


import java.util.List;

/**
 * Created by germanpunk on 21/09/15.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    private List<GalleryItem> items;
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    private Context context;

    public static class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imagen;
        public IMyViewHolderClicks mListener;

        public GalleryViewHolder(View v, IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            imagen = (ImageView) v.findViewById(R.id.netork_imageView);
            // nombre = (TextView) v.findViewById(R.id.name);
            imagen.setOnClickListener(this);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (v instanceof ImageView){
                mListener.onTomato((ImageView)v, getLayoutPosition());
            } else {
                mListener.onPotato(v,getLayoutPosition());
            }
        }
        public static interface IMyViewHolderClicks {
            public void onPotato(View caller, int i);
            public void onTomato(ImageView callerImage, int i);
        }


    }

    public GalleryAdapter(List<GalleryItem> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public GalleryAdapter.GalleryViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_gallery_item, viewGroup, false);
        if (imageLoader == null)
            imageLoader = MyVolleySingleton.getInstance().getImageLoader();

        GalleryAdapter.GalleryViewHolder vh = new GalleryViewHolder(v, new GalleryAdapter.GalleryViewHolder.IMyViewHolderClicks() {

            public void onPotato(View caller, int i) {
                GalleryItem foto  = items.get(i);
                PilotosDetalleActivity.updatePhoto(items.get(i).getImage());
            };
            public void onTomato(ImageView callerImage, int i) {
                PilotosDetalleActivity.updatePhoto(items.get(i).getImage());
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(GalleryViewHolder viewHolder, int i) {

        //viewHolder.imagen.setImageURI(items.get(i).getImage(), imageLoader);
        switch (items.get(i).getImage()){
            case "foto1":
                viewHolder.imagen.setImageResource(R.drawable.piloto_gallery);
                break;
            case "foto2":
                viewHolder.imagen.setImageResource(R.drawable.noticia_photo1);
                break;
            default:
                break;
        }
        //viewHolder.nombre.setText(items.get(i).ge());
    }


}

