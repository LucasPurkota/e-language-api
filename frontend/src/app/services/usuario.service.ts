import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioCreateRequest, UsuarioUpdateRequest, UsuarioResponse, UsuarioListResponse } from '../models/usuario.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = `${environment.apiUrl}/usuarios`;
  
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    withCredentials: true
  };

  constructor(private http: HttpClient) {}

  create(usuario: UsuarioCreateRequest): Observable<UsuarioResponse> {
    return this.http.post<UsuarioResponse>(this.apiUrl, usuario, this.httpOptions);
  }

  update(id: string, usuario: UsuarioUpdateRequest): Observable<UsuarioResponse> {
    return this.http.put<UsuarioResponse>(`${this.apiUrl}/${id}`, usuario, this.httpOptions);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, this.httpOptions);
  }

  getById(id: string): Observable<UsuarioResponse> {
    return this.http.get<UsuarioResponse>(`${this.apiUrl}/${id}`, { withCredentials: true });
  }

  getAll(): Observable<UsuarioListResponse[]> {
    return this.http.get<UsuarioListResponse[]>(this.apiUrl, { withCredentials: true });
  }
}
