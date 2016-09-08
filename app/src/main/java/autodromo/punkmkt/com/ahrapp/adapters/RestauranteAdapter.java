package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;
import autodromo.punkmkt.com.ahrapp.MapActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.Restaurante;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;

/**
 * Created by germanpunk on 24/09/15.
 */
public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder> {
    private List<Restaurante> items;
    private Context context;

    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();

    public static class RestauranteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombre;
        public TextView ubicacion;
        public TextView telefono;
        public TextView vermas;
        public TextView sitiourl;
        public NetworkImageView imagen;
        public ImageButton vermasbutton;

        public IMyViewHolderClicks mListener;

        public RestauranteViewHolder(View v, IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            imagen = (NetworkImageView) v.findViewById(R.id.imageView);
            vermasbutton = (ImageButton) v.findViewById(R.id.vermasbutton);
            vermas = (TextView) v.findViewById(R.id.vermas);
            nombre = (TextView) v.findViewById(R.id.name);
            ubicacion = (TextView) v.findViewById(R.id.ubicacion);
            telefono = (TextView) v.findViewById(R.id.telefono);
            sitiourl = (TextView) v.findViewById(R.id.sitiourl);
            telefono.setOnClickListener(this);
            sitiourl.setOnClickListener(this);
            vermas.setOnClickListener(this);
            sitiourl.setOnClickListener(this);
            vermasbutton.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (v instanceof ImageButton){
                mListener.onTomato((ImageButton) v, getLayoutPosition());
            } else if (v.getId() == R.id.vermas){
                mListener.onPotato((TextView) v, getLayoutPosition());
            }else if (v.getId() == R.id.telefono){
                mListener.callPlace((TextView) v, getLayoutPosition());
            }else if (v.getId() == R.id.sitiourl){
                mListener.openSiteUrl((TextView) v, getLayoutPosition());
            }
        }
        public static interface IMyViewHolderClicks {
            public void openSiteUrl(TextView textCall, int i);
            public void callPlace(TextView textCall, int i);
            public void onPotato(TextView caller, int i);
            public void onTomato(ImageButton callerImage, int i);
        }

    }

    public RestauranteAdapter(List<Restaurante> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RestauranteAdapter.RestauranteViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_ciudad_mexico, viewGroup, false);
        if (imageLoader == null)
            imageLoader = MyVolleySingleton.getInstance().getImageLoader();

        RestauranteAdapter.RestauranteViewHolder vh = new RestauranteViewHolder(v, new RestauranteAdapter.RestauranteViewHolder.IMyViewHolderClicks() {

            public void onPotato(TextView caller, int i) {
                Restaurante restaurante  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), MapActivity.class);

                Idetail.putExtra("id", Integer.toString(restaurante.getId()));
                Idetail.putExtra("titulo", restaurante.getNombre());
                Idetail.putExtra("ubicacion", restaurante.getUbicacion());
                Idetail.putExtra("latitud_mapa", restaurante.getLatitud_mapa());
                Idetail.putExtra("longitud_mapa", restaurante.getLongitud_mapa());
                viewGroup.getContext().startActivity(Idetail);
            };
            public void onTomato(ImageButton callerImage, int i) {
                Restaurante restaurante  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), MapActivity.class);

                Idetail.putExtra("id", Integer.toString(restaurante.getId()));
                Idetail.putExtra("titulo", restaurante.getNombre());
                Idetail.putExtra("ubicacion", restaurante.getUbicacion());
                Idetail.putExtra("latitud_mapa", restaurante.getLatitud_mapa());
                Idetail.putExtra("longitud_mapa", restaurante.getLongitud_mapa());
                viewGroup.getContext().startActivity(Idetail);
            };
            public void callPlace(TextView textCall, int i){
                Restaurante restaurante  = items.get(i);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + restaurante.getTelefono()));
                viewGroup.getContext().startActivity(callIntent);
            };
            public void openSiteUrl(TextView urlLink, int i){
                Restaurante restaurante  = items.get(i);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(restaurante.getUrlmap()));
                viewGroup.getContext().startActivity(browserIntent);
            };
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(RestauranteViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageUrl(items.get(i).getImagen(), imageLoader);
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.ubicacion.setText(items.get(i).getUbicacion());
        viewHolder.telefono.setText(items.get(i).getTelefono());
        if (items.get(i).getUrlmap().equals(null) || items.get(i).getUrlmap().equals("")){
            viewHolder.sitiourl.setVisibility(View.GONE);
        }else {
            viewHolder.sitiourl.setText(items.get(i).getUrlmap());
        }
    }

}