import { Produto } from './produto.model';

export interface ItemPedido {
    id?: number;
    produtoId: number;
    produto?: Produto;
    quantidade: number;
}
