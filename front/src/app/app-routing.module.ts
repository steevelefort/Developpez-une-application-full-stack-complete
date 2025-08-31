import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { FeedComponent } from './pages/feed/feed.component';
import { authGuard } from './guards/auth.guard';
import { guestGuard } from './guards/guest.guard';
import { RegisterComponent } from './pages/register/register.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ArticleAddComponent } from './pages/article-add/article-add.component';
import { ErrorComponent } from './pages/error/error.component';

export const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [guestGuard] },
  { path: 'login', component: LoginComponent, canActivate: [guestGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [guestGuard] },
  { path: 'feed', component: FeedComponent, canActivate: [authGuard] },
  { path: 'themes', component: ThemesComponent, canActivate: [authGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [authGuard] },
  { path: 'add-article', component: ArticleAddComponent, canActivate: [authGuard] },
  { path: '**', component: ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
