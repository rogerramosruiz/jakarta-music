package models;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;

@Entity
@Table(name = "song")

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    private String nombre;
    private int estrellas;
    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    public Song(){}
    public Song(String nombre, int estrellas, Album album) {
        this.nombre = nombre;
        this.estrellas = estrellas;
        this.album = album;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
