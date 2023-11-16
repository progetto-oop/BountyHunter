package buontyhunter.Core;

import buontyhunter.Graphics.ScreenHandlerImpl;
import buontyhunter.InputHandlers.KeyBoardController;
import buontyhunter.Models.GameObject;
import buontyhunter.Models.GameState;

public class GameEngine {
    private ScreenHandlerImpl screenHandler;
    private GameState gameState;
    private GameConfiguration configuration;
    private KeyBoardController keyBoardController;

    public GameEngine() {
        configuration = new GameConfiguration();
        this.keyBoardController = new KeyBoardController();
        gameState = new GameState(configuration.getTileSize());
        screenHandler = new ScreenHandlerImpl(this.gameState,this.keyBoardController);
    }

    public void gameRun() {
        double drawInterval = (1000000000 / configuration.getFPS());
        double delta = 0;
        long lastTime = System.nanoTime();

        long timer = 0;
        int drawCount = 0;

        while (!gameState.isGameOver()) {
            long currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                inputHandler();
                update();
                draw();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {

                System.out.println(drawCount + " FPS");

                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void inputHandler() {
        gameState.getTileManager().inputHadler(keyBoardController);
        for (GameObject obj : gameState.getGameObjects()) {
            obj.inputHadler(keyBoardController);
        }
    }

    public void update() {
        // TODO
    }

    public void draw() {
        screenHandler.draw();
    }

    /* getter area */

    public ScreenHandlerImpl getScreenHandler() {
        return screenHandler;
    }

    public GameState getGameState() {
        return gameState;
    }

    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public KeyBoardController getKeyBoardController() {
        return keyBoardController;
    }
}
