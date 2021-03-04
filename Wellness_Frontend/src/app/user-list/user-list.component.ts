import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';
import { Observable, Subject } from "rxjs";
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app1-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  constructor(private userService:UserService) { }

  usersArray:any[]=[];
  dtOptions: DataTables.Settings={};
  dtTrigger: Subject<any>=new Subject();

  users:Observable <User[]>;
  user: User=new User();
  deleteMessage=false;
  userList:any;
  isUpdated=false;
  
  ngOnInit() {

    this.isUpdated=false;
    this.dtOptions={
      pageLength:6,
      stateSave:true,
      lengthMenu:[[6, 16, 21, -1], [6, 16, 20, "All"]],
      processing:true
    };

    this.userService.getUserList().subscribe(data=> {
      this.users=data;
      this.dtTrigger.next();
    });
  }

  deleteUser(id:number) {
    this.userService.deleteUser(id).subscribe (data=> {
        console.log(data);
        this.deleteMessage=true;
        this.userService.getUserList().subscribe(data=> {
          this.users=data;
        })
      },
      error=>{
        console.log(error);
      }
    );
  }

  updateUser(id:number) {
    this.userService.getUser(id).subscribe(data=> {
      this.userList=data;
      console.log(this.userList);
    }),
    error=>{
      console.log(error);
    }
  }

  userUpdateForm=new FormGroup({
    userId:new FormControl(),
    firstName:new FormControl(),
    lastName:new FormControl(),
    username:new FormControl(),
    password:new FormControl(),
    email:new FormControl(),
    role:new FormControl(),
    status:new FormControl(),
    isOnline: new FormControl(),
    enabled: new FormControl()
  });

  updatUser() {
    this.user=new User();
    this.user.userId=this.UserId.value;
    this.user.firstName=this.FirstName.value;
    this.user.lastName=this.LastName.value;
    this.user.username=this.Username.value;
    this.user.password=this.Password.value;
    this.user.email=this.Email.value;
    this.user.role=this.Role.value;
    this.user.status=this.Status.value;
    this.user.isOnline=this.IsOnline.value;
    this.user.enabled=this.Enabled.value;
    
    this.userService.updateUser(this.user.userId, this.user).subscribe(data=> {
      this.isUpdated=true;
      this.userService.getUserList().subscribe(data=> {
        this.users=data
      })
    },
    error=>console.log(error));
  }

  get UserId() {
    return this.userUpdateForm.get('userId');
  }

  get FirstName() {
    return this.userUpdateForm.get('firstName');
  }

  get LastName() {
    return this.userUpdateForm.get('lastName');
  }

  get Username() {
    return this.userUpdateForm.get('username');
  }

  get Password() {
    return this.userUpdateForm.get('password');
  }

  get Email() {
    return this.userUpdateForm.get('email');
  }

  get Role() {
    return this.userUpdateForm.get('role');
  }

  get Status() {
    return this.userUpdateForm.get('status');
  }

  get IsOnline() {
    return this.userUpdateForm.get('isOnline');
  }

  get Enabled() {
    return this.userUpdateForm.get('enabled');
  }

  changeIsUpdated() {
    this.isUpdated=false;
  }
}
