package assignment1_000862859;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * @author: Ali Dahche.
 * @ID: 000862859.
 * This program is about creating Arkanid game that has many shapes , such as (bricks, paddle, ball, circle(ball), rectangle). Also, we separate a stage for two parts. first part is just for the result(current score, height score, level). The second part of the stage is a about making shapes for the game.
 */
public class Arkanoid extends Application {
    /**
     * breickWidth: The brick width.
     * brickHeight: The brick height.
     * paddleWidth: The paddle width.
     * paddleHeight: The paddle height.
     * ballRadius: The radius for the ball.
     * gameWidth: The game width.
     * gameHeight: The game height.
     * displayHeight: The display(the result of the game.) height.
     */
    private int breickWidth = 40, brickHeight = 20, paddleWidth = 150, paddleHeight = 150, ballRadius = 10, gameWidth = 550, gameHeight = 650, displayHeight = 200;

    /**
     * rows: The rows of the bricks.
     * columns: The columns of the bricks.
     * currentScoreGame: The current Score.
     *  hightScoreGame: The highest score in the game.
     *  levelOfGame: The level of difficulty in the game.
     *  xBall: The ball x position.
     *  yBall: The ball y position.
     *  xPaddle: The paddle x position.
     */
    private int rows, columns, currentScoreGame, hightScoreGame, levelOfGame, xBall, yBall, xPaddle, yMaxamium, yMinimum, yPaddle;

    /**
     * I did while loop for; asking the user for how many rows, asking the user or how many columns, asking the user about the current score, asking the user for the high score, asking the user about level wants to play on,asking the user about what's x position  wants the ball to be on, asking the user what's y position wants the ball to be on and asking the user where the paddle x wants to be on.
     * @param stage used.
     */
    @Override
    public void start(Stage stage) {
        /*
         * Create game screen
        */
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(gameWidth, gameHeight + displayHeight);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.setTitle("Arkanoid Game"); // Arkanoid game is a game that contains bricks,paddle,ball and result.
        GraphicsContext gc = canvas.getGraphicsContext2D();

        /*
          Get input from user
          In the while loop I asked the user about how many columns wants and I created a space to let the user enter the number.
         */
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Number of rows (1-10): ");
            rows = sc.nextInt();
        } while (rows < 1 || rows > 10);
        /*
         * Get input from user
         * In the while loop I asked the user about how many columns wants and I created a space to let the user enter the number.
         */
        do {
            System.out.print("Number of columns (1-10): ");
            columns = sc.nextInt();
        } while (columns < 1 || columns > 10);
        /*
         * Get input from user
         * In the while loop I asked the user about the current score and I created a space to let the user enter the number.
         */
        do {
            System.out.print("Current score (0-999): ");
            currentScoreGame = sc.nextInt();
        } while (currentScoreGame < 0 || currentScoreGame > 999);
        /*
         * Get input from user
         * In the while loop I asked the user about the high score and I created a space to let the user enter the number.
         */
        while (true) {
            System.out.print("High score (0-999): ");
            hightScoreGame = sc.nextInt();
            if (hightScoreGame >= 0 && hightScoreGame <= 999) {
                if (currentScoreGame > hightScoreGame) {
                    hightScoreGame = currentScoreGame;
                    System.out.println("New high score: " + hightScoreGame);
                }
                break;
            }
        }
        /*
         * Get input from user
         * In the while loop I asked the user about the level of difficulty wants to play on and I created a space to let the user enter the number.
         */
        do {
            System.out.print("Level (1-10): ");
            levelOfGame = sc.nextInt();
        } while (levelOfGame < 1 || levelOfGame > 10);
        /*
         * Get input from user
         * In the while loop I asked the user about the ball x position,should not go out of the stage and I created a space to let the user enter the number.
         */
        do {
            System.out.print("Ball x position (" + ballRadius + "-" + (gameWidth - ballRadius) + "): ");
            xBall = sc.nextInt();
        } while (xBall < ballRadius || xBall > gameWidth - ballRadius);
        /*
         * Get input from user
         * In the while loop I asked the user about the ball y position and I created a space to let the user enter the number.
         */
        yMaxamium=gameHeight +displayHeight-ballRadius;
        yMinimum=displayHeight + ballRadius;
        do {
            System.out.print("Ball y position (" + (yMinimum) + "-" + (yMaxamium) + "): ");
            yBall = sc.nextInt();
        } while (yBall < yMinimum || yBall > yMaxamium);
        /*
         * Get input from user
         * In the while loop I asked the user about the x position for paddle  and I created a space to let the user enter the number.
         */
        do {
            System.out.print("Paddle x position (" + paddleWidth + "-" + gameWidth + "): ");
            xPaddle = sc.nextInt();
        } while (xPaddle < paddleWidth || xPaddle > gameWidth);

