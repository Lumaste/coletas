package br.com.fiap.coletas.controller;

import br.com.fiap.coletas.dto.EmpresaExibicaoDTO;
import br.com.fiap.coletas.dto.SolicitacaoColetaDTO;
import br.com.fiap.coletas.model.Empresa;
import br.com.fiap.coletas.model.SolicitacaoColeta;
import br.com.fiap.coletas.service.SolicitacaoColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SolicitacaoColetaController {

    @Autowired
    private SolicitacaoColetaService solicitacaoColetaService;

    @PostMapping("/cadastro-solicitacao")
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitacaoColetaDTO criarSolicitacao (@RequestBody SolicitacaoColetaDTO solicitacaoColetaDTO) {
        return solicitacaoColetaService.criarSolicitacao(solicitacaoColetaDTO);
    }

    @GetMapping("/solicitacoes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<SolicitacaoColetaDTO>> listarTodasSolicitacoes(Pageable paginacao) {
        Page<SolicitacaoColetaDTO> solicitacaoColetaDTOS = solicitacaoColetaService.listarSolicitacoes(paginacao);
        return ResponseEntity.ok(solicitacaoColetaDTOS);
    }

    @GetMapping("/solicitacoes/{solicitacaoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SolicitacaoColetaDTO> localizarSolicitacaoPorId (@PathVariable Long solicitacaoId) {
        return ResponseEntity.ok(solicitacaoColetaService.buscarSolicitacaoColetaPorId(solicitacaoId));
    }

    @DeleteMapping("/solicitacoes/{solicitacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirSolicitacao (@PathVariable Long solicitacaoId) {
        solicitacaoColetaService.excluirSolicitacao(solicitacaoId);
    }

    @PutMapping("/solicitacoes")
    @ResponseStatus(HttpStatus.OK)
    public SolicitacaoColetaDTO atualiSolicitacao (@RequestBody SolicitacaoColeta solicitacaoColeta) {
        return solicitacaoColetaService.atualizarSolicitacao(solicitacaoColeta);
    }
}
