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
  result: any;

  constructor(public fb: FormBuilder, private router: Router, private personService: PersonService) {
  }

  getPersonForm = this.fb.group({
    personalId: [''],
    dateOfBirth: [''],
    firstName: [''],
    lastName: [''],
    gender: ['']
  })

  public error!: string;

  onSubmit() {
    this.error = '';
    this.persons = [];
    this.personService.getPerson(this.getPersonForm.value)
      .subscribe(
        data => {
          if (data.length != 0) {
            this.persons = data;
            this.result = 'Records found in DB:';
          } else {
            this.persons = undefined;
            this.result = 'No records found in DB';
          }
        }, error => {
          console.log(error);
          this.error = JSON.stringify(error, null, '\t');
        });
  }

}
