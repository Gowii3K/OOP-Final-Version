import { NgFor,NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Customer, TicketPool, Vendor } from '../models/ticket-pool.interface';

@Component({
  selector: 'app-ticket-pool',
  standalone: true,
  imports: [NgFor,NgIf],
  templateUrl: './ticket-pool.component.html',
  styleUrl: './ticket-pool.component.css'
})
export class TicketPoolComponent {
  //array of customer objects
  @Input()customers!:Customer[];
  //array of vendor objects
  @Input()vendors!:Vendor[];
  //ticketpool of the event
  @Input()ticketpool!:TicketPool;

  

}
