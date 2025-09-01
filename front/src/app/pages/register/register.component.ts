import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthLayoutComponent } from "src/app/components/layouts/auth-layout/auth-layout.component";
import { LoginRequest, RegisterRequest } from 'src/app/models/Auth';
import { AuthService } from 'src/app/services/auth.service';
import { passwordValidator } from 'src/app/validators/password-validator';

@Component({
  selector: 'app-login',
  imports: [AuthLayoutComponent, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  form: FormGroup;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = fb.group({
      userName: ['', [Validators.required, Validators.maxLength(100), Validators.pattern(/.*\S.*/)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(255), Validators.pattern(/.*\S.*/)]],
      password: ['', [Validators.required, passwordValidator(), Validators.pattern(/.*\S.*/)]],
    })
  }

  onSubmitRegister(): void {
    if (this.form.valid) {
      const registerRequest: RegisterRequest = this.form.getRawValue();
      this.authService.register(registerRequest).subscribe(
        {
          next: () => {
            this.router.navigateByUrl('/feed')
            this.errorMessage = null;
          },
          error: (error) => {
            console.log(error.error)
            if (error?.error) {
            this.errorMessage = '';
            if ('userName' in error.error) { this.errorMessage += error.error.userName + "\n" }
            if ('email' in error.error) { this.errorMessage += error.error.email + "\n" }
            if ('password' in error.error) { this.errorMessage += error.error.password + "\n" }
            } else {
              this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
            }
          }
        }
      );
    }
  }


}
