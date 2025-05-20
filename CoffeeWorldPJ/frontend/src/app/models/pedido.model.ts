import { ItemPedido } from './item-pedido.model';
import { Avaliacao } from './avaliacao.model';

export interface Pedido {
    id?: number;
    cpfCliente: string;
    valorTotal: number;
    formaPagamento: string;
    dataHoraPedido: string;
    previsaoEntrega?: string;
    status: 'PENDENTE' | 'EM_PREPARO' | 'FINALIZADO' | 'CANCELADO';
    itens: ItemPedido[];
    avaliacao?: Avaliacao;
    motivoCancelamento?: string;
}
