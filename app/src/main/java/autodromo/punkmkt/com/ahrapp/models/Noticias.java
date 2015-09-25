package autodromo.punkmkt.com.ahrapp.models;

import android.content.Intent;

/**
 * Created by sebastianmendezgiron on 19/09/15.
 */
public class Noticias {

    private Integer id;
    private String titulo;
    private String subtitulo;
    private String descripcion_corta;
    private String descripcion;
    private String imgen_contenido;
    private String imagen_portada_cuadrada;
    private String imagen_portada_rectangular;
    private String categoria;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion_corta() {
        return descripcion_corta;
    }

    public void setDescripcion_corta(String descripcion_corta) {
        this.descripcion_corta = descripcion_corta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImgen_contenido() {
        return imgen_contenido;
    }

    public void setImgen_contenido(String imgen_contenido) {
        this.imgen_contenido = imgen_contenido;
    }

    public String getImagen_portada_cuadrada() {
        return imagen_portada_cuadrada;
    }

    public void setImagen_portada_cuadrada(String imagen_portada_cuadrada) {
        this.imagen_portada_cuadrada = imagen_portada_cuadrada;
    }

    public String getImagen_portada_rectangular() {
        return imagen_portada_rectangular;
    }

    public void setImagen_portada_rectangular(String imagen_portada_rectangular) {
        this.imagen_portada_rectangular = imagen_portada_rectangular;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
