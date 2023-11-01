import { Component, Input, ViewEncapsulation } from '@angular/core';
import { MenuItem, MessageService } from 'primeng/api';
import { DEFAULT_MESSAGE_HEY } from 'src/utils/constants';
@Component({
    selector: 'app-entity-card',
    templateUrl: './entity-card.component.html',
    styleUrls: ['./entity-card.component.css'],
    providers: [MessageService],
})
export class EntityCardComponent {
    defaultMessage = DEFAULT_MESSAGE_HEY;
    @Input() heading!: string;
    @Input() authorName = '';
    @Input() subHeadings!: Array<Array<string>>;
    @Input() showMergeBtn = false;
    @Input() mergeUrl: string;
    @Input() web_url: string;
    @Input() subheadingColor = 'grey';
    @Input() headingColor = 'black';
    @Input() headingFontSize = '14px';
    @Input() subheadingFontSize = '12px';
    @Input() showBottomDivider!: boolean;
    @Input() labels!: Array<Array<string>>;
    @Input() authorEmail = '';
    items!: MenuItem[];
}
