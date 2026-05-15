import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

type Modo = 'login' | 'registro';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  modo: Modo = 'login';

  email = '';
  senha = '';
  nome = '';

  carregando = false;
  erro = '';

  private returnUrl = '/cardapio';

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/cardapio';
  }

  alternarModo(): void {
    this.modo = this.modo === 'login' ? 'registro' : 'login';
    this.erro = '';
  }

  enviar(): void {
    this.erro = '';
    this.carregando = true;

    const obs = this.modo === 'login'
      ? this.authService.login({ email: this.email, senha: this.senha })
      : this.authService.registrar({ nome: this.nome, email: this.email, senha: this.senha });

    obs.subscribe({
      next: () => {
        this.carregando = false;
        this.router.navigateByUrl(this.returnUrl);
      },
      error: (err) => {
        this.carregando = false;
        this.erro = err?.error?.message || 'Falha ao autenticar. Tente novamente.';
      },
    });
  }
}
