import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotApprovedBlogsComponent } from './not-approved-blogs.component';

describe('NotApprovedBlogsComponent', () => {
  let component: NotApprovedBlogsComponent;
  let fixture: ComponentFixture<NotApprovedBlogsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotApprovedBlogsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotApprovedBlogsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
