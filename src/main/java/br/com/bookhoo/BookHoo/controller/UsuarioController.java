package br.com.bookhoo.BookHoo.controller;

import br.com.bookhoo.BookHoo.dto.usuario.UsuarioCadastroDTO;
import br.com.bookhoo.BookHoo.dto.usuario.UsuarioListagemDTO;
import br.com.bookhoo.BookHoo.exceptions.CpfExistenteException;
import br.com.bookhoo.BookHoo.model.Usuario;
import br.com.bookhoo.BookHoo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioListagemDTO> listarTodos() {
        List<Usuario> usuarios = service.listar();
        return usuarios.stream()
                .map(UsuarioListagemDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioListagemDTO buscarPorId(@PathVariable Long id) throws Exception {
        var usuario = service.buscarPorId(id).orElseThrow();
        return new UsuarioListagemDTO(usuario);
    }

    @PostMapping
    public UsuarioListagemDTO criar(@RequestBody @Valid UsuarioCadastroDTO dados) throws CpfExistenteException {
        var usuario = new Usuario(dados);
        service.salvar(usuario);
        return new UsuarioListagemDTO(usuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        service.remover(id);
    }
}
