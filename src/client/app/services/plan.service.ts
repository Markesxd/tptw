import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { IPlan } from "src/client/model/Plan.model";

@Injectable({
    providedIn: 'root'
})
export class PlanService {
    private baseUrl = 'http://localhost:8080/plan';
    
    constructor(
        private http: HttpClient
    ) {}

    post(plan: IPlan): Observable<HttpResponse<Object>> {
        return this.http.post(this.baseUrl, plan, {
            observe: 'response'
        });
    } 
}