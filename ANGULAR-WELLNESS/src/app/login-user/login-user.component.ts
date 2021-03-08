import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app1-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.scss']
})
export class LoginUserComponent implements OnInit {

  user : User=new User();
  currentUser : any;  
  isShown1 : boolean;
  isShown2 : boolean;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.isShown1=false;
    this.isShown2=false;
  }

  loginform=new FormGroup({
    username:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required])
  })

  validateUser() {
    this.user=new User();
    this.user.username=this.Username.value;
    this.user.password=this.Password.value;
    
    this.userService.checkUser(this.user).subscribe (
      data => {
        console.log(data);
        if(data!=null) {
          
          //retrieving currentUser's data

          this.currentUser=data;
          console.log(this.currentUser.userId);
          this.addSuccess();
        }
        else {
          console.log("Object Empty");
        }
      },
      error => console.log(error)
    )
  }

  get Username() {
    return this.loginform.get ('username');
  }

  get Password() {
    return this.loginform.get ('password');
  }

  toggleShow1() {
    this.isShown1=true;
  }

  toggleShow2() {
    this.isShown2=true;
  }

  addSuccess() {
    if(this.currentUser.enabled==true) {

      

      $(".signin-container").css({"transform":"translateX(-100%)", "opacity":"0", "z-index":"1", "animation": "hide 0.9s"});
      $(".lo").css({"transform":"translateX(0%)", "opacity": "1", "z-index":"5", "visibility":"visible"});
      $(".lo .lodown").css("transform", "translateX(0%)");
    }
    else {
      alert("Your account is not activated yet!");
    }
  }

  btnClick() {
    if(this.currentUser.enabled==true) {
      this.router.navigateByUrl('/admin-page/'+`${this.currentUser.userId}`);
    }
  }

}
