import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { ActivatedRoute } from '@angular/router';
import { getImage } from '../getImage';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent {
  constructor(private ApiService: ApiService, private route: ActivatedRoute) {}

  public location: any = null;
  public id: number = 0;

  ngOnInit(): void {
    //get id from url
    this.route.params.subscribe((params) => {
      this.id = params['id'];
    });

    this.fetchLocation(this.id);
  }

  fetchLocation(id: number) {
    this.ApiService.getLocation(id).subscribe((response) => {
      this.location = response;
      this.location.image = getImage(this.location.type);
    });
  }
}
