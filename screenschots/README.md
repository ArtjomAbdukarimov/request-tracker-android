# Request Tracker (Android, Kotlin, Room)

Offline MVP to digitize a simple request workflow (maintenance / process requests).  
Built with **Kotlin + Jetpack Compose + Room**.

## Features
- Create requests (title required, optional description)
- Status workflow: **NEW → IN_PROGRESS → DONE**
- Filter by status (ALL / NEW / IN_PROGRESS / DONE)
- Local persistence with Room (data stays after app restart)
- **CSV export via Share** (reporting / handoff)

## Tech stack
- Kotlin, Jetpack Compose (Material 3)
- Room (KSP), Flow
- MVVM (AndroidViewModel), Navigation Compose

## Screenshots

### Empty state
![Empty state](https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/01_empty.png)

### Requests list
![Requests list](https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/02_list.png)

### CSV export
![CSV export](https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/03_export.png)

## Notes (digital transformation angle)
The app focuses on:
- simple, clear workflow states
- basic data quality rules (mandatory title, consistent status enum)
- exportable data for reporting / integration (CSV)