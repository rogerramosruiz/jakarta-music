package controllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;

import java.util.ArrayList;
import java.util.List;

import models.Artist;

public class ArtistController {
    private EntityManagerFactory entityManagerFactory;

    public ArtistController(){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public  void addArtist(Artist artist){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            em.persist(artist);
            et.commit();
        }
        catch (Exception ex){
            if(et !=null)
                et.rollback();
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public Artist getArtist(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            return em.find(Artist.class, id);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return null;
    }

    public List<Artist> getArtists(){
        EntityManager em = entityManagerFactory.createEntityManager();
        String strQuery = "SELECT a FROM Artist a";
        TypedQuery<Artist> tq = em.createQuery(strQuery, Artist.class);
        List<Artist> artists = new ArrayList<>();
        try{
            artists = tq.getResultList();
        }
        catch (NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return artists;
    }

    public void updateArtist(Artist art){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Artist artist = em.find(Artist.class, art.getId());
            if(artist != null){
                artist.setNombre(art.getNombre());
                artist.setImageurl(art.getImageurl());
                artist.setSpotify(art.getSpotify());
                artist.setYoutube(art.getSpotify());
                et.commit();
            }
        }
        catch (Exception ex){
            if(et !=null)
                et.rollback();
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public void close(){
        entityManagerFactory.close();
    }
}
