import {
  Component,
  Input,
  output,
  OutputEmitterRef, inject, OnInit
} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import ICategoriaResponse from '../../../../model/ICategoriaResponse';
import {map, Observable} from 'rxjs';
import {ServApiSpring} from '../../../../services/ServApiSpring';
import {AsyncPipe} from '@angular/common';

@Component({
  selector: 'app-categories-panel',
  templateUrl: './categories-panel.html',
  styleUrls: ['./categories-panel.css'],
  imports: [
    AsyncPipe
  ]
})
export class CategoriesPanel implements OnInit {
  private servApiSpring: ServApiSpring = inject( ServApiSpring );
  protected observableCategorias:Observable<ICategoriaResponse[]> | null = null;
  protected categoriaSeleccionada:string | null = null;


  // public open:InputSignal<boolean> = input<boolean>(); <---- porque esto no funciona???
  @Input() open:boolean = false;
  public close:OutputEmitterRef<void> = output<void>();

  onClose():void {
    this.close.emit();
  }


  public onCatClick(catPadre: ICategoriaResponse): void {
    const nombreCat:string = catPadre.nombre;
    this.categoriaSeleccionada = nombreCat;
    this.observableCategorias = this.servApiSpring
      .getCategorias( nombreCat )
      .pipe(
        map( ( resp: HttpResponse<ICategoriaResponse[]> ):ICategoriaResponse[] => resp.body || [] )
      );
    console.log('categoria seleccionada: ', this.categoriaSeleccionada);
  }

  ngOnInit(): void {
    this.observableCategorias = this.servApiSpring
      .getCategorias(null)
      .pipe(
        map( ( resp: HttpResponse<ICategoriaResponse[]> ):ICategoriaResponse[] => resp.body || [] )
      );
    this.categoriaSeleccionada = 'Categorias';
  }
}
