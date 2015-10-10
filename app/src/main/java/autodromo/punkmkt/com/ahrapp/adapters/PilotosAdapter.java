package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import autodromo.punkmkt.com.ahrapp.PilotosDetalleActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.Piloto;


import java.util.List;

/**
 * Created by germanpunk on 20/09/15.
 */
public class PilotosAdapter extends RecyclerView.Adapter<PilotosAdapter.PilotoViewHolder> {
    private List<Piloto> items;
    private Context context;

    public static class PilotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nombre;
        public ImageView imagen;
        public IMyViewHolderClicks mListener;
        public PilotoViewHolder(View v,IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            imagen = (ImageView) v.findViewById(R.id.netork_imageView);
            nombre = (TextView) v.findViewById(R.id.name);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onPotato(v,getLayoutPosition());
        }
        public static interface IMyViewHolderClicks {
            public void onPotato(View caller, int i);
            // public void onTomato(ImageView callerImage, int i);
        }
    }

    public PilotosAdapter(List<Piloto> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PilotosAdapter.PilotoViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_piloto, viewGroup, false);

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

        });
        return vh;
    }

    @Override
    public void onBindViewHolder(PilotoViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.imagen.setImageBitmap(items.get(i).getFotob());
    }

}
