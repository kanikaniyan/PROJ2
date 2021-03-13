import { Component, OnInit } from '@angular/core';
import { BlogService } from '../blog.service';

@Component({
  selector: 'app-not-approved-blogs',
  templateUrl: './not-approved-blogs.component.html',
  styleUrls: ['./not-approved-blogs.component.scss']
})
export class NotApprovedBlogsComponent implements OnInit {

  blogList: any;
  constructor(private blogService: BlogService) { }

  ngOnInit(): void {

    this.blogService.getNotApprovedBlogs().subscribe (
      data=> {
        if(data.length==0) {
          this.nothing=false;
        }
        else {
          this.nothing=true;  
          this.blogList=data;
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
  nothing: boolean;
  approveABlog(blogId: number) {
    this.blogService.approveABlog(blogId).subscribe (
      data=> {
        this.blogService.getNotApprovedBlogs().subscribe (
          data => {
            if(data.length==0) {
              this.blogList=data;
              this.nothing=false;
            }
            else {
              this.nothing=true;  
              this.blogList=data;
            }
          })
        })    
  }

}
