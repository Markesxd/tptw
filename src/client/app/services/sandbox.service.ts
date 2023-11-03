import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ISandbox } from "src/client/model/sandbox.model";

@Injectable({providedIn:'root'})
export class SandboxService {
    private readonly baseUrl = "http://localhost:8080/sandbox"

    constructor(
        private http: HttpClient
    ) {}

    create(sandbox: ISandbox): Observable<Object> {
        return this.http.post(this.baseUrl, sandbox);
    }

    delete(id: number): Observable<Object> {
        return this.http.delete(`${this.baseUrl}/${id}`);
    }

    patch(sandbox: ISandbox): Observable<Object> {
        return this.http.put(this.baseUrl, sandbox);
    }

    clean(sandbox: ISandbox): Observable<Object> {
        return this.http.patch(`${this.baseUrl}/${sandbox.id}/clean`, {});
    }
}