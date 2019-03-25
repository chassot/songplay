package com.chass.playsong.repository;

import java.util.List;

import com.chass.playsong.domain.Album;

/**
 * 
 * @author Fábio Chassot
 * 
 */
public interface AlbumRepository {

    void saveAlbum(Album album);

    void updateAlbum(Album album);

    void deleteAlbumById(Long id);

    Album findById(Long id);

    List<Album> findByName(String name);

    List<Album> findAllAlbums();

}
