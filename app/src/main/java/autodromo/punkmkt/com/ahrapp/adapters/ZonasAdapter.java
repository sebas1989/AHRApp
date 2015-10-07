package autodromo.punkmkt.com.ahrapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.ZonaDetalleActivity;
import autodromo.punkmkt.com.ahrapp.models.Zona;

/**
 * Created by sebastianmendezgiron on 03/10/15.
 */
public class ZonasAdapter extends RecyclerView.Adapter<ZonasAdapter.ZonaViewHolder> {
    private List<Zona> items;

    //ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    private Context context;

    public static class ZonaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombre;
        //NetworkImageView imagen;
        public IMyViewHolderClicks mListener;

        public ZonaViewHolder(View v, IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            //imagen = (NetworkImageView) v.findViewById(R.id.netork_imageView);
            nombre = (TextView) v.findViewById(R.id.name);
            //imagen.setOnClickListener(this);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof NetworkImageView) {
                mListener.onTomato((NetworkImageView) v, getLayoutPosition());
            } else {
                mListener.onPotato(v, getLayoutPosition());
            }
        }

        public static interface IMyViewHolderClicks {
            public void onPotato(View caller, int i);

            public void onTomato(NetworkImageView callerImage, int i);
        }


    }

    public ZonasAdapter(List<Zona> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ZonasAdapter.ZonaViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_zona, viewGroup, false);

        ZonasAdapter.ZonaViewHolder vh = new ZonaViewHolder(v, new ZonasAdapter.ZonaViewHolder.IMyViewHolderClicks() {

            public void onPotato(View caller, int i) {
                Zona zona = items.get(i);
                Intent Idetail = new Intent(viewGroup.getContext(), ZonaDetalleActivity.class);
                Idetail.putExtra("id", Integer.toString(zona.getId()));
                Idetail.putExtra("nombre_completo", zona.getNameCompleto());
                Idetail.putExtra("descripcion", zona.getDescripcion());
                Idetail.putExtra("mapa", zona.getMapa());
                viewGroup.getContext().startActivity(Idetail);
            }

            ;

            public void onTomato(NetworkImageView callerImage, int i) {
                Zona zona = items.get(i);
                Intent Idetail = new Intent(viewGroup.getContext(), ZonaDetalleActivity.class);
                Idetail.putExtra("id", Integer.toString(zona.getId()));
                Idetail.putExtra("nombre_completo", zona.getNameCompleto());
                Idetail.putExtra("descripcion", zona.getDescripcion());
                Idetail.putExtra("mapa", zona.getMapa());
                viewGroup.getContext().startActivity(Idetail);
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(ZonaViewHolder viewHolder, int i) {

        viewHolder.nombre.setText(items.get(i).getName());
        if (i==0){
            viewHolder.nombre.setBackgroundResource(R.color.area_verde);
        }else if (i==1){
            viewHolder.nombre.setBackgroundResource(R.color.area_azul);
        }else if (i==2){
            viewHolder.nombre.setBackgroundResource(R.color.area_morada);
        }else if (i==3){
            viewHolder.nombre.setBackgroundResource(R.color.area_amarilla);
        }else if (i==4){
            viewHolder.nombre.setBackgroundResource(R.color.area_naranja);
        }else if (i==5){
            viewHolder.nombre.setBackgroundResource(R.color.grey);
        }else if (i==6){
            viewHolder.nombre.setBackgroundResource(R.color.area_cafe);
        }
    }

}
