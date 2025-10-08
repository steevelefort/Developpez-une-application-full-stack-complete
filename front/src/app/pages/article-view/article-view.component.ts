import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UserLayoutComponent } from "src/app/components/layouts/user-layout/user-layout.component";
import { FullArticle } from 'src/app/models/Article';
import { Comment, CommentRequest } from 'src/app/models/Comment';
import { FeedService } from 'src/app/services/feed.service';

@Component({
  selector: 'app-article-view',
  imports: [UserLayoutComponent, ReactiveFormsModule, RouterLink, DatePipe],
  templateUrl: './article-view.component.html',
  styleUrl: './article-view.component.css'
})
export class ArticleViewComponent implements OnInit {

  article?: FullArticle;
  form: FormGroup;
  errorMessage: string | null = null;
  notFound = false;

  constructor(
    private feedService: FeedService,
    private fb: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute

  ) {

    this.form = fb.group({
      content: ['', [Validators.required, Validators.pattern(/.*\S.*/)]],
    })
  }
    ngOnInit(): void {
      const articleId = this.activatedRoute.snapshot.paramMap.get('id') || "";
      this.feedService.getOne(articleId).subscribe({
        next: (articleResponse: FullArticle) => {
          this.article = articleResponse
          console.log(articleResponse)
        },
        error: () => {
            // this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
            this.notFound = true;
        }
      });
    }

  onSubmitCreate(): void {
    if (this.form.valid) {
      const commentRequest: CommentRequest = this.form.getRawValue();
      const articleId = this.activatedRoute.snapshot.paramMap.get('id') || "";
      this.feedService.addComment(commentRequest,articleId).subscribe(
        {
          next: (comment) => {
            this.article?.comments.push(comment);
            this.errorMessage = null;
            this.form.reset();
          },
          error: () => {
            this.errorMessage = "Une erreur est survenue, veuillez essayer à nouveau dans un instant.";
          }
        }
      );
    }
  }
}
