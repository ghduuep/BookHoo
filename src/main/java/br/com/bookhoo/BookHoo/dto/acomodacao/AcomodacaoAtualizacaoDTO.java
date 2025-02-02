package br.com.bookhoo.BookHoo.dto.acomodacao;

import br.com.bookhoo.BookHoo.model.CategoriaAcomodacao;

public record AcomodacaoAtualizacaoDTO(
        String nome,
        String descricao,
        CategoriaAcomodacao categoria,
        String localizacao,
        Double precoNoite,
        Integer capacidade) {
}
