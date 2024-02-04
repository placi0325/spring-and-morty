import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CharactersComponent } from './characters/characters.component';
import { HeaderComponent } from './header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { PageSwitcherComponent } from './page-switcher/page-switcher.component';
import { LocationsComponent } from './locations/locations.component';
import { EpisodesComponent } from './episodes/episodes.component';
import { CharacterComponent } from './character/character.component';
import { LocationComponent } from './location/location.component';
import { LoginComponent } from './login/login.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CharactersComponent,
    HeaderComponent,
    PageSwitcherComponent,
    LocationsComponent,
    EpisodesComponent,
    CharacterComponent,
    LocationComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
