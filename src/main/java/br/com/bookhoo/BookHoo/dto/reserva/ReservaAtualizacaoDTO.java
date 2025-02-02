package br.com.bookhoo.BookHoo.dto.reserva;

import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.model.StatusReserva;
import br.com.bookhoo.BookHoo.model.Usuario;
import jakarta.validation.Valid;

import java.time.LocalDate;

public record ReservaAtualizacaoDTO(
        @Valid
        Usuario usuario,
        @Valid
        Acomodacao acomodacao,
        LocalDate checkin,
        LocalDate checkout,
        Double valorTotal,
        @Valid
        StatusReserva statusReserva
) {
}