        drawGameScreen(gc);
        stage.show();
    }

    /**
     * These codes create the game stage through GraphicsContext. I have added url for image to display it inside the game and drawing other shapes.
     */
    private void drawGameScreen(GraphicsContext gc) {
        /*
         * This an url for image to display it inside the game.
         */
        Image image = new Image("https://arkan0id.vercel.app/background.d36168c0.jpg");
        gc.drawImage(image, 0, displayHeight, gameWidth, gameHeight);

        /*
         * Creating bricks by different colors with specified dimensions to display on the game screen.
         */
        int topBrick = displayHeight + 10;
        Color[] cBrick = {Color.GRAY, Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE};

        int brickWidthResult = columns * (breickWidth + 4);
        int brickHeightResult = rows * (brickHeight + 2);
        int xSpacing = (gameWidth - brickWidthResult) / (columns + rows);
        int ySpacing = (gameHeight - brickHeightResult - topBrick- brickHeight - breickWidth) / (rows + 1); // Adjusted spacing calculation
        /*
         * This is for creating rows and columns for bricks. the first for loop is for rows, and second for loop is for columns.
         */
        int i;
        for (i = 0; i < rows; i++) {
            int yBrick = topBrick + (i + 1) * ySpacing + i * brickHeight;
            Color colorBrick = cBrick[i % cBrick.length];
            int xSpacingTotal = xSpacing * (columns + 1);  // Total spacing between columns
            int spaceTotalOfBrick = xSpacingTotal + brickWidthResult;  // Total space occupied by bricks and spacing
            int setOff = (gameWidth - spaceTotalOfBrick) / 2;  // Offset to center the bricks
            for (int j = 0; j < columns; j++) {
                int brickX = setOff + xSpacing + j * (breickWidth + xSpacing);
                gc.setFill(colorBrick);
                gc.fillRect(brickX, yBrick, breickWidth, brickHeight);
            }
        }

        /*
         * Drawing the ball with the specified dimensions and color.
         */
        gc.setFill(Color.RED);
        gc.fillOval(xBall - ballRadius, yBall, ballRadius * 2, ballRadius * 2);

        /*
         * Drawing a paddle with the specified dimensions and color.
         */
        yPaddle = gameHeight + displayHeight - paddleHeight;
        Image imagePaddle = new Image("https://static.wikia.nocookie.net/p__/images/2/2d/Vaus_ios.png/revision/latest?cb=20170509025312&path-prefix=protagonist"); // Replace "path_to_paddle_image.png" with the actual path to your paddle image file
        gc.drawImage(imagePaddle, xPaddle-paddleWidth-20, yPaddle, paddleWidth, paddleHeight);

        /*
         * Drawing the display region with specified color and dimensions.
         */
        gc.setFill(Color.LIGHTGRAY);
        gc.setFill(Color.ORANGE);
        gc.fillRect(0, 0, gameWidth, displayHeight);

        /*
         * Writing the current score, high score and level with specified color.
         */
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font(20));
        gc.fillText("Score: " + currentScoreGame, 10, 30);
        gc.fillText("High Score: " + hightScoreGame, 200, 30);
        gc.fillText("Level: " + levelOfGame, 400, 30);

        /*
         * Adding the image to display it in the game.
         */
        Image logoImage = new Image("https://www.muraldecal.com/en/img/arc031-jpg/folder/products-listado-merchant/stickers-arkanoid-logo.jpg");
        gc.drawImage(logoImage, 10, 50, 500, 120);
    }

    /**
     *
     * @param args used.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
