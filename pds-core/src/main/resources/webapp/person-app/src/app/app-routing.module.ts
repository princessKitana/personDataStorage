import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddPersonComponent} from "./person/add-person/add-person.component";

const routes: Routes = [
  {path: 'list-persons', redirectTo: '/', pathMatch: 'full'},
  {path: 'add-person', component: AddPersonComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
