import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../../services/customer.service';

@Component({
  selector: 'app-customer-home',
  imports: [CommonModule],
  templateUrl: './customer-home.component.html',
  styleUrl: './customer-home.component.css',
})
export class CustomerHomeComponent implements OnInit {
  customerData: any;

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.customerService.getDashboardData().subscribe({
      next: (data) => (this.customerData = data),
      error: (err) => console.error('Error fetching customer data:', err),
    });
  }
}
