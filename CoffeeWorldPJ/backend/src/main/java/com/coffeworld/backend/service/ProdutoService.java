package com.coffeworld.backend.service;

import com.coffeworld.backend.dto.ProdutoDTO;
import com.coffeworld.backend.mapper.ProdutoMapper;
import com.coffeworld.backend.model.Produto;
import com.coffeworld.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoDTO buscarPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(produtoMapper::toDTO).orElse(null);
    }

    @Transactional(rollbackFor = Throwable.class)
    public ProdutoDTO salvar(ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        Produto salvo = produtoRepository.save(produto);
        return produtoMapper.toDTO(salvo);
    }

    @Transactional(rollbackFor = Throwable.class)
    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produtoAtualizado = produtoMapper.toEntity(produtoDTO);
            produtoAtualizado.setId(id);
            return produtoMapper.toDTO(produtoRepository.save(produtoAtualizado));
        }
        return null;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}
