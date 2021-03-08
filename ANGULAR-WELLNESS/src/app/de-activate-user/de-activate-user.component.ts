import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-de-activate-user',
  templateUrl: './de-activate-user.component.html',
  styleUrls: ['./de-activate-user.component.scss']
})
export class DeActivateUserComponent implements OnInit {

  constructor(private userService:UserService) { }

  dtOptions: DataTables.Settings={};
  dtTrigger: Subject<any>=new Subject();

  users:Observable <User[]>;
  user: User=new User();
  userList: any;

  activateMessage=false;
  
  ngOnInit(): void {
    this.dtOptions={
      pageLength:6,
      stateSave:true,
      lengthMenu:[[6, 16, 21, -1], [6, 16, 20, "All"]],
      processing:true
    };

    this.userService.getActiveUserList().subscribe(data=> {
      this.users=data;
      this.dtTrigger.next();
    });
  }

  deActivateUser(id: number) {
    this.user.userId=id;
    this.userService.deActivateUser(this.user.userId).subscribe(data=> {
      this.userService.getActiveUserList().subscribe(data=> {
        this.users=data;
        this.activateMessage=true;
      })
    },
      error=>console.log(error));
  }

}
