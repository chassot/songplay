package com.chass.playsong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 
 * @author Fábio Chassot
 * 
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
