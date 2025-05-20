import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { PedidoService } from '../../../services/pedido.service';
import { AvaliacaoService } from '../../../services/avaliacao.service';
import { ProdutoService } from '../../../services/produto.service';
import { Pedido } from '../../../models/pedido.model';
import { Avaliacao } from '../../../models/avaliacao.model';
import { Produto } from '../../../models/produto.model';

@Component({
  selector: 'app-detalhes-pedido',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './detalhes.component.html',
  styleUrl: './detalhes.component.css',
})
export class DetalhesPedidoComponent implements OnInit {
  pedido: Pedido | null = null;
  produtos: { [key: number]: Produto } = {};
  avaliacao: Avaliacao = {
    nota: 5,
    comentario: '',
  };
  motivoCancelamento: string = '';
  mostrarFormularioAvaliacao: boolean = false;
  mostrarFormularioCancelamento: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private pedidoService: PedidoService,
    private avaliacaoService: AvaliacaoService,
    private produtoService: ProdutoService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.pedidoService.buscarPorId(Number(id)).subscribe({
        next: (pedido) => {
          this.pedido = pedido;
          if (pedido.avaliacao) {
            this.avaliacao = pedido.avaliacao;
          }
          this.carregarProdutos(pedido);
        },
        error: (error) => {
          console.error('Erro ao carregar pedido:', error);
          this.router.navigate(['/pedidos']);
        },
      });
    }
  }

  private carregarProdutos(pedido: Pedido): void {
    pedido.itens.forEach((item) => {
      this.produtoService.buscarPorId(item.produtoId).subscribe({
        next: (produto) => {
          this.produtos[item.produtoId] = produto;
        },
        error: (error) => {
          console.error('Erro ao carregar produto:', error);
        },
      });
    });
  }

  avaliarPedido(): void {
    if (this.pedido?.id) {
      this.avaliacaoService
        .avaliarPedido(this.pedido.id, this.avaliacao)
        .subscribe({
          next: () => {
            this.mostrarFormularioAvaliacao = false;
            if (this.pedido) {
              this.pedido.avaliacao = this.avaliacao;
            }
          },
          error: (error) => {
            console.error('Erro ao avaliar pedido:', error);
          },
        });
    }
  }

  cancelarPedido(): void {
    if (this.pedido?.id && this.motivoCancelamento) {
      this.pedidoService
        .atualizarStatus(this.pedido.id, 'CANCELADO')
        .subscribe({
          next: () => {
            this.mostrarFormularioCancelamento = false;
            if (this.pedido) {
              this.pedido.status = 'CANCELADO';
              this.pedido.motivoCancelamento = this.motivoCancelamento;
            }
          },
          error: (error) => {
            console.error('Erro ao cancelar pedido:', error);
          },
        });
    }
  }

  podeAvaliar(): boolean {
    return this.pedido?.status === 'FINALIZADO' && !this.pedido.avaliacao;
  }

  podeCancelar(): boolean {
    return (
      this.pedido?.status === 'PENDENTE' || this.pedido?.status === 'EM_PREPARO'
    );
  }
}
