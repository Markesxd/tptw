import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('../pages/login/login.component').then(c => c.LoginComponent)
  },
  {
    path: 'my-page',
    loadComponent: () => import('../pages/user-page/user-page.component').then(c => c.UserPageComponent)
  },
  {
    path: 'my-cats',
    loadComponent: () => import('../pages/cats/cats.component').then(c => c.CatsPageComponent)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
