package com.kulikov.clothing_store.dto.mappers;

import com.kulikov.clothing_store.dto.request.WorkingProcessRequestDto;
import com.kulikov.clothing_store.dto.response.WorkingProcessResponseDto;
import com.kulikov.clothing_store.models.WorkingProcess;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkingProcessMapper {
    WorkingProcess toEntity(WorkingProcessRequestDto dto);
    List<WorkingProcess> toEntityList(List<WorkingProcessRequestDto> dtoList);

    WorkingProcessResponseDto toResponse(WorkingProcess process);
    List<WorkingProcessResponseDto> toResponseList(WorkingProcess processList);
}
