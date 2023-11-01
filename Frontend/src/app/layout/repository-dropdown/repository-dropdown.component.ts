import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Repository } from 'src/app/models/Repository';
import { RepositoryService } from './repository.service';
import { SELECT_REPO_TEXT } from 'src/utils/constants';

@Component({
    selector: 'app-repository-dropdown',
    templateUrl: './repository-dropdown.component.html',
    styleUrls: ['./repository-dropdown.component.css'],
})
export class RepositoryDropdownComponent implements OnInit {
    selectRepoText = SELECT_REPO_TEXT;
    @Output() repoChangeEvent = new EventEmitter<number>();

    repositories: Repository[] = [];
    selectedRepository!: Repository;

    constructor(private repositoryService: RepositoryService) {}

    ngOnInit() {
        this.getRepositoriesList();
    }

    private getRepositoriesList() {
        this.repositoryService.getRepositories().subscribe((data) => {
            this.repositories = data;
            this.selectedRepository = data[0];
            this.onChange(this.selectedRepository.id);
        });
    }

    onChange(repoID: number) {
        this.repoChangeEvent.emit(repoID);
    }
}
