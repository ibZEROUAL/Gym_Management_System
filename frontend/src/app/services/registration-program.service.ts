import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { RegistrationProgram } from '../models/registration-program.model';

@Injectable({
  providedIn: 'root'
})
export class RegistrationProgramService {
  private endpoint = 'registrations';

  constructor(private apiService: ApiService) { }

  getAllRegistrations(): Observable<RegistrationProgram[]> {
    return this.apiService.get<RegistrationProgram[]>(this.endpoint);
  }

  getRegistrationById(id: number): Observable<RegistrationProgram> {
    return this.apiService.getById<RegistrationProgram>(this.endpoint, id);
  }

  createRegistration(registration: RegistrationProgram): Observable<RegistrationProgram> {
    return this.apiService.post<RegistrationProgram>(this.endpoint, registration);
  }

  updateRegistration(id: number, registration: RegistrationProgram): Observable<RegistrationProgram> {
    return this.apiService.put<RegistrationProgram>(this.endpoint, id, registration);
  }

  deleteRegistration(id: number): Observable<any> {
    return this.apiService.delete<any>(this.endpoint, id);
  }

  getRegistrationsByAdherent(adherentId: number): Observable<RegistrationProgram[]> {
    return this.apiService.get<RegistrationProgram[]>(`${this.endpoint}/adherent/${adherentId}`);
  }

  getRegistrationsByProgram(programId: number): Observable<RegistrationProgram[]> {
    return this.apiService.get<RegistrationProgram[]>(`${this.endpoint}/program/${programId}`);
  }

  getRegistrationsByStatus(status: string): Observable<RegistrationProgram[]> {
    return this.apiService.get<RegistrationProgram[]>(`${this.endpoint}/status/${status}`);
  }
}
