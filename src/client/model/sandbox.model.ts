import { IUser } from "./User.model";

export interface ISandbox {
    id?: number;
    name: string;
    cleanDate: Date;
    user?: IUser
}