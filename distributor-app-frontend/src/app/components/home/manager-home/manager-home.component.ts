import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ManagerService } from '../../../services/manager.service';

@Component({
  selector: 'app-manager-home',
  imports: [CommonModule],
  templateUrl: './manager-home.component.html',
  styleUrl: './manager-home.component.css',
})
export class ManagerHomeComponent implements OnInit {
  managerData: any;

  constructor(private managerService: ManagerService) {}

  ngOnInit(): void {
    this.managerService.getDashboardData().subscribe({
      next: (data) => (this.managerData = data),
      error: (err) => console.log('Error fetching manager data:', err),
    });
  }
}
