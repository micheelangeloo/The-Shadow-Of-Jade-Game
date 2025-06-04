
package io.github.some_example_name;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final Main game;
    SpriteBatch batch;
    BitmapFont font;
    Texture background;

    String[] options = { "JUGAR", "SALIR" };
    int selected = 0;

    public MainMenuScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        background = new Texture("imagen.png");

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.UP) {
                    selected = (selected - 1 + options.length) % options.length;
                } else if (keycode == Input.Keys.DOWN) {
                    selected = (selected + 1) % options.length;
                } else if (keycode == Input.Keys.ENTER) {
                    if (selected == 0) {
                        game.setScreen(new GameScreen(game));
                    } else if (selected == 1) {
                        Gdx.app.exit();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (int i = 0; i < options.length; i++) {
            if (i == selected) {
                font.setColor(Color.GREEN);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(batch, options[i], Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f - i * 40);
        }
        batch.end();
    }

    public void resize(int width, int height) {}
    public void pause() {}
    public void resume() {}
    public void hide() {}
    public void dispose() {
        batch.dispose();
        font.dispose();
        background.dispose();
    }
}
