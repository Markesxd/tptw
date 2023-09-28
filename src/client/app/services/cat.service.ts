import { Injectable } from "@angular/core";
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import { Observable, map } from "rxjs";
import { ICat } from "src/client/model/Cat.model";
import { IPageableResponse } from "../../model/Pageable.model";

@Injectable({
    providedIn: 'root'
})
export class CatService {
    private base = 'http://localhost:8080/cat';
    
    constructor(
        private http: HttpClient
    ) {}

    get(params?: any): Observable<IPageableResponse<ICat>> {
        return this.http.get(this.base, {
            observe: 'response',
            params
        }).pipe(map(resp => resp.body as IPageableResponse<ICat> ?? {}));
    }

    post(body: any, params?: any): Observable<HttpResponse<Object>> {
        return this.http.post(this.base, body, {
            observe: 'response',
            params,
        });
    }
}