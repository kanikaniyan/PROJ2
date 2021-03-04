import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  @Output() toggleTheSideBar: EventEmitter<any> =new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  toggleSideBar() {
    this.toggleTheSideBar.emit();
  }

}
