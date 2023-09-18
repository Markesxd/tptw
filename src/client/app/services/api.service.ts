import { Injectable } from "@angular/core";
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import { Observable, map } from "rxjs";
import { ICat } from "src/client/model/Cat";
import { NumberSymbol } from "@angular/common";

interface IResponse {
    content: any,
    empty: boolean,
    first: boolean,
    last: boolean,
    number: number,
    size: number,
    totalElements: number,
    totalPages: Number
}

@Injectable({
    providedIn: 'root'
})
export class ApiService {
    base = 'http://localhost:8080';
    constructor(
        private http: HttpClient
    ) {}

    get(resource: string, params?: any): Observable<IResponse> {
        return this.http.get(`${this.base}/${resource}`, {
            observe: 'response',
            params
        }).pipe(map(resp => resp.body as IResponse ?? {}));
    }

    post(resource: string, body: any, params?: any): Observable<HttpResponse<Object>> {
        return this.http.post(`${this.base}/${resource}`, body, {
            observe: 'response',
            params,
        });
    }
}