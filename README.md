# The Math Skater
 “MathSkater” is a mathematical educational game. The game features a skater that jumps over obstacles as players solve math problems. Built using JavaFX and Apache Derby, MathSkater offers a simple yet challenging gameplay with a clean user interface and an admin panel for managing game data and user details.

- **Math Challenges**: Players are presented with a math question and four possible answers. Choosing the correct answer makes the skater jump and increases the score.
- **Game Over on Mistake**: Selecting an incorrect answer ends the game, with an option to retry.
- **Game Controls**:
  - Pause/Resume: Players can pause the game and resume it at any time.
  - High Score: The game tracks and displays the highest score achieved by the player.
  - Exit: Players can exit the game at any time.

## Admin Panel
MathSkater includes an admin panel with the following features:
- **User Details Management**: The admin can view and manage user details, which are retrieved from the database.
- **Question Management**: Admin can add, edit, or remove math questions used in the game.
- **Reports**: Access to various reports related to user performance and game statistics.
- **Help Section**: Provides assistance and guidance on how to use the admin panel.

### Admin Login
- **Admin Credentials**: The system has a fixed set of credentials for the admin to log in.

## Software Architecture and Libraries

MathSkater is built on two key software architectures:
- **Client-Server Architecture**: The client requests and receives services from the server. The server handles database interactions using Apache Derby, an embedded RDBMS that runs in the JVM.
- **Model-View-Controller (MVC) Architecture**: Separates the business logic, display, and input handling. The model manages data and logic, the view handles layout and display, and the controller routes commands to the model and view.

### Technologies Used:
- **JavaFX**: Used for developing the GUI. JavaFX provides a rich set of controls and is ideal for building games.
- **Jfoenix**: A library used for UI components, offering smooth animations and enhanced UI/UX.
- **Apache Derby**: An embedded database engine that is lightweight and easy to integrate with Java applications.
- **Scene Builder**: A visual layout tool used to design the user interface without coding.

## Installation and Setup
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/mathskater.git
