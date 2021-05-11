package com.example.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactive.dto.OrientationDataDto;
import com.example.reactive.service.OrientationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orientations")
public class OrientationController {

	@Autowired
	private OrientationService service;

	@GetMapping
	public Flux<OrientationDataDto> getOrientations(){
		return service.getOrientations();
	}
	
	@GetMapping("/{id}")
	public Mono<OrientationDataDto> getOrientation(@PathVariable String id){
		return service.getOrientation(id);
	}
	
	@GetMapping("/average")
	public Mono<Double> getAverage(@RequestParam("device") String device, @RequestParam("dimension") String dimension){		
		return service.getAverage(device, dimension);
	}
	
	@PostMapping
	public Mono<OrientationDataDto> saveOrientation(@RequestBody Mono<OrientationDataDto> orientationDtoMono){
		return service.saveOrientation(orientationDtoMono);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> deleteOrientation(@PathVariable String id){
		return service.deleteOrientation(id);
	}
	
	@GetMapping("/device")
	public Flux<OrientationDataDto> getOrientationByDevice(@RequestParam("device") String device){
		return service.getOrientationByDevice(device);
	}
	
}
