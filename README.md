# Request Tracker (Android)

Offline mini-app to digitize internal requests/tasks with a simple workflow and quick CSV export (handoff/reporting).

Built with **Kotlin + Jetpack Compose**.

## Problem & goal
Many teams track small internal requests (maintenance / process issues) in chats or spreadsheets.  
This MVP provides a clear status workflow, basic data quality rules, and exportable data for reporting.

## Features
- Create requests (title required, optional description)
- Status workflow: **NEW → IN_PROGRESS → DONE** (tap status chip to move next)
- Filter by status (ALL / NEW / IN_PROGRESS / DONE)
- Local persistence with **Room** (data stays after app restart)
- **CSV export via Share** (simple reporting / handoff)

## Tech stack
- Kotlin, Jetpack Compose (Material 3)
- Room (KSP), Flow
- MVVM (ViewModel), Navigation Compose

## Screenshots

<p align="center">
  <img src="https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/01_empty.png" width="30%" />
  <img src="https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/02_list.png" width="30%" />
  <img src="https://raw.githubusercontent.com/ArtjomAbdukarimov/request-tracker-android/main/screenshots/03_export.png" width="30%" />
</p>

## How to run
1. Open the project in **Android Studio**
2. Wait for Gradle sync to finish
3. Run the `app` configuration on an emulator or device (**API 26+**)

## Digital transformation angle
This MVP focuses on:
- clear and consistent process states (workflow maturity)
- basic data quality rules (mandatory title, consistent status enum)
- exportable data for reporting / integration (CSV)