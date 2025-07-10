import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {HttpResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  constructor(private apiService: ApiService) { }

  public addAdherent(user: any): Observable<HttpResponse<any>> {
    return this.apiService.post<any>(
      "Adherent/addNewAdherent",
      user,
      { observe: 'response' }
    );
  }

  public addCoach(user : any) : Observable<any>{
    return  this.apiService.post<any>("", user,{ observe: 'response' })
  }

  public login(user : any) : Observable<any>{
    return  this.apiService.post<any>("v1/auth/authentication", user,{ observe: 'response' })
  }
}
