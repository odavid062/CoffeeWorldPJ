export interface Produto {
  id?: number;
  nome: string;
  preco: number;
  descricao: string;
  imagemUrl: string;
  tempoPreparoMinutos: number;
  categoria: 'bebida-quente' | 'bebida-gelada' | 'lanche' | 'sobremesa';
}
