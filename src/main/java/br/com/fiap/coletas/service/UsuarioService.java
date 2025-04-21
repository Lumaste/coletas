package br.com.fiap.coletas.service;

import br.com.fiap.coletas.dto.UsuarioCadastroDTO;
import br.com.fiap.coletas.dto.UsuarioExibicaoDTO;
import br.com.fiap.coletas.model.Usuario;
import br.com.fiap.coletas.model.UsuarioRole;
import br.com.fiap.coletas.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario (UsuarioCadastroDTO usuarioCadastroDTO) {

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
//        Essa linha deu erro. Tive que criar os Getters e Setters no na classe Usuario
        return new UsuarioExibicaoDTO(usuarioSalvo.getUsuarioId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail(), usuarioSalvo.getRole());
    }
}
