import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CustomerModel } from './models/customer.model';
import { CustomerService } from './services/customer.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  title = 'distributor-app-frontend';

  public customers: CustomerModel[] = [];

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.getCustomers();
  }

  public getCustomers(): void {
    this.customerService.getCustomers().subscribe(
      (response: CustomerModel[]) => {
        this.customers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
