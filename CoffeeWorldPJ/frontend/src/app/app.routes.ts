import { Routes } from '@angular/router';
import { CardapioComponent } from './components/cardapio/cardapio.component';
import { CarrinhoComponent } from './components/carrinho/carrinho.component';
import { PedidoComponent } from './components/pedido/pedido.component';
import { ProdutoComponent } from './components/produto/produto.component';
import { DetalhesPedidoComponent } from './components/pedido/detalhes/detalhes.component';
import { CozinhaComponent } from './components/cozinha/cozinha.component';
import { LoginComponent } from './components/login/login.component';
import { authGuard, cozinhaGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'cardapio', pathMatch: 'full' },
  { path: 'login',          component: LoginComponent },
  { path: 'cardapio',       component: CardapioComponent },
  { path: 'produto/:id',    component: ProdutoComponent },
  { path: 'carrinho',       component: CarrinhoComponent },
  // Pedido exige autenticação
  { path: 'pedido',         component: PedidoComponent,         canActivate: [authGuard] },
  { path: 'pedido/:id',     component: DetalhesPedidoComponent, canActivate: [authGuard] },
  // Cozinha exige role COZINHEIRO ou ADMIN
  { path: 'cozinha',        component: CozinhaComponent,        canActivate: [cozinhaGuard] },
  { path: '**', redirectTo: 'cardapio' },
];
