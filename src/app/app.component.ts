import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="container">
      <h1>E-Language Frontend</h1>
      <p>Frontend Angular funcionando!</p>
      <button (click)="testApi()">Testar API</button>
      <div *ngIf="response">{{ response | json }}</div>
    </div>
  `,
  styles: [`
    .container {
      padding: 20px;
      font-family: Arial, sans-serif;
    }
    button {
      padding: 10px 20px;
      background: #007bff;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background: #0056b3;
    }
  `]
})
export class AppComponent {
  response: any;

  constructor(private http: HttpClient) {}

  testApi() {
    this.http.get('http://localhost:8080/api/languages').subscribe({
      next: (data) => this.response = data,
      error: (error) => console.error('Erro ao conectar com API:', error)
    });
  }
}
