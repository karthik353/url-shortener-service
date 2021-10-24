import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class RedirectService {

  constructor(private http: HttpClient) { }

  getOriginalUrl(key: string) {
    const headers = { 'content-type': 'application/json'} ;
      return this.http.post('http://localhost:8080/url/' + key, {}, {'headers': headers});
  }

}