import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { DrawerComponent } from "../../drawer/drawer.component";

@Component({
  selector: 'app-user-layout',
  imports: [DrawerComponent],
  templateUrl: './user-layout.component.html',
  styleUrl: './user-layout.component.css'
})
export class UserLayoutComponent {

  @Input() title = '';

  constructor(private router: Router) { }

  onClickArticles() {
    this.router.navigateByUrl("/feed");
  }

  onClickThemes() {
    this.router.navigateByUrl("/themes");
  }


  onClickProfile() {
    this.router.navigateByUrl("/profile");
  }
}
