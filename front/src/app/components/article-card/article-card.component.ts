import { DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Article } from 'src/app/models/Article';

@Component({
  selector: 'app-article-card',
  imports: [DatePipe],
  templateUrl: './article-card.component.html',
  styleUrl: './article-card.component.css'
})
export class ArticleCardComponent {

  @Input() article?: Article;

}
