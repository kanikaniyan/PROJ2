import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Blog } from '../blog';
import { BlogService } from '../blog.service';

@Component({
  selector: 'app-approved-blogs',
  templateUrl: './approved-blogs.component.html',
  styleUrls: ['./approved-blogs.component.scss']
})
export class ApprovedBlogsComponent implements OnInit {

  blog: Blog;
  blogList: any;
  nothing: boolean;
  id: number;
  isUpdated=false;
  oneBlog: any;
  constructor(private blogService: BlogService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.isUpdated=false;
    const param = this.route.snapshot.params['paramKey'];
    this.id=parseInt(param);
    this.blogService.getAllApprovedBlogs(this.id).subscribe (
      data=> {
        if(data.length==0) {
          this.nothing=false;
          this.blogList=data;
          console.log("hello");
          
        }
        else {
          this.nothing=true;  
          this.blogList=data;
          console.log("hello2");
        }
      })
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
  
  delete(blogId: number) {
    this.blogService.deleteBlog(blogId).subscribe(
      data=> {
        this.blogService.getAllApprovedBlogs(this.id).subscribe(
          data=> {
            this.blogList=data;
          }
        );
      }
    );
  }

  updateBlog(blogId: number) {
    this.isUpdated=false;
    this.blogService.getBlogById(blogId).subscribe(
      data=> {
        this.oneBlog=data;
      }
    );
  }

  blogUpdateForm=new FormGroup({
    blogId: new FormControl(),
    blogTitle: new FormControl(),
    blogContent: new FormControl(),
    userId: new FormControl(),
    username: new FormControl()
  });

  updatBlog() {
    this.blog=new Blog();
    this.blog.blogId=this.BlogId.value;
    this.blog.blogTitle=this.BlogTitle.value;
    this.blog.blogContent=this.BlogContent.value;
    this.blog.blogPosted=new Date;
    this.blog.status="Approval Pending";
    this.blog.userId=this.UserId.value;
    this.blog.username=this.Username.value;

    this.blogService.updateBlog(this.blog.blogId, this.blog).subscribe(
      data=> {
        console.log(data);
        this.blogService.getAllApprovedBlogs(this.id).subscribe (
          data=> {
            if(data.length==0) {
              this.nothing=false;
            }
            else {
              this.nothing=true;
              this.isUpdated=true;  
              this.blogList=data;
            }
          })
      }
    );
  }

  get BlogId() {
    return this.blogUpdateForm.get('blogId');
  }

  get BlogTitle() {
    return this.blogUpdateForm.get('blogTitle');
  }

  get BlogContent() {
    return this.blogUpdateForm.get('blogContent');
  }

  get UserId() {
    return this.blogUpdateForm.get('userId');
  }

  get Username() {
    return this.blogUpdateForm.get('username');
  }

  changeIsUpdated() {
    this.isUpdated=true;
  }
  
}

