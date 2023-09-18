import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Cat, ICat } from 'src/client/model/Cat';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
  imports: [CommonModule, ReactiveFormsModule],
  standalone: true
})
export class ModalComponent {
  @Input()
  show = true;

  @Output()
  onClose = new EventEmitter<ICat | undefined>();

  editForm = this.fb.group({
    name: ['', [Validators.required]],
    birthday: '',
    gender: ''
  })

  constructor(
    protected fb: FormBuilder,
    private api: ApiService
  ) {}

  close(cat?: ICat): void {
    this.editForm.get('name')?.patchValue('');
    this.editForm.get('birthday')?.patchValue('');
    this.editForm.get('gender')?.patchValue('');
    this.onClose.emit(cat);
  }

  onSubmit():void {
    const cat = new Cat();
    cat.createFromForm(this.editForm);
    this.close(cat);
  }
}
