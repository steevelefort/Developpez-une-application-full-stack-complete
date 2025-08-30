import { Component } from '@angular/core';
import { UserLayoutComponent } from "src/app/components/layouts/user-layout/user-layout.component";

@Component({
  selector: 'app-profile',
  imports: [UserLayoutComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

}
