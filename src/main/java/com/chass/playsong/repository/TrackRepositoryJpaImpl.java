package com.chass.playsong.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chass.playsong.domain.Track;
/**
 * 
 * @author Fábio Chassot
 * 
 */
@Service("trackRepositoryJpa")
@Transactional
public class TrackRepositoryJpaImpl implements TrackRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveTrack(Track track) {
		entityManager.persist(track);
	}

	@Override
	public void updateTrack(Track track) {
		entityManager.merge(track);
	}

	@Override
	public void deleteTrackById(Long id) {
		entityManager.remove(findById(id));
	}

	@Override
	public Track findById(Long id) {
		return entityManager.find(Track.class, id);
	}
	
	
	/**
	 * Here we searching not only for the trackname, but whatever field with any part of the key words. 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Track> findByName(String keys) {
		String[] words = keys.split(" ");
		List<Track> tracksFound = new ArrayList<Track>();
		for (String word : words) {
			tracksFound.addAll(entityManager.createQuery("SELECT t FROM Track t WHERE t.trackname LIKE :trackname or t.trackname.album.name LIKE :trackname OR t.trackname.album.author LIKE :trackname")
					.setParameter("trackname", "%" + word + "%").getResultList());
		}
		//Just to remove duplicate objects
		//Not the best way, but works for now.
		Set<Track> hs = new HashSet<>();
		hs.addAll(tracksFound);
		tracksFound.clear();
		tracksFound.addAll(hs);
		return tracksFound;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Track> findAllTracks() {
		return entityManager
				.createQuery(
						"SELECT t FROM Track t ORDER BY t.trackname ASC").getResultList();
	}

}
