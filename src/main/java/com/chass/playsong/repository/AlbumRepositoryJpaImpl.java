package com.chass.playsong.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chass.playsong.domain.Album;
/**
 * 
 * @author Fábio Chassot
 * 
 */
@Service("albumRepositoryJpa")
@Transactional
public class AlbumRepositoryJpaImpl implements AlbumRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveAlbum(Album album) {
        entityManager.persist(album);
    }

    @Override
    public void updateAlbum(Album album) {
        entityManager.merge(album);
    }

    @Override
    public void deleteAlbumById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public Album findById(Long id) {
        return entityManager.find(Album.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Album> findByName(String name) {
        return entityManager.createQuery("SELECT t FROM Album t WHERE t.name LIKE :name")
                .setParameter("name", "%" + name + "%").getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Album> findAllAlbums() {
        return entityManager.createQuery("SELECT t FROM Album t ORDER BY t.name ASC")
                .getResultList();
    }
}
