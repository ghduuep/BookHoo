package br.com.bookhoo.BookHoo.controller;

import br.com.bookhoo.BookHoo.dto.reserva.ReservaCadastroDTO;
import br.com.bookhoo.BookHoo.dto.reserva.ReservaListagemDTO;
import br.com.bookhoo.BookHoo.exceptions.ReservaAtivaException;
import br.com.bookhoo.BookHoo.exceptions.ReservaNaoEncontradaException;
import br.com.bookhoo.BookHoo.model.Reserva;
import br.com.bookhoo.BookHoo.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public List<ReservaListagemDTO> listarTodos() {
        List<Reserva> reservas = service.listar();
        return reservas.stream()
                .map(ReservaListagemDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ReservaListagemDTO buscarPorId(@PathVariable Long id) throws ReservaNaoEncontradaException {
        var reserva = service.buscarPorId(id).orElseThrow();
        return new ReservaListagemDTO(reserva);
    }

    @PostMapping
    public ReservaListagemDTO criar(@RequestBody @Valid ReservaCadastroDTO dados) throws ReservaAtivaException, Exception {
        var reserva = new Reserva(dados);
        service.salvar(reserva);
        return new ReservaListagemDTO(reserva);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        service.remover(id);
    }
}
