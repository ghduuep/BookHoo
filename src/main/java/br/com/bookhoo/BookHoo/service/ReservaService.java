package br.com.bookhoo.BookHoo.service;

import br.com.bookhoo.BookHoo.dto.reserva.ReservaAtualizacaoDTO;
import br.com.bookhoo.BookHoo.dto.usuario.UsuarioAtualizacaoDTO;
import br.com.bookhoo.BookHoo.exceptions.ReservaAtivaException;
import br.com.bookhoo.BookHoo.exceptions.ReservaNaoEncontradaException;
import br.com.bookhoo.BookHoo.exceptions.UsuarioNaoEncontradoException;
import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.model.Reserva;
import br.com.bookhoo.BookHoo.model.StatusReserva;
import br.com.bookhoo.BookHoo.model.Usuario;
import br.com.bookhoo.BookHoo.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Reserva buscarPorId(Long id) throws ReservaNaoEncontradaException {
        return repository.findById(id).orElseThrow(() -> new ReservaNaoEncontradaException("Reserva não encontrada."));
    }

    private void validarReservaAtivaDoUsuario(Reserva reserva) throws ReservaAtivaException {
        List<Reserva> reservasDoUsuario = repository.findByUsuarioCpf(reserva.getUsuario().getCpf());
        for (Reserva reservaExistente : reservasDoUsuario) {
            if (reservaExistente.getStatus().equals(StatusReserva.CONFIRMADA)) {
                throw new ReservaAtivaException("O usuário já possui uma reserva ativa");
            }
        }
    }

    private void verificarDatas(Reserva reserva) throws Exception {
        if (reserva.getDataCheckIn().isAfter(reserva.getDataCheckOut())) {
            throw new Exception("A data de check-in deve ser anterior a de check-out");
        }
    }

    private Double calcularCusto(Reserva reserva){
        long dias = ChronoUnit.DAYS.between(reserva.getDataCheckIn(), reserva.getDataCheckOut());
        Acomodacao acomodacao = reserva.getAcomodacao();
        return acomodacao.getPrecoNoite() * dias;
    }

    @Transactional
    public Reserva salvar(Reserva reserva) throws ReservaAtivaException, Exception {
        validarReservaAtivaDoUsuario(reserva);
        verificarDatas(reserva);
        reserva.setValorTotal(calcularCusto(reserva));
        reserva.setStatus(StatusReserva.PENDENTE);
        return repository.save(reserva);
    }

    @Transactional
    public Reserva atualizar(Long id, ReservaAtualizacaoDTO dados) throws ReservaNaoEncontradaException {
        Reserva reserva = repository.findById(id).orElseThrow(() -> new ReservaNaoEncontradaException("Reserva não encontrado."));
        reserva.atualizarInformacoes(dados);
        return reserva;
    }

    @Transactional
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
