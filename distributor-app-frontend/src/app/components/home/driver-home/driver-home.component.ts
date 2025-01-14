import { Component, OnInit } from '@angular/core';
import { DriverService } from '../../../services/driver.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-driver-home',
  imports: [CommonModule],
  templateUrl: './driver-home.component.html',
  styleUrl: './driver-home.component.css',
})
export class DriverHomeComponent implements OnInit {
  driverData: any;

  constructor(private driverService: DriverService) {}

  ngOnInit(): void {
    this.driverService.getDashboardData().subscribe({
      next: (data) => (this.driverData = data),
      error: (err) => console.error('Error fetching driver data:', err),
    });
  }
}
