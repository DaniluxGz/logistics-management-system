import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bodega, EnvioMaritimo, EnvioTerrestre, Puerto } from '../models/envio.model';

@Injectable({ providedIn: 'root' })
export class EnvioService {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  // Envios terrestres
  obtenerTerrestres(): Observable<EnvioTerrestre[]> {
    return this.http.get<EnvioTerrestre[]>(`${this.apiUrl}/envios/terrestres`);
  }

  crearTerrestre(envio: EnvioTerrestre): Observable<EnvioTerrestre> {
    return this.http.post<EnvioTerrestre>(`${this.apiUrl}/envios/terrestres`, envio);
  }

  eliminarTerrestre(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/envios/terrestres/${id}`);
  }

  // Envios maritimos
  obtenerMaritimos(): Observable<EnvioMaritimo[]> {
    return this.http.get<EnvioMaritimo[]>(`${this.apiUrl}/envios/maritimos`);
  }

  crearMaritimo(envio: EnvioMaritimo): Observable<EnvioMaritimo> {
    return this.http.post<EnvioMaritimo>(`${this.apiUrl}/envios/maritimos`, envio);
  }

  eliminarMaritimo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/envios/maritimos/${id}`);
  }

  // Bodegas
  obtenerBodegas(): Observable<Bodega[]> {
    return this.http.get<Bodega[]>(`${this.apiUrl}/bodegas`);
  }

  crearBodega(bodega: Bodega): Observable<Bodega> {
    return this.http.post<Bodega>(`${this.apiUrl}/bodegas`, bodega);
  }

  // Puertos
  obtenerPuertos(): Observable<Puerto[]> {
    return this.http.get<Puerto[]>(`${this.apiUrl}/puertos`);
  }

  crearPuerto(puerto: Puerto): Observable<Puerto> {
    return this.http.post<Puerto>(`${this.apiUrl}/puertos`, puerto);
  }
}