import { Commit } from './Commit';
export interface Branch {
    name: string;
    protected: boolean;
    default: boolean;
    merged: boolean;
    web_url: string;
    mergeUrl: string;
    commit: Commit;
    commitMessage: string;
    commitCommittedAt: string;
    commitUrl: string;

    authorName: string;
    shortId: string;
    labels: Array<Array<string>>;
}
