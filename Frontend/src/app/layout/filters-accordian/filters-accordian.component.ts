import { Component, EventEmitter, Output } from '@angular/core';
import { BOOLEAN_OPTIONS } from 'src/utils/constants';

@Component({
    selector: 'app-filters-accordian',
    templateUrl: './filters-accordian.component.html',
    styleUrls: ['./filters-accordian.component.css'],
})
export class FiltersAccordianComponent {
    @Output() filterChangeEvent = new EventEmitter<any>();
    @Output() dateChangeEvent = new EventEmitter<any>();

    rangeDates: Date[] | undefined;
    option1: string[] = BOOLEAN_OPTIONS;
    canDevPush!: string;
    option2: string[] = BOOLEAN_OPTIONS;
    canDevMerge!: string;
    option3: string[] = BOOLEAN_OPTIONS;
    protectedOrNot!: string;
    value!: string;

    onDevPushChange(value: string) {
        this.filterChangeEvent.emit({ canPush: value });
    }

    onDevCanMerge(value: string) {
        this.filterChangeEvent.emit({ merged: value });
    }

    onProtectedOrNot(value: string) {
        this.filterChangeEvent.emit({ protected: value });
    }

    onDateSelect() {
        if (this.rangeDates[0] && this.rangeDates[1]) {
            this.dateChangeEvent.emit(this.rangeDates);
        }
    }
}
