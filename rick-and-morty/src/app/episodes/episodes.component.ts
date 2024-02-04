import { Component } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-episodes',
  templateUrl: './episodes.component.html',
  styleUrls: ['./episodes.component.css']
})
export class EpisodesComponent {
  constructor(private ApiService: ApiService){}

  public episodePage: number = 1;
  public episodes: any = null;
  public maxPage: number = 1;

  ngOnInit(): void {
    this.fetchepisodes(this.episodePage);
  }

  fetchepisodes(pageNumber: number) {
    this.ApiService.getEpisodes(pageNumber).subscribe((response) => {
      console.log(response);
      this.maxPage = response.info.pages;
      this.episodes = response.results;
    });
  }

  public setPage(pageNumber: number) {
    if (pageNumber >= 1 && pageNumber <= this.maxPage) {
      this.episodePage = pageNumber;
      this.fetchepisodes(pageNumber);
    }
  }
}
