package com.example.reactive.utils;


import org.springframework.beans.BeanUtils;

import com.example.reactive.dto.OrientationDataDto;
import com.example.reactive.entity.OrientationData;


public class AppUtils {

	public static OrientationDataDto entityToDto(OrientationData orientation) {
		OrientationDataDto orientationDto = new OrientationDataDto();
		BeanUtils.copyProperties(orientation, orientationDto);
		return orientationDto;
	}
	
	public static OrientationData dtoToEntity(OrientationDataDto orientationDto) {
		OrientationData orientation = new OrientationData();
		BeanUtils.copyProperties(orientationDto, orientation);
		return orientation;
	}
	

}
