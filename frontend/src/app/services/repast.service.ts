import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Repast, RepastType, RepastObjective } from '../models/repast.model';

@Injectable({
  providedIn: 'root'
})
export class RepastService {
  private endpoint = 'repasts';

  constructor(private apiService: ApiService) { }

  getAllRepasts(): Observable<Repast[]> {
    return this.apiService.get<Repast[]>(this.endpoint);
  }

  getRepastById(id: number): Observable<Repast> {
    return this.apiService.getById<Repast>(this.endpoint, id);
  }

  createRepast(repast: Repast): Observable<Repast> {
    return this.apiService.post<Repast>(this.endpoint, repast);
  }

  updateRepast(id: number, repast: Repast): Observable<Repast> {
    return this.apiService.put<Repast>(this.endpoint, id, repast);
  }

  deleteRepast(id: number): Observable<any> {
    return this.apiService.delete<any>(this.endpoint, id);
  }

  getRepastsByType(type: RepastType): Observable<Repast[]> {
    return this.apiService.get<Repast[]>(`${this.endpoint}/type/${type}`);
  }

  getRepastsByObjective(objective: RepastObjective): Observable<Repast[]> {
    return this.apiService.get<Repast[]>(`${this.endpoint}/objective/${objective}`);
  }

  getRepastsByTypeAndObjective(type: RepastType, objective: RepastObjective): Observable<Repast[]> {
    return this.apiService.get<Repast[]>(`${this.endpoint}/type/${type}/objective/${objective}`);
  }
}
