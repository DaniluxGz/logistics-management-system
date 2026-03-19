import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioEnvioMaritimoComponent } from './formulario-envio-maritimo.component';

describe('FormularioEnvioMaritimoComponent', () => {
  let component: FormularioEnvioMaritimoComponent;
  let fixture: ComponentFixture<FormularioEnvioMaritimoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormularioEnvioMaritimoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioEnvioMaritimoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
