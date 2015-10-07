package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import autodromo.punkmkt.com.ahrapp.PilotosDetalleActivity;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.Piloto;


import java.util.List;

/**
 * Created by germanpunk on 20/09/15.
 */
public class PilotosAdapter extends RecyclerView.Adapter<PilotosAdapter.PilotoViewHolder> {
    private List<Piloto> items;
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    private Context context;

    public static class PilotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombre;
        public NetworkImageView imagen;
        public IMyViewHolderClicks mListener;

        public PilotoViewHolder(View v, IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            imagen = (NetworkImageView) v.findViewById(R.id.netork_imageView);
            nombre = (TextView) v.findViewById(R.id.name);
            imagen.setOnClickListener(this);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (v instanceof NetworkImageView){
                mListener.onTomato((NetworkImageView)v, getLayoutPosition());
            } else {
                mListener.onPotato(v,getLayoutPosition());
            }
        }
        public static interface IMyViewHolderClicks {
            public void onPotato(View caller, int i);
            public void onTomato(NetworkImageView callerImage, int i);
        }


    }

    public PilotosAdapter(List<Piloto> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PilotosAdapter.PilotoViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_piloto, viewGroup, false);
        if (imageLoader == null)
            imageLoader = MyVolleySingleton.getInstance().getImageLoader();

        PilotosAdapter.PilotoViewHolder vh = new PilotoViewHolder(v, new PilotosAdapter.PilotoViewHolder.IMyViewHolderClicks() {

            public void onPotato(View caller, int i) {
                Piloto piloto  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), PilotosDetalleActivity.class);
                Idetail.putExtra("id", Integer.toString(piloto.getId()));
                Idetail.putExtra("nombre", piloto.getNombre());
                Idetail.putExtra("image", piloto.getFoto());
                Idetail.putExtra("equipo", piloto.getEquipo());
                Idetail.putExtra("numero", piloto.getNumero());
                Idetail.putExtra("nacionalidad", piloto.getNacionalidad());
                Idetail.putExtra("fecha_nacimiento", piloto.getFecha_nacimiento());
                Idetail.putExtra("campeonatos", piloto.getCampeonatos());
                Idetail.putExtra("grands_prix", piloto.getGrands_prix());
                Idetail.putExtra("podiums", piloto.getPodiums());
                viewGroup.getContext().startActivity(Idetail);
            };
            public void onTomato(NetworkImageView callerImage, int i) {
                Piloto piloto  = items.get(i);

                Intent Idetail = new Intent (viewGroup.getContext(), PilotosDetalleActivity.class);
                Idetail.putExtra("id", Integer.toString(piloto.getId()));
                Idetail.putExtra("nombre", piloto.getNombre());
                Idetail.putExtra("image", piloto.getFoto());
                Idetail.putExtra("equipo", piloto.getEquipo());
                Idetail.putExtra("numero", piloto.getNumero());
                Idetail.putExtra("nacionalidad", piloto.getNacionalidad());
                Idetail.putExtra("fecha_nacimiento", piloto.getFecha_nacimiento());
                Idetail.putExtra("campeonatos", piloto.getCampeonatos());
                Idetail.putExtra("grands_prix", piloto.getGrands_prix());
                Idetail.putExtra("podiums", piloto.getPodiums());
                viewGroup.getContext().startActivity(Idetail);
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(PilotoViewHolder viewHolder, int i) {

        if (i == 0){
            viewHolder.imagen.setDefaultImageResId(R.drawable.alexander_rossi_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 1){
            viewHolder.imagen.setDefaultImageResId(R.drawable.carlos_sainz_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 2){
            viewHolder.imagen.setDefaultImageResId(R.drawable.daniel_ricciardo_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 3){
            viewHolder.imagen.setDefaultImageResId(R.drawable.daniil_kvyat_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 4){
            viewHolder.imagen.setDefaultImageResId(R.drawable.felipe_massa_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 5){
            viewHolder.imagen.setDefaultImageResId(R.drawable.felipe_nassar_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 6){
            viewHolder.imagen.setDefaultImageResId(R.drawable.fernando_alonso_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 7){
            viewHolder.imagen.setDefaultImageResId(R.drawable.jenson_button_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 8){
            viewHolder.imagen.setDefaultImageResId(R.drawable.kimi_rnikkinen_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 9){
            viewHolder.imagen.setDefaultImageResId(R.drawable.lewis_hamilton_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 10){
            viewHolder.imagen.setDefaultImageResId(R.drawable.marcus_ericsson_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 11){
            viewHolder.imagen.setDefaultImageResId(R.drawable.max_verstappen_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 12){
            viewHolder.imagen.setDefaultImageResId(R.drawable.nico_hulkenberg_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 13){
            viewHolder.imagen.setDefaultImageResId(R.drawable.nico_rosberg_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 14){
            viewHolder.imagen.setDefaultImageResId(R.drawable.pastor_maldonado_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 15){
            viewHolder.imagen.setDefaultImageResId(R.drawable.romain_grosjean_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 16){
            viewHolder.imagen.setDefaultImageResId(R.drawable.sebastian_vettel_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 17){
            viewHolder.imagen.setDefaultImageResId(R.drawable.sergio_perez_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 18){
            viewHolder.imagen.setDefaultImageResId(R.drawable.valtteri_bottas_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }else if (i == 19){
            viewHolder.imagen.setDefaultImageResId(R.drawable.will_stevens_pilot);
            viewHolder.imagen.setImageUrl(items.get(i).getFoto(), imageLoader);
        }
        viewHolder.nombre.setText(items.get(i).getNombre());
    }


}
