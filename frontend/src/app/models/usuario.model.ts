export interface UsuarioCreateRequest {
  nome: string;
  cpf: string;
  email: string;
  senha: string;
}

export interface UsuarioUpdateRequest {
  nome?: string;
  cpf?: string;
  email?: string;
  senha?: string;
}

export interface UsuarioResponse {
  usuarioId: string;
  nome: string;
  email: string;
  criadoEm: string | null;
  perfilPrincipal?: string;
  status?: string;
}

export interface UsuarioListResponse {
  usuarioId: string;
  nome: string;
  email: string;
  criadoEm: string | null;
  perfilPrincipal?: string;
  status?: string;
}