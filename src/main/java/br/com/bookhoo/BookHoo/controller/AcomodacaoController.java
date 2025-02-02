package br.com.bookhoo.BookHoo.controller;

import br.com.bookhoo.BookHoo.dto.acomodacao.AcomodacaoCadastroDTO;
import br.com.bookhoo.BookHoo.dto.acomodacao.AcomodacaoListagemDTO;
import br.com.bookhoo.BookHoo.exceptions.AcomodacaoExistenteException;
import br.com.bookhoo.BookHoo.exceptions.CpfExistenteException;
import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.service.AcomodacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("acomodacoes")
public class AcomodacaoController {

    @Autowired
    private AcomodacaoService service;

    @GetMapping
    public List<AcomodacaoListagemDTO> listarTodos() {
        List<Acomodacao> acomodacaos = service.listar();
        return acomodacaos.stream()
                .map(AcomodacaoListagemDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public AcomodacaoListagemDTO buscarPorId(@PathVariable Long id) throws Exception {
        var acomodacao = service.buscarPorId(id).orElseThrow();
        return new AcomodacaoListagemDTO(acomodacao);
    }

    @PostMapping
    public AcomodacaoListagemDTO criar(@RequestBody @Valid AcomodacaoCadastroDTO dados) throws CpfExistenteException, AcomodacaoExistenteException {
        var acomodacao = new Acomodacao(dados);
        service.salvar(acomodacao);
        return new AcomodacaoListagemDTO(acomodacao);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        service.remover(id);
    }
}
