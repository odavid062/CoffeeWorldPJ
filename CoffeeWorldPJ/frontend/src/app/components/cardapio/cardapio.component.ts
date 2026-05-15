import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProdutoService } from '../../services/produto.service';
import { CarrinhoService } from '../../services/carrinho.service';
import { AuthService } from '../../services/auth.service';
import { Produto } from '../../models/produto.model';
import { AuthResponse } from '../../models/auth.model';
import { Observable } from 'rxjs';

interface Categoria {
  id: string;
  titulo: string;
  key: string;
}

@Component({
  selector: 'app-cardapio',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './cardapio.component.html',
  styleUrls: ['./cardapio.component.css'],
})
export class CardapioComponent implements OnInit {
  produtos: Produto[] = [];
  mensagemSucesso: string = '';
  termoPesquisa: string = '';

  usuario$: Observable<AuthResponse | null>;

  categorias: Categoria[] = [
    { id: 'quentes',    titulo: 'Bebidas Quentes', key: 'bebida-quente' },
    { id: 'geladas',    titulo: 'Bebidas Geladas', key: 'bebida-gelada' },
    { id: 'lanches',    titulo: 'Lanches',         key: 'lanche'        },
    { id: 'sobremesas', titulo: 'Sobremesas',      key: 'sobremesa'     },
  ];

  constructor(
    private produtoService: ProdutoService,
    private carrinhoService: CarrinhoService,
    public authService: AuthService,
  ) {
    this.usuario$ = this.authService.usuario$;
  }

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.listarProdutos().subscribe({
      next: (produtos) => (this.produtos = produtos),
      error: (error) => console.error('Erro ao carregar produtos:', error),
    });
  }

  filtrar(event: Event): void {
    this.termoPesquisa = (event.target as HTMLInputElement).value.toLowerCase();
  }

  produtosPorCategoria(key: string): Produto[] {
    return this.produtos.filter(
      (p) =>
        p.categoria === key &&
        (this.termoPesquisa === '' ||
          p.nome.toLowerCase().includes(this.termoPesquisa) ||
          p.descricao?.toLowerCase().includes(this.termoPesquisa))
    );
  }

  adicionarAoCarrinho(produto: Produto): void {
    this.carrinhoService.adicionarProduto(produto);
    this.mensagemSucesso = `${produto.nome} adicionado ao carrinho!`;
    setTimeout(() => (this.mensagemSucesso = ''), 3000);
  }

  logout(): void {
    this.authService.logout();
  }
}
