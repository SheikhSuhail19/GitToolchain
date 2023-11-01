export interface Group {
    name: string;
    protected: boolean;
    default: boolean;
    merged: boolean;
    web_url: string;
    mergeUrl: string;

    authorName: string;
    shortId: string;
    labels: Array<Array<string>>;
}
