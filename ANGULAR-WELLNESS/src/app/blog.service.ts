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

  getBlogById(blogId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/oneBlog/${blogId}`);
  }

  getAllApprovedBlogs(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/allApprovedBlogs/${userId}`);
  }

  getNotApprovedBlogs(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'notApprovedBlogs');
  }

  approveABlog(blogId: number): Observable<object> {
    return this.http.post(`${this.baseUrl}/approveABlog/${blogId}`, { responseType: 'text' });
  }

  approvedBlogs(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'blog-list');
  }

  updateBlog(blogId: number,  blog): Observable<any> {
    return this.http.post(`${this.baseUrl}/update-blog/${blogId}`, blog);
  }

  deleteBlog(blogId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete-blog/${blogId}`);
  }

  addLike(blogId: number, bUserId:number, userId:number): Observable<any> {
    return this.http.post(`${this.baseUrl}/add-like/${userId}/${blogId}/${bUserId}`, { responseType: 'text' });
  }
  
  removeLike(blogId: number, userId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/remove-like/${userId}/${blogId}`, { responseType: 'text' });  
  }

  getAllLikesById(userId: number): Observable<object>{
    return this.http.get(`${this.baseUrl}/like-list-by-id/${userId}`);
  }

  getLikesByUserId(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/likes-for-user/${userId}`);
  }
}
