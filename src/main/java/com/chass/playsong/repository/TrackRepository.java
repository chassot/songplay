package com.chass.playsong.repository;

import java.util.List;

import com.chass.playsong.domain.Track;

/**
 * 
 * @author Fábio Chassot
 * 
 */
public interface TrackRepository {

    void saveTrack(Track track);

    void updateTrack(Track track);

    void deleteTrackById(Long id);

    Track findById(Long id);

    List<Track> findByName(String name);

    List<Track> findAllTracks();

}
