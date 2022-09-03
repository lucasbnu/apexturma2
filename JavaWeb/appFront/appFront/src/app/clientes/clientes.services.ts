import { Injectable } from "@angular/core";

import { HttpClient, HttpErrorResponse, HttpParams  } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { Clientes } from "./clientes";


@Injectable({
    providedIn: 'root' 
})
export class ClientesServices {
        urlProjeto = "http://localhost:8090/api/clientes";
        clientes: Clientes[];
        
        private falha(error: HttpErrorResponse) {
            console.log(error);
            return throwError('Erro ao processar a requisição');
        }
        constructor (private http: HttpClient) { }
    getClientes(): Observable<Clientes[]> {
        return this.http.get<Clientes[]>(this.urlProjeto)
            .pipe(
                map((res) => {
                    this.clientes = res;
                    return this.clientes;
                }, catchError(this.falha)
                )
            );
    }

}