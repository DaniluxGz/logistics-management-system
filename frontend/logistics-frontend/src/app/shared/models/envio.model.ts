// Shipment models
export interface EnvioTerrestre {
    id?: number;
    numeroGuia: string;
    tipoProducto: string;
    cantidad: number;
    fechaRegistro: string;
    fechaEntrega: string;
    precioOriginal: number;
    precioFinal?: number;
    estado?: string;
    cliente: { id: number };
    placaVehiculo: string;
    bodega: { id: number };
    clienteNombre?: string;
    clienteEmail?: string;
    bodegaNombre?: string;
}

export interface EnvioMaritimo {
    id?: number;
    numeroGuia: string;
    tipoProducto: string;
    cantidad: number;
    fechaRegistro: string;
    fechaEntrega: string;
    precioOriginal: number;
    precioFinal?: number;
    estado?: string;
    cliente: { id: number };
    numeroFlota: string;
    puerto: { id: number };
    clienteNombre?: string;
    clienteEmail?: string;
    puertoNombre?: string;
}

export interface Bodega {
    id?: number;
    nombre: string;
    ubicacion: string;
}

export interface Puerto {
    id?: number;
    nombre: string;
    ubicacion: string;
}