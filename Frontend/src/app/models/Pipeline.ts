export interface Pipeline {
    id: number;
    iid: number;
    project_id: number;
    sha: string;
    ref: string;
    status: string;
    source: string;
    created_at: string;
    updated_at: string;
    web_url: string;
    before_sha: string;
    tag: boolean;
    yaml_errors: null | string; // It can be either null or a string
    user: {
        id: number;
        username: string;
        name: string;
        state: string;
        avatar_url: null | string; // It can be either null or a string
        web_url: string;
    };
    started_at: string;
    finished_at: string;
    committed_at: null | string; // It can be either null or a string
    duration: number;
    queued_duration: number;
    coverage: null | number; // It can be either null or a number
    detailed_status: {
        icon: string;
        text: string;
        label: string;
        group: string;
        tooltip: string;
        has_details: boolean;
        details_path: string;
        illustration: null | string; // It can be either null or a string
        favicon: string;
    };
    name: null | string; // It can be either null or a string
}
