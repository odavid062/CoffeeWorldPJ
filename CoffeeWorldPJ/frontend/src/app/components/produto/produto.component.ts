import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ProdutoService } from '../../services/produto.service';
import { CarrinhoService } from '../../services/carrinho.service';
import { Produto } from '../../models/produto.model';

@Component({
  selector: 'app-produto',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './produto.component.html',
  styleUrl: './produto.component.css'
})
export class ProdutoComponent implements OnInit {
  produto: Produto | null = null;
  quantidade: number = 1;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private produtoService: ProdutoService,
    private carrinhoService: CarrinhoService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.produtoService.buscarPorId(Number(id)).subscribe({
        next: (produto) => {
          this.produto = produto;
        },
        error: (error) => {
          console.error('Erro ao carregar produto:', error);
          this.router.navigate(['/cardapio']);
        }
      });
    }
  }

  adicionarAoCarrinho(): void {
    if (this.produto) {
      for (let i = 0; i < this.quantidade; i++) {
        this.carrinhoService.adicionarProduto(this.produto);
      }
      this.router.navigate(['/carrinho']);
    }
  }

  aumentarQuantidade(): void {
    this.quantidade++;
  }

  diminuirQuantidade(): void {
    if (this.quantidade > 1) {
      this.quantidade--;
    }
  }
}
