package br.com.bookhoo.BookHoo.dto.usuario;

import br.com.bookhoo.BookHoo.model.Usuario;

public record UsuarioListagemDTO (Long id, String nome, String cpf, String email, String telefone){
    public UsuarioListagemDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getTelefone());
    }
}
