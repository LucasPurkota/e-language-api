import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './usuario-form.component.html',
  styles: [`
    .container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }
    .form-group {
      margin-bottom: 15px;
    }
    .form-group label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }
    .form-group input {
      width: 100%;
      padding: 8px;
      border: 1px solid #ddd;
      border-radius: 4px;
    }
    .error-text {
      color: #f44336;
      font-size: 12px;
      margin-top: 5px;
    }
    .form-actions {
      margin-top: 20px;
      display: flex;
      gap: 10px;
      justify-content: flex-end;
    }
    .btn-primary {
      padding: 10px 15px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .btn-secondary {
      padding: 10px 15px;
      background-color: #f5f5f5;
      border: 1px solid #ddd;
      border-radius: 4px;
      cursor: pointer;
    }
    .error-message {
      padding: 10px;
      background-color: #ffebee;
      color: #c62828;
      border: 1px solid #c62828;
      border-radius: 4px;
      margin-bottom: 20px;
    }
  `]
})
export class UsuarioFormComponent implements OnInit {
  usuarioForm!: FormGroup;
  loading = false;
  error = '';

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  private initForm(): void {
    this.usuarioForm = this.fb.group({
      nome: ['Teste Usuario', [Validators.required]],
      cpf: ['123.456.789-00', [Validators.required]],
      email: ['teste@email.com', [Validators.required, Validators.email]],
      senha: ['Senha@123', [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.usuarioForm.valid) {
      this.loading = true;
      this.error = '';
      
      const formData = this.usuarioForm.value;
      console.log('Enviando dados:', formData);
      
      this.usuarioService.create(formData).subscribe({
        next: (response) => {
          console.log('Usuário criado:', response);
          alert('Usuário criado com sucesso!');
          this.router.navigate(['/usuarios']);
        },
        error: (err: any) => {
          console.error('Erro ao criar usuário:', err);
          this.error = err.message || JSON.stringify(err) || 'Erro ao criar usuário';
          this.loading = false;
        }
      });
    } else {
      Object.keys(this.usuarioForm.controls).forEach(field => {
        const control = this.usuarioForm.get(field);
        control?.markAsTouched({ onlySelf: true });
      });
    }
  }

  onCancel(): void {
    this.router.navigate(['/usuarios']);
  }
}
