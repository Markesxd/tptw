import { FormGroup } from "@angular/forms";

export interface ICat {
    id?: number;
    name?: string;
    birthday?: Date;
    gender?: TypeGender;
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
    pic?: string;
    
    public createFromForm(formGroup: FormGroup): Cat {
        this.name = formGroup.get('name')?.value;
        this.birthday = formGroup.get('birthday')?.value;
        this.gender = formGroup.get('gender')?.value;
        return this;
    }
}