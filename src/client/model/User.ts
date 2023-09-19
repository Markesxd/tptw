import { FormGroup } from "@angular/forms";

export interface IUser {
    id?: string,
    name?: string,
    email?: string,
    password?: string
}

export class User implements IUser {
    id?: string;
    name?: string;
    email?: string;
    password?: string;

    public createFromForm(formGroup: FormGroup) {
        this.name = formGroup.get('name')?.value;
        this.email = formGroup.get('email')?.value;
        this.password = formGroup.get('password')?.value;
    }
}