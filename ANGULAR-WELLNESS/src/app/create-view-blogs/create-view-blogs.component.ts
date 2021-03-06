import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Blog } from '../blog';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';
import { BlogService } from '../blog.service';
import { BlogComments } from '../blog-comments';

@Component({
  selector: 'app-create-view-blogs',
  templateUrl: './create-view-blogs.component.html',
  styleUrls: ['./create-view-blogs.component.scss']
})
export class CreateViewBlogsComponent implements OnInit{

  blog : Blog=new Blog();
  blogComments: BlogComments=new BlogComments();
  userList: any;

  id : number;
  currentUser : any;
  blogList: any;
  retrievedImage: any;
  base64Data: any;
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
      }
    ) 
  }

  blogForm= new FormGroup({
    title: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required])
  });

  posting() {
    this.blog=new Blog();
    
    this.blog.blogTitle=this.BlogTitle.value;
    this.blog.blogContent=this.BlogContent.value;
    this.blog.username=this.currentUser.username;
    this.blog.userId=this.currentUser.userId;
    this.blog.blogPosted=new Date;
    
    if(this.currentUser.role=="Admin") {
      this.blog.status="Posted by Admin";  
    } else {
      this.blog.status="Approval Pending";
    }
    

    this.blogService.addBlog(this.blog).subscribe(
      data=> {
        if(data){
          $(".form").css({"opacity": "0", "visibility": "hidden", "transition": "visibility 0s 1s, opacity 0.5s linear"});
          $(".success").css({"visibility":"visible","opacity": "1", "transition": "opacity 0.8s linear"});
          
          setTimeout(() => {
            $(".form").css({"visibility":"visible","opacity": "1", "transition": "opacity 0.8s linear"});
            $(".success").css({"opacity": "0", "visibility": "hidden", "transition": "visibility 0s 1s, opacity 0.5s linear"});
          },2000)
          
          console.log(data);
          this.blogService.approvedBlogs().subscribe(
            data=> {
              this.blogList=data;
            }
          );
          this.blogForm.reset();
        }
      }
    );
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

  get BlogTitle() {
    return this.blogForm.get("title");
  }

  get BlogContent() {
    return this.blogForm.get("description");
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

  disliked(blogId: number, bUserId: number) {

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