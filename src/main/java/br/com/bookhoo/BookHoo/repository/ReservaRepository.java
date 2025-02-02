package br.com.bookhoo.BookHoo.repository;

import br.com.bookhoo.BookHoo.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioCpf(String cpf);
}
