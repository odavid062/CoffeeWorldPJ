import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Avaliacao } from '../models/avaliacao.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AvaliacaoService {

  private apiUrl = `${environment.apiUrl}/api/avaliacoes`;

  constructor(private http: HttpClient) { }

    criarAvaliacao(avaliacao: Avaliacao): Observable<Avaliacao> {
      return this.http.post<Avaliacao>(this.apiUrl, avaliacao);
    }

    listarPedidos(): Observable<Avaliacao[]> {
      return this.http.get<Avaliacao[]>(this.apiUrl);
    }

    buscarAvaliacao(pedidoId: number): Observable<Avaliacao> {
      return this.http.get<Avaliacao>(`${this.apiUrl}/pedido/${pedidoId}`);
    }

    avaliarPedido(pedidoId: number, avaliacao: Avaliacao): Observable<Avaliacao> {
      return this.http.post<Avaliacao>(`${this.apiUrl}/pedido/${pedidoId}`, avaliacao);
    }

}
