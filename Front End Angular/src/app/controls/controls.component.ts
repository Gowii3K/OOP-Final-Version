import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-controls',
  standalone: true,
  imports: [],
  templateUrl: './controls.component.html',
  styleUrl: './controls.component.css',
})
export class ControlsComponent {
  constructor(private http: HttpClient) {}

  //endpoint for stopping and starting threads
  stopUrl = 'http://localhost:8080/stop';
  //endpoint for restarting the system
  restartUrl = 'http://localhost:8080/restart';

  //boolean to check if threads is stopped or not to update button text
  isThreadsStopped = false;

  //stop ticketpool operations
  stopThreads() {
    this.http.post(this.stopUrl, {}).subscribe(
      (response) => {
        console.log('Threads pasued');
      },
      (error) => {
        console.log('Error Occured when stopping threds');
      }
    );

    if (this.isThreadsStopped) {
      this.isThreadsStopped = false;
    } else {
      this.isThreadsStopped = true;
    }
  }

  restartThreads() {
    this.http.post(this.restartUrl, {}).subscribe(
      (response) => {
        console.log('Threads restarted');
      },
      (error) => {
        console.log('Error Occured when resrating threds');
      }
      
    );
    this.isThreadsStopped=false;
  }
}
