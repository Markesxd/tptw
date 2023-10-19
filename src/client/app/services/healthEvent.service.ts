import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, map } from "rxjs";
import { IHealthEvent } from "src/client/model/healthEvent.model";

@Injectable({
    providedIn: 'root'
})
export class HealthEventService {
    private baseUrl = 'http://localhost:8080/health-event';
    constructor(
        private http: HttpClient,
    ) {}

    post(healthEvent: IHealthEvent): Observable<IHealthEvent> {
        return this.http.post(this.baseUrl, healthEvent, {observe: 'response'})
        .pipe(map(res => res.body as IHealthEvent));
    }

    put(healthEvent: IHealthEvent): Observable<Object> {
        return this.http.put(this.baseUrl, healthEvent, {observe: 'response'});
    }

    delete(id: number): Observable<Object> {
        return this.http.delete(`${this.baseUrl}/${id}`, {observe: 'response'});
    }
}