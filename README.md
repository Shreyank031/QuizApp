# Quizzy - Android Quiz Application

Quizzy is an Android quiz application developed using Kotlin and Firebase Authentication. 
It allows users to sign up, log in, and participate in quizzes.
Note: The project is still in developing/incomplete state

## Features

- User authentication using Firebase Authentication
- Sign up and log in functionality
- Quiz participation and scoring

## Code Structure

The project follows the following structure:

- `activity`: Contains all activity classes
- `model`: Contains data models used in the application
- `util`: Contains utility classes and helper functions

## Firebase Authentication

Firebase Authentication is used for user authentication in the application. It provides secure authentication methods,
including email/password authentication.

To set up Firebase Authentication in your project:

1. Create a Firebase project and add your Android app to it.
2. Enable Firebase Authentication in the Firebase console.
3. Add the Firebase Authentication dependency to your app-level build.gradle file.
4. Configure Firebase Authentication settings in your app, such as allowed authentication methods and email verification settings.
5. Use Firebase Authentication methods in your app to handle user sign up, log in, and authentication state.

For more detailed instructions, refer to the [Firebase Authentication documentation](https://firebase.google.com/docs/auth).

## Usage

To use this project:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Connect your project to Firebase by following the Firebase setup instructions.
4. Build and run the application on an Android device or emulator.

## Contributions

Contributions to this project are welcome. If you find any bugs or want to suggest new features, 
please open an issue or submit a pull request.

## License

This project is licensed under the MIT License.
