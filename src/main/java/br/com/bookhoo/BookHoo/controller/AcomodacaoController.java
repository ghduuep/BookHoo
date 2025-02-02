package br.com.bookhoo.BookHoo.controller;

import br.com.bookhoo.BookHoo.dto.acomodacao.AcomodacaoCadastroDTO;
import br.com.bookhoo.BookHoo.dto.acomodacao.AcomodacaoListagemDTO;
import br.com.bookhoo.BookHoo.exceptions.AcomodacaoExistenteException;
import br.com.bookhoo.BookHoo.exceptions.CpfExistenteException;
import br.com.bookhoo.BookHoo.model.Acomodacao;
import br.com.bookhoo.BookHoo.service.AcomodacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("acomodacoes")
public class AcomodacaoController {

    @Autowired
    private AcomodacaoService service;

    @GetMapping
    public ResponseEntity<List<AcomodacaoListagemDTO>> listarTodos() {
        List<Acomodacao> acomodacaos = service.listar();
        List<AcomodacaoListagemDTO> acomodacoesDTO = acomodacaos.stream()
                .map(AcomodacaoListagemDTO::new)
                .toList();
        return ResponseEntity.ok(acomodacoesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcomodacaoListagemDTO> buscarPorId(@PathVariable Long id) throws Exception {
        var acomodacao = service.buscarPorId(id).orElseThrow();
        var dto = new AcomodacaoListagemDTO(acomodacao);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AcomodacaoListagemDTO> criar(@RequestBody @Valid AcomodacaoCadastroDTO dados) throws CpfExistenteException, AcomodacaoExistenteException {
        var acomodacao = new Acomodacao(dados);
        service.salvar(acomodacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AcomodacaoListagemDTO(acomodacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
