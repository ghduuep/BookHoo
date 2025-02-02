package br.com.bookhoo.BookHoo.service;

import br.com.bookhoo.BookHoo.exceptions.ReservaAtivaException;
import br.com.bookhoo.BookHoo.exceptions.ReservaNaoEncontradaException;
import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.model.Reserva;
import br.com.bookhoo.BookHoo.model.StatusReserva;
import br.com.bookhoo.BookHoo.repository.AcomodacaoRepository;
import br.com.bookhoo.BookHoo.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Optional<Reserva> buscarPorId(Long id) throws ReservaNaoEncontradaException {
        Optional<Reserva> reservaExistente = repository.findById(id);
        if (reservaExistente.isEmpty()) {
            throw new ReservaNaoEncontradaException("Reserva não existente.");
        }
        return reservaExistente;
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
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
