import { Component, computed, OnInit, signal } from '@angular/core';
import { UserLayoutComponent } from "src/app/components/layouts/user-layout/user-layout.component";
import { FeedService } from 'src/app/services/feed.service';
import { ArticleCardComponent } from "src/app/components/article-card/article-card.component";
import { Router, RouterLink } from '@angular/router';
import { Article } from 'src/app/models/Article';

@Component({
  selector: 'app-feed',
  imports: [UserLayoutComponent, ArticleCardComponent, RouterLink],
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.css'
})
export class FeedComponent implements OnInit {

  errorMessage: String | null = null;
  // sortDesc = signal(true);
  sortDesc = true;

  articles: Article[] = []

  // articles = computed(() => {
  //   const sortedFeed = [...this.feedService.feed()];
  //
  //   return sortedFeed.sort(
  //     (a, b) => {
  //       const dateA = new Date(a.createdAt).getTime();
  //       const dateB = new Date(b.createdAt).getTime();
  //       return this.sortDesc() ? dateB - dateA : dateA - dateB;
  //     }
  //   );
  // })

  constructor(private feedService: FeedService) { }

  ngOnInit(): void {
    this.feedService.loadFeed().subscribe({
      next: (feed) => {
        this.articles = feed;
        this.errorMessage = null;
      },
      error: (error) => {
        this.errorMessage = error.toString();
      }
    })
  }

  onSort() {
    this.sortDesc = !this.sortDesc;

    this.articles = this.articles.sort(
      (a, b) => {
        const dateA = new Date(a.createdAt).getTime();
        const dateB = new Date(b.createdAt).getTime();
        return this.sortDesc ? dateB - dateA : dateA - dateB;
      }
    );
  }

}



