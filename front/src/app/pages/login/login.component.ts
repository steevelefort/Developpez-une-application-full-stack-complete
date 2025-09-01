import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthLayoutComponent } from "src/app/components/layouts/auth-layout/auth-layout.component";
import { LoginRequest } from 'src/app/models/Auth';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  imports: [AuthLayoutComponent, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  form: FormGroup;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = fb.group({
      identifier: ['', [Validators.required]],
      password: ['', [Validators.required]],
    })
  }

  onSubmitLogin(): void {
    if (this.form.valid) {
      const loginRequest: LoginRequest = this.form.getRawValue();
      this.authService.login(loginRequest).subscribe(
        {
          next: () => {
            this.router.navigateByUrl('/feed')
            this.errorMessage = null;
          },
          error: (error) => {
            console.log(error)
            if (error?.error?.error) {
              this.errorMessage = error.error.error
            } else {
              this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
            }
          }
        }
      );
    }
  }


}
