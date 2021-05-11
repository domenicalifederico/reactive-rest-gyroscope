package com.example.reactive.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.reactive.dto.OrientationDataDto;
import com.example.reactive.entity.OrientationData;

import reactor.core.publisher.Flux;

@Repository
public interface OrientationDataRepository extends ReactiveMongoRepository<OrientationData,String>{

	//Mono<OrientationDataDto> getAverageByDevice(String device);

	@Query("{ 'device': ?0 }")
	Flux<OrientationDataDto> getOrientationByDevice(String device);

}
