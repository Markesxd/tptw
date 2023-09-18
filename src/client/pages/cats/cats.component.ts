import { animate, state, style, transition, trigger } from '@angular/animations';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ModalComponent } from 'src/client/app/components/modal/modal.component';
import { ApiService } from 'src/client/app/services/api.service';
import { ICat } from 'src/client/model/Cat';

@Component({
  selector: 'cats-page',
  templateUrl: './cats.component.html',
  styleUrls: ['./cats.component.css'],
  standalone: true,
  imports: [CommonModule, ModalComponent],
  animations: [
    trigger('cardsAnimation', [
      state('left', style({transform: 'translateX(0)'})),
      state('right', style({transform: 'translateX(0)'})),
      transition('void => right', [
        style({transform: 'translateX(100%)'}),
        animate(300)
      ]),
      transition('void => left', [
        style({transform: 'translateX(-100%)'}),
        animate(300)
      ])
    ])
  ]
})
export class CatsPageComponent implements OnInit {
  pets: ICat[] = [];

  direction = 'right';
  showModal = false;
  private _currentCard = 0;

  constructor(
    private api: ApiService
  ) {}

  ngOnInit(): void {
    this.load();
  }

  onNextCard(): void {
    this.currentCard++;
  }

  onPreviousCard(): void {
    this.currentCard--;
  }

  openModal(): void {
    this.showModal = true;
  }

  closeModal(cat?: ICat): void {
    this.showModal = false;
    if(cat){
      this.api.post('cat', cat).subscribe(res => {
        if(res.ok){
          this.pets.push(cat);
        }
      })
    }
  }

  set currentCard(i: number) {
    if(i < 0 || i > this.pets.length - 1) {
      return;
    }
    this.direction = i < this._currentCard ? 'left' : 'right';
    this._currentCard = i;
  }

  get currentCard(): number {
    return this._currentCard;
  }

  get hasCats(): boolean {
    return this.pets.length !== 0;
  }

  getCatAge(cat: ICat): string {
    if(!cat.birthday){
      return '';
    }
    const today = new Date();
    const birthday = new Date(cat.birthday);
    const dif = today.getTime() - birthday.getTime();
    const yearDif = Math.floor(dif / (1000 * 60 * 60 * 24 * 365))
    return `${yearDif} anos`;
  }



  private load(): void {
    this.api.get('cat').subscribe(res => {
      if(!res){
        return;
      }
      this.pets = res.content as ICat[];
    });
  }
}
