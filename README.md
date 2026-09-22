# DueMinus

## Overview

DueMinus is an Android application designed to help students manage upcoming assignments, tests, examinations and other important deadlines.

The application provides students with a simple way to create and manage deadlines, view their upcoming work, see a countdown to their next deadline and use a study timer to support focused study sessions.

DueMinus was developed using Android Studio and Kotlin, with Firebase Authentication and Firestore used for user authentication and data storage. The project also includes a REST API developed using Node.js and Express.js.

## Features

- User registration
- User login
- Firebase Authentication
- Student dashboard
- Deadline creation
- Deadline management
- Deadline completion
- Deadline deletion
- Upcoming deadline countdown
- 25-minute study timer
- Settings
- English language option
- Afrikaans language option
- isiXhosa language option
- Notification setting
- User logout
- REST API for deadline management

## Technologies Used

- Android Studio
- Kotlin
- XML
- Firebase Authentication
- Firebase Firestore
- Node.js
- Express.js
- REST API
- Visual Studio Code
- GitHub

## Android Application

The Android application was developed using Kotlin in Android Studio.

The main application allows users to register and log in before accessing the DueMinus dashboard.

The dashboard provides access to the main features of the application, including:

- Add Deadline
- View My Deadlines
- Study Timer
- Settings
- Logout

Users can create deadlines containing information such as the title, category, description, priority and due date/time.

The application then displays the user's upcoming deadlines and calculates the remaining time until the next deadline.

## REST API

DueMinus includes a REST API developed using Node.js and Express.js.

The REST API provides an additional layer between the Android application and Firebase Firestore.

The basic architecture is:

Android Application  
↓  
REST API  
↓  
Node.js + Express.js  
↓  
Firebase Firestore

The API provides endpoints for retrieving, creating, updating and deleting deadlines.

## Firebase

Firebase is used in DueMinus for authentication and cloud data storage.

### Firebase Authentication

Firebase Authentication is used to allow users to register and log in using an email address and password.

### Firebase Firestore

Firestore is used to store user profile information and deadline data.

## Database Structure

The main Firestore structure is:

users/{userId}

- name
- email
- language
- notificationsEnabled

users/{userId}/deadlines/{deadlineId}

- title
- category
- description
- priority
- dueDateTime
- completed
- createdAt

Each user's deadlines are stored inside their own user document.

## API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/health` | Checks whether the API is running |
| GET | `/api/deadlines/:userId` | Retrieves a user's deadlines |
| POST | `/api/deadlines` | Creates a new deadline |
| PUT | `/api/deadlines/:userId/:deadlineId` | Updates an existing deadline |
| DELETE | `/api/deadlines/:userId/:deadlineId` | Deletes a deadline |

## Installation

### Android Application

1. Clone the DueMinus GitHub repository.
2. Open the Android project using Android Studio.
3. Allow Gradle to synchronise the project.
4. Make sure the Firebase configuration is available in the project.
5. Build the application.
6. Run the application on an Android emulator or compatible Android device.

### REST API

1. Open the `DueMinusAPI` folder in Visual Studio Code.
2. Install the required Node.js dependencies.
3. Configure the Firebase service account securely.
4. Start the REST API.
5. The API runs on the configured local port.

> Do not upload `serviceAccountKey.json`, `.env` files or other private credentials to GitHub.

## Testing

The application should be tested to confirm that the main features work correctly.

Testing includes:

- User registration
- User login
- Invalid login details
- Creating deadlines
- Viewing deadlines
- Completing deadlines
- Deleting deadlines
- Countdown functionality
- Study timer
- Settings
- REST API endpoints

Automated testing is also included as part of the project development and GitHub workflow.

## Screenshots

Screenshots of the DueMinus application will be added below.

### Login

_Add Login screenshot here._

### Registration

_Add Registration screenshot here._

### Dashboard

_Add Dashboard screenshot here._

### Add Deadline

_Add Add Deadline screenshot here._

### Deadline List

_Add Deadline List screenshot here._

### Study Timer

_Add Study Timer screenshot here._

### Settings

_Add Settings screenshot here._

## Demonstration Video

The DueMinus prototype demonstration video is available on YouTube.

**YouTube Video:**  
https://youtu.be/lDd1pz0pHdc

## GitHub Repository

**GitHub:**  
https://github.com/Aykayhay47/ST10435507-DueMinus
