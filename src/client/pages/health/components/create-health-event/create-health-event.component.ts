import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { FormatRepeatInterval } from 'src/client/app/Pipes/FormatRepeatInterval.pipe';
import { UserService } from 'src/client/app/services/user.service';
import { ICat } from 'src/client/model/Cat.model';
import { User } from 'src/client/model/User.model';
import { HealthEvent, IHealthEvent, ReapeatInterval } from 'src/client/model/healthEvent.model';

@Component({
  selector: 'create-health-event',
  templateUrl: './create-health-event.component.html',
  styleUrls: ['./create-health-event.component.css'],
  imports: [CommonModule, ReactiveFormsModule, FormatRepeatInterval],
  standalone: true
})
export class CreateHealthEventComponent {
  @Input()
  show = true;

  @Output()
  onClose = new EventEmitter<IHealthEvent | undefined>();

  repeatIntervalValues = (Object.values(ReapeatInterval) as ReapeatInterval[]).filter(o => !isNaN(o));

  editForm = this.fb.group({
    name: ['', [Validators.required]],
    date: [new Date, [Validators.required]],
    repeatInterval: ReapeatInterval.NO_REPEAT,
    cats: [[] as ICat[]]
  });

  cats?: ICat[];

  constructor(
    protected fb: FormBuilder,
    private cookieService: CookieService,
    private userService: UserService
  ) {}

  ngOnInit():void {
    const id = this.cookieService.get('id');
    this.userService.getCats(id).subscribe(cats => {
      this.cats = cats;
    })
  }

  close(healthEvent?: IHealthEvent): void {
    this.editForm.get('name')?.patchValue('');
    this.editForm.get('date')?.patchValue(new Date);
    this.editForm.get('repeatInterval')?.patchValue(ReapeatInterval.NO_REPEAT);
    this.editForm.get('cats')?.patchValue([]);
    this.onClose.emit(healthEvent);
  }

  onChangeCat(event: Event): void {
    const input = event.target as HTMLInputElement;
    if(input === null){
      return;
    }
    const id = Number(input.value);
    if(input.checked) {
      const cat = this.getCatById(id);
      if(!cat) {
        return;
      }
      this.pushCat(cat);
    } else {
      this.removeCat(id);
    }
  }

  onSubmit():void {
    const healthEvent = new HealthEvent;
    const user = new User;
    user.id = this.cookieService.get("id");
    healthEvent.user = user;
    healthEvent.createFromForm(this.editForm);
    this.close(healthEvent);
  }

  get formCats(): ICat[] {
    const cats = this.editForm.get('cats')?.value;
    if(!Array.isArray(cats)) {
      throw new Error("Cats is not an array!");
    }
    return cats;
  }

  getCatById(id: number): ICat | undefined {
    return this.cats?.find(cat => cat.id === id);
  }

  pushCat(cat: ICat): void {
    const cats = this.formCats;
    cats.push(cat);
    this.editForm.patchValue({cats});
  }

  removeCat(id: number): void {
    const cats = this.formCats;
    this.editForm.patchValue({
      cats: cats.filter(cat => cat.id !== id)
    })
  }
}
