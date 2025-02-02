package br.com.bookhoo.BookHoo.service;

import br.com.bookhoo.BookHoo.dto.usuario.UsuarioAtualizacaoDTO;
import br.com.bookhoo.BookHoo.exceptions.CpfExistenteException;
import br.com.bookhoo.BookHoo.exceptions.UsuarioNaoEncontradoException;
import br.com.bookhoo.BookHoo.model.Usuario;
import br.com.bookhoo.BookHoo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) throws UsuarioNaoEncontradoException {
        return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado."));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) throws CpfExistenteException {
        Optional<Usuario> usuarioExistente = repository.findByCpf(usuario.getCpf());
        if (usuarioExistente.isPresent()) {
            throw new CpfExistenteException("Já existe um usuario com esse cpf.");
        }
        return repository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, UsuarioAtualizacaoDTO dados) throws UsuarioNaoEncontradoException {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));
        usuario.atualizarInformacoes(dados);
        return usuario;
    }
    @Transactional
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
