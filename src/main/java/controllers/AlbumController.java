package controllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;

import models.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    private EntityManagerFactory entityManagerFactory;

    AlbumController(){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public void addAlbum(Album album){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            em.persist(album);
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
    public Album getAlbum(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            return em.find(Album.class, id);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return  null;
    }

    public List<Album> getAlbums(){
        EntityManager em = entityManagerFactory.createEntityManager();
        String strQuery = "SELECT a FROM Album a";
        TypedQuery<Album> tq = em.createQuery(strQuery, Album.class);
        List<Album> albums = new ArrayList<>();
        try{
            albums = tq.getResultList();
        }
        catch (NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return albums;
    }
    public void updateAlbum(Album alb){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Album album = em.find(Album.class, alb.getId());
            if(album != null){
                album.setNombre(alb.getNombre());
                alb.setArtist(alb.getArtist());
                em.merge(album);
            }
            et.commit();
        }
        catch (Exception ex){
            if(et !=null)
                et.rollback();
            ex.printStackTrace();
        }
    }
    public void deleteAlbum(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            Album album = em.find(Album.class, id);
            if(album != null){
                et.begin();
                em.remove(album);
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
