import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerModel } from '../models/customer.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private apiServerUrl = '';

  constructor(private http: HttpClient) {}

  public getCustomers(): Observable<CustomerModel[]> {
    return this.http.get<any>(`${this.apiServerUrl}/customer/all`);
  }

  public addCustomer(customer: CustomerModel): Observable<CustomerModel> {
    return this.http.post<CustomerModel>(
      `${this.apiServerUrl}/customer/add`,
      customer
    );
  }

  public updateCustomer(customer: CustomerModel): Observable<CustomerModel> {
    return this.http.put<CustomerModel>(
      `${this.apiServerUrl}/customer/edit`,
      customer
    );
  }

  public deleteUser(customerId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/customer/delete/${customerId}`
    );
  }
}
