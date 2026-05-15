export interface LoginRequest {
  email: string;
  senha: string;
}

export interface RegisterRequest {
  nome: string;
  email: string;
  senha: string;
}

export interface AuthResponse {
  token: string;
  nome: string;
  email: string;
  role: 'CLIENTE' | 'COZINHEIRO' | 'ADMIN';
}
