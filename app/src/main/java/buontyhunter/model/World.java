package buontyhunter.model;

import java.util.List;

import java.util.Optional;

import buontyhunter.common.Point2d;
import buontyhunter.core.GameFactory;
import buontyhunter.physics.BoundaryCollision;

import java.util.ArrayList;

public class World {
    private GameObject player;
    private TileManager tileManager;
    private HidableObject titleScreen;
    private RectBoundingBox mainBBox;
    private WorldEventListener evListener;
    private HidableObject miniMap;
    private NavigatorLine navigatorLine;
    private HealthBar healthBar;

    public World(RectBoundingBox bbox) {
        mainBBox = bbox;
        this.healthBar = GameFactory.getInstance().createHealthBar();
        this.titleScreen = GameFactory.getInstance().createTitleScreen();
    }

    public void setEventListener(WorldEventListener l) {
        evListener = l;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
        laodMap(0);
    }

    public void setPlayer(GameObject player) {
        this.player = player;
    }

    public void setMiniMap(HidableObject miniMap) {
        this.miniMap = miniMap;
    }

    public void setNavigatorLine(NavigatorLine navigatorLine) {
        this.navigatorLine = navigatorLine;
    }

    public void updateState(long dt) {
        if (player != null) {
            player.updatePhysics(dt, this);
        }
        if (tileManager != null) {
            tileManager.updatePhysics(dt, this);
        }
        if (miniMap != null) {
            miniMap.updatePhysics(dt, this);
        }
    }

    public void notifyWorldEvent(WorldEvent ev) {
        evListener.notifyEvent(ev);
    }

    public RectBoundingBox getBBox() {
        return mainBBox;
    }

    public GameObject getPlayer() {
        return player;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public HidableObject getTitleScreen(){
        return this.titleScreen;
    }

    public HidableObject getMiniMap() {
        return miniMap;
    }

    public NavigatorLine getNavigatorLine() {
        return navigatorLine;
    }

    public List<GameObject> getSceneEntities() {
        List<GameObject> entities = new ArrayList<GameObject>();
        if (this.titleScreen.isShow()) {
            entities.add(titleScreen);
        } else {
            if (tileManager != null)
                entities.add(tileManager);
            if (player != null)
                entities.add(player);
            if (navigatorLine != null)
                entities.add(navigatorLine);
            if (healthBar != null)
                entities.add(healthBar);
            if (miniMap != null)
                entities.add(miniMap);
        }

        return entities;
    }

    private void laodMap(int map) {
        if (tileManager == null)
            return;
        var box = tileManager.loadMap(map);
        mainBBox = box;
    }

    public Optional<BoundaryCollision> checkCollisionWithBoundaries(Point2d pos, RectBoundingBox box) {
        Point2d ul = mainBBox.getULCorner();
        Point2d br = mainBBox.getBRCorner();
        RectBoundingBox rect = new RectBoundingBox(pos, box.getWidth(), box.getHeight());
        if (rect.getULCorner().y < ul.y) {
            return Optional.of(new BoundaryCollision(BoundaryCollision.CollisionEdge.TOP, new Point2d(pos.x, ul.y)));
        } else if (rect.getBRCorner().y > br.y) {
            return Optional.of(new BoundaryCollision(BoundaryCollision.CollisionEdge.BOTTOM,
                    new Point2d(pos.x, br.y - rect.getHeight())));
        } else if (rect.getBRCorner().x > br.x) {
            return Optional.of(new BoundaryCollision(BoundaryCollision.CollisionEdge.RIGHT,
                    new Point2d(br.x - rect.getWidth(), pos.y)));
        } else if (rect.getULCorner().x < ul.x) {
            return Optional.of(new BoundaryCollision(BoundaryCollision.CollisionEdge.LEFT, new Point2d(ul.x, pos.y)));
        } else {
            return Optional.empty();
        }
    }
}
