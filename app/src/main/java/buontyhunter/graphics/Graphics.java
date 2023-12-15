package buontyhunter.graphics;

import buontyhunter.model.*;

public interface Graphics {

	void drawPlayer(GameObject obj, World w);

	void drawMap(TileManager tileManager, World w);

	void drawMiniMap(HidableObject tileManager, World w);

	void drawNavigatorLine(NavigatorLine navigatorLine, World w);

	void drawHealthBar(HealthBar healthBar, World w);

	void drawTitleScreen(HidableObject titleScreen,World w);
}
