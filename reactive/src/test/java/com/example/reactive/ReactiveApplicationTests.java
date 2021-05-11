package com.example.reactive;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

import com.example.reactive.controller.OrientationController;
import com.example.reactive.dto.OrientationDataDto;
import com.example.reactive.service.OrientationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(OrientationController.class)
class ReactiveApplicationTests {

	@Autowired
	private WebTestClient client;
	@MockBean
	private OrientationService service;
	
	@Test
	public void addOrientationTest() {
		OrientationDataDto orientationDto = new OrientationDataDto();
		orientationDto.setDevice("device2");
		orientationDto.setX(4);
		orientationDto.setY(8);
		orientationDto.setZ(10);
		orientationDto.setId("100");
		Mono<OrientationDataDto> orientationDtoMono = Mono.just(orientationDto);
		when(service.saveOrientation(orientationDtoMono)).thenReturn(orientationDtoMono);
		
		client.post().uri("/orientations")
			.body(Mono.just(orientationDtoMono), OrientationDataDto.class)
				.exchange()
					.expectStatus().isOk();
		
	}
	
	/*
	public Flux<OrientationDataDto> getOrientation(){
		return client.get().uri("/orientations/100").exchange().returnResult(OrientationDataDto.class).getResponseBody();
	}
	
	@Test
	public void multipleGets() {
		
	}
	*/
}
