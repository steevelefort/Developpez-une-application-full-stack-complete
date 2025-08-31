import { DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Article } from 'src/app/models/Article';

@Component({
  selector: 'app-article-card',
  imports: [DatePipe,RouterLink],
  templateUrl: './article-card.component.html',
  styleUrl: './article-card.component.css'
})
export class ArticleCardComponent {

  @Input() article?: Article;

}
