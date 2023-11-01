import { TestBed } from '@angular/core/testing';

import { ApiRepositoryServiceService } from './api-repository.service';

describe('ApiRepositoryServiceService', () => {
  let service: ApiRepositoryServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiRepositoryServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
