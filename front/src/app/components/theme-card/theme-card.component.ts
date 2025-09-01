import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Theme } from 'src/app/models/Theme';
import { UserService } from 'src/app/services/user.service';

/**
 * This component print a theme
 *
 * canUnSubstribe: activate unsubscription
 * theme: the Theme to show
 * onSubscribe: emit themeId when subscribe
 * onUnSubscribe: emit themeId when unsubscribe
 */
@Component({
  selector: 'app-theme-card',
  imports: [CommonModule],
  templateUrl: './theme-card.component.html',
  styleUrl: './theme-card.component.css'
})
export class ThemeCardComponent {

  @Input() canUnSubstribe = false;
  @Input({ required: true }) theme!: Theme;
  @Output() onSubscribe = new EventEmitter<Number>();
  @Output() onUnSubscribe = new EventEmitter<Number>();

  constructor(public userService: UserService) { }

  onClickSubscribe(themeId: Number) {
    if (themeId != null)
      this.onSubscribe.emit(themeId);
  }

  onClickUnSubscribe(themeId: Number) {
    if (themeId != null)
      this.onUnSubscribe.emit(themeId);
  }
}
