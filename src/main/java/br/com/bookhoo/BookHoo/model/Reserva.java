package br.com.bookhoo.BookHoo.model;

import br.com.bookhoo.BookHoo.dto.reserva.ReservaCadastroDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "acomodacao_id", nullable = false)
    private Acomodacao acomodacao;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private Double valorTotal;
    private StatusReserva status;

    public Reserva(@Valid ReservaCadastroDTO dados) {
        this.usuario = dados.usuario();
        this.acomodacao = dados.acomodacao();
        this.dataCheckIn = dados.checkin();
        this.dataCheckOut = dados.checkout();
    }
}
