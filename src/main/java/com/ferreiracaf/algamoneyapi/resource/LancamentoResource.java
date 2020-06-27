package com.ferreiracaf.algamoneyapi.resource;

import com.ferreiracaf.algamoneyapi.event.RecursoCriadoEvent;
import com.ferreiracaf.algamoneyapi.exceptionhandler.AlgaMoneyExceptionHandler;
import com.ferreiracaf.algamoneyapi.model.Lancamento;
import com.ferreiracaf.algamoneyapi.repository.filter.LancamentoFilter;
import com.ferreiracaf.algamoneyapi.service.LancamentoService;
import com.ferreiracaf.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<Page<Lancamento>> listarLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable){
        return ResponseEntity.ok(lancamentoService.listarLancamentos(lancamentoFilter, pageable));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo){
        Lancamento lancamento = lancamentoService.buscarPorCodigo(codigo);
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> cadastrarLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento lancamentoCriado = lancamentoService.cadastrarLancamento(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoCriado.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoCriado);
    }

    @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
        String msgUsr = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String msgDev = ex.toString();
        List <AlgaMoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgaMoneyExceptionHandler.Erro(msgUsr, msgDev));
        return ResponseEntity.badRequest().body(erros);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerLancamento(@PathVariable Long codigo){
        lancamentoService.removerLancamento(codigo);
    }
}
