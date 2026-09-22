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

### Registration

### Dashboard

### Add Deadline

### Deadline List

### Study Timer

### Settings
<img width="1920" height="1080" alt="Screenshot 2026-09-22 233930" src="https://github.com/user-attachments/assets/f872e90a-95d9-4a41-86d8-c02dfd736109" />
<img width="1920" height="1080" alt="Screenshot 2026-09-22 233930" src="https://github.com/user-attachments/assets/5d8fb322-def2-422e-8c7b-f8a1cc502594" />
<img width="1920" height="1080" alt="Screenshot 2026-09-22 233843" src="https://github.com/user-attachments/assets/632c59c4-bbc4-4771-aea8-23c8c1a928ca" />
<img width="1920" height="1080" alt="Screenshot 2026-09-22 233809" src="https://github.com/user-attachments/assets/e160736e-e870-4d08-ae4d-cf92217ffdcb" />
<img width="1920" height="1080" alt="Screenshot 2026-09-22 233755" src="https://github.com/user-attachments/assets/0ce3697d-1ae1-4001-8dfc-ac6698966954" />
<img width="1920" height="1080" alt="Screenshot 2026-09-22 233738" src="https://github.com/user-attachments/assets/08ebca0c-d6e0-418d-91aa-2205a08558e8" />
<img width="1920" height="1080" alt="Screenshot 2026-09-22 233709" src="https://github.com/user-attachments/assets/306c7418-58e0-4fea-a557-1139863c2293" />
<img width="1920" height="1080" alt="Screenshot 2026-09-22 233918" src="https://github.com/user-attachments/assets/854ccdfb-9521-4317-b2f1-4b4794979099" />



## Demonstration Video

The DueMinus prototype demonstration video is available on YouTube.

**YouTube Video:**  
https://youtu.be/lDd1pz0pHdc

## GitHub Repository

**GitHub:**  
https://github.com/Aykayhay47/ST10435507-DueMinus
