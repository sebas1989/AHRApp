package autodromo.punkmkt.com.ahrapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.ServicioDetalleActivity;
import autodromo.punkmkt.com.ahrapp.models.Zona;

/**
 * Created by sebastianmendezgiron on 03/10/15.
 */
public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ServicioViewHolder> {
    private List<Zona> items;


    public static class ServicioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombre;
        //NetworkImageView imagen;
        public IMyViewHolderClicks mListener;

        public ServicioViewHolder(View v, IMyViewHolderClicks listener) {
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

    public ServiciosAdapter(List<Zona> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ServicioViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_zona, viewGroup, false);

        ServicioViewHolder vh = new ServicioViewHolder(v, new ServicioViewHolder.IMyViewHolderClicks() {

            public void onPotato(View caller, int i) {
                Zona zona = items.get(i);
                Intent Idetail = new Intent(viewGroup.getContext(), ServicioDetalleActivity.class);
                Idetail.putExtra("id", Integer.toString(zona.getId()));
                Idetail.putExtra("descripcion", zona.getDescripcion());
                Idetail.putExtra("name", zona.getName());
                viewGroup.getContext().startActivity(Idetail);
            };

            public void onTomato(NetworkImageView callerImage, int i) {
                Zona zona = items.get(i);
                Intent Idetail = new Intent(viewGroup.getContext(), ServicioDetalleActivity.class);
                Idetail.putExtra("id", Integer.toString(zona.getId()));
                Idetail.putExtra("descripcion", zona.getDescripcion());
                Idetail.putExtra("name", zona.getName());
                viewGroup.getContext().startActivity(Idetail);
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(ServicioViewHolder viewHolder, int i) {

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
            viewHolder.nombre.setBackgroundResource(R.color.grey);
        }else if (i==5){
            viewHolder.nombre.setBackgroundResource(R.color.area_cafe);
        }else if (i==6){
            viewHolder.nombre.setBackgroundResource(R.color.area_padro);
        }
    }

}
