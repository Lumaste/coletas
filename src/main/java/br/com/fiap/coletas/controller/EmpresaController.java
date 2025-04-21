package br.com.fiap.coletas.controller;


import br.com.fiap.coletas.dto.EmpresaExibicaoDTO;
import br.com.fiap.coletas.model.Empresa;
import br.com.fiap.coletas.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaExibicaoDTO cadastrar(@RequestBody @Valid EmpresaExibicaoDTO empresaExibicaoDTO) {
        return empresaService.cadastrarEmpresa(empresaExibicaoDTO);
    }

    @GetMapping("/empresas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<EmpresaExibicaoDTO>> listarTodas(Pageable paginacao) {
        Page<EmpresaExibicaoDTO> empresasDTO = empresaService.listarEmpresas(paginacao);
        return ResponseEntity.ok(empresasDTO);
    }

    @GetMapping("/empresas/{empresaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmpresaExibicaoDTO> localizarEmpresaPorId (@PathVariable Long empresaId) {
        return ResponseEntity.ok(empresaService.buscarEmpresaPorId(empresaId));
    }

    @DeleteMapping("/empresas/{empresaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirEmpresa (@PathVariable Long empresaId) {
        empresaService.excluir(empresaId);
    }

    @PutMapping ("/empresas")
    @ResponseStatus(HttpStatus.OK)
    public EmpresaExibicaoDTO  atualizarEmpresa (@RequestBody Empresa empresa) {
        return empresaService.atualizar(empresa);
    }
}
