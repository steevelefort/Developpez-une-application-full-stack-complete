import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
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
