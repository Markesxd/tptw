import { FormGroup } from "@angular/forms";
import { ICat } from "./Cat.model";
import { IUser } from "./User.model";

export interface IHealthEvent {
    id?: number;
    name?: string;
    date?: Date;
    repeatInterval?: ReapeatInterval
    user?: IUser;
    cats?: ICat[];
}

export class HealthEvent implements IHealthEvent {
    id?: number;
    name?: string;
    date?: Date;
    repeatInterval?: ReapeatInterval;
    user?: IUser;
    cats?: ICat[];

    createFromForm(formGroup: FormGroup): void {
        this.name = formGroup.get('name')?.value;
        this.date = formGroup.get('date')?.value;
        this.cats = formGroup.get('cats')?.value;
        this.repeatInterval = formGroup.get('repeatInterval')?.value;
    }
}

export enum ReapeatInterval {
    NO_REPEAT,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}