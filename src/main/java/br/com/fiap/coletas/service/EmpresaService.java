package br.com.fiap.coletas.service;

import br.com.fiap.coletas.dto.EmpresaExibicaoDTO;
import br.com.fiap.coletas.exceptions.EmpresaException;
import br.com.fiap.coletas.model.Empresa;
import br.com.fiap.coletas.repository.EmpresaRepository;
import br.com.fiap.coletas.repository.SolicitacaoColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private SolicitacaoColetaRepository solicitacaoColetaRepository;

    public EmpresaExibicaoDTO cadastrarEmpresa(EmpresaExibicaoDTO empresaExibicaoDTO) {
        Empresa empresa = new Empresa();
        BeanUtils.copyProperties(empresaExibicaoDTO, empresa);
        Optional<Empresa> empresaExistente = empresaRepository.findByNomeEmpresa(empresa.getNomeEmpresa());
        if (empresaExistente.isPresent()) {
            throw new EmpresaException("Já existe uma empresa com o nome " + empresa.getNomeEmpresa());
        }
        return new EmpresaExibicaoDTO(empresaRepository.save(empresa));
    }

    public Page<EmpresaExibicaoDTO> listarEmpresas(Pageable paginacao) {
        Page<Empresa> empresas = empresaRepository.findAll(paginacao);
        return empresas.map(EmpresaExibicaoDTO::new);
    }

    public EmpresaExibicaoDTO buscarEmpresaPorId(Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return new EmpresaExibicaoDTO(empresaOptional.get());
        } else {
            throw new EmpresaException("Empresa não existe na base de dados!");
        }
    }

    public void excluir (Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            empresaRepository.delete(empresaOptional.get());
        } else {
            throw new EmpresaException("Empresa não encontrada!");
        }
    }


    public EmpresaExibicaoDTO atualizar(Empresa empresa){
        //Optional<Empresa> empresaOptional = empresaRepository.findById(empresa.getIdEmpresa());
        if (!empresaRepository.existsById(empresa.getIdEmpresa())) {
            throw new EmpresaException("Empresa não encontrada com ID " + empresa.getIdEmpresa());
        }
        Empresa empresaAtualizada = empresaRepository.save(empresa);
        return new EmpresaExibicaoDTO(empresaAtualizada);
    }
}
