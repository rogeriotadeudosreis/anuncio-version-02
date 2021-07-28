import { Profile } from "../profile/profile.model";

export interface User {
    id?: number
    name: string
    email: string
    password: string
    dtRegister: string
    profiles: Profile
}