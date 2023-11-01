
export interface Commit {
  id:number;
  short_id:string;
  created_at:string;
  title:string;
  message:string;
  author_name:string;
  author_email:string;
  committer_name:string;
  committer_email:string;
  committed_date:string;
  web_url:string;
}
