package com.apirest.demo.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.demo.document.Playlist;
import com.apirest.demo.services.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class PlaylistController {

	@Autowired
	PlaylistService service;
	
	@GetMapping(value = "/playlist")
	public Flux<Playlist> getPlaylist(){		
		return service.findAll();
	}
	
	@GetMapping(value = "/playlist/{id}")
	public Mono<Playlist> getPlaylistById(@PathVariable String id){		
		return service.findById(id);
	}
	
	@PostMapping(value = "/playlist")
	public Mono<Playlist> save(@RequestBody Playlist playlist){
		return service.save(playlist);
	}
	
	@GetMapping(value = "/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE )
	public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents(){
		
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
		Flux<Playlist> events = service.findAll();

		return Flux.zip(interval, events);			
	}	
}