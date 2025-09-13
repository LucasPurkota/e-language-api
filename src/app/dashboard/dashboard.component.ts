import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="dashboard-container">
      <header class="dashboard-header">
        <h1>Dashboard</h1>
        <button (click)="logout()" class="logout-btn">Sair</button>
      </header>

      <div class="dashboard-content">
        <p>Bem-vindo ao dashboard!</p>
        <p>Usuário: {{ getUser() }}</p>
      </div>
    </div>
  `,
  styles: [`
    .dashboard-container {
      padding: 20px;
      font-family: Arial, sans-serif;
    }

    .dashboard-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 2rem;
      padding-bottom: 1rem;
      border-bottom: 2px solid #eee;
    }

    .logout-btn {
      padding: 0.5rem 1rem;
      background: #e53e3e;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    .logout-btn:hover {
      background: #c53030;
    }

    .dashboard-content {
      background: white;
      padding: 2rem;
      border-radius: 10px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
  `]
})
export class DashboardComponent {
  constructor(private router: Router) {}

  getUser(): string {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user).username : 'Usuário não encontrado';
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.router.navigate(['/login']);
  }
}
