import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeActivateUserComponent } from './de-activate-user.component';

describe('DeActivateUserComponent', () => {
  let component: DeActivateUserComponent;
  let fixture: ComponentFixture<DeActivateUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeActivateUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeActivateUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
