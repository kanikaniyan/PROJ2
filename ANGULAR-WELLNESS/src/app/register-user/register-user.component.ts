import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';  
import {FormControl,FormGroup,Validators} from '@angular/forms';  
import { User } from '../user';

@Component({
  selector: 'app1-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.scss']
})
export class RegisterUserComponent implements OnInit {

  constructor(private userService:UserService, private cd: ChangeDetectorRef) {}
    user : User=new User();
    submitted = false;

    isShown1:boolean;
    isShown2:boolean;
    isShown3:boolean;
    isShown4:boolean;
    isShown5:boolean;
    isShown6:boolean;
    isShown7:boolean;

    changecolor:boolean;

  ngOnInit() {  
    this.submitted=false;

    this.isShown1=false;
    this.isShown2=false;
    this.isShown3=false;
    this.isShown4=false;
    this.isShown5=false;
    this.isShown6=false;
    this.isShown7=false;

    this.changecolor=false;
    $("#radiocolor").css("color","black");
  }

  registrationform=new FormGroup({
    firstName:new FormControl('' , [Validators.required , Validators.minLength(5)]),  
    lastName:new FormControl('',[Validators.required,Validators.minLength(5)]),
    username:new FormControl('',[Validators.required,Validators.minLength(5)]),
    password:new FormControl('',[Validators.required,Validators.pattern('(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!#^~%*?&,.<>"\'\\;:\{\\\}\\\[\\\]\\\|\\\+\\\-\\\=\\\_\\\)\\\(\\\)\\\`\\\/\\\\\\]])[A-Za-z0-9\d$@].{7,}')]),
    confirmPassword:new FormControl('',[Validators.required]),
    email:new FormControl('',[Validators.required,Validators.email]),
    role:new FormControl('',[Validators.required,Validators.minLength(4), Validators.maxLength(5)])
  }
  );

  saveUser() {
    this.user=new User();
    this.user.firstName=this.FirstName.value;
    this.user.lastName=this.LastName.value;
    this.user.username=this.Username.value;
    this.user.password=this.Password.value;
    this.user.confirmPassword=this.ConfirmPassword.value;
    this.user.email=this.Email.value;
    this.user.role=this.Role.value;
    this.user.isOnline=false;
    
    if (this.user.role==="Admin"){
      this.user.enabled=true;
      this.user.status="Active";
    }
    else {
      this.user.enabled=false;
      this.user.status="Inactive";
    }

    this.submitted=true;
    console.log(this.user);
    this.save();
  }

  save() {
    this.userService.addUser(this.user)
    .subscribe(data => console.log(data), error => console.log(error));
    this.user=new User();
  }

  get FirstName() {
    return this.registrationform.get ('firstName');
  }

  get LastName() {
    return this.registrationform.get ('lastName');
  }

  get Username() {
    return this.registrationform.get ('username');
  }

  get Password() {
    return this.registrationform.get ('password');
  }

  get ConfirmPassword() {
    return this.registrationform.get ('confirmPassword');
  }

  get Email() {
    return this.registrationform.get ('email');
  }

  get Role() {
    return this.registrationform.get ('role');
  }

  toggleShow1() {
    this.isShown1=true;
  }

  toggleShow2() {
    this.isShown2=true;
  }

  toggleShow3() {
    this.isShown3=true;
  }

  toggleShow4() {
    this.isShown4=true;
  }

  toggleShow5() {
    this.isShown5=true;
  }

  toggleShow6() {
    this.isShown6=true;
  }

  toggleShow7() {
    this.isShown7=true;
  }

  changeColor() {
    this.changecolor=true;
    $("#radiocolor").css("color","red");
  }

  addSuccess() {
    $(".ro").css({"transform":"translateX(0%)", "opacity":"1", "z-index":"5", "animation": "yes 1s"});
    $(".ro .rodown").css("transform", "translateX(0%)");
  }

  ngAfterContentChecked() {
    this.cd.detectChanges();
  }

  addUserForm() {
    this.submitted=false;
    this.registrationform.reset();
  }
}
