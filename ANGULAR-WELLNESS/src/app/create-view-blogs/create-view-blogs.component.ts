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
  
  cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [
          { title: 'Create a Blog', cols: 1, rows: 1 },
          { title: 'Card 2', cols: 1, rows: 1 },
          { title: 'Card 3', cols: 1, rows: 1 },
          { title: 'Card 4', cols: 1, rows: 1 }
        ];
      }

      return [
        { title: 'Create a Blog', cols: 2, rows: 1 },
        { title: 'Card 2', cols: 1, rows: 1 },
        { title: 'Card 3', cols: 1, rows: 2 },
        { title: 'Card 4', cols: 1, rows: 1 }
      ];
    })
  );

  blog : Blog=new Blog();
  blogComments: BlogComments=new BlogComments();

  id : number;
  currentUser : any;
  blogList: any;
  blogImage: any[];
  retrievedImage: any;
  base64Data: any;
  retrieveResponse: any;
  
  constructor(private breakpointObserver: BreakpointObserver, private userService: UserService, private blogService: BlogService,
     private route: ActivatedRoute) {}
    
  ngOnInit() {
    const param = this.route.snapshot.params['paramKey'];
    this.id=parseInt(param);
    this.userService.getUser(this.id).subscribe (
      data=> {
        this.currentUser=data;
      });
        this.blogService.approvedBlogs().subscribe(
          data=> {
            this.blogList=data;
            
            // this.blogList.forEach(element => {
            //   this.userService.downloadImage(element.userId).subscribe(
            //     res => {
            //       this.retrieveResponse = res;
            //       this.base64Data = this.retrieveResponse.data;
            //       this.retrievedImage = 'data:image/png;base64,' + this.base64Data;
            //       if(element.userId==this.retrieveResponse.userId){
            //       this.blogImage.push(this.retrievedImage);
            //       }
            //       console.log(this.blogImage);
            //     });
            // });
          }
        );
        
      
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

  dislike=false;

  liked(blogId) {
    console.log(blogId + " kai");

    this.blogService.addLike(blogId, this.currentUser.userId).subscribe(
      data => {
        console.log(data);
      }
    );

    $(".dislike").css({"opacity": "0", "visibility": "hidden", "transition": "visibility 0s 1s, opacity 0.2s linear"});
    $(".like").css({"visibility":"visible","opacity": "1", "transition": "opacity 0.8s linear"});
  }

  disliked(blogId) {
    console.log(blogId + " kai");

    this.blogService.removeLike(blogId, this.currentUser.userId).subscribe(
      data => {
        console.log(data);
      }
    );

    $(".dislike").css({"visibility":"visible","opacity": "1", "transition": "opacity 0.8s linear"});
    $(".like").css({"opacity": "0", "visibility": "hidden", "transition": "visibility 0s 1s, opacity 0.2s linear"});
  }

}