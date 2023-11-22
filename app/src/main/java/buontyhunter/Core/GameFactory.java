package buontyhunter.core;

import buontyhunter.input.NullInputComponent;
import buontyhunter.model.FighterEntity;
import buontyhunter.model.GameObject;
import buontyhunter.model.GameObjectType;
import buontyhunter.model.RectBoundingBox;
import buontyhunter.model.TileManager;
import buontyhunter.physics.NullPhysiscsCompoment;
import buontyhunter.physics.PlayerPhysicsComponent;
import buontyhunter.common.Point2d;
import buontyhunter.common.Vector2d;
import buontyhunter.graphics.MapGraphicsComponent;
import buontyhunter.graphics.PlayerGraphicsComponent;
import buontyhunter.input.PlayerInputController;

/* this class has methods to create all gameObjects */
public class GameFactory {

    static private GameFactory instance;

    static public GameFactory getInstance() {
        if (instance == null) {
            instance = new GameFactory();
        }
        return instance;
    }

    public FighterEntity createPlayer(Point2d point, Vector2d vector, int health) {
        return new FighterEntity(GameObjectType.Player, point, vector,
                new RectBoundingBox(new Point2d(0, 0), 1, 1),
                new PlayerInputController(), new PlayerGraphicsComponent(), new PlayerPhysicsComponent(),
                health);
    }

    public TileManager createTileManager() {
        return new TileManager(GameObjectType.TileManager,
                new Point2d(-(GameEngine.WORLD_WIDTH / 2), GameEngine.WORLD_HEIGHT / 2), new Vector2d(0, 0),
                new RectBoundingBox(new Point2d(0, 0), GameEngine.WORLD_HEIGHT, GameEngine.WORLD_WIDTH),
                new NullInputComponent(), new MapGraphicsComponent(), new NullPhysiscsCompoment());
    }
}