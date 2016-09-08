package autodromo.punkmkt.com.ahrapp.models;

/**
 * Created by sebastianmendezgiron on 03/10/15.
 */
public class Zona {

    public Integer id;
    public String name;
    public String nameCompleto;
    public String descripcion;
    public String mapa;

    public Zona(int id, String name, String nameCompleto, String descripcion, String mapa) {
        this.id = id;
        this.name = name;
        this.nameCompleto = nameCompleto;
        this.descripcion = descripcion;
        this.mapa = mapa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCompleto() {
        return nameCompleto;
    }

    public void setNameCompleto(String nameCompleto) {
        this.nameCompleto = nameCompleto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }
}
