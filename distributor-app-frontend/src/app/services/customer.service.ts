import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerModel } from '../models/customer.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private apiUrl = 'https://localhost:8080/customer';

  constructor(private http: HttpClient) {}

  getDashboardData(): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard`);
  }

  public getCustomers(): Observable<CustomerModel[]> {
    return this.http.get<any>(`${this.apiUrl}/all`);
  }

  public addCustomer(customer: CustomerModel): Observable<CustomerModel> {
    return this.http.post<CustomerModel>(`${this.apiUrl}/add`, customer);
  }

  public updateCustomer(customer: CustomerModel): Observable<CustomerModel> {
    return this.http.put<CustomerModel>(`${this.apiUrl}/edit`, customer);
  }

  public deleteUser(customerId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${customerId}`);
  }
}
