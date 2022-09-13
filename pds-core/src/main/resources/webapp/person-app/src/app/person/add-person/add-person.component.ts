import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PersonService} from "../person-service.service";


@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.less']
})
export class AddPersonComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private router: Router, private bookService: PersonService) {
  }

  addForm!: FormGroup;

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      personalId: ['', Validators.required],
      dateOfBirth: ['', Validators.required]
    });

  }

  public error!: string;

  onSubmit() {
    this.bookService.addPerson(this.addForm.value)
      .subscribe(data => {
        this.router.navigate(['list-persons']);
      }, error => {
        console.log(error);
        this.error = JSON.stringify(error, null, '\t');
      });
  }

}
