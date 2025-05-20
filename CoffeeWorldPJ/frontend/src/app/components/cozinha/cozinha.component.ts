import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PedidoService } from '../../services/pedido.service';
import { ProdutoService } from '../../services/produto.service';
import { Pedido } from '../../models/pedido.model';
import { Produto } from '../../models/produto.model';
import { Subscription, interval } from 'rxjs';

@Component({
  selector: 'app-cozinha',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cozinha.component.html',
  styleUrl: './cozinha.component.css'
})
export class CozinhaComponent implements OnInit, OnDestroy {
  pedidosPendentes: Pedido[] = [];
  pedidosEmPreparo: Pedido[] = [];
  pedidosFinalizados: Pedido[] = [];
  produtos: { [key: number]: Produto } = {};
  private subscription: Subscription = new Subscription();

  constructor(
    private pedidoService: PedidoService,
    private produtoService: ProdutoService
  ) {}

  ngOnInit(): void {
    this.carregarPedidos();
    this.subscription = interval(30000).subscribe(() => {
      this.carregarPedidos();
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private carregarPedidos(): void {
    this.pedidoService.listarPorStatus('PENDENTE').subscribe({
      next: (pedidos) => {
        this.pedidosPendentes = pedidos;
        this.carregarProdutos(pedidos);
      }
    });

    this.pedidoService.listarPorStatus('EM_PREPARO').subscribe({
      next: (pedidos) => {
        this.pedidosEmPreparo = pedidos;
        this.carregarProdutos(pedidos);
      }
    });

    this.pedidoService.listarPorStatus('FINALIZADO').subscribe({
      next: (pedidos) => {
        this.pedidosFinalizados = pedidos;
        this.carregarProdutos(pedidos);
      }
    });
  }

  private carregarProdutos(pedidos: Pedido[]): void {
    pedidos.forEach(pedido => {
      pedido.itens.forEach(item => {
        if (!this.produtos[item.produtoId]) {
          this.produtoService.buscarPorId(item.produtoId).subscribe({
            next: (produto) => {
              this.produtos[item.produtoId] = produto;
            }
          });
        }
      });
    });
  }

  iniciarPreparo(pedido: Pedido): void {
    if (pedido.id) {
      this.pedidoService.atualizarStatus(pedido.id, 'EM_PREPARO').subscribe({
        next: () => {
          this.carregarPedidos();
        },
        error: (error) => {
          console.error('Erro ao iniciar preparo:', error);
        }
      });
    }
  }

  finalizarPedido(pedido: Pedido): void {
    if (pedido.id) {
      this.pedidoService.atualizarStatus(pedido.id, 'FINALIZADO').subscribe({
        next: () => {
          this.carregarPedidos();
        },
        error: (error) => {
          console.error('Erro ao finalizar pedido:', error);
        }
      });
    }
  }

  calcularTempoPreparo(pedido: Pedido): number {
    return pedido.itens.reduce((total, item) => {
      const produto = this.produtos[item.produtoId];
      return total + (produto?.tempoPreparoMinutos || 0) * item.quantidade;
    }, 0);
  }
}
