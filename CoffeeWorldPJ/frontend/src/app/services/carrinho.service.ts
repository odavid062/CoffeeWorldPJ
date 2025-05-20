// src/app/shared/services/carrinho.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Produto } from '../models/produto.model';

export interface ItemCarrinho {
  produto: Produto;
  quantidade: number;
}

@Injectable({ providedIn: 'root' })
export class CarrinhoService {
  private carrinhoSubject = new BehaviorSubject<ItemCarrinho[]>(this.carregarCarrinho());
  carrinho$ = this.carrinhoSubject.asObservable();

  private carregarCarrinho(): ItemCarrinho[] {
    const carrinhoSalvo = localStorage.getItem('carrinho');
    return carrinhoSalvo ? JSON.parse(carrinhoSalvo) : [];
  }

  private salvarCarrinho(carrinho: ItemCarrinho[]): void {
    localStorage.setItem('carrinho', JSON.stringify(carrinho));
  }

  adicionarProduto(produto: Produto) {
    console.log('Adicionando produto ao carrinho:', produto);
    const atual = this.carrinhoSubject.getValue();
    const index = atual.findIndex(item => item.produto.id === produto.id);

    if (index > -1) {
      atual[index].quantidade += 1;
    } else {
      atual.push({ produto, quantidade: 1 });
    }

    console.log('Carrinho atualizado:', atual);
    this.carrinhoSubject.next([...atual]);
    this.salvarCarrinho(atual);
  }

  limparCarrinho() {
    this.carrinhoSubject.next([]);
    this.salvarCarrinho([]);
  }

  removerProduto(produtoId: number) {
    const atual = this.carrinhoSubject.getValue().filter(p => p.produto.id !== produtoId);
    this.carrinhoSubject.next([...atual]);
    this.salvarCarrinho(atual);
  }

  getItens(): ItemCarrinho[] {
    return this.carrinhoSubject.getValue();
  }
}
