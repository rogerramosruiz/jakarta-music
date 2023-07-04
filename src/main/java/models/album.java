package models;

import jakarta.persistence.*;

@Entity
@Table(name = "album")
public class album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    private String nombre;

    private int id_artist;

    public album(){}

    public album(String nombre, int id_artist) {
        this.nombre = nombre;
        this.id_artist = id_artist;
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

    public int getId_artist() {
        return id_artist;
    }

    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }
}
