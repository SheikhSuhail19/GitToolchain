import { Component, Input } from '@angular/core';
@Component({
    selector: 'app-hovercard',
    templateUrl: './hovercard.component.html',
    styleUrls: ['./hovercard.component.css'],
})
export class HovercardComponent {
    @Input() cardData: any;
    labelItems: string[] = [];
    isMRCard = false;
    isIssueCard = false;
    isCommitCard = false;
    isBranchCard = false;
    allColors = ['info', 'warning', 'danger', 'success'];
    randomColor: string[] = [];
    coloredLables: string[][] = [];

    constructor() {}
    ngOnChanges() {
        this.isIssueCard = this.cardData.title === 'Issues';
        this.isMRCard = this.cardData.title === 'Merge Requests';
        this.isCommitCard = this.cardData.title === 'Total Commits';
        this.isBranchCard = this.cardData.title === 'Branches';
        this.labelItems = Object.entries(this.cardData.labels).map(
            ([key, value]) => `${key}: ${value}`
        );
        this.randomColor = Array.from(
            { length: this.labelItems.length },
            () =>
                this.allColors[
                    Math.floor(Math.random() * this.allColors.length)
                ]
        );
        this.coloredLables=[];
        for (let i = 0; i < this.labelItems.length; i++) {
            this.coloredLables.push([]);
            this.coloredLables[i][0] = this.labelItems[i];
            this.coloredLables[i][1] = this.randomColor[i];
        }
    }
}
