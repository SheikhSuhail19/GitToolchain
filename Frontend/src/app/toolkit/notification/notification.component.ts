import { Component } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';
import { HttpClient } from '@angular/common/http';
import {YOUR_MR, PENDING_MERGE_REQUESTS,WITH_ID,IS_PENDING} from "src/utils/constants"
@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent {
  visible: boolean = false;
    position: string = 'top-right'; // Default position
    
    your_mr = YOUR_MR;
    pending_mr = PENDING_MERGE_REQUESTS;
    

   
}
