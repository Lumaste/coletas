package br.com.fiap.coletas.controller;


import br.com.fiap.coletas.dto.CooperativaExibicaoCadastroDTO;
import br.com.fiap.coletas.model.Cooperativa;
import br.com.fiap.coletas.service.CooperativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CooperativaController {

    @Autowired
    private CooperativaService cooperativaService;

    @PostMapping("/cadastro-cooperativa")
    @ResponseStatus(HttpStatus.CREATED)
    public CooperativaExibicaoCadastroDTO cadastrar(@RequestBody @Valid CooperativaExibicaoCadastroDTO cooperativaExibicaoCadastroDTO) {
        return cooperativaService.cadastrarCooperativa(cooperativaExibicaoCadastroDTO);
    }

    @GetMapping("/cooperativas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<CooperativaExibicaoCadastroDTO>> listarCooperativas(Pageable paginacao) {
        Page<CooperativaExibicaoCadastroDTO> cooperativaExibicaoCadastroDTOS = cooperativaService.listarCooperativas(paginacao);
        return ResponseEntity.ok(cooperativaExibicaoCadastroDTOS);
    }

    @GetMapping("/cooperativas/{cooperativaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CooperativaExibicaoCadastroDTO> localizarCooperativaPorId (@PathVariable Long cooperativaId) {
        return ResponseEntity.ok(cooperativaService.buscarCooperativaPorId(cooperativaId));
    }

    @DeleteMapping("/cooperativas/{cooperativaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCooperativa (@PathVariable Long cooperativaId) {
        cooperativaService.excluirCooperativa(cooperativaId);
    }

    @PutMapping ("/cooperativas")
    @ResponseStatus(HttpStatus.OK)
    public CooperativaExibicaoCadastroDTO atualizarCooperativa(@RequestBody Cooperativa cooperativa) {
        return cooperativaService.atualizarCooperativa(cooperativa);
    }

}
