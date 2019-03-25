package com.chass.playsong.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Fábio Chassot
 * 
 */
@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "author", nullable = false)
	private String author;
	@Column(name = "year", nullable = false)
	private String year;
	
	
	//We need to ignore on json transformation to avoid a eternal loop
	@JsonIgnore
	@OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
	private List<Track> tracks;

	public Album() {
		super();
	}

	public Album(Long id, String albumName, String author, String year, List<Track> tracks) {
		super();
		this.id = id;
		this.name = albumName;
		this.author = author;
		this.year = year;
		this.tracks = tracks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

}
