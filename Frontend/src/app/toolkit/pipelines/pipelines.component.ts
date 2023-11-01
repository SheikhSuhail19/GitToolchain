import { Component, OnInit } from '@angular/core';
import { PipelineService } from './pipelines.service';
import { Pipeline } from 'src/app/models/Pipeline';
import {
    DANGER_TEXT,
    FAILED_TEXT,
    INFO_TEXT,
    PIPELINE_AGO_LABEL,
    PIPELINE_HOUR_LABEL,
    PIPELINE_MINUTE_LABEL,
    PIPELINE_SECOND_LABEL,
    ROLE_DESCRIPTIONS_DEVELOPER,
    ROLE_DESCRIPTIONS_GUEST,
    ROLE_DESCRIPTIONS_MAINTAINER,
    ROLE_DESCRIPTIONS_MINIMAL_ACCESS,
    ROLE_DESCRIPTIONS_NO_ACCESS,
    ROLE_DESCRIPTIONS_OWNER,
    ROLE_DESCRIPTIONS_REPORTER,
    SUCCESS_TEXT,
} from 'src/utils/constants';

@Component({
    selector: 'app-pipelines',
    templateUrl: './pipelines.component.html',
    styleUrls: ['./pipelines.component.css'],
})
export class PipelinesComponent implements OnInit {
    pipelines: Pipeline[];
    totalPipelinesLength: number;
    repoId: number;

    roleDescriptions: { [key: number]: string } = {
        0: ROLE_DESCRIPTIONS_NO_ACCESS,
        5: ROLE_DESCRIPTIONS_MINIMAL_ACCESS,
        10: ROLE_DESCRIPTIONS_GUEST,
        20: ROLE_DESCRIPTIONS_REPORTER,
        30: ROLE_DESCRIPTIONS_DEVELOPER,
        40: ROLE_DESCRIPTIONS_MAINTAINER,
        50: ROLE_DESCRIPTIONS_OWNER,
    };

    constructor(private pipelineService: PipelineService) {}

    setPipelines(page: number) {
        this.pipelineService
            .getPipelines(this.repoId, page)
            .subscribe((data) => {
                this.pipelines = data?.pipelines;
                this.totalPipelinesLength = data?.totalLength;
            });
    }

    ngOnInit(): void {
        this.setPipelines(0);
    }

    getSeverity(status: string) {
        switch (status) {
            case SUCCESS_TEXT:
                return SUCCESS_TEXT;
            case FAILED_TEXT:
                return DANGER_TEXT;
            default:
                return INFO_TEXT;
        }
    }

    getTimeDiff(date: string) {
        const dateObj = new Date(date);
        const currentDate = new Date();
        const timestamp = dateObj.getTime();
        const currentTimestamp = currentDate.getTime();
        const timeDifference = currentTimestamp - timestamp;

        const seconds = Math.floor(timeDifference / 1000);
        const minutes = Math.floor(seconds / 60);
        const hours = Math.floor(minutes / 60);
        const days = Math.floor(hours / 24);

        if (days > 0) {
            return this.parseDate(date);
        } else if (hours > 0) {
            return `${hours} ${PIPELINE_HOUR_LABEL}${
                hours > 1 ? 's' : ''
            } ${PIPELINE_AGO_LABEL}`;
        } else if (minutes > 0) {
            return `${minutes} ${PIPELINE_MINUTE_LABEL}${
                minutes > 1 ? 's' : ''
            } ${PIPELINE_AGO_LABEL}`;
        } else {
            return `${seconds} ${PIPELINE_SECOND_LABEL}${
                seconds > 1 ? 's' : ''
            } ${PIPELINE_AGO_LABEL}`;
        }
    }
    parseDate(created_at: string) {
        const newDate = new Date(created_at);
        return created_at ? newDate.toDateString() : '';
    }

    setRepoId(repoId: any) {
        this.repoId = repoId;
        this.setPipelines(0);
    }

    onPageChange(page: number) {
        this.setPipelines(page);
    }
}
