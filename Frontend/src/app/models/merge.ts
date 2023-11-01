export interface Merge {
    id:number;
    iid:number;
    project_id:number;
    title:string;
    author:{
        id:number;
        username:string;
        name:string;
        state:string;
        avatar_url:string;
        web_url:string;
    };
    created_at:string;
    state:string;
    web_url:string;
    description:string;

}
