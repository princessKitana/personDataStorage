import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PersonService} from "../person-service.service";
import {FormBuilder, Validators} from "@angular/forms";
import {Person} from "../Person";


@Component({
  selector: 'get-person',
  templateUrl: './get-person.component.html',
  styleUrls: ['./get-person.component.less']
})
export class GetPersonComponent {

  genders: any = ['MALE', 'FEMALE']
  persons: Person[] | undefined;

  constructor(public fb: FormBuilder, private router: Router, private personService: PersonService) {
  }

  getPersonForm = this.fb.group({
    personalId: [''],
    dateOfBirth: [''],
    gender: ['']
  })

  public error!: string;

  onSubmit() {
    this.personService.getPerson(this.getPersonForm.value)
      .subscribe(
        data => {
          this.persons = data;
        }, error => {
          console.log(error);
          this.error = JSON.stringify(error, null, '\t');
        });
  }

}
