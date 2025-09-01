import { Component, computed } from '@angular/core';
import { UserLayoutComponent } from "src/app/components/layouts/user-layout/user-layout.component";
import { ThemesService } from 'src/app/services/themes.service';
import { UserService } from 'src/app/services/user.service';
import { ThemeCardComponent } from "src/app/components/theme-card/theme-card.component";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { passwordValidator } from 'src/app/validators/password-validator';
import { RegisterRequest } from 'src/app/models/Auth';

@Component({
  selector: 'app-profile',
  imports: [UserLayoutComponent, ThemeCardComponent, ReactiveFormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  form: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;
  readonly subscribedThemes = computed(() => this.themesService.themes().filter((theme) => this.userService.user()?.subscriptions.includes(theme.id)))

  constructor(
    public themesService: ThemesService,
    public userService: UserService,
    private fb: FormBuilder,
  ) {
    this.form = fb.group({
      userName: ['', [Validators.required, Validators.maxLength(100), Validators.pattern(/.*\S.*/)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(255), Validators.pattern(/.*\S.*/)]],
      password: ['', [passwordValidator(), Validators.pattern(/.*\S.*/)]],
    })
  }

  fillForm(): void {
    const user = this.userService.user();
    if (user) {
      this.form.patchValue({
        userName: user.userName,
        email: user.email,
      });
    }
  }

  ngOnInit(): void {
    if (this.themesService.themes().length === 0) {
      this.themesService.loadthemes().subscribe(
        {
          next: (themes) => {
            this.errorMessage = null;
          },
          error: () => {
            this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
          }
        }
      )
    }

    if (!this.userService.user()) {
      this.userService.me().subscribe(
        {
          next: () => {
            this.errorMessage = null;
            this.fillForm();
          },
          error: () => {
            this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
          }
        }
      )
    }
    this.fillForm();

  }

  onUnSubscribe(themeId: Number) {
    this.userService.unSubscribe(themeId).subscribe({
      next: () => {
        this.errorMessage = null;
      },
      error: () => {
        this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
      }
    })
  }

  onSaveProfile(): void {
    if (this.form.valid) {
      const registerRequest: RegisterRequest = this.form.getRawValue();
      this.userService.update(registerRequest).subscribe(
        {
          next: () => {
            this.errorMessage = null;
            this.successMessage = "Sauvegarde effectuée";
            setTimeout(() => { this.successMessage = null }, 1000)
          },
          error: (error) => {
            if (error?.error?.error) {
              this.errorMessage = error.error.error;
            } else {
              this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
            }
          }
        }
      );
    }
  }

}
