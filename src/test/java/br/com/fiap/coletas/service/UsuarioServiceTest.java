package br.com.fiap.coletas.service;

import br.com.fiap.coletas.dto.UsuarioCadastroDTO;
import br.com.fiap.coletas.dto.UsuarioExibicaoDTO;
import br.com.fiap.coletas.model.Usuario;
import br.com.fiap.coletas.model.UsuarioRole;
import br.com.fiap.coletas.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void salvarUsuario() {
        UsuarioCadastroDTO usuarioCadastroDTO = new UsuarioCadastroDTO(1L,"nameTeste", "name@gmail.com","1234senha", UsuarioRole.ADMIN);
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(new Usuario());
        UsuarioExibicaoDTO usuarioExibicaoDTO = usuarioService.salvarUsuario(usuarioCadastroDTO);
        assertNotNull(usuarioExibicaoDTO);

    }
}