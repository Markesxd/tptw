import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateHealthEventComponent } from './components/create-health-event/create-health-event.component';
import { IHealthEvent } from 'src/client/model/healthEvent.model';
import { HealthEventService } from 'src/client/app/services/healthEvent.service';
import { UserService } from 'src/client/app/services/user.service';
import { CookieService } from 'ngx-cookie-service';
import { FormatDate } from 'src/client/app/Pipes/FormatDate.pipe';
import { Router } from '@angular/router';

@Component({
  selector: 'app-health',
  standalone: true,
  imports: [CommonModule, CreateHealthEventComponent, FormatDate],
  templateUrl: './health.component.html',
  styleUrls: ['./health.component.css']
})
export class HealthComponent {
  showModal = false;
  events?: IHealthEvent[];
  editId?: number;
  constructor(
    private healthEventService: HealthEventService,
    private userService: UserService,
    private cookieService: CookieService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.cookieService.get('id');
    if(id === ''){
      this.router.navigate(['login']);
    }
    this.userService.getHealthEvents(id).subscribe(res => {
      this.events = res;
    })
  }

  openModal(id?: number): void {
    this.editId = id;
    this.showModal = true;
  }

  onModalClose(event?: IHealthEvent): void {
    this.showModal = false;
    if(!event) {
      return;
    }
    if(this.editId !== undefined){
      event.id = this.editId;
      this.healthEventService.put(event).subscribe(() => {
        this.events = this.events?.filter(evt => evt.id !== this.editId);
        this.events?.push(event);
        this.editId = undefined;
      })
    } else {
      this.healthEventService.post(event).subscribe((res) => {
        this.events?.push(res);
      });
    }
  }

  deleteEvent(event: IHealthEvent) {
    if(!event.id) {
      throw new Error('Evento sem id');
    }
    this.healthEventService.delete(event.id).subscribe(() => {
      this. events = this.events?.filter(evt => evt !== event);
    });
  }
}
