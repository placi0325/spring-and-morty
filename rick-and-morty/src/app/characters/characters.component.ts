import { Component, OnInit} from '@angular/core';
import { ApiService } from '../api.service';

interface Character {
  id: number;
  name: string;
  type: string;
  image: string;
}

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html',
  styleUrls: ['./characters.component.css']
})
export class CharactersComponent implements OnInit {
  constructor(private ApiService: ApiService){}

  public characterPage: number = 1;
  public characters: any = null;
  //public charactersCopy: any = null;
  public maxPage: number = 1;
  public name: string = '';
  public status: string = '';

  ngOnInit(): void {
    this.fetchCharacters(this.characterPage);
  }

  fetchCharacters(pageNumber: number) {
    this.ApiService.getCharacters(pageNumber, this.name, this.status).subscribe((response) => {
      this.maxPage = response.info.pages;
      this.characters = response.results;
      //this.charactersCopy = response.results;
    }, (error) => {
      this.characters = null;
    });
  }

  public setPage(pageNumber: number) {
    if (pageNumber >= 1 && pageNumber <= this.maxPage) {
      this.characterPage = pageNumber;
      this.fetchCharacters(pageNumber);
    }
  }

  filterCharactersByName(name: string){
    this.name = name;
    this.characterPage = 1;
    this.fetchCharacters(this.characterPage);
  }

  filterCharactersByStatus(status: string){
    this.status = status;
    this.characterPage = 1;
    this.fetchCharacters(this.characterPage);
  }
} 
