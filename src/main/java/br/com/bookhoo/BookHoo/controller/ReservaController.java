package br.com.bookhoo.BookHoo.controller;

import br.com.bookhoo.BookHoo.dto.reserva.ReservaAtualizacaoDTO;
import br.com.bookhoo.BookHoo.dto.reserva.ReservaCadastroDTO;
import br.com.bookhoo.BookHoo.dto.reserva.ReservaListagemDTO;
import br.com.bookhoo.BookHoo.dto.usuario.UsuarioAtualizacaoDTO;
import br.com.bookhoo.BookHoo.dto.usuario.UsuarioListagemDTO;
import br.com.bookhoo.BookHoo.exceptions.ReservaAtivaException;
import br.com.bookhoo.BookHoo.exceptions.ReservaNaoEncontradaException;
import br.com.bookhoo.BookHoo.exceptions.UsuarioNaoEncontradoException;
import br.com.bookhoo.BookHoo.model.Reserva;
import br.com.bookhoo.BookHoo.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<List<ReservaListagemDTO>> listarTodos() {
        List<Reserva> reservas = service.listar();
        var dtos =  reservas.stream()
                .map(ReservaListagemDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaListagemDTO> buscarPorId(@PathVariable Long id) throws ReservaNaoEncontradaException {
        var reserva = service.buscarPorId(id);
        var dto = new ReservaListagemDTO(reserva);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ReservaListagemDTO> criar(@RequestBody @Valid ReservaCadastroDTO dados) throws ReservaAtivaException, Exception {
        var reserva = new Reserva(dados);
        service.salvar(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ReservaListagemDTO(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaListagemDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ReservaAtualizacaoDTO dados){
        try {
            var reservaAtualizada = service.atualizar(id, dados);
            return ResponseEntity.ok(new ReservaListagemDTO(reservaAtualizada));
        } catch(ReservaNaoEncontradaException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
