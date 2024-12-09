import { HttpClient } from '@angular/common/http';
import { Component, OnInit,OnDestroy} from '@angular/core';
import { LogsComponent } from "./logs/logs.component";
import { TicketPoolComponent } from "./ticket-pool/ticket-pool.component";
import { ControlsComponent } from "./controls/controls.component";
import { Subscription,interval } from 'rxjs';
import {  switchMap } from 'rxjs/operators';
import { Customer, TicketPool, Vendor } from './models/ticket-pool.interface';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ LogsComponent, TicketPoolComponent, ControlsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  //array of customer objects
  customers!: Customer[]; 
  //array of vendor objects
  vendors!: Vendor[];
  //ticketpool of the event
  ticketpool!: TicketPool;
  //log messages
  logs!: string[];
  
  private pollingSubscription!: Subscription;
  
  constructor(private http: HttpClient) {}
  
  
  ngOnInit(): void {
    //poll the API every second
    this.pollingSubscription = interval(1000)
      .pipe(
        switchMap(() => this.http.get('http://localhost:8080/ticketPool'))
      )
      .subscribe((data: any) => {
        //assign data received from api to the respective variables
        this.customers = data.customers;
        this.vendors = data.vendors;
        this.ticketpool = data.ticketPool;
        this.logs = data.ticketPoolService.logs;
      });
  }
  
 
}