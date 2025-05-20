import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CarrinhoService } from '../../services/carrinho.service';
import { PedidoService } from '../../services/pedido.service';
import { ProdutoService } from '../../services/produto.service';
import { Pedido } from '../../models/pedido.model';
import { ItemPedido } from '../../models/item-pedido.model';
import { Produto } from '../../models/produto.model';

@Component({
  selector: 'app-pedido',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './pedido.component.html',
  styleUrl: './pedido.component.css'
})
export class PedidoComponent implements OnInit {
  pedido: Pedido = {
    cpfCliente: '',
    formaPagamento: '',
    valorTotal: 0,
    status: 'PENDENTE',
    dataHoraPedido: new Date().toISOString(),
    itens: []
  };

  itensComProduto: Array<ItemPedido & { produto: Produto }> = [];

  formasPagamento = [
    { label: 'PIX', value: 'PIX' },
    { label: 'Cartão de Crédito', value: 'CARTAO_CREDITO' },
    { label: 'Cartão de Débito', value: 'CARTAO_DEBITO' },
    { label: 'Dinheiro', value: 'DINHEIRO' }
  ];

  constructor(
    private carrinhoService: CarrinhoService,
    private pedidoService: PedidoService,
    private produtoService: ProdutoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const itensCarrinho = this.carrinhoService.getItens();
    this.pedido.itens = itensCarrinho.map(item => ({
      produtoId: item.produto.id!,
      quantidade: item.quantidade
    }));

    // Carregar informações dos produtos
    this.itensComProduto = itensCarrinho.map(item => ({
      produtoId: item.produto.id!,
      quantidade: item.quantidade,
      produto: item.produto
    }));

    this.pedido.valorTotal = itensCarrinho.reduce((acc, item) =>
      acc + (item.produto.preco * item.quantidade), 0
    );
  }

  finalizarPedido(): void {
    if (!this.pedido.cpfCliente || !this.pedido.formaPagamento) {
      alert('Por favor, preencha todos os campos obrigatórios.');
      return;
    }

    this.pedidoService.criarPedido(this.pedido).subscribe({
      next: (pedidoCriado) => {
        this.carrinhoService.limparCarrinho();
        this.router.navigate(['/pedido', pedidoCriado.id]);
      },
      error: (error) => {
        console.error('Erro ao finalizar pedido:', error);
        alert('Erro ao finalizar pedido. Tente novamente.');
      }
    });
  }
}
