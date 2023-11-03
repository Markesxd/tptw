import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('../pages/login/login.component').then(c => c.LoginComponent)
  },
  {
    path: 'my-page',
    loadComponent: () => import('../pages/user-page/user-page.component').then(c => c.UserPageComponent)
  },
  {
    path: 'my-cats',
    loadComponent: () => import('../pages/cats/cats.component').then(c => c.CatsPageComponent)
  },
  {
    path: 'food',
    loadComponent: () => import('../pages/food/food.component').then(c => c.FoodComponent)
  },
  {
    path: 'health',
    loadComponent: () => import('../pages/health/health.component').then(c => c.HealthComponent)
  },
  {
    path: 'sandbox',
    loadComponent: () => import('../pages/sandboxes/sandboxes.component').then(c => c.SandboxesComponent)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
