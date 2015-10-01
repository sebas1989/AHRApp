package autodromo.punkmkt.com.ahrapp.adapters;

import android.content.Context;
import android.content.Intent;


import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


import java.util.List;
import android.support.v7.widget.RecyclerView;

import autodromo.punkmkt.com.ahrapp.SingleNewDetailActivity;
import autodromo.punkmkt.com.ahrapp.utils.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.models.Noticias;
import autodromo.punkmkt.com.ahrapp.R;


/**
 * Created by sebastianmendezgiron on 20/09/15.
 */
public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.NoticiaViewHolder> {

    private List<Noticias> items;
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    private Context context;

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nombre, descripcion_corta;
        public NetworkImageView imagen;
        public IMyViewHolderClicks mListener;

        public NoticiaViewHolder(View itemView, IMyViewHolderClicks listener) {
            super(itemView);
            mListener = listener;
            imagen = (NetworkImageView) itemView.findViewById(R.id.netork_imageView);
            nombre = (TextView) itemView.findViewById(R.id.name);
            descripcion_corta = (TextView) itemView.findViewById(R.id.descripcion_corta);
            imagen.setOnClickListener(this);
            itemView.setOnClickListener(this);
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

    @Override
    public NoticiaViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_items, viewGroup, false);
        if (imageLoader == null)
            imageLoader = MyVolleySingleton.getInstance().getImageLoader();

        NoticiasAdapter.NoticiaViewHolder vh = new NoticiasAdapter.NoticiaViewHolder(v, new NoticiasAdapter.NoticiaViewHolder.IMyViewHolderClicks() {

            public void onPotato(View caller, int i) {
                Noticias noticia  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), getClass());

                Idetail.putExtra("id", Integer.toString(noticia.getId()));
                Idetail.putExtra("nombre", noticia.getTitulo());
                Idetail.putExtra("imagen", noticia.getImgen_contenido());
                Idetail.putExtra("subtitulo", noticia.getSubtitulo());
                Idetail.putExtra("descripcion", noticia.getDescripcion());

                viewGroup.getContext().startActivity(Idetail);

            };
            public void onTomato(NetworkImageView callerImage, int i) {
                Noticias noticia  = items.get(i);

                Intent Idetail = new Intent (viewGroup.getContext(), SingleNewDetailActivity.class);
                Idetail.putExtra("id", Integer.toString(noticia.getId()));
                Idetail.putExtra("nombre", noticia.getTitulo());
                Idetail.putExtra("imagen", noticia.getImgen_contenido());
                Idetail.putExtra("subtitulo", noticia.getSubtitulo());
                Idetail.putExtra("descripcion", noticia.getDescripcion());

                viewGroup.getContext().startActivity(Idetail);
            }

        });

        return vh;

    }

    @Override
    public void onBindViewHolder(NoticiaViewHolder noticiaViewHolder, int i) {
        noticiaViewHolder.imagen.setImageUrl(items.get(i).getImgen_contenido(), imageLoader);
        noticiaViewHolder.nombre.setText(items.get(i).getTitulo());
        noticiaViewHolder.descripcion_corta.setText(items.get(i).getDescripcion_corta());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public NoticiasAdapter(List<Noticias> items) {
        this.items = items;
    }

}
