package com.chass.playsong.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * 
 * @author Fábio Chassot
 * 
 */
@Entity
public class Track {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "trackname", nullable = false)
	private String trackname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ALBUM_ID")
	private Album album;

	public Track() {
		super();
	}

	public Track(Long id, String trackname, Album album) {
		super();
		this.id = id;
		this.trackname = trackname;
		this.album = album;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrackname() {
		return trackname;
	}

	public void setTrackname(String trackname) {
		this.trackname = trackname;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}
