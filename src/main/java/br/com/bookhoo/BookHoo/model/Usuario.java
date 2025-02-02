package br.com.bookhoo.BookHoo.model;

import br.com.bookhoo.BookHoo.dto.usuario.UsuarioAtualizacaoDTO;
import br.com.bookhoo.BookHoo.dto.usuario.UsuarioCadastroDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String cpf;
    private String email;
    private String telefone;

    public Usuario(@Valid UsuarioCadastroDTO dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
    }

    public void atualizarInformacoes(UsuarioAtualizacaoDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
    }
}
