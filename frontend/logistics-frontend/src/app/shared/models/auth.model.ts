// Authentication request and response models
export interface LoginRequest {
    email: string;
    password: string;
}

export interface RegistroRequest {
    nombre: string;
    email: string;
    password: string;
}

export interface LoginResponse {
    token: string;
    email: string;
    nombre: string;
    rol: string;
}