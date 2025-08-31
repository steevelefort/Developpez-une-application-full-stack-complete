import { Component } from '@angular/core';
import { AuthLayoutComponent } from "src/app/components/layouts/auth-layout/auth-layout.component";

@Component({
  selector: 'app-error',
  imports: [AuthLayoutComponent],
  templateUrl: './error.component.html',
  styleUrl: './error.component.css'
})
export class ErrorComponent {

}
