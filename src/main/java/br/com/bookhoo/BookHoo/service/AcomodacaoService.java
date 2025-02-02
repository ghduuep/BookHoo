package br.com.bookhoo.BookHoo.service;

import br.com.bookhoo.BookHoo.dto.acomodacao.AcomodacaoAtualizacaoDTO;
import br.com.bookhoo.BookHoo.exceptions.AcomodacaoExistenteException;
import br.com.bookhoo.BookHoo.exceptions.AcomodacaoNaoEncontradaException;
import br.com.bookhoo.BookHoo.exceptions.UsuarioNaoEncontradoException;
import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.model.Usuario;
import br.com.bookhoo.BookHoo.repository.AcomodacaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    public Acomodacao buscarPorId(Long id) throws AcomodacaoNaoEncontradaException {
        return repository.findById(id).orElseThrow(() -> new AcomodacaoNaoEncontradaException("Acomodação não encontrada."));
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
    public Acomodacao atualizar(Long id, AcomodacaoAtualizacaoDTO dados) throws AcomodacaoNaoEncontradaException {
        Acomodacao acomodacao = repository.findById(id).orElseThrow(() -> new AcomodacaoNaoEncontradaException("Acomodação não encontrado."));
        acomodacao.atualizarInformacoes(dados);
        return acomodacao;
    }

    @Transactional
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
