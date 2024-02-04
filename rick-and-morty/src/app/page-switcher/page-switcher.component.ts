import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-page-switcher',
  templateUrl: './page-switcher.component.html',
  styleUrls: ['./page-switcher.component.css']
})
export class PageSwitcherComponent {
  @Input() max: number | null = null;
  @Input() pageNumber: number = 1;

  @Output() pageChanged: EventEmitter<number> = new EventEmitter<number>();

  onPageChange(nextPage: number) {
    this.pageChanged.emit(nextPage);
  }
}
