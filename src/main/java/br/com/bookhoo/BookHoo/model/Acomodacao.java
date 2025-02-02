package br.com.bookhoo.BookHoo.model;

import br.com.bookhoo.BookHoo.dto.acomodacao.AcomodacaoCadastroDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
