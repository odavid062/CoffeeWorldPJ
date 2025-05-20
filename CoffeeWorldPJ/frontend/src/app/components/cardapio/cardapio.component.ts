import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProdutoService } from '../../services/produto.service';
import { CarrinhoService } from '../../services/carrinho.service';
import { Produto } from '../../models/produto.model';

@Component({
  selector: 'app-cardapio',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './cardapio.component.html',
  styleUrls: ['./cardapio.component.css'], // Corrigido: styleUrls em vez de styleUrl
})
export class CardapioComponent implements OnInit {
  produtos: Produto[] = [];
  mensagemSucesso: string = '';

  constructor(
    private produtoService: ProdutoService,
    private carrinhoService: CarrinhoService
  ) {}

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.listarProdutos().subscribe({
      next: (produtos) => {
        this.produtos = produtos;
      },
      error: (error) => {
        console.error('Erro ao carregar produtos:', error);
      },
    });
  }

  adicionarAoCarrinho(produto: Produto): void {
    this.carrinhoService.adicionarProduto(produto);
    this.mensagemSucesso = `${produto.nome} adicionado ao carrinho!`;
    setTimeout(() => (this.mensagemSucesso = ''), 3000);
  }

  get bebidasQuentes(): Produto[] {
    return this.produtos.filter((p) => p.categoria === 'bebida-quente');
  }

  get bebidasGeladas(): Produto[] {
    return this.produtos.filter((p) => p.categoria === 'bebida-gelada');
  }

  get lanches(): Produto[] {
    return this.produtos.filter((p) => p.categoria === 'lanche');
  }

  get sobremesas(): Produto[] {
    return this.produtos.filter((p) => p.categoria === 'sobremesa');
  }
}
