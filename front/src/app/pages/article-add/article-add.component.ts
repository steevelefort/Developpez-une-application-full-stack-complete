import { CommonModule } from '@angular/common';
import { Component, computed, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserLayoutComponent } from "src/app/components/layouts/user-layout/user-layout.component";
import { ArticleRequest } from 'src/app/models/Article';
import { Theme } from 'src/app/models/Theme';
import { FeedService } from 'src/app/services/feed.service';
import { ThemesService } from 'src/app/services/themes.service';

@Component({
  selector: 'app-article-add',
  imports: [UserLayoutComponent, ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './article-add.component.html',
  styleUrl: './article-add.component.css'
})
export class ArticleAddComponent implements OnInit {

  form: FormGroup;
  errorMessage: string | null = null;
  themes = computed(() => {
    const themesCopy = [...this.themesService.themes()];

    return themesCopy.sort(
      (a, b) => {
        return a.name < b.name ? -1 : a.name > b.name ? 1 : 0;
      }
    );
  })

  constructor(
    private feedService: FeedService,
    private fb: FormBuilder,
    private themesService: ThemesService,
    private router: Router
  ) {

    this.form = fb.group({
      themeId: ['', [Validators.required, Validators.pattern(/.*\S.*/)]],
      title: ['', [Validators.required, Validators.maxLength(100), Validators.pattern(/.*\S.*/)]],
      content: ['', [Validators.required, Validators.pattern(/.*\S.*/)]],
    })
  }

  ngOnInit(): void {
    if (this.themesService.themes().length === 0) {
      this.themesService.loadthemes().subscribe(
        {
          next: () => {
            this.errorMessage = null;
          },
          error: (error) => {
            this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
          }
        }
      );
    }
  }

  onSubmitCreate() {
    if (this.form.valid) {
      const articleRequest: ArticleRequest = this.form.getRawValue();
      this.feedService.create(articleRequest).subscribe(
        {
          next: () => {
            this.router.navigateByUrl('/feed')
            this.errorMessage = null;
          },
          error: () => {
            this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
          }
        }
      );
    }
  }

}
