package br.com.bookhoo.BookHoo.dto.acomodacao;

import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.model.CategoriaAcomodacao;

public record AcomodacaoListagemDTO(Long id, String nome, String descricao, CategoriaAcomodacao categoria, String localizacao, Double precoNoite, Integer capacidade) {
    public AcomodacaoListagemDTO(Acomodacao acomodacao) {
        this(acomodacao.getId(), acomodacao.getNome(), acomodacao.getDescricao(), acomodacao.getCategoria(), acomodacao.getLocalizacao(), acomodacao.getPrecoNoite(), acomodacao.getCapacidade());
    }
}
