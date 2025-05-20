import { Produto } from './produto.model';

export interface ItemCarrinho {
  produto: Produto;
  quantidade: number;
}
