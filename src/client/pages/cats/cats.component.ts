import { animate, state, style, transition, trigger } from '@angular/animations';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { CreateCatComponent } from 'src/client/app/components/create-cat/create-cat.component';
import { ModalComponent } from 'src/client/app/components/modal/modal.component';
import { CatService } from 'src/client/app/services/cat.service';
import { UserService } from 'src/client/app/services/user.service';
import { ICat } from 'src/client/model/Cat.model';

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
  private _currentCard = 0;
  private _id = '';

  constructor(
    private catService: CatService,
    private cookieService: CookieService,
    private userService: UserService,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this._id = this.cookieService.get('id');
    if(this._id === '') {
      this.router.navigate(['']);
    }
    this.load();
  }

  onNextCard(): void {
    this.currentCard++;
  }

  onPreviousCard(): void {
    this.currentCard--;
  }

  openModal(): void {
    const ref = this.modalService.open(CreateCatComponent, {centered: true});
    ref.closed.subscribe((cat: ICat) => {
      this.catService.post(cat).subscribe(res => {
        if(res.ok){
          this.pets.push(cat);
        }
      });
    })
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
    this.userService.getCats(this._id).subscribe(res => {
      this.pets = res;
    });
  }
}