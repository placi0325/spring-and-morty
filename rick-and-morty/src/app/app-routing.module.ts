import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CharactersComponent } from './characters/characters.component';
import { LocationsComponent } from './locations/locations.component';
import { EpisodesComponent } from './episodes/episodes.component';
import { CharacterComponent } from './character/character.component';
import { LocationComponent } from './location/location.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' }, // Default route to '/home'
  { path: 'location/:id', component: LocationComponent },
  { path: 'locations', component: LocationsComponent },
  { path: 'character/:id', component: CharacterComponent },
  { path: 'characters', component: CharactersComponent },
  { path: 'episodes', component: EpisodesComponent },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
