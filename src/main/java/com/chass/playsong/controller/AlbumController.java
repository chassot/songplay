package com.chass.playsong.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

	@GetMapping(value = "/album/")
	public ResponseEntity<List<Album>> fetchAllAlbums() {
		List<Album> albums = albumRepository.findAllAlbums();
		if (albums.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(albums, HttpStatus.OK);
	}

	@GetMapping(value = "/album/{albumname}")
	public ResponseEntity<List<Album>> fetchAlbumByName(@PathVariable("albumname") String albumname) {
		List<Album> albums = albumRepository.findByName(albumname);
		if (albums.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(albums, HttpStatus.OK);
	}

	@PostMapping(value = "/album/")
	public ResponseEntity<Void> createAlbum(@RequestBody Album album, UriComponentsBuilder ucBuilder) {
		albumRepository.saveAlbum(album);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/album/{id}").buildAndExpand(album.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/album/{id}")
	public ResponseEntity<Album> updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {

		Album currentAlbum = albumRepository.findById(id);
		if (currentAlbum == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		currentAlbum.setName(album.getName());
		currentAlbum.setAuthor(album.getAuthor());
		currentAlbum.setYear(album.getYear());
		albumRepository.updateAlbum(currentAlbum);

		return new ResponseEntity<>(currentAlbum, HttpStatus.OK);
	}

	@DeleteMapping(value = "/album/{id}")
	public ResponseEntity<Album> deleteAlbum(@PathVariable("id") long id) {
		Album album = albumRepository.findById(id);
		if (album == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		albumRepository.deleteAlbumById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
