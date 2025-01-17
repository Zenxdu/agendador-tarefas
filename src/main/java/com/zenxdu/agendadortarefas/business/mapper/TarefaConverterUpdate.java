package com.zenxdu.agendadortarefas.business.mapper;

import com.zenxdu.agendadortarefas.business.dto.TarefasDTO;
import com.zenxdu.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
public class TarefaConverterUpdate {

    public void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity) {
        if (dto.getNomeTarefa() != null) {
            entity.setNomeTarefa(dto.getNomeTarefa());
        }
        if (dto.getDescricao() != null) {
            entity.setDescricao(dto.getDescricao());
        }
        if (dto.getDataCriacao() != null) {
            entity.setDataCriacao(dto.getDataCriacao());
        }
        if (dto.getDataEvento() != null) {
            entity.setDataEvento(dto.getDataEvento());
        }
        if (dto.getEmailUsuario() != null) {
            entity.setEmailUsuario(dto.getEmailUsuario());
        }
        if (dto.getDataAlteracao() != null) {
            entity.setDataAlteracao(dto.getDataAlteracao());
        }
        if (dto.getStatusNotificacaoEnum() != null) {
            entity.setStatusNotificacaoEnum(dto.getStatusNotificacaoEnum());
        }
    }
}
