import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

export interface User {
  id: number;
  email: string;
  password: string;
  role: string;
  locationIds: number[];
  characterIds: number[];
}

export enum Role {
  ADMIN = 'ADMIN',
  USER = 'USER',
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private TOKEN: string = '';
  private user: User = {
    id: 0,
    email: "",
    password: "",
    role: "",
    locationIds: [],
    characterIds: [],
  };

  urls = {
    login: "/auth/login-user"
  };

  public login(email: String, password: String): void {
    let body = {
      email: email,
      password: password
    }

    this.http.post(this.urls.login, body, { observe: 'response' })
      .subscribe(((response: HttpResponse<Object>) => {
        let token = response.headers.get("Authorization");
        let body = response.body;
        if (token) {
          this.TOKEN = token.substring(7);
          console.log(token);
          
        }

        if (body) {
          this.user = body as User;
          console.log(body);
        }
      }));
  }

  public logout(): void {
    this.TOKEN = "";
    this.user = {
      id: 0,
      email: "",
      password: "",
      role: "",
      locationIds: [],
      characterIds: [],
    };
  }

  public isUserLoggedIn(): boolean {
    return this.TOKEN.length !== 0 || this.user.id !== 0;
  }
}
