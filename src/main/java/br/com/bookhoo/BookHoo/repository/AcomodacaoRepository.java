package br.com.bookhoo.BookHoo.repository;

import br.com.bookhoo.BookHoo.model.Acomodacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcomodacaoRepository extends JpaRepository<Acomodacao, Long> {
    Optional<Acomodacao> findByLocalizacao(String localizacao);
}
