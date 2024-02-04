import { Component, Input, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-character',
  templateUrl: './character.component.html',
  styleUrls: ['./character.component.css'],
})
export class CharacterComponent implements OnInit {
  constructor(private ApiService: ApiService, private route: ActivatedRoute) {}

  public character: any = null;
  public id: number = 0;

  ngOnInit(): void {
    //get id from url
    this.route.params.subscribe((params) => {
      this.id = params['id'];
    });

    this.fetchCharacter(this.id);
  }

  fetchCharacter(id: number) {
    this.ApiService.getCharacter(id).subscribe((response) => {
      this.character = response;
    });
  }
}
