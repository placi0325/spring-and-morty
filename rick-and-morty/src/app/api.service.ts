import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private mainUrls = {
    character: "https://rickandmortyapi.com/api/character/",
    characters: "https://rickandmortyapi.com/api/character/?page=",
    location: "https://rickandmortyapi.com/api/location/",
    locations: "https://rickandmortyapi.com/api/location/?page=",
    episode: "https://rickandmortyapi.com/api/episode/",
    episodes: "https://rickandmortyapi.com/api/episode?page="
  };

    constructor(private http: HttpClient) {}

    public getCharacter(id: number): Observable<any> {
      let link = `${this.mainUrls.character}${id}`;
      return this.http.get(link);
    }

    public getCharacters(pageNumber: number, name: string, status: string): Observable<any> {
      let link = `${this.mainUrls.characters}${pageNumber}`;
      if (name.length != 0){
        link += `&name=${name}`;
      } 
      if(status.length != 0){
        link += `&status=${status}`;
      }
      console.log(link);
      return this.http.get(link);
    }

    public getLocation(id: number): Observable<any> {
      return this.http.get(`${this.mainUrls.location}${id}`);
    }
  
    public getLocations(pageNumber: number, name: string, type: string, dimension: string): Observable<any> {
      let link = `${this.mainUrls.locations}${pageNumber}`;

      if (name.length != 0){
        link += `&name=${name}`;
      } 

      if (type.length != 0){
        link += `&type=${type}`;
      } 

      if (dimension.length != 0){
        link += `&dimension=${dimension}`;
      } 
      return this.http.get(link);
    }

    public getEpisode(id: number): Observable<any> {
      return this.http.get(`${this.mainUrls.episode}${id}`);
    }

    public getEpisodes(pageNumber: number): Observable<any> {
      return this.http.get(`${this.mainUrls.episodes}${pageNumber}`);
    }
}
