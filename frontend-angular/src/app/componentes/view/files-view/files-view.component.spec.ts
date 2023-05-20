import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilesViewComponent } from './files-view.component';

describe('FilesViewComponent', () => {
  let component: FilesViewComponent;
  let fixture: ComponentFixture<FilesViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FilesViewComponent]
    });
    fixture = TestBed.createComponent(FilesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
