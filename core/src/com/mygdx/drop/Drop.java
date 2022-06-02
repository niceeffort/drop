package com.mygdx.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;

import sun.jvm.hotspot.oops.VirtualCallTypeData;


public class Drop extends ApplicationAdapter {
	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Rectangle bucket;
	private Vector3 touchPos;

	
	@Override
	public void create () {
		//Load images for droplet and bucket
		dropImage = new Texture(Gdx.files.internal("drop.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		//Load music and sound
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		//Start playback of rain music
		//rainMusic.setLooping(true);
		//rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);

		batch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		touchPos = new Vector3();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, .2f, 1);
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y );
		batch.end();

		if(Gdx.input.isTouched()){
			//Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		}
		if(bucket.x < 0){
			bucket.x = 0;
		}
		if(bucket.x > 800 - 64){
			bucket.x = 800 - 64;
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		bucketImage.dispose();
		dropImage.dispose();
	}
}