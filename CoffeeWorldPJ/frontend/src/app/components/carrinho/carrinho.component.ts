import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { CarrinhoService, ItemCarrinho } from '../../services/carrinho.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-carrinho',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './carrinho.component.html',
  styleUrl: './carrinho.component.css'
})
export class CarrinhoComponent implements OnInit, OnDestroy {
  itens: ItemCarrinho[] = [];
  total: number = 0;
  private subscription: Subscription = new Subscription();

  constructor(
    private carrinhoService: CarrinhoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.subscription = this.carrinhoService.carrinho$.subscribe(itens => {
      this.itens = itens;
      this.calcularTotal();
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  calcularTotal(): void {
    this.total = this.itens.reduce((acc, item) =>
      acc + (item.produto.preco * item.quantidade), 0
    );
  }

  aumentarQuantidade(item: ItemCarrinho): void {
    this.carrinhoService.adicionarProduto(item.produto);
  }

  diminuirQuantidade(item: ItemCarrinho): void {
    if (item.quantidade > 1) {
      item.quantidade--;
      this.calcularTotal();
    }
  }

  removerItem(produtoId: number | undefined): void {
    if (produtoId === undefined) return;
    this.carrinhoService.removerProduto(produtoId);
  }

  limparCarrinho(): void {
    this.carrinhoService.limparCarrinho();
  }

  finalizarPedido(): void {
    this.router.navigate(['/pedido']);
  }

  voltarParaCarrinho(): void {
    this.router.navigate(['/carrinho']);
  }
}
