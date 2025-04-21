package br.com.fiap.coletas.service;

import br.com.fiap.coletas.dto.CooperativaExibicaoCadastroDTO;
import br.com.fiap.coletas.exceptions.CooperativaException;
import br.com.fiap.coletas.exceptions.EmpresaException;
import br.com.fiap.coletas.model.Cooperativa;
import br.com.fiap.coletas.model.Empresa;
import br.com.fiap.coletas.repository.CooperativaRepository;
import br.com.fiap.coletas.repository.SolicitacaoColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CooperativaService {

    @Autowired
    private CooperativaRepository cooperativaRepository;

    public CooperativaExibicaoCadastroDTO cadastrarCooperativa(CooperativaExibicaoCadastroDTO cooperativaExibicaoCadastroDTO) {
        Cooperativa cooperativa = new Cooperativa();
        BeanUtils.copyProperties(cooperativaExibicaoCadastroDTO, cooperativa);
        Optional<Cooperativa> cooperativaExistente = cooperativaRepository.findByNomeCooperativa(cooperativa.getNomeCooperativa());
        if (cooperativaExistente.isPresent()) {
            throw new CooperativaException("Já existe uma cooperativa com o nome " + cooperativa.getNomeCooperativa());
        }
//        Optional<Cooperativa> cooperativaExistente = cooperativaRepository.findById(cooperativa.getIdCooperativa());
//        if (cooperativaExistente.isPresent()) {
//            throw new CooperativaException("Cooperativa com o ID " + cooperativa.getIdCooperativa() + " já está cadastrada.");
//        }
        return new CooperativaExibicaoCadastroDTO(cooperativaRepository.save(cooperativa));
    }

    public Page<CooperativaExibicaoCadastroDTO> listarCooperativas(Pageable paginacao) {
        Page<Cooperativa> cooperativas = cooperativaRepository.findAll(paginacao);
        return cooperativas.map(CooperativaExibicaoCadastroDTO::new);
    }

    public CooperativaExibicaoCadastroDTO buscarCooperativaPorId(Long id) {
        Optional<Cooperativa> cooperativaOptional = cooperativaRepository.findById(id);
        if (cooperativaOptional.isPresent()) {
            return new CooperativaExibicaoCadastroDTO(cooperativaOptional.get());
        } else {
            throw new CooperativaException("Cooperativa não existe!");
        }
    }

    public void excluirCooperativa (Long id) {
        Optional<Cooperativa> cooperativaOptional = cooperativaRepository.findById(id);
        if (cooperativaOptional.isPresent()) {
            cooperativaRepository.delete(cooperativaOptional.get());
        } else {
            throw new CooperativaException("Cooperativa não encontrada!");
        }
    }

    public CooperativaExibicaoCadastroDTO atualizarCooperativa(Cooperativa cooperativa){
        //Optional<Empresa> empresaOptional = empresaRepository.findById(empresa.getIdEmpresa());
        if (!cooperativaRepository.existsById(cooperativa.getIdCooperativa())) {
            throw new CooperativaException("Cooperativa não encontrada com ID " + cooperativa.getIdCooperativa());
        }
        Cooperativa cooperativaAtualizada = cooperativaRepository.save(cooperativa);
        return new CooperativaExibicaoCadastroDTO(cooperativaAtualizada);
    }


}
