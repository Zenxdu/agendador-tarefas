package com.zenxdu.agendadortarefas.business;

import com.zenxdu.agendadortarefas.business.dto.TarefasDTO;
import com.zenxdu.agendadortarefas.business.mapper.TarefaConverterUpdate;
import com.zenxdu.agendadortarefas.business.mapper.TarefasConverter;
import com.zenxdu.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.zenxdu.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.zenxdu.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
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
    private final TarefaConverterUpdate tarefaConverterUpdate;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrad " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao altera status da tarefa " + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try {
            // Busca a tarefa pelo ID
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));

            // Atualiza os dados da entidade com o DTO
            tarefaConverterUpdate.updateTarefas(dto, entity);  // Usando o método updateTarefas da interface TarefasConverter

            // Salva a tarefa atualizada no banco
            TarefasEntity updatedEntity = tarefasRepository.save(entity);

            // Converte a entidade salva para DTO e retorna
            return tarefaConverter.paraTarefaDTO(updatedEntity);

        } catch (ResourceNotFoundException e) {
            // Exceção de recurso não encontrado
            throw new ResourceNotFoundException("Erro ao alterar a tarefa: " + e.getMessage());
        } catch (Exception e) {
            // Captura qualquer outro erro inesperado
            throw new RuntimeException("Erro desconhecido ao atualizar a tarefa: " + e.getMessage(), e);
        }
    }



}

