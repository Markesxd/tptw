import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, map } from "rxjs";
import { IUser } from "src/client/model/User";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private base = 'http://localhost:8080/user';
    
    constructor(
        private http: HttpClient
    ) {}

    login(user: IUser): Observable<string> {
        return this.http.post(`${this.base}/login`, user, {
            observe: 'response'
        })
        .pipe(map(response => response.body as string))
    }
}