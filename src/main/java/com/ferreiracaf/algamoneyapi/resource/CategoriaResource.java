package com.ferreiracaf.algamoneyapi.resource;

import com.ferreiracaf.algamoneyapi.event.RecursoCriadoEvent;
import com.ferreiracaf.algamoneyapi.model.Categoria;
import com.ferreiracaf.algamoneyapi.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Categoria> listarTodos(){
        return categoriaService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaService.criarCategoria(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarCategoriaPorCodigo(@PathVariable Long codigo){
        Categoria categoria = categoriaService.buscarPorCodigo(codigo);
        if (categoria != null)
            return ResponseEntity.ok(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCategoria(@PathVariable Long codigo){
        categoriaService.removerCategoria(codigo);
    }
}
