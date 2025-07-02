import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Program, ProgramLevel, ProgramObjective } from '../models/program.model';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {
  private endpoint = 'programs';

  constructor(private apiService: ApiService) { }

  getAllPrograms(): Observable<Program[]> {
    return this.apiService.get<Program[]>(this.endpoint);
  }

  getProgramById(id: number): Observable<Program> {
    return this.apiService.getById<Program>(this.endpoint, id);
  }

  createProgram(program: Program): Observable<Program> {
    return this.apiService.post<Program>(this.endpoint, program);
  }

  updateProgram(id: number, program: Program): Observable<Program> {
    return this.apiService.put<Program>(this.endpoint, id, program);
  }

  deleteProgram(id: number): Observable<any> {
    return this.apiService.delete<any>(this.endpoint, id);
  }

  getProgramsByLevel(level: ProgramLevel): Observable<Program[]> {
    return this.apiService.get<Program[]>(`${this.endpoint}/level/${level}`);
  }

  getProgramsByObjective(objective: ProgramObjective): Observable<Program[]> {
    return this.apiService.get<Program[]>(`${this.endpoint}/objective/${objective}`);
  }

  getProgramsByCoach(coachId: number): Observable<Program[]> {
    return this.apiService.get<Program[]>(`${this.endpoint}/coach/${coachId}`);
  }

  getVisiblePrograms(): Observable<Program[]> {
    return this.apiService.get<Program[]>(`${this.endpoint}/visible`);
  }
}
