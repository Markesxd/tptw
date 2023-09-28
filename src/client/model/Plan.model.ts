import { FormGroup } from "@angular/forms";
import { ICat } from "./Cat.model";
import { IUser } from "./User.model";

export interface IPlan {
    id?: number;
    name?: string;
    user?: IUser;
    meals?: IMeal[];
    cats?: ICat[];
}

export class Plan implements IPlan {
    id?: number;
    name?: string;
    user?: IUser;
    meals?: IMeal[];
    cats?: ICat[];

    public createFromForm(formGroup: FormGroup): void {
        this.name = formGroup.get("name")?.value ?? "";
        this.meals = deserializeMeals(formGroup.get("meals")?.value ?? []);
        this.cats = formGroup.get("cats")?.value ?? [];
    }
}

export interface IMeal {
    id?: number;
    label?: string;
    plan?: IPlan;
}

export class Meal implements IMeal {
    id?: number;
    label?: string;
    plan?: IPlan;
}

function deserializeMeals(meals: string[]): IMeal[] {
    return meals.map(label => {
        const meal = new Meal;
        meal.label = label;
        return meal;
    });
}