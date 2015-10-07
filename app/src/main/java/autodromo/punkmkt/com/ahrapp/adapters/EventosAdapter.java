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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import autodromo.punkmkt.com.ahrapp.MapActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.Evento;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;

/**
 * Created by germanpunk on 24/09/15.
 */
public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.EventoViewHolder> {
    private List<Evento> items;
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    private Context context;

    public static class EventoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombre;
        public TextView ubicacion;
        public TextView telefono;
        public TextView vermas;
        public NetworkImageView imagen;
        public ImageButton vermasbutton;
        public IMyViewHolderClicks mListener;

        public EventoViewHolder(View v, IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            imagen = (NetworkImageView) v.findViewById(R.id.imageView);
            vermasbutton = (ImageButton) v.findViewById(R.id.vermasbutton);
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
                mListener.onTomato((ImageButton)v, getLayoutPosition());
            } else {
                mListener.onPotato((TextView)v,getLayoutPosition());
            }
        }
        public static interface IMyViewHolderClicks {
            public void onPotato(TextView caller, int i);
            public void onTomato(ImageButton callerImage, int i);
        }

    }

    public EventosAdapter(List<Evento> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EventosAdapter.EventoViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_ciudad_mexico, viewGroup, false);
        if (imageLoader == null)
            imageLoader = MyVolleySingleton.getInstance().getImageLoader();

        EventosAdapter.EventoViewHolder vh = new EventoViewHolder(v, new EventosAdapter.EventoViewHolder.IMyViewHolderClicks() {

            public void onPotato(TextView caller, int i) {
                Evento evento  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), MapActivity.class);

                Idetail.putExtra("id", Integer.toString(evento.getId()));
                Idetail.putExtra("titulo", evento.getNombre());
                Idetail.putExtra("ubicacion", evento.getUbicacion());
                Idetail.putExtra("latitud_mapa", evento.getLatitud_mapa());
                Idetail.putExtra("longitud_mapa", evento.getLongitud_mapa());
                viewGroup.getContext().startActivity(Idetail);
            };
            public void onTomato(ImageButton callerImage, int i) {
                Evento evento  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), MapActivity.class);

                Idetail.putExtra("id", Integer.toString(evento.getId()));
                Idetail.putExtra("titulo", evento.getNombre());
                Idetail.putExtra("ubicacion", evento.getUbicacion());
                Idetail.putExtra("latitud_mapa", evento.getLatitud_mapa());
                Idetail.putExtra("longitud_mapa", evento.getLongitud_mapa());
                viewGroup.getContext().startActivity(Idetail);
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(EventoViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageUrl(items.get(i).getImagen(), imageLoader);
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.ubicacion.setText(items.get(i).getUbicacion());
        viewHolder.telefono.setText(items.get(i).getTelefono());
    }

}
