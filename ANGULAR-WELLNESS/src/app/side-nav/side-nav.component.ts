import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss']
})
export class SideNavComponent implements OnInit {
  
  id : number;
  currentUser:any;
  retrievedImage: any;
  base64Data: any;
  retrieveResponse: any;

  constructor(private userService: UserService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.userService.getUser(this.id).subscribe (
            data=> {
              this.currentUser=data;
              if(this.currentUser) {
                this.userService.downloadImage(this.currentUser.userId).subscribe(
                  res => {
                    this.retrieveResponse = res;
                    this.base64Data = this.retrieveResponse.data;
                    this.retrievedImage = 'data:image/png;base64,' + this.base64Data;
                  });
              }    
            }
          );
        }
      );
  }
}
