import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Url } from '../model/url.model';

@Injectable({
    providedIn: 'root',
})
export class HomeService {

    private readonly headers = { 'content-type': 'application/json' };

    constructor(private http: HttpClient) { }

    getAllUrls() {
        return this.http.get('http://localhost:8080/allUrls', { 'headers': this.headers });
    }

    createNewUrlRecord(url: Url) {
        return this.http.post('http://localhost:8080/', JSON.stringify(url), { 'headers': this.headers });
    }

}