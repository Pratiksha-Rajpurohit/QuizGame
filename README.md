# QuizGame
QuizGame is an Android application that allows users to participate in various quizzes across different levels. The app is integrated with Firebase for user authentication and Firestore for storing user progress.

# Features
User authentication with Firebase
Multiple levels of quizzes
Different categories of quizzes
Real-time data storage with Firebase Firestore
Responsive UI with RecyclerView and GridLayoutManager

# Getting Started
### Prerequisites
* Android Studio
* Firebase account
### Installation
 1. Clone the repository:
 ```bash

git clone https://github.com/yourusername/quizgame.git
 ```

2. Open the project in Android Studio.

3. Set up Firebase:
    - Go to the Firebase Console and create a new project.
    - Add an Android app to your project with your app's package name.
    - Download the google-services.json file and place it in the app directory of your project.
    - Add the Firebase SDK to your project by following the instructions in the Firebase Console.
    - Sync the project with Gradle files.

4. Running the Application
    - Build and run the application on an emulator or physical device.

    - Sign up or log in using your Firebase credentials.

    - Start playing the quiz by selecting a level and answering the questions.
  
# Code Overview
### MainActivity.kt
This is the entry point of the application. It handles Firebase initialization and sets up the main screen with two buttons: "Start Quiz" and "Logout". The "Start Quiz" button navigates to the Levels screen, and the "Logout" button logs the user out and returns to the login screen.

### LevelsActivity.kt
This activity displays a list of quiz levels. It uses a RecyclerView with a GridLayoutManager to show the levels.

### QuizActivity.kt
This activity handles the quiz logic. It fetches quiz data from an API, displays the questions and options, and handles user interactions. When a user selects an option, it highlights the correct and incorrect answers. The user can proceed to the next question using the "Next" button. After answering all questions, the result page is displayed.

### FinishActivity.kt
This activity shows the user's score after completing a quiz. It displays the result in the form of stars based on the user's performance. The page also provides options to go to the previous level, next level, or quit the game. It also stores the user's progress in Firebase Firestore.

# Firebase Integration
* The app uses Firebase Authentication for user login and registration.
* User progress is stored in Firestore, which includes the level and the number of correct answers.
