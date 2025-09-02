import { Component, Input } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { DrawerComponent } from "../../drawer/drawer.component";
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user-layout',
  imports: [DrawerComponent, RouterModule],
  templateUrl: './user-layout.component.html',
  styleUrl: './user-layout.component.css'
})
export class UserLayoutComponent {

  @Input() title = '';

  constructor(private router: Router, private authService: AuthService) { }

  onLogout() {
    this.authService.logout();
    this.router.navigateByUrl("/");
  }
}
