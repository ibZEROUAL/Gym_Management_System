import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:8082/api';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  get<T>(endpoint: string): Observable<T> {
    return this.http.get<T>(`${this.apiUrl}/${endpoint}`, this.httpOptions);
  }

  getById<T>(endpoint: string, id: number): Observable<T> {
    return this.http.get<T>(`${this.apiUrl}/${endpoint}/${id}`, this.httpOptions);
  }

  post<T>(endpoint: string, data: any, options: { observe: 'response' }): Observable<HttpResponse<T>>;
  post<T>(endpoint: string, data: any, options?: any): Observable<T>;
  post<T>(endpoint: string, data: any, options?: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${endpoint}`, data, options || this.httpOptions);
  }

  put<T>(endpoint: string, id: number | undefined, data: any): Observable<T> {
    return this.http.put<T>(`${this.apiUrl}/${endpoint}/${id}`, data, this.httpOptions);
  }

  delete<T>(endpoint: string, id: number): Observable<T> {
    return this.http.delete<T>(`${this.apiUrl}/${endpoint}/${id}`, this.httpOptions);
  }
}
