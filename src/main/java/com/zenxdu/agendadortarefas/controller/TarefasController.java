package com.zenxdu.agendadortarefas.controller;

import com.zenxdu.agendadortarefas.business.TarefasService;
import com.zenxdu.agendadortarefas.business.dto.TarefasDTO;
import com.zenxdu.agendadortarefas.business.mapper.TarefasConverter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefas(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal)
    {
            return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial,dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token){
        // ISSO QUE ESTA OU ISSO da na mesma
        // List<TarefasDTO> tarefas = tarefasService.buscaTarefasPorEmail(token);
        // return ResponseEntity.ok(tarefas);
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

}
