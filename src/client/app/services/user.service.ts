import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, map } from "rxjs";
import { ICat } from "src/client/model/Cat.model";
import { IPlan } from "src/client/model/Plan.model";
import { IUser } from "src/client/model/User.model";
import { IHealthEvent } from "src/client/model/healthEvent.model";
import { ISandbox } from "src/client/model/sandbox.model";

interface ICatsResponse {
    cats?: ICat[]
}

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private baseUrl = 'http://localhost:8080/user';
    
    constructor(
        private http: HttpClient
    ) {}

    login(user: IUser): Observable<string> {
        return this.http.post(`${this.baseUrl}/login`, user, {
            observe: 'response'
        })
        .pipe(map(response => response.body as string))
    }

    fetch(id: string): Observable<IUser> {
        return this.http.get(`${this.baseUrl}/${id}`, {
            observe: 'response'
        }).pipe(map((res: HttpResponse<IUser>) => {
            return res.body ?? {};
        }));
    }

    getCats(id: string): Observable<ICat[]> {
        return this.http.get(`${this.baseUrl}/${id}/cats`, {
            observe: 'response'
        }).pipe(map((res: HttpResponse<ICatsResponse>) => {
            return res.body?.cats ?? []; 
        }))
    }

    getPlans(id: string): Observable<IPlan[]> {
        return this.http.get(`${this.baseUrl}/${id}/plans`, {
            observe: 'response'
        }).pipe(map(res => res.body as IPlan[] ?? []))
    }

    getHealthEvents(id: string): Observable<IHealthEvent[]>{
        return this.http.get(`${this.baseUrl}/${id}/health-events`, {
            observe: 'response'
        }).pipe(map(res => {
            const events = res.body as IHealthEvent[] ?? []
            return events.map(event => {
                event.date = new Date(event.date ?? '');
                return event;
            });
        }));
    }

    getSandBoxes(id: string): Observable<ISandbox[]> {
        return this.http.get(`${this.baseUrl}/${id}/sandboxes`, {observe: 'response'}).pipe(map(
            res => {
                const sandboxes = res.body as ISandbox[] ?? [];
                return sandboxes.map(sandbox => {
                    sandbox.cleanDate = new Date(sandbox.cleanDate ?? '');
                    return sandbox;
                })
            }
        ))
    }
}