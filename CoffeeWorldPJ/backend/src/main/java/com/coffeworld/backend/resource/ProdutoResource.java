package com.coffeworld.backend.resource;

import com.coffeworld.backend.dto.ProdutoDTO;
import com.coffeworld.backend.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
@Tag(name = "Produtos", description = "Gerenciamento dos produtos disponíveis no cardápio")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Listar todos os produtos do cardápio")
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @Operation(summary = "Buscar produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId( @Parameter(description = "ID do produto") @PathVariable(name = "id") Long id) {
        ProdutoDTO produto = produtoService.buscarPorId(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Criar novo produto")
    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.salvar(produtoDTO));
    }

    @Operation(summary = "Atualizar produto existente")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable(name = "id") Long id, @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO atualizado = produtoService.atualizar(id, produtoDTO);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(name = "id") Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
