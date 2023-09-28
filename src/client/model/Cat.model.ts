import { FormGroup } from "@angular/forms";
import { IUser } from "./User.model";

export interface ICat {
    id?: number;
    name?: string;
    birthday?: Date;
    gender?: TypeGender;
    owner?: IUser;
    pic?: string;
}

export enum TypeGender {
    MALE = 'male',
    FEMALE = 'female'
}

export class Cat implements ICat {
    id?: number;
    name?: string;
    birthday?: Date;
    gender?: TypeGender;
    owner?: IUser;
    pic?: string;
    
    public createFromForm(formGroup: FormGroup, owner: IUser): Cat {
        this.name = formGroup.get('name')?.value;
        this.birthday = formGroup.get('birthday')?.value;
        this.gender = formGroup.get('gender')?.value;
        this.owner = owner;
        return this;
    }
}