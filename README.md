# QuizGame
QuizGame is an Android application that allows users to participate in various quizzes across different levels. The app is integrated with Firebase for user authentication and Firestore for storing user progress.

# Features
* User authentication with Firebase
* Multiple levels of quizzes
* Different categories of quizzes
* data storage with Firebase Firestore
* Responsive UI with RecyclerView and GridLayoutManager

# ScreenShots

![Screenshot 2024-05-23 193156](https://github.com/Pratiksha-Rajpurohit/QuizGame/assets/132194955/7ef8339e-8110-437a-afbb-4672e803ced4)
![Screenshot 2024-05-24 103144](https://github.com/Pratiksha-Rajpurohit/QuizGame/assets/132194955/72ad9f25-353d-4887-9157-113d2049dc10)
![Screenshot 2024-05-23 192330](https://github.com/Pratiksha-Rajpurohit/QuizGame/assets/132194955/5d78dc6f-7db0-424f-9264-9fdfd9e23c67)
![Screenshot 2024-05-23 192642](https://github.com/Pratiksha-Rajpurohit/QuizGame/assets/132194955/3826513c-ab8d-4c4b-905c-56cf362df39c)
![Screenshot 2024-05-23 192709](https://github.com/Pratiksha-Rajpurohit/QuizGame/assets/132194955/a7133ed2-ceba-41dd-ad47-0325d9b7b780)
![Screenshot 2024-05-23 192821](https://github.com/Pratiksha-Rajpurohit/QuizGame/assets/132194955/0647870f-a524-461a-be94-de1eac60c0ff)
![Screenshot 2024-05-23 192739](https://github.com/Pratiksha-Rajpurohit/QuizGame/assets/132194955/8cbb8d77-c4e3-4c4f-8731-6dddea90f72e)



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

### SignUpActivity.kt
This activity allows new users to create an account. It integrates with Firebase Authentication to register users using their email and password. 

### SignInActivity.kt
This activity allows existing users to log in. It uses Firebase Authentication to authenticate users with their email and password. Upon successful login, users are redirected to the main screen.

# Firebase Integration
* The app uses Firebase Authentication for user login and registration.
* User progress is stored in Firestore, which includes the level and the number of correct answers.

# Contact
For any questions or suggestions, please open an issue on GitHub or contact the repository owner at rajpurohitpratiksha123@gmail.com.
  
