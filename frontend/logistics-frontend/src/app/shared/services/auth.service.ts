import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginRequest, LoginResponse, RegistroRequest } from '../models/auth.model';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  // Iniciar sesion y guardar token
  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, request).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('usuario', JSON.stringify(response));
      })
    );
  }

  // Registrar nuevo usuario
  registro(request: RegistroRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/registro`, request).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('usuario', JSON.stringify(response));
      })
    );
  }

  // Cerrar sesion
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('usuario');
  }

  // Verificar si el usuario esta autenticado
  estaAutenticado(): boolean {
    return !!localStorage.getItem('token');
  }

  // Obtener token almacenado
  obtenerToken(): string | null {
    return localStorage.getItem('token');
  }

  // Obtener datos del usuario autenticado
  obtenerUsuario(): LoginResponse | null {
    const usuario = localStorage.getItem('usuario');
    return usuario ? JSON.parse(usuario) : null;
  }
}