# Request Tracker (Android)

Offline mini-app to track internal requests/tasks with a simple workflow and quick CSV export (handoff/reporting).

Built with **Kotlin + Jetpack Compose**.

## Features
- Create requests (title required, optional description)
- Status workflow: **NEW → IN_PROGRESS → DONE** (tap status chip to move next)
- Filter by status (ALL / NEW / IN_PROGRESS / DONE)
- Local persistence with **Room** (data stays after app restart)
- **CSV export via Share** (simple reporting / integration)

## Tech stack
- Kotlin, Jetpack Compose (Material 3)
- Room (KSP), Flow
- MVVM (AndroidViewModel), Navigation Compose

## Screenshots

![Empty state](https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/01_empty.png)
![Requests list](https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/02_list.png)
![CSV export](https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/03_export.png)

## How to run
1. Open the project in **Android Studio**
2. Wait for Gradle sync to finish
3. Run the `app` configuration on an emulator or device (**API 26+**)

## Notes (digital transformation angle)
The app focuses on:
- simple, clear workflow states
- basic data quality rules (mandatory title, consistent statuses)
- exportable data for reporting / handoff (CSV)

