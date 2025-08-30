import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthLayoutComponent } from "src/app/components/layouts/auth-layout/auth-layout.component";

@Component({
  selector: 'app-login',
  imports: [AuthLayoutComponent,ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = fb.group({
      identifier: ['', [Validators.required]],
      password: ['', [Validators.required]],
    })
  }


}
