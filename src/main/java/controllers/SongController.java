package controllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;
import models.Album;
import models.Song;

import java.util.ArrayList;
import java.util.List;

public class SongController {

    private EntityManagerFactory entityManagerFactory;

    public SongController(){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public void addSong(Song song){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            em.persist(song);
            et.commit();
        }
        catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
    public Song getSong(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            return em.find(Song.class, id);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return  null;
    }

    public List<Song> getSongs(){
        EntityManager em = entityManagerFactory.createEntityManager();
        String strQuery = "SELECT s FROM Song s";
        TypedQuery<Song> tq = em.createQuery(strQuery, Song.class);
        List<Song> songs = new ArrayList<>();
        try{
            songs = tq.getResultList();
        }
        catch (NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return songs;
    }
    
    public List<Song> getSongs(Album album){
        EntityManager em = entityManagerFactory.createEntityManager();
        String strQuery = "SELECT s FROM Song s where s.album = :album";
        TypedQuery<Song> tq = em.createQuery(strQuery, Song.class);
        tq.setParameter("album", album);
        List<Song> songs = new ArrayList<>();
        try{
            songs = tq.getResultList();
        }
        catch (NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return songs;
    }

    public void updateSong(int id, String nombre, int estrellas){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Song song = em.find(Song.class, id);
            if(song != null){
                song.setNombre(nombre);
                song.setEstrellas(estrellas);
                em.merge(song);
            }
            et.commit();
        }
        catch (Exception ex){
            if(et !=null)
                et.rollback();
            ex.printStackTrace();
        }
    }
    public void deleteSong(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            Song song = em.find(Song.class, id);
            if(song != null){
                et.begin();
                em.remove(song);
                et.commit();
            }
        }
        catch (Exception ex){
            if(et !=null)
                et.rollback();
            ex.printStackTrace();
        }
    }
    public void close(){
        entityManagerFactory.close();
    }
}
