import { User } from './user';

export interface Member {
    access_level: number;
    created_at: string;
    created_by: User;
    expires_at: string | null;
    id: number;
    username: string;
    name: string;
    state: string;
    avatar_url: string;
    web_url: string;
    membership_state: string;
}
