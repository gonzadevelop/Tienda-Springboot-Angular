import {Component, OnInit} from '@angular/core';
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

  toggleCategories():void {
    this.categoriesOpen = !this.categoriesOpen;
  }

  closeCategories():void {
    this.categoriesOpen = false;
  }

  ngOnInit(): void {
    console.log('componente header cargado');
    this.sesionIniciada = localStorage.getItem('JWT') !== null;
  }
}
