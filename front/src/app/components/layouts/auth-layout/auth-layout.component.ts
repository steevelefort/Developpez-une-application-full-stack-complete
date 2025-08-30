import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth-layout',
  imports: [],
  templateUrl: './auth-layout.component.html',
  styleUrl: './auth-layout.component.css'
})
export class AuthLayoutComponent {

  @Input() title = '';

  constructor(private router: Router) { }

  backHome() {
    this.router.navigateByUrl("/");
  }
}
