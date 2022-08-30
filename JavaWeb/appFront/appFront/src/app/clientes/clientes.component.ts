import { Component, OnInit } from '@angular/core';
import { ClientesServices } from './clientes.services';
import { Clientes } from './clientes';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {
  clientes: Clientes[];
  erro = '';
  sucesso = '';

  constructor(private ClientesServices: ClientesServices ) { }

  ngOnInit(): void {
    
    this.ObterClientes();
  }

  ObterClientes() {
    this.ClientesServices.getClientes().subscribe(
    (res: Clientes[]) => {
        this.clientes = res;
      },
      error => {
        this.erro = error;
      }
    );
  }

}
