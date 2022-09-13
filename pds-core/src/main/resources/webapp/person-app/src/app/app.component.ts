import {Component, OnInit} from '@angular/core';
import {PersonService} from './person/person-service.service';
import {Router} from "@angular/router";
import {Person} from "./person/Person";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit {
  title = 'Person data storage app';
  persons: Person[] | undefined;

  constructor(private router: Router, private personService: PersonService) {
  }

  getAllPersons() {
    this.personService.getAllPersons().subscribe(data => {
      this.persons = data;
    });
  }

  addPerson(): void {
    this.router.navigate(['add-person'])
      .then((e) => {
        if (e) {
          console.log("Navigation is successful!");
        } else {
          console.log("Navigation has failed!");
        }
      });
  };

  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getAllPersons();
    });
  }
}
