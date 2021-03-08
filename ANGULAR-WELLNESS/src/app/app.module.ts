import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {DataTablesModule} from 'angular-datatables';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';  
import { HttpClientModule } from '@angular/common/http';  
import {MatSidenavModule} from '@angular/material/sidenav';

import { RegisterUserComponent } from './register-user/register-user.component';
import { UserListComponent } from './user-list/user-list.component';
import { LoginUserComponent } from './login-user/login-user.component'; 

import { ConfirmEqualValidatorDirective } from './share/confirm-equal-validator.directive';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { FooterComponent } from './footer/footer.component';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { SideNavComponent } from './side-nav/side-nav.component';
import { ActivateUserComponent } from './activate-user/activate-user.component';
import { DeActivateUserComponent } from './de-activate-user/de-activate-user.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { LayoutModule } from '@angular/cdk/layout';
@NgModule({
  declarations: [
    AppComponent,
    RegisterUserComponent,
    UserListComponent,
    LoginUserComponent,
    ConfirmEqualValidatorDirective,
    HomeComponent,
    NavBarComponent,
    FooterComponent,
    SideNavComponent,
    ActivateUserComponent,
    DeActivateUserComponent,
    AdminPageComponent,
    ProfilePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    ReactiveFormsModule,
    HttpClientModule,
    DataTablesModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatDividerModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    LayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
