import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioEnvioTerrestreComponent } from './formulario-envio-terrestre.component';

describe('FormularioEnvioTerrestreComponent', () => {
  let component: FormularioEnvioTerrestreComponent;
  let fixture: ComponentFixture<FormularioEnvioTerrestreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormularioEnvioTerrestreComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioEnvioTerrestreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
