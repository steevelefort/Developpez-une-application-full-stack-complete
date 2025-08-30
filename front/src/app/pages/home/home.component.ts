import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  imports: [CommonModule,RouterModule]
})
export class HomeComponent implements OnInit {
  constructor( private router: Router ) {}

  ngOnInit(): void { }

  start() {
    alert('Commencez par lire le README et à vous de jouer !');
  }

  signup() {
  }

  login() {
    this.router.navigateByUrl('login');
  }
}
