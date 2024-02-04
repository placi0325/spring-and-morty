# spring-and-morty
<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/placi0325/spring-and-morty">
    <img src="images/logo.png" alt="Logo" width="80">
  </a>

  <h3 align="center">Spring and Morty</h3>

  <p align="center">
    A fullstack project for browsing Rick and Morty related material with user management.
    <br />
    <a href="https://github.com/placi0325/spring-and-morty"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/placi0325/spring-and-morty">View Demo</a>
    ·
    <a href="https://github.com/placi0325/spring-and-morty/issues">Report Bug</a>
    ·
    <a href="https://github.com/placi0325/spring-and-morty/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

This is a full stack project which has Java Spring Boot as backend, and Angular for frontend.
There is user handling, adding favorites, and more to come!

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![Java][Java.img]][Java-url]
* [![Spring][Spring.img]][Spring-url]
* [![PostgreSQL][PostgreSQL.img]][PostgreSQL-url]
  
* [![TypeScript][TypeScript.img]][TypeScript-url]
* [![Angular][Angular.img]][Angular-url]


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To run this project, you need to have the following:

:one: Java <br>
:two: Apache Maven  <br>
:three: IntelliJ <br>
:four: PostgreSQL <br>

<div id="installation"></div>

### Installation :floppy_disk:	

:one: Clone the repository to your local machine
   ```sh
   git clone https://github.com/placi0325/spring-and-morty.git
   ```
:two: Create a database in PostgreSQL named for example: "spring_and_morty"

:three: Open the project in IntelliJ, and set up these environment variables: <br>
   * DATABASE_NAME= spring_and_morty <br>
   * DATABASE_USERNAME=_your username_<br>
   * DATABASE_PASSWORD=_your password_<br>
   * JWT_KEY=_your key_<br>

JWT (JSON Web Token) key is in HS256 format, which means
example key could be: Key-Must-Be-at-least-32-bytes-in-length!

:four: To start the frontend separately (optional), you have to go into the rick-and-morty folder and run the following command in the terminal:
  ```sh
      ng serve --open
   ```

:five: To start the backend, run the application by the "Run" button in the top right corner or with Shift + F10 shortcut

:six: For reaching the website through the backend, use:
  ```sh
      http://localhost:8080/
  ```
:seven: If the frontend is running, the website will be available on
  ```sh
      http://localhost:4200/
  ```
<br>

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

You can browse the locations, the characters, or the episodes of the show.
Anyone can register to the page, after logging in there are some future features available: adding favorite of each type of browseable content.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

:envelope:	**László** - peterfi.laci.0325@gmail.com  &nbsp;&nbsp;&nbsp;&nbsp; :point_right: &nbsp;&nbsp;&nbsp;&nbsp; [![László's LinkedIn][linkedin-shield]][linkedin-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/placi0325/spring-and-morty.svg?style=for-the-badge
[contributors-url]: https://github.com/placi0325/spring-and-morty/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/placi0325/spring-and-morty.svg?style=for-the-badge
[forks-url]: https://github.com/placi0325/spring-and-morty/network/members
[stars-shield]: https://img.shields.io/github/stars/placi0325/spring-and-morty.svg?style=for-the-badge
[stars-url]: https://github.com/placi0325/spring-and-morty/stargazers
[issues-shield]: https://img.shields.io/github/issues/placi0325/spring-and-morty.svg?style=for-the-badge
[issues-url]: https://github.com/placi0325/spring-and-morty/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/lászló-péterfi/
[product-screenshot]: images/demo.png

[Spring.img]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/

[Java.img]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/en/

[Angular.img]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/

[TypeScript.img]:  https://img.shields.io/badge/TypeScript-007AC?style=for-the-badge&logo=typescript&logoColor=white
[TypeScript-url]:  https://www.typescriptlang.org/ 

[PostgreSQL.img]: https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white
[PostgreSQL-url]: https://www.postgresql.org/

[Docker.img]: https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]: https://www.docker.com/


