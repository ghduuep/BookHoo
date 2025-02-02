package br.com.bookhoo.BookHoo.controller;

import br.com.bookhoo.BookHoo.dto.usuario.UsuarioAtualizacaoDTO;
import br.com.bookhoo.BookHoo.dto.usuario.UsuarioCadastroDTO;
import br.com.bookhoo.BookHoo.dto.usuario.UsuarioListagemDTO;
import br.com.bookhoo.BookHoo.exceptions.CpfExistenteException;
import br.com.bookhoo.BookHoo.exceptions.UsuarioNaoEncontradoException;
import br.com.bookhoo.BookHoo.model.Usuario;
import br.com.bookhoo.BookHoo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioListagemDTO>> listarTodos() {
        List<Usuario> usuarios = service.listar();
        var dtos=  usuarios.stream()
                .map(UsuarioListagemDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListagemDTO> buscarPorId(@PathVariable Long id) throws UsuarioNaoEncontradoException {
        var usuario = service.buscarPorId(id);
        var dto = new UsuarioListagemDTO(usuario);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioListagemDTO> criar(@RequestBody @Valid UsuarioCadastroDTO dados) throws CpfExistenteException {
        var usuario = new Usuario(dados);
        service.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioListagemDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioListagemDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioAtualizacaoDTO dados) throws UsuarioNaoEncontradoException {
        try {
            var usuarioAtualizado = service.atualizar(id, dados);
            return ResponseEntity.ok(new UsuarioListagemDTO(usuarioAtualizado));
        } catch(UsuarioNaoEncontradoException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
