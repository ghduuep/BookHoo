package br.com.bookhoo.BookHoo.dto.acomodacao;

import br.com.bookhoo.BookHoo.model.CategoriaAcomodacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AcomodacaoCadastroDTO(
        @NotBlank
        String nome,
        String descricao,
        @Valid
        CategoriaAcomodacao categoriaAcomodacao,
        String localizacao,
        @NotNull
        Double precoNoite,
        Integer capacidade
) {
}
