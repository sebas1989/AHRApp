package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.Lugar;
import autodromo.punkmkt.com.ahrapp.utils.MyVolleySingleton;

/**
 * Created by germanpunk on 24/09/15.
 */
public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.LugarViewHolder> {
    private List<Lugar> items;
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    private Context context;

    public static class LugarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombre;
        public TextView ubicacion;
        public TextView telefono;
        ImageView imagen;
        TextView vermas;
        ImageView vermasbutton;
        public IMyViewHolderClicks mListener;

        public LugarViewHolder(View v, IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            imagen = (ImageView) v.findViewById(R.id.imageView);
            vermasbutton = (ImageView) v.findViewById(R.id.vermasbutton);
            vermas = (TextView) v.findViewById(R.id.vermas);
            nombre = (TextView) v.findViewById(R.id.name);
            ubicacion = (TextView) v.findViewById(R.id.ubicacion);
            telefono = (TextView) v.findViewById(R.id.telefono);
            vermas.setOnClickListener(this);
            vermasbutton.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (v instanceof ImageView){
                mListener.onTomato((ImageView)v, getLayoutPosition());
            } else {
                mListener.onPotato((TextView)v,getLayoutPosition());
            }
        }
        public static interface IMyViewHolderClicks {
            public void onPotato(TextView caller, int i);
            public void onTomato(ImageView callerImage, int i);
        }

    }

    public LugaresAdapter(List<Lugar> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public LugaresAdapter.LugarViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_ciudad_mexico, viewGroup, false);
        if (imageLoader == null)
            imageLoader = MyVolleySingleton.getInstance().getImageLoader();

        LugaresAdapter.LugarViewHolder vh = new LugarViewHolder(v, new LugaresAdapter.LugarViewHolder.IMyViewHolderClicks() {

            public void onPotato(TextView caller, int i) {
                /*Lugar lugar  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), MapActivity.class);
                Idetail.putExtra("latitud", lugar.getLatitud_mapa());
                Idetail.putExtra("longitud", lugar.getLongitud_mapa());
                Idetail.putExtra("titulo", lugar.getNombre());
                viewGroup.getContext().startActivity(Idetail);*/
            };
            public void onTomato(ImageView callerImage, int i) {
                /*Lugar lugar  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), MapActivity.class);
                Idetail.putExtra("latitud", lugar.getLatitud_mapa());
                Idetail.putExtra("longitud", lugar.getLongitud_mapa());
                Idetail.putExtra("titulo", lugar.getNombre());
                viewGroup.getContext().startActivity(Idetail);*/
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(LugarViewHolder viewHolder, int i) {
        //viewHolder.imagen.setImageUrl(items.get(i).getImagen(), imageLoader);
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.ubicacion.setText(items.get(i).getUbicacion());
        viewHolder.telefono.setText(items.get(i).getTelefono());
        switch (i)
        {
            case 0:
                viewHolder.imagen.setImageResource(R.drawable.museos_rectangulo);
                break;
            case 1:
                viewHolder.imagen.setImageResource(R.drawable.museos_rectangulo);
                break;
            case 2:
                viewHolder.imagen.setImageResource(R.drawable.museos_rectangulo);
                break;
            case 3:
                viewHolder.imagen.setImageResource(R.drawable.museos_rectangulo);
                break;
            default:
                break;
        }
    }

}