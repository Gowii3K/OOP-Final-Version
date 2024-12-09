import { Component,Input } from '@angular/core';
import { NgFor} from '@angular/common';

@Component({
  selector: 'app-logs',
  standalone: true,
  imports: [NgFor],
  templateUrl: './logs.component.html',
  styleUrl: './logs.component.css'
})
export class LogsComponent {
  @Input()logs:any;
}
