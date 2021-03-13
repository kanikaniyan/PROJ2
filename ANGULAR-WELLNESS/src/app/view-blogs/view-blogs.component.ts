import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Blog } from '../blog';
import { BlogComments } from '../blog-comments';
import { BlogService } from '../blog.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-view-blogs',
  templateUrl: './view-blogs.component.html',
  styleUrls: ['./view-blogs.component.scss']
})
export class ViewBlogsComponent implements OnInit {

  blog : Blog=new Blog();
  blogComments: BlogComments=new BlogComments();

  id : number;
  currentUser : any;
  blogList: any;
  retrievedImage: any;
  base64Data: any;
  userList: any;
  imageList: any;

  constructor(private userService: UserService, private blogService: BlogService,
     private route: ActivatedRoute) {}
    
  ngOnInit() {
    this.userService.getUserList().subscribe(data=> {
      this.userList=data;
    });
    const param = this.route.snapshot.params['paramKey'];
    this.id=parseInt(param);
    this.userService.getUser(this.id).subscribe (
      data=> {
        this.currentUser=data;
        this.blogService.approvedBlogs().subscribe(
          data=> {
            this.blogList=data;
            this.blogService.getAllLikesById(this.currentUser.userId).subscribe(
              data=> {
                this.likeData=data;
                this.likeData.forEach(element => {
                  if(element.likes==true) {
                    this.dislike=true;
                  }
                  else {
                    this.dislike=false;
                  }
                });
              })
            });
          }
        );
        this.userService.getAllImages().subscribe(
          data=>{
            console.log(data)
            this.imageList=data;
          });  
  }
  
  convert: string;
  blogDate: Date;
  year: number;
  month: number;
  day: number;
  hours: number;
  minutes: number;
  seconds: number;

  posted(blogPosted) {
    for(var i in blogPosted) {
      this.year = blogPosted[0];
      this.day = blogPosted[1];
      this.month = blogPosted[2];
      this.hours = blogPosted[3];
      this.minutes = blogPosted[4];
      this.seconds = blogPosted[5];
      this.convert = this.day+"-"+this.month+"-"+this.year+" "+this.hours+":"+this.minutes+":"+this.seconds;
      this.blogDate = new Date(this.convert);
    }
  }

  dislike: boolean;
  likeData: any;

  liked(blogId: number, bUserId: number) {
    
    this.blogService.addLike(blogId, bUserId, this.currentUser.userId).subscribe(
      data => {
        this.blogService.getAllLikesById(this.currentUser.userId).subscribe(
          data=> {
            this.likeData=data;
            console.log(data);
            this.dislike=true;
          }
        );
      }
    );
  }

  disliked(blogId) {
    console.log(blogId + " kai");

    this.blogService.removeLike(blogId, this.currentUser.userId).subscribe(
      data => {
        console.log(data);
        this.blogService.getAllLikesById(this.currentUser.userId).subscribe(
          data=> {
            this.likeData=data;
            console.log(data);
            this.dislike=true;
          }
        );
      }
    );
  }

  convertimage(data) {
    this.base64Data = data;
    this.retrievedImage = 'data:image/png;base64,' + this.base64Data;
  }

}
