package com.example.reactive.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reactive.dto.OrientationDataDto;
import com.example.reactive.repository.OrientationDataRepository;
import com.example.reactive.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrientationService {
	
	@Autowired
	private OrientationDataRepository repository;
	
	public Flux<OrientationDataDto> getOrientations(){
		return repository.findAll().map(AppUtils::entityToDto);
	}
	
	public Mono<OrientationDataDto> getOrientation(String id){
		return repository.findById(id).map(AppUtils::entityToDto);
	}
	
	public Mono<OrientationDataDto> saveOrientation(Mono<OrientationDataDto> orientationDtoMono){
		return orientationDtoMono.map(AppUtils::dtoToEntity).flatMap(repository::insert).map(AppUtils::entityToDto);
	}
	
	public Mono<Void> deleteOrientation(String id){
		return repository.deleteById(id);
	}
	
	public Flux<OrientationDataDto> getOrientationByDevice(String device){
		return repository.getOrientationByDevice(device);
	}
	
	public Mono<Double> getAverage(String device, String dimension){
		Mono<Double> result = Mono.empty();
		switch(dimension.charAt(0)) {
			case 'x':
				result= getAverageX(device);
				break;
			case 'y':
				result= getAverageY(device);
				break;
			case 'z':
				result= getAverageZ(device);
				break;
		}
		return result;
	}
	
	private Mono<Double> getAverageX(String device){
		return repository.getOrientationByDevice(device).collect(Collectors.averagingInt(OrientationDataDto::getX));
	}
	
	private Mono<Double> getAverageY(String device){
		return repository.getOrientationByDevice(device).collect(Collectors.averagingInt(OrientationDataDto::getY));
	}
	
	private Mono<Double> getAverageZ(String device){
		return repository.getOrientationByDevice(device).collect(Collectors.averagingInt(OrientationDataDto::getZ));
	}
}
