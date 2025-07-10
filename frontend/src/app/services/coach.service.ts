import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Coach } from '../models/coach.model';

@Injectable({
  providedIn: 'root'
})
export class CoachService {
  private endpoint = 'coaches';

  constructor(private apiService: ApiService) { }

  getAllCoaches(): Observable<Coach[]> {
    return this.apiService.get<Coach[]>(this.endpoint);
  }

  getCoachById(id: number): Observable<Coach> {
    return this.apiService.getById<Coach>(this.endpoint, id);
  }

  createCoach(coach: Coach): Observable<Coach> {
    return this.apiService.post<Coach>(this.endpoint, coach);
  }

  updateCoach(id: number, coach: Coach): Observable<Coach> {
    return this.apiService.put<Coach>(this.endpoint, id, coach);
  }

  deleteCoach(id: number): Observable<any> {
    return this.apiService.delete<any>(this.endpoint, id);
  }

  getCoachesBySpeciality(speciality: string): Observable<Coach[]> {
    return this.apiService.get<Coach[]>(`${this.endpoint}/speciality/${speciality}`);
  }
}
