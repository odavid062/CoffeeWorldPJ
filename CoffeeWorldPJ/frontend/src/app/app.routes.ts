import { Routes } from '@angular/router';
import { CardapioComponent } from './components/cardapio/cardapio.component';
import { CarrinhoComponent } from './components/carrinho/carrinho.component';
import { PedidoComponent } from './components/pedido/pedido.component';
import { ProdutoComponent } from './components/produto/produto.component';
import { DetalhesPedidoComponent } from './components/pedido/detalhes/detalhes.component';
import { CozinhaComponent } from './components/cozinha/cozinha.component';

export const routes: Routes = [
    { path: '', redirectTo: 'cardapio', pathMatch: 'full' },
    { path: 'cardapio', component: CardapioComponent },
    { path: 'produto/:id', component: ProdutoComponent },
    { path: 'carrinho', component: CarrinhoComponent },
    { path: 'pedido', component: PedidoComponent },
    { path: 'pedido/:id', component: DetalhesPedidoComponent },
    { path: 'cozinha', component: CozinhaComponent }
  ];
