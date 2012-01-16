package edu.feri.virusss8.menu;

import java.util.Date;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.MathUtils;

public class MyPyramide implements ApplicationListener {
	
    private Mesh[] triangleMesh;
    private PerspectiveCamera camera;
    public static final float angle = new Float(0.45);
    public static final float angleStep = new Float(2.5);
    public static final int finalStep = 36; 
    float comX = new Float(0),
    		comY = new Float(2),
    		comZ = new Float(3),
    		step = new Float(0.01);
    private boolean endRight = true, 
    				endLeft = true, 
    				touched = false;
	Date pressed, released;
	int lastTouched, firstTouched;
	float position;
	int stranica = 1, 
		stevec = 0;

	OdpriActivity oa;
	
	public MyPyramide(OdpriActivity oa) {
		this.oa = oa;
	}

    @Override
    public void create() {
    	
    	if(triangleMesh == null) {
    		triangleMesh = new Mesh[8];
    		
    		for (int i = 0; i < 8; i++) {
    			triangleMesh[i] = new Mesh(true, 3, 3,
    					new VertexAttribute(Usage.Position, 3, "a_position"),
						new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
    			
    			triangleMesh[i].setIndices(new short[] { 0, 1, 2 });
    		}
    		
    		triangleMesh[0].setVertices(new float[] { //sprednaZ
        			-0.5f, 0, -0.5f, Color.toFloatBits(255, 0, 0, 255),
        	        0.5f, 0, -0.5f, Color.toFloatBits(255, 0, 0, 255),
        	        0, 1, 0, Color.toFloatBits(255, 0, 0, 255) });
    		
    		triangleMesh[1].setVertices(new float[] { //zadnaZ
        			-0.5f, 0, 0.5f, Color.toFloatBits(0, 255, 0, 255),
        	        0.5f, 0, 0.5f, Color.toFloatBits(0, 255, 0, 255),
        	        0, 1, 0, Color.toFloatBits(0, 255, 0, 255) });
    		
    		triangleMesh[2].setVertices(new float[] { //desnaZ
    				-0.5f, 0, 0.5f, Color.toFloatBits(0, 0, 255, 255),
    				-0.5f, 0, -0.5f, Color.toFloatBits(0, 0, 255, 255),
        	        0, 1, 0, Color.toFloatBits(0, 0, 255, 255) });
    		
    		triangleMesh[3].setVertices(new float[] { //levaZ
    				0.5f, 0, 0.5f, Color.toFloatBits(85, 75, 13, 255),
    				0.5f, 0, -0.5f, Color.toFloatBits(85, 75, 13, 255),
    				0, 1, 0, Color.toFloatBits(85, 75, 13, 255) });
    		
    		triangleMesh[4].setVertices(new float[] { //sprednaS
        			-0.5f, 0, -0.5f, Color.toFloatBits(255, 0, 0, 255),
        	        0.5f, 0, -0.5f, Color.toFloatBits(255, 0, 0, 255),
        	        0, -1, 0, Color.toFloatBits(255, 0, 0, 255) });
    		
    		triangleMesh[5].setVertices(new float[] { //zadnaS
        			-0.5f, 0, 0.5f, Color.toFloatBits(0, 255, 0, 255),
        	        0.5f, 0, 0.5f, Color.toFloatBits(0, 255, 0, 255),
        	        0, -1, 0, Color.toFloatBits(0, 255, 0, 255) });
    		
    		triangleMesh[6].setVertices(new float[] { //desnaS
    				-0.5f, 0, 0.5f, Color.toFloatBits(0, 0, 255, 255),
    				-0.5f, 0, -0.5f, Color.toFloatBits(0, 0, 255, 255),
        	        0, -1, 0, Color.toFloatBits(0, 0, 255, 255) });
    		
    		triangleMesh[7].setVertices(new float[] { //levaS
    				0.5f, 0, 0.5f, Color.toFloatBits(85, 75, 13, 255),
    				0.5f, 0, -0.5f, Color.toFloatBits(85, 75, 13, 255),
    				0, -1, 0, Color.toFloatBits(85, 75, 13, 255) });
    	}        
        Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
    }

    @Override
    public void dispose() { }
    
    @Override
    public void render() {
    	if (Gdx.input.justTouched()) {
			pressed = new Date();
			touched = true;
			firstTouched = lastTouched = Gdx.input.getX();
		} 
    	else if (Gdx.input.isTouched()) {
			lastTouched = Gdx.input.getX();
		}
		if(!Gdx.input.isTouched()){
			if(touched) {
				position = lastTouched - firstTouched;
				released = new Date();
				long timePressed = released.getTime()-pressed.getTime();
				if(timePressed < 150){
					System.out.println("KLIK");
					oa.whichActivity(stranica);
				}
				else {
					if(position > 0) {
						System.out.println("PRESTAVU BI levo");
						endLeft = false;
						stevec = 0;
//						rotate(90);
						switch (stranica) {
						case 1:
							stranica = 4;
							System.out.println("Stranica: " + stranica);
							break;
						case 2:
							stranica = 1;
							System.out.println("Stranica: " + stranica);
							break;
						case 3:
							stranica = 2;
							System.out.println("Stranica: " + stranica);
							break;
						case 4:
							stranica = 3;
							System.out.println("Stranica: " + stranica);
							break;
						default:
							break;
						}
					}
					else {
						System.out.println("PRESTAVU BI desno");
//						rotate(-(90));
						endRight = false;
						stevec = 0;
						switch (stranica) {
						case 1:
							stranica = 2;
							System.out.println("Stranica: " + stranica);
							break;
						case 2:
							stranica = 3;
							System.out.println("Stranica: " + stranica);
							break;
						case 3:
							stranica = 4;
							System.out.println("Stranica: " + stranica);
							break;
						case 4:
							stranica = 1;
							System.out.println("Stranica: " + stranica);
							break;
						default:
							break;
						}
					}
				}
				touched = false;
			}
		}
		if(!endLeft) {
			if (stevec < finalStep) {
				rotate(angleStep);
				System.out.println("Stevec: " + stevec + "kot: " + angleStep);
				stevec++;
			}
			else
				endLeft = true;
		}
		
		if(!endRight) {
			if (stevec < finalStep) {
				rotate(-angleStep);
				System.out.println("Stevec: " + stevec + "kot: " + angleStep);
				stevec++;
			}
			else
				endRight = true;
		}

		camera.position.set(comX, comY, comZ);
		camera.lookAt(0, 0, 0);

		camera.update();
		camera.apply(Gdx.gl10);

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		for (Mesh allMashes : triangleMesh) {
			allMashes.render(GL10.GL_TRIANGLES, 0, 3);
		}
    }
    
    private void rotate(float angleDeg) {
		float cos = MathUtils.cosDeg(angleDeg);
		float sin = MathUtils.sinDeg(angleDeg);
		float x = comX;
		float z = comZ;

		comX = (cos*x - sin*z);
		comZ = (sin*x + cos*z);
	}
    
    public void rotateDefault() {
		rotate(angle);
	}
    
    @Override
    public void resize(int width, int height) {
    	float aspectRatio = (float) width / (float) height;
//    	camera = new OrthographicCamera(2f * aspectRatio, 2f); // ta kamera ne spreminja oblike objektov, zato tudi 3d objekte vidimo v 2D
		camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f);  // tukaj lahko dejansko objekte vidimo v 3D, ker se jim spreminja oblika, glede na kot gledanja
		
		camera.near = 0.1f;
		camera.translate(comX, comY, comZ);
    }

    @Override
    public void resume() { 
    	Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
    }
    
    @Override
    public void pause() { }
}
