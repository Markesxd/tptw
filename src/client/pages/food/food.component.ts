import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from 'src/client/app/services/user.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { CreatePlanComponent } from './components/create-plan/create-plan.component';
import { IPlan } from 'src/client/model/Plan.model';
import { PlanService } from 'src/client/app/services/plan.service';

@Component({
  selector: 'app-food',
  standalone: true,
  imports: [CommonModule, CreatePlanComponent],
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.css']
})
export class FoodComponent implements OnInit {

  showModal = false;

  plans: IPlan[] = [];
  private _currentCard = 0;

  constructor(
    private userService: UserService,
    private cookieService: CookieService,
    private planService: PlanService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.cookieService.get("id");
    if(id === '') {
      this.router.navigate(['']);
      return;
    }
    this.userService.getPlans(id).subscribe(plans => {
      this.plans = plans;
    })
  }

  openModal():void {
    this.showModal = true;
  }

  onPreviousCard(): void {
    this.currentCard--;
  }

  onNextCard(): void {
    this.currentCard++;
  }

  handleModalClose(plan?: IPlan): void {
    if(plan){
      this.planService.post(plan).subscribe(() => {
        this.plans.push(plan);
      });
    }
    this.showModal = false;
  }

  get currentCard(): number {
    return this._currentCard;
  }

  set currentCard(i: number) {
    if(i < 0 || i > this.plans.length -1) {
      return;
    }
    this._currentCard = i;
  }
}
