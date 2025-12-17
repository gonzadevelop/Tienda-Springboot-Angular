import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-categories-panel',
  templateUrl: './categories-panel.html',
  styleUrls: ['./categories-panel.css'],
})
export class CategoriesPanel {
  @Input() open = false;
  @Output() close = new EventEmitter<void>();

  onClose() {
    this.close.emit();
  }
}

