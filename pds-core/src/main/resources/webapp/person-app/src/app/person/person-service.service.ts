import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private baseUrl = 'http://localhost:8081';
  private getAllPersonsEndpoint = '/person/getAllPersons';
  private addPersonEndpoint = '/person/addPerson';
  private getPersonByParamEndpoint = '/person/findPersonsByParams';

  constructor(private http: HttpClient) {
  }

  getAllPersons(): Observable<any> {
    return this.http.get(`${this.baseUrl + this.getAllPersonsEndpoint}`);
  }

  addPerson(person: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl + this.addPersonEndpoint}`, person);
  }

  getPerson(person: Object): Observable<any> {
    return this.http.post(`${this.baseUrl + this.getPersonByParamEndpoint}`, person);
  }

}
