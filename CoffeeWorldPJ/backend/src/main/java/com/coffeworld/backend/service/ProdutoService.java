package com.coffeworld.backend.service;

import com.coffeworld.backend.dto.ProdutoDTO;
import com.coffeworld.backend.exception.ResourceNotFoundException;
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
        return produtoRepository.findById(id)
                .map(produtoMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public ProdutoDTO salvar(ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        Produto salvo = produtoRepository.save(produto);
        return produtoMapper.toDTO(salvo);
    }

    @Transactional(rollbackFor = Throwable.class)
    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO) {
        produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        Produto produtoAtualizado = produtoMapper.toEntity(produtoDTO);
        produtoAtualizado.setId(id);
        return produtoMapper.toDTO(produtoRepository.save(produtoAtualizado));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}
