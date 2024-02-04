import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { getImage } from '../getImage';

interface Location {
  id: number;
  name: string;
  type: string;
  image: string;
}

@Component({
  selector: 'app-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.css']
})
export class LocationsComponent implements OnInit {
  constructor(private ApiService: ApiService) { }

  public locationPage: number = 1;
  public locations: Location[] = [];
  public maxPage: number = 1;

  public name: string = '';
  public type: string = '';
  public dimension: string = '';


  ngOnInit(): void {
    this.fetchLocations(this.locationPage);
  }

  fetchLocations(pageNumber: number) {
    this.ApiService.getLocations(pageNumber, this.name, this.type, this.dimension).subscribe((response) => {
      this.maxPage = response.info.pages;
      this.locations = response.results;

      // Map the locations and assign images based on the type
      this.locations = response.results.map((location: Location) => {
        location.image = getImage(location.type);
        return location;
      });
    }, (error) => {
      this.locations = [];
    });
  }

  public setPage(pageNumber: number) {
    if (pageNumber >= 1 && pageNumber <= this.maxPage) {
      this.locationPage = pageNumber;
      this.fetchLocations(pageNumber);
    }
  }

  filterLocationsByName(name: string) {
    this.name = name;
    this.locationPage = 1;
    this.fetchLocations(this.locationPage);
  }

  filterLocationsByType(type: string) {
    this.type = type;
    this.locationPage = 1;
    this.fetchLocations(this.locationPage);
  }

  filterLocationsByDimension(dimension: string) {
    this.dimension = dimension;
    this.locationPage = 1;
    this.fetchLocations(this.locationPage);
  }

  dimensions: String[] = [
    "",
    "Replacement Dimension",
    "unknown",
    "Dimension C-137",
    "Fantasy Dimension",
    "Unknown dimension",
    "Post-Apocalyptic Dimension",
    "Cronenberg Dimension",
    "Dimension 5-126",
    "Testicle Monster Dimension",
    "Cromulon Dimension",
    "Dimension C-500A",
    "Dimension K-83",
    "Dimension J19ζ7",
    "Eric Stoltz Mask Dimension",
    "Evil Rick's Target Dimension",
    "Giant Telepathic Spiders Dimension",
    "Dimension K-22",
    "Dimension D-99",
    "Dimension D716",
    "Dimension D716-B",
    "Dimension D716-C",
    "Dimension J-22",
    "Dimension C-35",
    "Pizza Dimension",
    "Phone Dimension",
    "Chair Dimension",
    "Fascist Dimension",
    "Fascist Shrimp Dimension",
    "Fascist Teddy Bear Dimension",
    "Wasp Dimension",
    "Tusk Dimension",
    "Magic Dimension",
    "Merged Dimension",
  ];

  types: String[] = [
    "",
    "Planet",
    "Space station",
    "Microverse",
    "Diegesis",
    "Liquid",
    "Dream",
    "Mount",
    "Spacecraft",
    "Consciousness",
    "unknown",
    "Cluster",
    "TV",
    "Resort",
    "Fantasy town",
    "Dimension",
    "Menagerie",
    "Game",
    "Customs",
    "Daycare",
    "Dwarf planet (Celestial Dwarf)",
    "Miniverse",
    "Teenyverse",
    "Box",
    "Artificially generated world",
    "Machine",
    "Arcade",
    "Spa",
    "Quadrant",
    "Quasar",
    "Woods",
    "Non-Diegetic Alternative Reality",
    "Nightmare",
    "Asteroid",
    "Acid Plant",
    "Reality",
    "Death Star",
    "Base",
    "Elemental Rings",
    "Human",
    "Space",
    "Hell",
    "Police Department",
    "Country",
    "Memory",
  ]
}
