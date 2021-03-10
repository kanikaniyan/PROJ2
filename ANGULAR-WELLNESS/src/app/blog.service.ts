import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from './blog';

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  blog: Blog=new Blog();

  private baseUrl = 'http://localhost:8080/api/';

  constructor(private http:HttpClient) { }

  addBlog(blog: object): Observable<object> {
    return this.http.post(`${this.baseUrl}`+'add-blog', blog);
  }

  approvedBlogs(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'blog-list');
  }

}
