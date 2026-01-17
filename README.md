# Movie Explorer App

A native Android application written in Kotlin that serves as a movie database and discovery platform (similar to IMDb). The app fetches real-time data from a public API and allows users to browse movies, view details, and manage their personal ratings locally.

## Features

* **Discover Movies:** Browse popular, trending, and top-rated movies using a public API.
* **Search Functionality:** Search for specific titles.
* **Movie Details:** View detailed information including plot, release date, and posters.
* **Star Rating System:** Users can rate movies (1-5 stars).
* **Local Persistence:** User ratings and "account" data are stored locally on the device, allowing for a personalized experience without an internet backend.
* **Offline Support:** Basic data persistence for viewed items.

## Tech Stack & Architecture

* **Language:** [Kotlin](https://kotlinlang.org/)
* **Architecture:** MVVM (Model-View-ViewModel)
* **Networking:** Retrofit
* **Asynchronous Programming:** Kotlin Coroutines & Flow
* **Image Loading:** [Coil](https://github.com/coil-kt/coil)
* **Local Database:** Room Database
* **UI Components:** Jetpack Compose
* **API:** [The Movie Database (TMDB) API](https://www.themoviedb.org/documentation/api)
  
*Created as a semester project in 2025*
