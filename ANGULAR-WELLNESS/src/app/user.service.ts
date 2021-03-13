import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs'; 
import { User } from './user';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  user: User=new User();
  
  private baseUrl = 'http://localhost:8080/api/';  
  
  constructor(private http:HttpClient) { 
  
  }
  
    checkUser(user: object): Observable<any> {
      return this.http.post(`${this.baseUrl}`+"validate-user", user);
    }

    activateUser(userId: number): Observable<object> {
      return this.http.post(`${this.baseUrl}/activate-user/${userId}` , { responseType: 'text' });
    }

    deActivateUser(userId: number): Observable<object> {
      return this.http.post(`${this.baseUrl}/deactivate-user/${userId}` , { responseType: 'text' });
    }

    getUserList(): Observable<any> {
      return this.http.get(`${this.baseUrl}`+'user-list');
    }

    getDeactiveUserList(): Observable<any> {
      return this.http.get(`${this.baseUrl}`+'deactivate-list');
    }

    getActiveUserList(): Observable<any> {
      return this.http.get(`${this.baseUrl}`+'activate-list');
    }

    addUser(user: object): Observable<object> {  
      return this.http.post(`${this.baseUrl}`+'save-user', user);  
    }  
    
    deleteUser(userId: number): Observable<any> {
      return this.http.delete(`${this.baseUrl}/delete-user/${userId}`, { responseType: 'text' });  
    }  
    
    getUser(userId: number): Observable<object> {  
      return this.http.get(`${this.baseUrl}/user/${userId}`);  
    }  
    
    updateUser(userId: number, value: any): Observable<object> {  
      return this.http.post(`${this.baseUrl}/update-user/${userId}`, value);  
    }

    uploadImage(uploadImageData, userId: number): Observable<object> {
      return this.http.post(`http://localhost:8080/image/upload-image/${userId}`, uploadImageData);
    }

    downloadImage(userId: number): Observable<object> {
      return this.http.get(`http://localhost:8080/image/downloadFile/${userId}`);
    }

    getAllImages(): Observable<any> {
      return this.http.get(`http://localhost:8080/image/all-images`);
    }

    removeImage(userId: number): Observable<object> {
      return this.http.delete(`http://localhost:8080/image/remove-image/${userId}`);
    }

    logout(userId: number): Observable<any> {
      return this.http.post(`${this.baseUrl}/logout/${userId}`, { responseType: 'text' });  
    } 
  
}

