import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationComponent } from './notification.component';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { BadgeModule } from 'primeng/badge';
import { OrderListModule } from 'primeng/orderlist';
import { TagModule } from 'primeng/tag';
import { ScrollPanelModule } from 'primeng/scrollpanel';



@NgModule({
  declarations: [
    NotificationComponent
  ],
  exports: [
    NotificationComponent
  ],
  imports: [
    CommonModule,
    OverlayPanelModule,
    ButtonModule,
    CardModule,
    BadgeModule,
    OrderListModule,
    TagModule,
    ScrollPanelModule
  ]
})
export class NotificationModule { }

