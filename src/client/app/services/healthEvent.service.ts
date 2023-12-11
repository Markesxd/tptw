import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CookieService } from "ngx-cookie-service";
import { Observable, map } from "rxjs";
import { ICat } from "src/client/model/Cat.model";
import { IHealthEvent } from "src/client/model/healthEvent.model";

@Injectable({
    providedIn: 'root'
})
export class HealthEventService {
    private baseUrl = 'http://localhost:8080/health-event';
    constructor(
        private http: HttpClient,
        private cookieService: CookieService
    ) {}

    post(healthEvent: IHealthEvent): Observable<IHealthEvent> {
        return this.http.post(this.baseUrl, healthEvent, {
            headers: this.getHeaders(),
            observe: 'response'
        })
        .pipe(map(res => res.body as IHealthEvent));
    }

    put(healthEvent: IHealthEvent): Observable<Object> {
        return this.http.put(this.baseUrl, healthEvent, {
            headers: this.getHeaders(),
            observe: 'response'
        });
    }

    delete(id: number): Observable<Object> {
        return this.http.delete(`${this.baseUrl}/${id}`, {
            headers: this.getHeaders(),
            observe: 'response'
        });
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.cookieService.get('token'));
        return headers; 
    }
}