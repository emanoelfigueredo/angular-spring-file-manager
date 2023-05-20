import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BotaoPageComponent } from './botao-page.component';

describe('BotaoPageComponent', () => {
  let component: BotaoPageComponent;
  let fixture: ComponentFixture<BotaoPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BotaoPageComponent]
    });
    fixture = TestBed.createComponent(BotaoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
