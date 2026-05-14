export const environment = {
  production: false,
  // Em desenvolvimento local aponta direto para o backend
  // Em produção (Docker) o nginx faz proxy de /api/* para o backend
  apiUrl: ''
};
