import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterUserComponent } from './register-user/register-user.component';
import { UserListComponent } from './user-list/user-list.component';
import { LoginUserComponent } from './login-user/login-user.component'; 
import { HomeComponent } from './home/home.component';
import { AppComponent } from './app.component';
import { DeActivateUserComponent } from './de-activate-user/de-activate-user.component';
import { ActivateUserComponent } from './activate-user/activate-user.component';
import { AdminPageComponent } from './admin-page/admin-page.component';

const routes: Routes = [
  //{ path: '', redirectTo: 'signin-signup', pathMatch: 'full' },
  { path: 'home-page', component: AppComponent },
  { path: 'signin-signup', component: HomeComponent },
  { path: 'login-user', component: LoginUserComponent },
  { path: 'home-page/:id', component: HomeComponent },
  { path: 'admin-page/:id', component: AdminPageComponent,
      children: [
        { path: 'user-list' , component: UserListComponent },
        { path: 'active-user-list', component: ActivateUserComponent },
        { path: 'deactive-user-list', component: DeActivateUserComponent }
      ]},
    
  { path: 'add-user', component: RegisterUserComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
