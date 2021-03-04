import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'AngularWellness';

  switchPanel() {
    const signUpButton = document.getElementById('signUp');
    const signInButton = document.getElementById('signIn');
    const container = document.getElementById('container');

    signUpButton.addEventListener('click', () => {
      $(".register-container").css("visibility", "visible");
      $(".login-container").css("visibility", "hidden");
      container.classList.add("right-panel-active");
    });

    signInButton.addEventListener('click', () => {
      $(".login-container").css("visibility", "visible");
      $(".register-container").css("visibility", "hidden");
      container.classList.remove("right-panel-active");
    });
  }
}
