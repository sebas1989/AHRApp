package autodromo.punkmkt.com.ahrapp.models;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
/**
 * Created by germanpunk on 20/09/15.
 */
public class Piloto {
    private Integer id;
    private String nombre;
    private String foto;
    private String numero;
    private String equipo;
    private String nacionalidad;
    private String fecha_nacimiento;
    private String campeonatos;
    private String grands_prix;
    private String podiums;
    public Piloto(){

    }

    public Piloto(int id, String nombre, String foto, String numero, String equipo, String nacionalidad, String fecha_nacimiento, String campeonatos, String grands_prix, String podiums) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.numero = numero;
        this.equipo = equipo;
        this.nacionalidad = nacionalidad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.campeonatos = campeonatos;
        this.grands_prix = grands_prix;
        this.podiums = podiums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(String campeonatos) {
        this.campeonatos = campeonatos;
    }

    public String getGrands_prix() {
        return grands_prix;
    }

    public void setGrands_prix(String grands_prix) {
        this.grands_prix = grands_prix;
    }

    public String getPodiums() {
        return podiums;
    }

    public void setPodiums(String podiums) {
        this.podiums = podiums;
    }

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(String campeonatos) {
        this.campeonatos = campeonatos;
    }*/

}

