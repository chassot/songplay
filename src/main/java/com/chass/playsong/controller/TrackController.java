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

import com.chass.playsong.domain.Track;
import com.chass.playsong.repository.TrackRepository;

/**
 * 
 * @author Fábio Chassot
 * 
 */
@Controller
public class TrackController {

	@Autowired
	@Resource(name = "trackRepositoryJpa")
	private TrackRepository trackRepository;

	@GetMapping(value = "/track/")
	public ResponseEntity<List<Track>> fetchAllTracks() {
		List<Track> tracks = trackRepository.findAllTracks();
		if (tracks.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tracks, HttpStatus.OK);
	}

	@GetMapping(value = "/track/{trackname}")
	public ResponseEntity<List<Track>> fetchTrackByName(@PathVariable("trackname") String trackname) {
		List<Track> tracks = trackRepository.findByName(trackname);
		if (tracks.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tracks, HttpStatus.OK);
	}

	@PostMapping(value = "/track/")
	public ResponseEntity<Void> createTrack(@RequestBody Track track, UriComponentsBuilder ucBuilder) {
		trackRepository.saveTrack(track);

		// Updates the Grid.
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/track/{id}").buildAndExpand(track.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/track/{id}")
	public ResponseEntity<Track> updateTrack(@PathVariable("id") Long id, @RequestBody Track track) {

		Track currentTrack = trackRepository.findById(id);
		if (currentTrack == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		currentTrack.setTrackname(track.getTrackname());
		currentTrack.setAlbum(track.getAlbum());

		trackRepository.updateTrack(currentTrack);
		return new ResponseEntity<>(currentTrack, HttpStatus.OK);
	}

	@DeleteMapping(value = "/track/{id}")
	public ResponseEntity<Track> deleteTrack(@PathVariable("id") long id) {

		Track track = trackRepository.findById(id);
		if (track == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		trackRepository.deleteTrackById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
