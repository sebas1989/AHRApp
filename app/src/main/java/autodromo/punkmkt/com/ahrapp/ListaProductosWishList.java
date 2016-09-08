package autodromo.punkmkt.com.ahrapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import autodromo.punkmkt.com.ahrapp.helpers.TiendaDBHelper;
import autodromo.punkmkt.com.ahrapp.models.TiendaRestauranteItem;

/**
 * Created by sebastianmendezgiron on 16/08/16.
 */

public class ListaProductosWishList extends AppCompatActivity{
    TableLayout tabla_informacion;
    private TiendaDBHelper tiendaDBHelper;
    private Context context;
    private ArrayList<TiendaRestauranteItem> productos = new ArrayList<TiendaRestauranteItem>();
    float x1,x2;
    float y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda_wishlist_items);
        tituloListaWishList();
        cargaWishListItems();

    }

    public void tituloListaWishList(){
        LayoutInflater inflater = (LayoutInflater) this.getApplicationContext().getSystemService(this.getApplicationContext().LAYOUT_INFLATER_SERVICE );
        //LayoutInflater inflater = LayoutInflater.from(getActivity().getApplicationContext());
        tabla_informacion = (TableLayout) findViewById(R.id.tabla_info_wl);
        TableRow row_title = (TableRow) inflater.inflate(R.layout.tienda_wishlist_header, null);
        tabla_informacion.addView(row_title);
    }

    public void cargaWishListItems(){
        tiendaDBHelper = new TiendaDBHelper(this);
        Cursor cursor = tiendaDBHelper.getAllItems();
        if ( cursor.getCount() > 0){
            while ( cursor.moveToNext() ){
                final int item_id = cursor.getInt(cursor.getColumnIndex("id"));
                final String item_name = cursor.getString(cursor.getColumnIndex("name"));
                int item_qty = cursor.getInt(cursor.getColumnIndex("qty"));
                //String item_price = cursor.getFloat(cursor.getColumnIndex("price"));
                Float item_price = cursor.getFloat(cursor.getColumnIndex("price"));
                String final_qty = Integer.toString(item_qty);
                String final_price = Float.toString(item_price);
                LayoutInflater inflater = (LayoutInflater) this.getApplicationContext().getSystemService(this.getApplicationContext().LAYOUT_INFLATER_SERVICE );
                final TableRow row_pos = (TableRow)inflater.inflate(R.layout.tienda_wishlist_item_row, null);
                ((TextView)row_pos.findViewById(R.id.nombre)).setText(item_name);
                ((TextView)row_pos.findViewById(R.id.cantidad)).setText(final_qty);
                ((TextView)row_pos.findViewById(R.id.precio)).setText(final_price);
                tabla_informacion.addView(row_pos);

                Double item_total = tiendaDBHelper.getTotal();
                TextView total_final = (TextView)findViewById(R.id.sum_total);
                total_final.setText(String.valueOf(item_total));

                ImageButton eliminar = (ImageButton)row_pos.findViewById(R.id.eliminar);
                eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(ListaProductosWishList.this);
                        final View view = LayoutInflater.from(ListaProductosWishList.this).inflate(R.layout.tienda_eliminar_dewl, null);

                        TextView deseasEliminar = (TextView)view.findViewById(R.id.eliminar_item);
                        String deseasEliminarItem = "¿Deseas eliminar "+ item_name.toUpperCase() +" de tu WishList?";
                        deseasEliminar.setText(deseasEliminarItem);
                        builder.setView(view);
                        final AlertDialog alertDialog = builder.create();

                        Button eliminar = (Button) view.findViewById(R.id.eliminar);
                        eliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tiendaDBHelper.deleteItem(item_id);
                                tabla_informacion.removeView(row_pos);
                                Double item_total = tiendaDBHelper.getTotal();
                                TextView total_final = (TextView)findViewById(R.id.sum_total);
                                total_final.setText(String.valueOf(item_total));
                                alertDialog.dismiss();
                                Toast.makeText(ListaProductosWishList.this, getResources().getString(R.string.prod_eliminado), Toast.LENGTH_SHORT).show();
                            }
                        });
                        Button cancelar = (Button) view.findViewById(R.id.cancelar);

                        cancelar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });

                        alertDialog.show();

                    }
                });

            }
        }/*else {
            Fragment f1 = new TiendaFragmentProducts();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frame, f1).commit();
        }*/
    }

}
