import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css'],
})
export class PaginationComponent {
  @Input() length = 0;
  @Output() pageChangeEvent = new EventEmitter<number>();

  onChange(page: number) {
    this.pageChangeEvent.emit(page);
  }
}
