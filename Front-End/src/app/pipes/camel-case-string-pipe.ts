import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'camelCaseString'
})
export class CamelCaseStringPipe implements PipeTransform {

  transform(value: string, maxLength:number): string {
    let valorTranform:string='';
    valorTranform=value.split(' ').map( palabra => palabra.substring(0,1).toUpperCase() + palabra.substring(1,)).join(' ');

    if(maxLength && valorTranform.length>maxLength){
      return valorTranform.substring(0,maxLength)+'...';
    }
    return valorTranform;
  }
}
