import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CookieService } from "ngx-cookie-service";
import { Observable } from "rxjs";
import { IPlan } from "src/client/model/Plan.model";

@Injectable({
    providedIn: 'root'
})
export class PlanService {
    private baseUrl = 'http://localhost:8080/plan';
    
    constructor(
        private http: HttpClient,
        private cookieService: CookieService
    ) {}

    post(plan: IPlan): Observable<HttpResponse<Object>> {
        return this.http.post(this.baseUrl, plan, {
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