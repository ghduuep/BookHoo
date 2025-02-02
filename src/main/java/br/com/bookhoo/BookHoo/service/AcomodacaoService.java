package br.com.bookhoo.BookHoo.service;

import br.com.bookhoo.BookHoo.exceptions.AcomodacaoExistenteException;
import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.repository.AcomodacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcomodacaoService {

    @Autowired
    private AcomodacaoRepository repository;

    public List<Acomodacao> listar() {
        return repository.findAll();
    }

    public Optional<Acomodacao> buscarPorId(Long id) throws Exception {
        Optional<Acomodacao> acomodacaoExistente = repository.findById(id);
        if (acomodacaoExistente.isEmpty()) {
            throw new Exception("Acomodação não existente.");
        }
        return acomodacaoExistente;
    }

    @Transactional
    public Acomodacao salvar(Acomodacao acomodacao) throws AcomodacaoExistenteException {
        Optional<Acomodacao> acomodacaoExistente = repository.findByLocalizacao(acomodacao.getLocalizacao());
        if (acomodacaoExistente.isPresent()) {
            throw new AcomodacaoExistenteException("Acomodação na mesma localização já existente");
        }
        return repository.save(acomodacao);
    }

    @Transactional
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
