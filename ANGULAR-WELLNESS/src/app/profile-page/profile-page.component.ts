import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { FormControl, FormGroup } from '@angular/forms';
import { User } from '../user';
import { BlogService } from '../blog.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit{

  id: number;
  currentUser: any;
  user: User=new User();
  isUpdated=false;
  show=true;
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResponse: any;
  message: string;
  imageName: any;
  likeCounts: number;

  constructor(private breakpointObserver: BreakpointObserver, private userService: UserService, private route: ActivatedRoute,
    private blogService: BlogService) {}

  ngOnInit() {
    const param = this.route.snapshot.params['paramKey'];
    this.id=parseInt(param);
    this.userService.getUser(this.id).subscribe (
      data=> {
        this.currentUser=data;
        if(this.currentUser) {
          this.userService.downloadImage(this.currentUser.userId).subscribe(
            res => {
              if(res) {
                this.retrieveResponse = res;
                this.base64Data = this.retrieveResponse.data;
                this.retrievedImage = 'data:image/png;base64,' + this.base64Data;
              }else {
                console.log("No Image Found");
              }
            });
        }
      });
      
      this.blogService.getLikesByUserId(this.id).subscribe(
        data=> {
          this.likeCounts=data;
        }
      );
  }

  cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [
          { title: 'Information', cols: 1, rows: 2 },
          { title: 'Profile', cols: 1, rows: 1 }
        ];
      }

      return [
        { title: 'Profile', cols: 1, rows: 1 },
        { title: 'Information', cols: 1, rows: 2 },
        { title: 'Profile1', cols: 1, rows: 1 }
      ];
    })
  );

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
      this.userService.getUser(this.user.userId).subscribe(data=> {
        this.currentUser=data;
        setTimeout(()=> {
          location.reload();
        }, 1000);
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

  upload() {
    this.show=false;
  }

  public onFileChanged(event) {
    this.selectedFile=event.target.files[0];
  }

  onUpload() {
    console.log(this.selectedFile);

    const uploadImageData = new FormData();
    uploadImageData.append("imageFile", this.selectedFile, this.selectedFile.name);

    this.userService.uploadImage(uploadImageData, this.currentUser.userId)
      .subscribe((data) => {
        console.log(data);
        location.reload();
      });
  }

  delete() {
    this.userService.removeImage(this.currentUser.userId).subscribe(
      data=> {
        console.log(data);
        location.reload();
      }
    )
  }
}

