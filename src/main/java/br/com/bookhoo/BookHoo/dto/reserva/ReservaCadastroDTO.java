package br.com.bookhoo.BookHoo.dto.reserva;

import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.model.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservaCadastroDTO(
        @Valid
        @NotNull
        Usuario usuario,
        @Valid
        @NotNull
        Acomodacao acomodacao,
        LocalDate checkin,
        LocalDate checkout
) {
}
