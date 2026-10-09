# LearnHub

## 1. Architecture

I used MVVM architecture with the Repository pattern. Compose is used for UI, ViewModel handles UI state, and Repository handles API and database operations. I chose this because it keeps the code clean, easy to maintain, and easy to test.

## 2. Offline Support

I used Room to store course data locally. When the API returns courses, they are saved in the database. If there is no internet, the app can load the courses from Room.

## 3. Security

In a production app, I would make sure user passwords and authentication tokens are not stored in plain text. I would use with encrypted storage such as EncryptedSharedPreferences or an equivalent solution. Tokens should never be hardcoded or stored in plain text. Sensitive operations should be validated on the backend.

## 4. Scale

If the app had 1 million users and hundreds of courses, I would:

- Use pagination to load courses in small batches.
- Add caching to reduce unnecessary API calls.
- Monitor app performance and fix issues as the number of users grows.

## 5. Second Platform — iOS/macOS

I mainly worked on Android for this project. If I had to develop it for iOS/macOS, I would learn Swift and SwiftUI and build a similar app using the same backend API.
