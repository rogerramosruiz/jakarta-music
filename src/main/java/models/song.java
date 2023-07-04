package models;

import jakarta.persistence.*;

@Entity
@Table(name = "song")

public class song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    private String nombre;
    private int estrellas;
    private int id_album;

    public song(){}
    public song(String nombre, int estrellas, int id_album) {
        this.nombre = nombre;
        this.estrellas = estrellas;
        this.id_album = id_album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getId_album() {
        return id_album;
    }

    public void setId_album(int id_album) {
        this.id_album = id_album;
    }
}
