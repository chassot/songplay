package com.chass.playsong.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.chass.playsong.domain.Album;
import com.chass.playsong.repository.AlbumRepository;

/**
 * 
 * @author Fábio Chassot
 * 
 */
@Controller
public class AlbumController {

	@Autowired
	@Resource(name = "albumRepositoryJpa")
	private AlbumRepository albumRepository;

	@RequestMapping(value = "/album/", method = RequestMethod.GET)
	public ResponseEntity<List<Album>> fetchAllAlbums() {
		List<Album> albums = albumRepository.findAllAlbums();
		if (albums.isEmpty()) {
			return new ResponseEntity<List<Album>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Album>>(albums, HttpStatus.OK);
	}

	@RequestMapping(value = "/album/{albumname}", method = RequestMethod.GET)
	public ResponseEntity<List<Album>> fetchAlbumByName(@PathVariable("albumname") String albumname) {
		List<Album> albums = albumRepository.findByName(albumname);
		if (albums.isEmpty()) {
			return new ResponseEntity<List<Album>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Album>>(albums, HttpStatus.OK);
	}

	@RequestMapping(value = "/album/", method = RequestMethod.POST)
	public ResponseEntity<Void> createAlbum(@RequestBody Album album, UriComponentsBuilder ucBuilder) {
		albumRepository.saveAlbum(album);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/album/{id}").buildAndExpand(album.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/album/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Album> updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {

		Album currentAlbum = albumRepository.findById(id);
		if (currentAlbum == null) {
			return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
		}

		currentAlbum.setName(album.getName());
		currentAlbum.setAuthor(album.getAuthor());
		currentAlbum.setYear(album.getYear());
		albumRepository.updateAlbum(currentAlbum);

		return new ResponseEntity<Album>(currentAlbum, HttpStatus.OK);
	}

	@RequestMapping(value = "/album/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Album> deleteAlbum(@PathVariable("id") long id) {
		Album album = albumRepository.findById(id);
		if (album == null) {
			return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
		}
		albumRepository.deleteAlbumById(id);
		return new ResponseEntity<Album>(HttpStatus.NO_CONTENT);
	}
}
