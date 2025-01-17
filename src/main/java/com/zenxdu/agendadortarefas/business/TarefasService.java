package com.zenxdu.agendadortarefas.business;

import com.zenxdu.agendadortarefas.business.dto.TarefasDTO;
import com.zenxdu.agendadortarefas.business.mapper.TarefasConverter;
import com.zenxdu.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.zenxdu.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.zenxdu.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.zenxdu.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas(String token, TarefasDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial,dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token){

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }



}
