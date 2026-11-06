# DueMinus

**Homework & Study Countdown Android Application**

DueMinus is an Android application designed to help learners organise homework, assignments, tests and examination deadlines. It provides a simple place to record due dates, review upcoming work, receive reminders and use a focused study timer.

## Project information

- **Application:** DueMinus
- **Platform:** Android
- **Language:** Kotlin
- **Package name:** `com.fake.dueminus`
- **Authentication:** Firebase Authentication (email/password and Google Sign-In)
- **Local database:** Room
- **API communication:** Retrofit
- **Background tasks:** WorkManager
- **Supported interface languages:** English, Afrikaans and isiXhosa

## Features

- **Account registration and login:** Learners can register and sign in using an email address and password.
- **Google Sign-In (SSO):** Learners can authenticate using an existing Google account through Android Credential Manager and Firebase Authentication.
- **Dashboard:** A central starting screen for the learner, including relevant deadline information.
- **Deadline management:** Add deadlines, view saved items, mark work as completed and delete items.
- **Offline-first storage:** Deadline records are stored locally with Room so that saved information can remain available without an internet connection.
- **API synchronisation:** The app includes background synchronisation support for pending local changes when network connectivity is available.
- **Deadline reminders:** WorkManager schedules local Android notifications for upcoming deadlines and at the due time.
- **Study timer:** A 25-minute countdown timer supports focused study sessions, with start and reset controls.
- **Settings:** Learners can select a language and manage the notification preference.
- **Multiple languages:** Supported interface text includes English, Afrikaans and isiXhosa. The selected language preference is saved.

> **Notification note:** The current deadline reminders are locally scheduled Android notifications using WorkManager. They are not remote Firebase Cloud Messaging (FCM) push notifications.

## How the app works

### 1. Register or sign in

When DueMinus opens, the learner can log in with an email address and password. A new learner can use the registration option to create an account. Alternatively, the learner can select **Continue with Google**, choose a Google account and authenticate through Firebase Authentication.

### 2. Use the dashboard

After successful authentication, the learner is taken to the dashboard. This acts as the main navigation point for the app and displays relevant deadline information. The available dashboard text follows the selected app language where translations have been implemented.

### 3. Add and manage deadlines

The learner opens the deadline section and adds a task by entering its details and due date/time. The saved deadline is displayed in the list. The learner can return to the list to review upcoming work, mark a task as complete or delete it.

### 4. Store work locally and synchronise

Deadline data is stored in the local Room database. This supports access to saved deadline information when the phone is offline. The app also has a background synchronisation mechanism that can process pending changes when a network connection is available and communicate with the project's REST API. For an assessment demonstration, show an offline save followed by reconnecting and verifying the synchronisation result.

### 5. Receive deadline reminders

The app schedules local background work for deadline reminders. When a scheduled reminder is due, Android displays a notification, provided the learner has granted notification permission and the device allows notifications. The app schedules a reminder before the deadline and another at the due time.

### 6. Study with the timer

The learner can open the study timer, start the 25-minute countdown and reset it when required. This provides a simple focus session without needing a separate timer application.

### 7. Change settings and language

In Settings, the learner can choose English, Afrikaans or isiXhosa and manage the notification preference. The language selection is saved so that it can be restored when the app is opened again. The translated interface coverage depends on the screens for which translated string resources have been added.

## Technologies used

| Technology | Purpose |
|---|---|
| Kotlin | Android application development |
| Android Studio | Project development and APK building |
| Firebase Authentication | Email/password authentication and Google SSO |
| Cloud Firestore | User settings/profile data |
| Room | Local deadline database |
| Retrofit | Communication between Android and the REST API |
| WorkManager | Background synchronisation and scheduled reminders |
| Android notification APIs | Displaying local deadline notifications |
| Gradle | Build and dependency management |

## Build and run

1. Clone or download this repository.
2. Open the project in Android Studio.
3. Make sure Android Studio has a compatible JDK and Android SDK installed.
4. Confirm that the Firebase configuration file `app/google-services.json` is present. Do not publish private credentials or signing keystore files.
5. Sync the project with Gradle.
6. Connect an Android device with USB debugging enabled, or start an emulator.
7. Build and run the `app` configuration.

To build a debug APK from the project root:

```powershell
.\gradlew assembleDebug
```

The debug APK is normally generated at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## API

The Android application uses Retrofit to communicate with the DueMinus REST API. The API project is maintained separately from the Android application. The API base URL must be configured for the environment being used. For a physical-device test, the phone and development computer must be able to reach the configured server over the same network, unless a hosted API URL is configured.

## Testing and demonstration

The following features have been built and tested during development:

- Email/password authentication.
- Google Sign-In successfully reaching the DueMinus dashboard on a physical Android phone.
- Deadline creation and management.
- A scheduled local deadline notification received on a physical phone.
- Study timer operation.
- English, Afrikaans and isiXhosa selection and language persistence on the tested screens.
- Successful Gradle debug build.

Before final submission, also verify the signed release APK, offline-to-online synchronisation, reminder cancellation/completion behaviour and all screens included in the final demonstration.

## Video presentation

**YouTube demonstration:** [INSERT YOUR YOUTUBE VIDEO LINK HERE]

The video should demonstrate registration/login, Google Sign-In, dashboard navigation, adding and managing deadlines, reminders, the study timer, settings/language selection and offline behaviour/synchronisation where available.

Replace the placeholder above with the published YouTube URL before submitting.

## Repository

GitHub: https://github.com/Aykayhay47/ST10435507-DueMinus

## Developer

**Muhammad Aashiq Petersen**  
**Student number:** ST10435507  
**Project:** DueMinus
