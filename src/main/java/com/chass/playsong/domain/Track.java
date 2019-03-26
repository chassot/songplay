package com.chass.playsong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "trackname", nullable = false)
	private String trackname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ALBUM_ID")
	private Album album;
}
