import { Route } from '@angular/compiler/src/core';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { data } from 'jquery';
import { UserService } from '../user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  @Output() toggleTheSideBar: EventEmitter<any> =new EventEmitter();
  
  constructor(private route: ActivatedRoute, private userService: UserService, private router: Router) { }

  id: number;
  currentUser: any;
  
  ngOnInit(): void {
    this.route.params.subscribe (
      (params:Params)=> {
        this.id=+params['id'];
        this.userService.getUser(this.id).subscribe (
          data=> {
            this.currentUser=data;
            console.log(this.currentUser.role);
            
          }
        )
      }
    )
  }

  toggleSideBar() {
    this.toggleTheSideBar.emit();
  }

  logout() {
    setTimeout(()=> {
      this.router.navigateByUrl("/signin-signup");
    }),20;
    alert("You have successfully logged out");
    this.userService.logout(this.currentUser.userId).subscribe(
      data=> {
        console.log(data);
      }
    );
  }

}
