package com.apirest.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.demo.document.Playlist;
import com.apirest.demo.repository.PlaylistRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaylistServicesImpl implements PlaylistService{

	@Autowired
	PlaylistRepository repository;
	
	@Override
	public Flux<Playlist> findAll() {
		return repository.findAll();		
	}

	@Override
	public Mono<Playlist> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Playlist> save(Playlist playlist) {
		return repository.save(playlist);
	}

}
