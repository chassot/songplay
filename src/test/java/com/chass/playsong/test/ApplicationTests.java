package com.chass.playsong.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chass.playsong.repository.AlbumRepository;
import com.chass.playsong.repository.TrackRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private AlbumRepository albumService;
	
	@Autowired
	private TrackRepository trackService;
	
	@Test
	public void album_service_list_all(){
		Assert.assertEquals(3, albumService.findAllAlbums().size());
	}
	
	@Test
	public void track_service_list_all(){
		Assert.assertEquals(8, trackService.findAllTracks().size());
	}
	
	@Test
	public void track_service_list_track_with_album(){
		Assert.assertEquals("Arame Farpado",trackService.findByName("Serra Do Mar").get(0).getAlbum().getAuthor());
	}
	
}
