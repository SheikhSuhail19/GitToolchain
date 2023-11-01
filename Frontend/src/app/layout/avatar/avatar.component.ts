import { Component, Input } from '@angular/core';
import { DEFAULT_MESSAGE_HEY, SOPRA_BANKING_EMAIL } from 'src/utils/constants';

@Component({
    selector: 'app-avatar',
    templateUrl: './avatar.component.html',
    styleUrls: ['./avatar.component.css'],
})
export class AvatarComponent {
    sopraEmail = SOPRA_BANKING_EMAIL;
    defaultMessage = DEFAULT_MESSAGE_HEY;
    @Input() avatarUrl: string | undefined;
    @Input() name: string = '';
    @Input() username: string = '';
    getNameFirstLetter(name: string): string {
        return name.charAt(0).toUpperCase();
    }
}
