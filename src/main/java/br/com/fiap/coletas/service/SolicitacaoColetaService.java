package br.com.fiap.coletas.service;


import br.com.fiap.coletas.dto.SolicitacaoColetaDTO;
import br.com.fiap.coletas.exceptions.SolicitacaoException;
import br.com.fiap.coletas.model.SolicitacaoColeta;
import br.com.fiap.coletas.repository.CooperativaRepository;
import br.com.fiap.coletas.repository.EmpresaRepository;
import br.com.fiap.coletas.repository.SolicitacaoColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoColetaService {

    @Autowired
    private SolicitacaoColetaRepository solicitacaoColetaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CooperativaRepository cooperativaRepository;

    public SolicitacaoColetaDTO criarSolicitacao(SolicitacaoColetaDTO solicitacaoColetaDTO) {
        SolicitacaoColeta solicitacaoColeta = new SolicitacaoColeta();
        BeanUtils.copyProperties(solicitacaoColetaDTO, solicitacaoColeta);
        List<String> erros = new ArrayList<>(); // Criando uma lista para capturar erros
        // Verifica se a empresa existe
        if (!empresaRepository.existsById(solicitacaoColeta.getIdEmpresa())) {
            erros.add("Empresa não encontrada com ID " + solicitacaoColeta.getIdEmpresa());
        }
        // Verifica se a cooperativa existe
        if (!cooperativaRepository.existsById(solicitacaoColeta.getIdCooperativa())) {
            erros.add("Cooperativa não encontrada com ID " + solicitacaoColeta.getIdCooperativa());
        }
        // Se houver erros, lança uma exceção com a lista de erros
        if (!erros.isEmpty()) {
            throw new SolicitacaoException(String.join(", ", erros));
        }
        // Salva a nova solicitação
        SolicitacaoColeta novaSolicitacao = solicitacaoColetaRepository.save(solicitacaoColeta);

        return new SolicitacaoColetaDTO(novaSolicitacao);
    }

    public Page<SolicitacaoColetaDTO> listarSolicitacoes(Pageable paginacao) {
        Page<SolicitacaoColeta> solicitacaoColetas = solicitacaoColetaRepository.findAll(paginacao);
        return solicitacaoColetas.map(SolicitacaoColetaDTO::new);
    }

    public SolicitacaoColetaDTO buscarSolicitacaoColetaPorId(Long id) {
        Optional<SolicitacaoColeta> solicitacaoColetaOptional = solicitacaoColetaRepository.findById(id);
        if (solicitacaoColetaOptional.isPresent()) {
            return new SolicitacaoColetaDTO(solicitacaoColetaOptional.get());
        } else {
            throw new SolicitacaoException("Solicitação de Coleta inexistente!");
        }
    }

    public void excluirSolicitacao (Long id) {
        Optional<SolicitacaoColeta> solicitacaoOptional = solicitacaoColetaRepository.findById(id);
        if (solicitacaoOptional.isPresent()) {
            solicitacaoColetaRepository.delete(solicitacaoOptional.get());
        } else {
            throw new SolicitacaoException("Solicitação de Coleta não encontrada!");
        }
    }

    public SolicitacaoColetaDTO atualizarSolicitacao(SolicitacaoColeta solicitacaoColeta){
        if (!solicitacaoColetaRepository.existsById(solicitacaoColeta.getIdSolicitacao())) {
            throw new SolicitacaoException("Solicitação não encontrada com ID " + solicitacaoColeta.getIdSolicitacao());
        }
        SolicitacaoColeta solicitacaoAtualizada = solicitacaoColetaRepository.save(solicitacaoColeta);
        return new SolicitacaoColetaDTO(solicitacaoAtualizada);
    }

}
