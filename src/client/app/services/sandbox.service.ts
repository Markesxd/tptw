import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CookieService } from "ngx-cookie-service";
import { Observable } from "rxjs";
import { ISandbox } from "src/client/model/sandbox.model";

@Injectable({providedIn:'root'})
export class SandboxService {
    private readonly baseUrl = "http://localhost:8080/sandbox"

    constructor(
        private http: HttpClient,
        private cookieService: CookieService
    ) {}

    create(sandbox: ISandbox): Observable<Object> {
        return this.http.post(this.baseUrl, sandbox, {
            headers: this.getHeaders(),
        });
    }

    delete(id: number): Observable<Object> {
        return this.http.delete(`${this.baseUrl}/${id}`, {headers: this.getHeaders()});
    }

    patch(sandbox: ISandbox): Observable<Object> {
        return this.http.put(this.baseUrl, sandbox, {headers: this.getHeaders()});
    }

    clean(sandbox: ISandbox): Observable<Object> {
        return this.http.patch(`${this.baseUrl}/${sandbox.id}/clean`, {}, {headers: this.getHeaders()});
    }

    private getHeaders(): HttpHeaders {
        const headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.cookieService.get('token'));
        return headers; 
    }
}