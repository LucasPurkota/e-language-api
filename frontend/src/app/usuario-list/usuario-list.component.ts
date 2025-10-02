import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';
import { UsuarioListResponse } from '../models/usuario.model';

@Component({
  selector: 'app-usuario-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './usuario-list.component.html',
  styles: [`
    .container {
      max-width: 1000px;
      margin: 0 auto;
      padding: 20px;
    }
    .users-table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    .users-table th, .users-table td {
      padding: 10px;
      border: 1px solid #ddd;
      text-align: left;
    }
    .users-table th {
      background-color: #f2f2f2;
    }
    .actions {
      margin: 20px 0;
      display: flex;
      gap: 10px;
    }
    .btn-create, .btn-refresh {
      padding: 10px 15px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .btn-refresh {
      background-color: #2196F3;
    }
    .btn-delete {
      padding: 5px 10px;
      background-color: #f44336;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .loading {
      padding: 20px;
      text-align: center;
    }
    .error-message {
      padding: 10px;
      background-color: #ffebee;
      color: #c62828;
      border: 1px solid #c62828;
      border-radius: 4px;
      margin-bottom: 20px;
    }
    .no-data {
      padding: 20px;
      text-align: center;
      color: #757575;
    }
  `]
})
export class UsuarioListComponent implements OnInit {
  usuarios: UsuarioListResponse[] = [];
  loading = true;
  error = '';

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.carregarUsuarios();
  }

  carregarUsuarios(): void {
    this.loading = true;
    this.error = '';
    
    this.usuarioService.getAll().subscribe({
      next: (data) => {
        this.usuarios = data;
        this.loading = false;
        console.log('Usuários carregados:', data);
      },
      error: (err) => {
        this.error = 'Erro ao buscar usuários: ' + (err.message || JSON.stringify(err));
        this.loading = false;
        console.error('Erro ao buscar usuários:', err);
      }
    });
  }

  criarUsuario(): void {
    this.router.navigate(['/usuarios/create']);
  }

  excluirUsuario(id: string): void {
    if (confirm('Tem certeza que deseja excluir este usuário?')) {
      this.usuarioService.delete(id).subscribe({
        next: () => {
          this.carregarUsuarios();
          alert('Usuário excluído com sucesso!');
        },
        error: (err: any) => {
          alert('Erro ao excluir usuário: ' + (err.error?.message || 'Erro desconhecido'));
        }
      });
    }
  }
}
