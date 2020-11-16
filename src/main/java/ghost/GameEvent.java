package ghost;

public class GameEvent {

    public static void gameEvent(App app) {
        if (app.fruitCount == 0) {
            GameEvent.gameFinishScreen(app, true, true);
            app.restartTime++;
            GameEvent.restart(app);
            
        } else if (app.lives == 0) {
            GameEvent.gameFinishScreen(app, true, false);
            app.restartTime++;
            GameEvent.restart(app);
        } else {
            app.background(0, 0, 0);
            for (GameCell cell : app.cells) {
                cell.tick(app);
                cell.draw(app);
            }
        }
        if (app.fruitCount > 0 && app.lives > 0) {
            app.ghostList.forEach((ghost) -> ghost.draw(app)); // draw the ghosts last so that their sprites appear over the fruit
        }
    }


    public static void gameFinishScreen(App app, boolean display, boolean won) {
        if (display) {
            app.background(0);
            app.textFont(app.gameFont);
            if (won) {
                app.text("YOU WIN", app.WIDTH/4, app.HEIGHT/3);
            } else {
                app.text("GAME OVER", app.WIDTH/5, app.HEIGHT/3);
            }
        }
    }

    public static void restart(App app) {
        if (app.restartTime == 600) {
            GameEvent.gameFinishScreen(app, false, false);
            app.restartTime = 0;
            app.fruitCount = 0;
            app.ghostList.clear();
            app.setup();
        }
    }
}