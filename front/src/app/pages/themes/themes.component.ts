import { CommonModule } from '@angular/common';
import { Component, computed, OnInit } from '@angular/core';
import { UserLayoutComponent } from "src/app/components/layouts/user-layout/user-layout.component";
import { ThemesService } from 'src/app/services/themes.service';
import { UserService } from 'src/app/services/user.service';
import { ThemeCardComponent } from "src/app/components/theme-card/theme-card.component";

@Component({
  selector: 'app-themes',
  imports: [UserLayoutComponent, CommonModule, ThemeCardComponent],
  templateUrl: './themes.component.html',
  styleUrl: './themes.component.css'
})
export class ThemesComponent implements OnInit {

  errorMessage: string | null = null;

  constructor(
    public themesService: ThemesService,
    public userService: UserService
  ) { }

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
          },
          error: () => {
            this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
          }
        }
      )
    }
  }

  onSubscribe(themeId: Number) {
    this.userService.subscribe(themeId).subscribe({
      next: () => {
        this.errorMessage = null;
      },
      error: () => {
        this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
      }
    })

  }

}
