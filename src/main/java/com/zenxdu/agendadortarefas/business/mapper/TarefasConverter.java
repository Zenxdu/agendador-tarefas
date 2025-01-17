package com.zenxdu.agendadortarefas.business.mapper;

import com.zenxdu.agendadortarefas.business.dto.TarefasDTO;
import com.zenxdu.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "nomeTarefa", target = "nomeTarefa")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "emailUsuario", target = "emailUsuario")
    @Mapping(source = "dataAlteracao", target = "dataAlteracao")
    @Mapping(source = "statusNotificacaoEnum", target = "statusNotificacaoEnum")
    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    @Mapping(source = "nomeTarefa", target = "nomeTarefa")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "emailUsuario", target = "emailUsuario")
    @Mapping(source = "dataAlteracao", target = "dataAlteracao")
    @Mapping(source = "statusNotificacaoEnum", target = "statusNotificacaoEnum")
    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
