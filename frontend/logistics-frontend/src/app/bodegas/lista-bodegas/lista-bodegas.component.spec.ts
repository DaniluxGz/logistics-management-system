import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaBodegasComponent } from './lista-bodegas.component';

describe('ListaBodegasComponent', () => {
  let component: ListaBodegasComponent;
  let fixture: ComponentFixture<ListaBodegasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaBodegasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaBodegasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
