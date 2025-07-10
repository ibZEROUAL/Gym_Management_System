import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Exercise, ExerciseCategorie } from '../models/exercise.model';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {
  private endpoint = 'exercises';

  constructor(private apiService: ApiService) { }

  getAllExercises(): Observable<Exercise[]> {
    return this.apiService.get<Exercise[]>(this.endpoint);
  }

  getExerciseById(id: number): Observable<Exercise> {
    return this.apiService.getById<Exercise>(this.endpoint, id);
  }

  createExercise(exercise: Exercise): Observable<Exercise> {
    return this.apiService.post<Exercise>(this.endpoint, exercise);
  }

  updateExercise(id: number, exercise: Exercise): Observable<Exercise> {
    return this.apiService.put<Exercise>(this.endpoint, id, exercise);
  }

  deleteExercise(id: number): Observable<any> {
    return this.apiService.delete<any>(this.endpoint, id);
  }

  getExercisesByCategory(category: ExerciseCategorie): Observable<Exercise[]> {
    return this.apiService.get<Exercise[]>(`${this.endpoint}/category/${category}`);
  }

  getExercisesByName(name: string): Observable<Exercise[]> {
    return this.apiService.get<Exercise[]>(`${this.endpoint}/name/${name}`);
  }
}
