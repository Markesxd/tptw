import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { ICat, TypeGender } from 'src/client/model/Cat.model';

@Component({
  selector: 'app-create-cat',
  templateUrl: './create-cat.component.html',
  styleUrls: ['./create-cat.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule]
})
export class CreateCatComponent {
  
  editForm = this.fb.group({
    name: ['', [Validators.required]],
    birthday: '',
    gender: ''
  })

  constructor(
    private fb: FormBuilder,
    private activeModal: NgbActiveModal,
    private cookieService: CookieService
  ) {}

    onSubmit(): void {
      this.activeModal.close(this.createFromForm());
    }

    createFromForm(): ICat {
      const cat = {} as ICat;
      cat.name = this.editForm.get('name')?.value ?? '';
      cat.birthday = new Date(this.editForm.get('birthday')?.value ?? '');
      cat.gender = (this.editForm.get('gender')?.value ?? '') as TypeGender;
      cat.owner = {
        id: this.cookieService.get('id')
      };
      return cat;
    }
}
