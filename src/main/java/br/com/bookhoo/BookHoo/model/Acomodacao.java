package br.com.bookhoo.BookHoo.model;

import br.com.bookhoo.BookHoo.dto.acomodacao.AcomodacaoAtualizacaoDTO;
import br.com.bookhoo.BookHoo.dto.acomodacao.AcomodacaoCadastroDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Acomodacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private CategoriaAcomodacao categoria;
    @JoinColumn(unique = true, nullable = false)
    private String localizacao;
    private Double precoNoite;
    private Integer capacidade;

    public Acomodacao(@Valid AcomodacaoCadastroDTO dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.categoria = dados.categoriaAcomodacao();
        this.localizacao = dados.localizacao();
        this.precoNoite = dados.precoNoite();
        this.capacidade = dados.capacidade();
    }

    public void atualizarInformacoes(AcomodacaoAtualizacaoDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
        if (dados.localizacao() != null) {
            this.localizacao = dados.localizacao();
        }
        if (!Objects.equals(dados.precoNoite(), precoNoite)) {
            this.precoNoite = dados.precoNoite();
        }
        if (dados.capacidade() != null) {
            this.capacidade = dados.capacidade();
        }
    }
}
