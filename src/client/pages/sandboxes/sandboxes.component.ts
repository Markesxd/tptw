import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ISandbox } from 'src/client/model/sandbox.model';
import { FormatDate } from 'src/client/app/Pipes/FormatDate.pipe';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalSandboxComponent } from './modal-sandbox/modal-sandbox.component';
import { UserService } from 'src/client/app/services/user.service';
import { SandboxService } from 'src/client/app/services/sandbox.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sandboxes',
  standalone: true,
  imports: [CommonModule, FormatDate, NgbModule],
  templateUrl: './sandboxes.component.html',
  styleUrls: ['./sandboxes.component.css']
})
export class SandboxesComponent {

  sandboxes?: ISandbox[];
  private userId = '';

  constructor(
    private modalService: NgbModal,
    private userService: UserService,
    private sandboxService: SandboxService,
    private cookieService: CookieService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userId = this.cookieService.get('id');
    this.load();
  }

  openModal(): void {
    const ref = this.modalService.open(ModalSandboxComponent, {centered: true});
    ref.closed.subscribe(sandbox => {
      sandbox.user = {id:this.userId};
      this.sandboxService.create(sandbox).subscribe(() => this.load());
    })
  }

  cleanBox(sandbox: ISandbox): void {
    this.sandboxService.clean(sandbox).subscribe(() => {
      sandbox.cleanDate = new Date();
    });
  }

  deleteBox(sandbox: ISandbox): void {
    if(!sandbox.id) {
      throw new Error("Sandbox missing id");
    }
    this.sandboxService.delete(sandbox.id).subscribe(() => this.load());
  }

  load(): void {
    if(this.userId === '') {
      this.router.navigate(['/']);
      return;
    }
    this.userService.getSandBoxes(this.userId).subscribe(_sandboxes => {
      this.sandboxes = _sandboxes;
    });
  }
}
