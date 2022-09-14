import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddPersonComponent} from "./person/add-person/add-person.component";
import {GetPersonComponent} from "./person/get-person/get-person.component";

const routes: Routes = [
  {path: 'list-persons', redirectTo: '/', pathMatch: 'full'},
  {path: 'add-person', component: AddPersonComponent},
  {path: 'get-person', component: GetPersonComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
