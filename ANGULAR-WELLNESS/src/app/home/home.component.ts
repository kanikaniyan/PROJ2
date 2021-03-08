import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

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

  openUp() {
    $(".common").css({"animation": "show1 0.9s forwards"});
    $(".container").css({"opacity":"0" ,"animation": "show 0.9s forwards", "visibility":"visible"});
    $(".title1").css({"opacity":"0" ,"animation": "show 0.9s forwards", "visibility":"visible"});
  }

}
