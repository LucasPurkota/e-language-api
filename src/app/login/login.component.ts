import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';  

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="login-container">
      <div class="login-card">
        <h2>Login</h2>
        <form (ngSubmit)="onSubmit()" #loginForm="ngForm">
          <div class="form-group">
            <label for="username">Usuário:</label>
            <input
              type="text"
              id="username"
              name="username"
              [(ngModel)]="credentials.username"
              required
              placeholder="Digite seu usuário"
            >
          </div>

          <div class="form-group">
            <label for="password">Senha:</label>
            <input
              type="password"
              id="password"
              name="password"
              [(ngModel)]="credentials.password"
              required
              placeholder="Digite sua senha"
            >
          </div>

          <button
            type="submit"
            [disabled]="!loginForm.valid || loading"
            class="login-btn"
          >
            {{ loading ? 'Entrando...' : 'Entrar' }}
          </button>

          <div *ngIf="error" class="error-message">
            {{ error }}
          </div>
        </form>
      </div>
    </div>
  `,
  styles: [`
    .login-container {
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      padding: 20px;
    }

    .login-card {
      background: white;
      padding: 2rem;
      border-radius: 10px;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
    }

    h2 {
      text-align: center;
      margin-bottom: 1.5rem;
      color: #333;
    }

    .form-group {
      margin-bottom: 1rem;
    }

    label {
      display: block;
      margin-bottom: 0.5rem;
      color: #555;
      font-weight: 500;
    }

    input {
      width: 100%;
      padding: 0.75rem;
      border: 2px solid #ddd;
      border-radius: 5px;
      font-size: 1rem;
      transition: border-color 0.3s;
    }

    input:focus {
      outline: none;
      border-color: #667eea;
    }

    .login-btn {
      width: 100%;
      padding: 0.75rem;
      background: #667eea;
      color: white;
      border: none;
      border-radius: 5px;
      font-size: 1rem;
      cursor: pointer;
      transition: background 0.3s;
    }

    .login-btn:hover:not(:disabled) {
      background: #5a67d8;
    }

    .login-btn:disabled {
      background: #ccc;
      cursor: not-allowed;
    }

    .error-message {
      color: #e53e3e;
      text-align: center;
      margin-top: 1rem;
      padding: 0.5rem;
      background: #fed7d7;
      border-radius: 5px;
    }
  `]
})
export class LoginComponent {
  credentials = {
    username: '',
    password: ''
  };

  loading = false;
  error = '';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  onSubmit() {
    if (this.loading) return;

    this.loading = true;
    this.error = '';

    // Simulação de login - substitua pela sua API real
    setTimeout(() => {
      if (this.credentials.username === 'admin' && this.credentials.password === 'admin') {
        // Login bem-sucedido
        localStorage.setItem('token', 'fake-jwt-token');
        localStorage.setItem('user', JSON.stringify({ username: this.credentials.username }));
        this.router.navigate(['/dashboard']);
      } else {
        this.error = 'Credenciais inválidas. Tente admin/admin';
      }
      this.loading = false;
    }, 1000);

    // Para API real, descomente e remova o setTimeout acima:
    /*
    this.http.post('http://localhost:8080/api/auth/login', this.credentials)
      .subscribe({
        next: (response: any) => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('user', JSON.stringify(response.user));
          this.router.navigate(['/dashboard']);
          this.loading = false;
        },
        error: (error) => {
          this.error = error.error?.message || 'Erro ao fazer login';
          this.loading = false;
        }
      });
    */
  }
}
