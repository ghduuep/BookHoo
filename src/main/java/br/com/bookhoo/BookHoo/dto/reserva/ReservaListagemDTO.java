package br.com.bookhoo.BookHoo.dto.reserva;

import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.model.Reserva;
import br.com.bookhoo.BookHoo.model.StatusReserva;
import br.com.bookhoo.BookHoo.model.Usuario;

import java.time.LocalDate;

public record ReservaListagemDTO(Usuario usuario, Acomodacao acomodacao, LocalDate checkin, LocalDate checkout, Double valorTotal, StatusReserva status) {
    public ReservaListagemDTO(Reserva reserva) {
        this(reserva.getUsuario(), reserva.getAcomodacao(), reserva.getDataCheckIn(), reserva.getDataCheckOut(), reserva.getValorTotal(), reserva.getStatus());
    }
}
