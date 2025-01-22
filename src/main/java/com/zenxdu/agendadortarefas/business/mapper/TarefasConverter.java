package com.zenxdu.agendadortarefas.business.mapper;

import com.zenxdu.agendadortarefas.business.dto.TarefasDTO;
import com.zenxdu.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {



    // O MapStruct irá mapear automaticamente os campos com nomes e tipos iguais
    TarefasEntity paraTarefaEntity(TarefasDTO dto);


    TarefasDTO paraTarefaDTO(TarefasEntity entity);

    // Para listas, o MapStruct também consegue mapear automaticamente os objetos dentro da lista
    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);


}
