import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriesPanel } from './categories-panel/categories-panel';
import {HolaUsuario} from './hola-usuario/hola-usuario';

@Component({
  selector: 'app-header',
  imports: [CommonModule, CategoriesPanel, HolaUsuario],
  templateUrl: './header.html',
  styleUrls: ['./header.css'],
})
export class Header implements OnInit {
  categoriesOpen:boolean = false;
  sesionIniciada:boolean = false;

  @Output() categoriesOpenChange = new EventEmitter<boolean>();

  toggleCategories():void {
    this.categoriesOpen = !this.categoriesOpen;
    this.categoriesOpenChange.emit(this.categoriesOpen);
  }

  closeCategories():void {
    this.categoriesOpen = false;
    this.categoriesOpenChange.emit(this.categoriesOpen);
  }

  ngOnInit(): void {
    console.log('componente header cargado');
    this.sesionIniciada = localStorage.getItem('JWT') !== null;
  }
}
