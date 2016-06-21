package com.tx.agir.LightPackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import java.lang.Math;
import java.text.MessageFormat;
import java.util.ArrayList;

import com.tx.agir.R;
import com.tx.agir.common.RawResourceReader;
import com.tx.agir.common.ShaderHelper;

/**
 * This class implements our custom renderer. Note that the GL10 parameter passed in is unused for OpenGL ES 2.0
 * renderers -- the static class GLES20 is used instead.
 */
public class LessonFourRenderer implements GLSurfaceView.Renderer
{	
	/** Used for debug logs. */
	private static final String TAG = "LessonFourRenderer";
	private boolean is_finished = false;
	private boolean are_finished[] = {false,false,false,false};
    private ArrayList<String> myFinalePhrase = new ArrayList<String>();
	private final Context mActivityContext;
	private final DictioLight dicoo;
    private String[] maPhrase;


    /**
	 * Store the model matrix. This matrix is used to move models from object space (where each model can be thought
	 * of being located at the center of the universe) to world space.
	 */
	private float[] mModelMatrix = new float[16];
    private float[] gyroscope = new float[] {0.0f, 0.0f, 0.0f};
	/**
	 * Store the view matrix. This can be thought of as our camera. This matrix transforms world space to eye space;
	 * it positions things relative to our eye.
	 */
	private float[] mViewMatrix = new float[16];

	/** Store the projection matrix. This is used to project the scene onto a 2D viewport. */
	private float[] mProjectionMatrix = new float[16];
	
	/** Allocate storage for the final combined matrix. This will be passed into the shader program. */
	private float[] mMVPMatrix = new float[16];
	
	/** 
	 * Stores a copy of the model matrix specifically for the light position.
	 */
	private float[] mLightModelMatrix = new float[16];	
	
	/** Store our model data in a float buffer. */
	private final FloatBuffer mCubePositions1;
	private final FloatBuffer mCubeColors1;
	private final FloatBuffer mCubeNormals1;
	private final FloatBuffer mCubeTextureCoordinates1;

    private final FloatBuffer mCubePositions2;
    private final FloatBuffer mCubeColors2;
    private final FloatBuffer mCubeNormals2;
    private final FloatBuffer mCubeTextureCoordinates2;

    private final FloatBuffer mCubePositions3;
    private final FloatBuffer mCubeColors3;
    private final FloatBuffer mCubeNormals3;
    private final FloatBuffer mCubeTextureCoordinates3;

    private final FloatBuffer mCubePositions4;
    private final FloatBuffer mCubeColors4;
    private final FloatBuffer mCubeNormals4;
    private final FloatBuffer mCubeTextureCoordinates4;

    /** This will be used to pass in the transformation matrix. */
	private int mMVPMatrixHandle;
	
	/** This will be used to pass in the modelview matrix. */
	private int mMVMatrixHandle;
	
	/** This will be used to pass in the light position. */
	private int mLightPosHandle;
	
	/** This will be used to pass in the texture. */
	private int mTextureUniformHandle;
	
	/** This will be used to pass in model position information. */
	private int mPositionHandle;
	
	/** This will be used to pass in model color information. */
	private int mColorHandle;

    private int mDirectionHandle;
	/** This will be used to pass in model normal information. */
	private int mNormalHandle;
	
	/** This will be used to pass in model texture coordinate information. */
	private int mTextureCoordinateHandle;

	/** How many bytes per float. */
	private final int mBytesPerFloat = 4;	
	
	/** Size of the position data in elements. */
	private final int mPositionDataSize = 3;	
	
	/** Size of the color data in elements. */
	private final int mColorDataSize = 4;	
	
	/** Size of the normal data in elements. */
	private final int mNormalDataSize = 3;
	
	/** Size of the texture coordinate data in elements. */
	private final int mTextureCoordinateDataSize = 2;
	
	/** Used to hold a light centered on the origin in model space. We need a 4th coordinate so we can get translations to work when
	 *  we multiply this by our transformation matrices. */
	private final float[] mLightPosInModelSpace = new float[] {0.0f, 0.0f, 0.0f, 1.0f};
	
	/** Used to hold the current position of the light in world space (after transformation via model matrix). */
	private final float[] mLightPosInWorldSpace = new float[4];
	
	/** Used to hold the transformed position of the light in eye space (after transformation via modelview matrix) */
	private final float[] mLightPosInEyeSpace = new float[4];
	
	/** This is a handle to our cube shading program. */
	private int mProgramHandle;
		
	/** This is a handle to our light point program. */
	private int mPointProgramHandle;
	



    private float timestamp;



    /**
	 * Initialize the model data.
	 */
	public LessonFourRenderer(final Context activityContext, DictioLight dicoo)
	{
		this.dicoo = dicoo;
        mActivityContext = activityContext;

        // Define points for a cube.
		
		// X, Y, Z
		final float[] cubePositionData1 =
		{
				// In OpenGL counter-clockwise winding is default. This means that when we look at a triangle, 
				// if the points are counter-clockwise we are looking at the "front". If not we are looking at
				// the back. OpenGL has an optimization where all back-facing triangles are culled, since they
				// usually represent the backside of an object and aren't visible anyways.
				
				// Front face
				-2.0f, 1.0f, 1.0f,
				-2.0f, -1.0f, 1.0f,
                2.0f, 1.0f, 1.0f,
				-2.0f, -1.0f, 1.0f,
                2.0f, -1.0f, 1.0f,
                2.0f, 1.0f, 1.0f,

		};

		final float[] cubePositionData2 =
				{
						// In OpenGL counter-clockwise winding is default. This means that when we look at a triangle,
						// if the points are counter-clockwise we are looking at the "front". If not we are looking at
						// the back. OpenGL has an optimization where all back-facing triangles are culled, since they
						// usually represent the backside of an object and aren't visible anyways.




						// Back face
						-2.0f, 1.0f, 1.0f,
						-2.0f, -1.0f, 1.0f,
						2.0f, 1.0f, 1.0f,
						-2.0f, -1.0f, 1.0f,
						2.0f, -1.0f, 1.0f,
						2.0f, 1.0f, 1.0f,


				};

		final float[] cubePositionData3 =
				{
						// In OpenGL counter-clockwise winding is default. This means that when we look at a triangle,
						// if the points are counter-clockwise we are looking at the "front". If not we are looking at
						// the back. OpenGL has an optimization where all back-facing triangles are culled, since they
						// usually represent the backside of an object and aren't visible anyways.




						// Top face
                        -2.0f, 1.0f, 1.0f,
                        -2.0f, -1.0f, 1.0f,
                        2.0f, 1.0f, 1.0f,
                        -2.0f, -1.0f, 1.0f,
                        2.0f, -1.0f, 1.0f,
                        2.0f, 1.0f, 1.0f,

                };

		final float[] cubePositionData4 =
				{
						// In OpenGL counter-clockwise winding is default. This means that when we look at a triangle,
						// if the points are counter-clockwise we are looking at the "front". If not we are looking at
						// the back. OpenGL has an optimization where all back-facing triangles are culled, since they
						// usually represent the backside of an object and aren't visible anyways.



						// Bottom face
                        -2.0f, 1.0f, 1.0f,
                        -2.0f, -1.0f, 1.0f,
                        2.0f, 1.0f, 1.0f,
                        -2.0f, -1.0f, 1.0f,
                        2.0f, -1.0f, 1.0f,
                        2.0f, 1.0f, 1.0f,

				};

		// R, G, B, A
		final float[] cubeColorData1 =
		{
                1.0f, 1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 1.0f,


		};
		final float[] cubeColorData2 =
				{

						1.0f, 1.0f, 1.0f, 1.0f,
						1.0f, 1.0f, 1.0f, 1.0f,
						1.0f, 1.0f, 1.0f, 1.0f,
						1.0f, 1.0f, 1.0f, 1.0f,
						1.0f, 1.0f, 1.0f, 1.0f,
						1.0f, 1.0f, 1.0f, 1.0f,

				};
		final float[] cubeColorData3 =
				{

                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,

				};
		final float[] cubeColorData4 =
				{

                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,


                };

		// X, Y, Z
		// The normal is used in light calculations and is a vector which points
		// orthogonal to the plane of the surface. For a cube model, the normals
		// should be orthogonal to the points of each face.
		final float[] cubeNormalData1 =
		{
				// Front face
				0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f,


		};
		final float[] cubeNormalData2 =
				{

						// Back face
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,

				};
		final float[] cubeNormalData3 =
				{



                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,

				};
		final float[] cubeNormalData4 =
				{

                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
				};

		// S, T (or X, Y)
		// Texture coordinate data.
		// Because images have a Y axis pointing downward (values increase as you move down the image) while
		// OpenGL has a Y axis pointing upward, we adjust for that here by flipping the Y axis.
		// What's more is that the texture coordinates are the same for every face.
		final float[] cubeTextureCoordinateData1 =
		{												
				// Front face
				0.0f, 0.0f, 				
				0.0f, 1.0f,
				1.0f, 0.0f,
				0.0f, 1.0f,
				1.0f, 1.0f,
				1.0f, 0.0f,				
				

		};
		final float[] cubeTextureCoordinateData2 =
				{



						// Back face
						0.0f, 0.0f,
						0.0f, 1.0f,
						1.0f, 0.0f,
						0.0f, 1.0f,
						1.0f, 1.0f,
						1.0f, 0.0f,


				};

		final float[] cubeTextureCoordinateData3 =
				{

                        0.0f, 0.0f,
                        0.0f, 1.0f,
                        1.0f, 0.0f,
                        0.0f, 1.0f,
                        1.0f, 1.0f,
                        1.0f, 0.0f,


                };

		final float[] cubeTextureCoordinateData4 =
				{

                        0.0f, 0.0f,
                        0.0f, 1.0f,
                        1.0f, 0.0f,
                        0.0f, 1.0f,
                        1.0f, 1.0f,
                        1.0f, 0.0f,

                };


		// Initialize the buffers.
		mCubePositions1 = ByteBuffer.allocateDirect(cubePositionData1.length * mBytesPerFloat)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
		mCubePositions1.put(cubePositionData1).position(0);
		
		mCubeColors1 = ByteBuffer.allocateDirect(cubeColorData1.length * mBytesPerFloat)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
		mCubeColors1.put(cubeColorData1).position(0);
		
		mCubeNormals1 = ByteBuffer.allocateDirect(cubeNormalData1.length * mBytesPerFloat)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
		mCubeNormals1.put(cubeNormalData1).position(0);
		
		mCubeTextureCoordinates1 = ByteBuffer.allocateDirect(cubeTextureCoordinateData1.length * mBytesPerFloat)
		.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeTextureCoordinates1.put(cubeTextureCoordinateData1).position(0);

		mCubePositions2 = ByteBuffer.allocateDirect(cubePositionData2.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubePositions2.put(cubePositionData2).position(0);

		mCubeColors2 = ByteBuffer.allocateDirect(cubeColorData2.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeColors2.put(cubeColorData2).position(0);

		mCubeNormals2 = ByteBuffer.allocateDirect(cubeNormalData2.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeNormals2.put(cubeNormalData2).position(0);

		mCubeTextureCoordinates2 = ByteBuffer.allocateDirect(cubeTextureCoordinateData2.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeTextureCoordinates2.put(cubeTextureCoordinateData2).position(0);


		mCubePositions3 = ByteBuffer.allocateDirect(cubePositionData3.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubePositions3.put(cubePositionData3).position(0);

		mCubeColors3 = ByteBuffer.allocateDirect(cubeColorData3.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeColors3.put(cubeColorData3).position(0);

		mCubeNormals3 = ByteBuffer.allocateDirect(cubeNormalData3.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeNormals3.put(cubeNormalData3).position(0);

		mCubeTextureCoordinates3 = ByteBuffer.allocateDirect(cubeTextureCoordinateData3.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeTextureCoordinates3.put(cubeTextureCoordinateData3).position(0);


		mCubePositions4 = ByteBuffer.allocateDirect(cubePositionData4.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubePositions4.put(cubePositionData4).position(0);

		mCubeColors4 = ByteBuffer.allocateDirect(cubeColorData4.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeColors4.put(cubeColorData4).position(0);

		mCubeNormals4 = ByteBuffer.allocateDirect(cubeNormalData4.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeNormals4.put(cubeNormalData4).position(0);

		mCubeTextureCoordinates4 = ByteBuffer.allocateDirect(cubeTextureCoordinateData4.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeTextureCoordinates4.put(cubeTextureCoordinateData4).position(0);

	}
	
	protected String getVertexShader()
	{
		return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.per_pixel_vertex_shader);
	}
	
	protected String getFragmentShader()
	{
		return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.per_pixel_fragment_shader);
	}
	
	@Override
	public void onSurfaceCreated(GL10 myGL, EGLConfig config)
	{

		// Set the background clear color to black.
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		// Use culling to remove back faces.
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		
		// Enable depth testing
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);


		// The below glEnable() call is a holdover from OpenGL ES 1, and is not needed in OpenGL ES 2.
		// Enable texture mapping
		// GLES20.glEnable(GLES20.GL_TEXTURE_2D);
			
		// Position the eye in front of the origin.
		final float eyeX = 0.0f;
		final float eyeY = 0.0f;
		final float eyeZ = -0.5f;

		// We are looking toward the distance
		final float lookX = 0.0f;
		final float lookY = 0.0f;
		final float lookZ = -5.0f;

		// Set our up vector. This is where our head would be pointing were we holding the camera.
		final float upX = 0.0f;
		final float upY = 1.0f;
		final float upZ = 0.0f;

		// Set the view matrix. This matrix can be said to represent the camera position.
		// NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination of a model and
		// view matrix. In OpenGL 2, we can keep track of these matrices separately if we choose.
		Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);		

		final String vertexShader = getVertexShader();   		
 		final String fragmentShader = getFragmentShader();			
		
		final int vertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, vertexShader);		
		final int fragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);		
		
		mProgramHandle = ShaderHelper.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle, 
				new String[] {"a_Position",  "a_Color", "a_Normal", "a_TexCoordinate", "a_LightDir"});
        
        // Define a simple shader program for our point.
        final String pointVertexShader = RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.point_vertex_shader);        	       
        final String pointFragmentShader = RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.point_fragment_shader);
        
        final int pointVertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, pointVertexShader);
        final int pointFragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, pointFragmentShader);
        mPointProgramHandle = ShaderHelper.createAndLinkProgram(pointVertexShaderHandle, pointFragmentShaderHandle, 
        		new String[] {"a_Position"}); 
        
        // Load the texture
      //  mTextureDataHandle = TextureHelper.loadTexture(mActivityContext, R.drawable.bumpy_bricks_public_domain);

        mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_TexCoordinate");
        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        // Bind the texture to this unit.
        //GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.


	}
		
	@Override
	public void onSurfaceChanged(GL10 glUnused, int width, int height) 
	{
		// Set the OpenGL viewport to the same size as the surface.
		GLES20.glViewport(0, 0, width, height);

		// Create a new perspective projection matrix. The height will stay the same
		// while the width will vary as per aspect ratio.
		final float ratio = (float) width / height;
		final float left = -ratio;
		final float right = ratio;
		final float bottom = -1.0f;
		final float top = 1.0f;
		final float near = 1.0f;
		final float far = 10.0f;
		
		Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
	}	

	@Override
	public void onDrawFrame(GL10 glUnused) 
	{
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);			        
                
        // Do a complete rotation every 10 seconds.
        long time = SystemClock.uptimeMillis() % 10000L;        
        float angleInDegrees = (360.0f / 1000.0f) * ((int) time);
        
        // Set our per-vertex lighting program.
        GLES20.glUseProgram(mProgramHandle);
        
        // Set program handles for cube drawing.
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVPMatrix");
        mMVMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVMatrix");
        mLightPosHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_LightPos");
        mTextureUniformHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_Texture");
        mPositionHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Position");
        mColorHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Color");
        mDirectionHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_LightDir");

        mNormalHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Normal"); 


        // Calculate position of the light. Rotate and then push into the distance.
        Matrix.setIdentityM(mLightModelMatrix, 0);
        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, -7.0f);
        //Matrix.rotateM(mLightModelMatrix, 0, angleInDegrees, 0.0f, 1.0f, 0.0f);
        //Matrix.rotateM(mLightModelMatrix, 0, gyroscope[2], 0.0f, 0.3f, 0.0f);
        //Matrix.rotateM(mLightModelMatrix, 0, gyroscope[1], 0.4f, 0.0f, 0.0f);
        Matrix.translateM(mLightModelMatrix, 0,0.0f,  gyroscope[1]/9, 0.0f);
        Matrix.translateM(mLightModelMatrix, 0,gyroscope[2]/9,  0.0f,0.0f );
		Matrix.translateM(mLightModelMatrix, 0, 0.0f, 2.0f, 0.0f);

     //   Log.w("gyro1", "" +gyroscope[1]);
     //   Log.w("gyro2", "" +gyroscope[2]);

        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, 1.001f);
        Matrix.multiplyMV(mLightPosInWorldSpace, 0, mLightModelMatrix, 0, mLightPosInModelSpace, 0);
        Matrix.multiplyMV(mLightPosInEyeSpace, 0, mViewMatrix, 0, mLightPosInWorldSpace, 0);

        GLES20.glUniform1i(mTextureUniformHandle, 0);
        maPhrase = dicoo.getPhrase();
        mTextureUniformHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_Texture");

        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, 2.8f, -0.9f, -7.0f);
        //Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 1.0f, 0.0f, 0.0f);
        drawCube1();

        // Texte qui sera en haut
        //	Log.w("GAUCHE", maPhrase[0]);

        drawText(maPhrase[0]);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
        GLES20.glUniform1i(mTextureUniformHandle, 1);


        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, -1.3f, 0.0f, -7.0f);
        //Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 1.0f, 0.0f, 0.0f);
        drawCube2();
        // Texte qui sera en bas
        //	Log.w("HAUT", maPhrase[1]);

        drawText(maPhrase[1]);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE2);
        GLES20.glUniform1i(mTextureUniformHandle, 2);


        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, 0.0f, 4.0f, -7.0f);
        //Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 1.0f, 0.0f, 0.0f);
        drawCube3();
        // Texte qui sera à droite
        //Log.w("BAS", maPhrase[2]);

        drawText(maPhrase[2]);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE3);
        GLES20.glUniform1i(mTextureUniformHandle, 3);


        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, 0.0f, -4.0f, -7.0f);
        //Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 1.0f, 0.0f, 0.0f);
        drawCube4();
        // Texte qui sera à gauche
        //	Log.w("DROITE", maPhrase[3]);

        drawText(maPhrase[3]);


        // Draw a point to indicate the light.
        GLES20.glUseProgram(mPointProgramHandle);        
        drawLight();
	}				
	
	/**
	 * Draws a cube.
	 */			
	private void drawCube1()
	{		
		// Pass in the position information
		mCubePositions1.position(0);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
        		0, mCubePositions1);
                
        GLES20.glEnableVertexAttribArray(mPositionHandle);        
        
        // Pass in the color information
        mCubeColors1.position(0);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
        		0, mCubeColors1);
        
        GLES20.glEnableVertexAttribArray(mColorHandle);
        
        // Pass in the normal information
        mCubeNormals1.position(0);
        GLES20.glVertexAttribPointer(mNormalHandle, mNormalDataSize, GLES20.GL_FLOAT, false, 
        		0, mCubeNormals1);
        
        GLES20.glEnableVertexAttribArray(mNormalHandle);
        
        // Pass in the texture coordinate information
        mCubeTextureCoordinates1.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false,
                0, mCubeTextureCoordinates1);
        
        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);
        
		// This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);   
        
        // Pass in the modelview matrix.
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);                
        
        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        // Pass in the combined matrix.
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        
        // Pass in the light position in eye space.
        GLES20.glUniform3f(mLightPosHandle, mLightPosInEyeSpace[0], mLightPosInEyeSpace[1], mLightPosInEyeSpace[2]);
        GLES20.glUniform3f(mDirectionHandle, gyroscope[0], gyroscope[1], gyroscope[2]);

        // Draw the cube.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 4);
	}
    private void drawCube2()
    {
        // Pass in the position information
        mCubePositions2.position(0);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
                0, mCubePositions2);

        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Pass in the color information
        mCubeColors2.position(0);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
                0, mCubeColors2);

        GLES20.glEnableVertexAttribArray(mColorHandle);

        // Pass in the normal information
        mCubeNormals2.position(0);
        GLES20.glVertexAttribPointer(mNormalHandle, mNormalDataSize, GLES20.GL_FLOAT, false,
                0, mCubeNormals2);

        GLES20.glEnableVertexAttribArray(mNormalHandle);

        // Pass in the texture coordinate information
        mCubeTextureCoordinates2.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false,
                0, mCubeTextureCoordinates2);

        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

        // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);

        // Pass in the modelview matrix.
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);

        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        // Pass in the combined matrix.
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);

        // Pass in the light position in eye space.
        GLES20.glUniform3f(mLightPosHandle, mLightPosInEyeSpace[0], mLightPosInEyeSpace[1], mLightPosInEyeSpace[2]);

        // Draw the cube.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 4);
    }
    private void drawCube3()
    {
        // Pass in the position information
        mCubePositions3.position(0);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
                0, mCubePositions3);

        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Pass in the color information
        mCubeColors3.position(0);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
                0, mCubeColors3);

        GLES20.glEnableVertexAttribArray(mColorHandle);

        // Pass in the normal information
        mCubeNormals3.position(0);
        GLES20.glVertexAttribPointer(mNormalHandle, mNormalDataSize, GLES20.GL_FLOAT, false,
                0, mCubeNormals3);

        GLES20.glEnableVertexAttribArray(mNormalHandle);

        // Pass in the texture coordinate information
        mCubeTextureCoordinates3.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false,
                0, mCubeTextureCoordinates3);

        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

        // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);

        // Pass in the modelview matrix.
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);

        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        // Pass in the combined matrix.
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);

        // Pass in the light position in eye space.
        GLES20.glUniform3f(mLightPosHandle, mLightPosInEyeSpace[0], mLightPosInEyeSpace[1], mLightPosInEyeSpace[2]);

        // Draw the cube.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 4);
    }
    private void drawCube4()
    {
        // Pass in the position information
        mCubePositions4.position(0);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
                0, mCubePositions4);

        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Pass in the color information
        mCubeColors4.position(0);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
                0, mCubeColors4);

        GLES20.glEnableVertexAttribArray(mColorHandle);

        // Pass in the normal information
        mCubeNormals4.position(0);
        GLES20.glVertexAttribPointer(mNormalHandle, mNormalDataSize, GLES20.GL_FLOAT, false,
                0, mCubeNormals4);

        GLES20.glEnableVertexAttribArray(mNormalHandle);

        // Pass in the texture coordinate information
        mCubeTextureCoordinates4.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false,
                0, mCubeTextureCoordinates4);

        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

        // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);

        // Pass in the modelview matrix.
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);

        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        // Pass in the combined matrix.
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);

        // Pass in the light position in eye space.
        GLES20.glUniform3f(mLightPosHandle, mLightPosInEyeSpace[0], mLightPosInEyeSpace[1], mLightPosInEyeSpace[2]);

        // Draw the cube.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 4);
    }


	private void drawText(String texte)
	{
        GLES20.glDisable(GLES20.GL_DEPTH_TEST);

        Bitmap bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0);

// get a background image from resources
// note the image format must match the bitmap format
        Drawable background = mActivityContext.getResources().getDrawable(R.drawable.background);
        background.setBounds(0, 0, 256, 256);
        background.draw(canvas); // draw the background to our bitmap

// Draw the text
        Paint textPaint = new Paint(0);
        textPaint.setAntiAlias(false);
        textPaint.setARGB(255, 255, 255, 255);
        textPaint.setTextSize(35);
        Typeface myCustomFont = Typeface.createFromAsset(mActivityContext.getAssets(),"fonts/PoiretOne-Regular.ttf");
        textPaint.setTypeface(myCustomFont);
// draw the text centered
        canvas.drawText(texte, 16, 112, textPaint);

        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);

//Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

//Clean up
        bitmap.recycle();

        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

		// This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
		// (which currently contains model * view).
		Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);

		// Pass in the modelview matrix.
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);

		// This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
		// (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

		// Pass in the combined matrix.
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);

		// Pass in the light position in eye space.
        GLES20.glUniform3f(mLightPosHandle, mLightPosInEyeSpace[0], mLightPosInEyeSpace[1], mLightPosInEyeSpace[2]);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

	}

	/**
	 * Draws a point representing the position of the light.
	 */
	private void drawLight()
	{
		final int pointMVPMatrixHandle = GLES20.glGetUniformLocation(mPointProgramHandle, "u_MVPMatrix");
        final int pointPositionHandle = GLES20.glGetAttribLocation(mPointProgramHandle, "a_Position");
        
		// Pass in the position.
		GLES20.glVertexAttrib3f(pointPositionHandle, mLightPosInModelSpace[0], mLightPosInModelSpace[1], mLightPosInModelSpace[2]);

		// Since we are not using a buffer object, disable vertex arrays for this attribute.
        GLES20.glDisableVertexAttribArray(pointPositionHandle);  
		
		// Pass in the transformation matrix.
		Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mLightModelMatrix, 0);
		Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
		GLES20.glUniformMatrix4fv(pointMVPMatrixHandle, 1, false, mMVPMatrix, 0);
		
		// Draw the point.
		//GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 1);
		Circle circ = new Circle();
		circ.draw(mMVPMatrix);
	}


	public ArrayList<String> setGyro(float gyro[]){




        this.gyroscope[0] = (float) (gyro[0] * 180/Math.PI);
        this.gyroscope[1] = (float) (gyro[1] * 180/Math.PI);
        this.gyroscope[2] = (float) (gyro[2] * 180/Math.PI);

        GLES20.glUniform3f(mDirectionHandle, gyroscope[0], gyroscope[1], gyroscope[2]);

     //  Log.w("LogMatrice", ""+mLightModelMatrix[12] + " " + mLightModelMatrix[13]);
if(!are_finished[0]){
		if(((mLightModelMatrix[12] > 1.0) && 	(mLightModelMatrix[12] < 3.0))&&((mLightModelMatrix[13] > -1.0) && (mLightModelMatrix[13] < -0.2))){
			Log.w("LogMatrice", "Phrase 1" );
            myFinalePhrase.add(maPhrase[3]);
            are_finished[0]= true;
            if(are_finished[0] && are_finished[1] && are_finished[2] && are_finished[3])
                return myFinalePhrase;
		}}
        if(!are_finished[1]){
		if(((mLightModelMatrix[12] >= -1.8) && 	(mLightModelMatrix[12] <= 0.0)) && ((mLightModelMatrix[13] >= 3.8) &&	(mLightModelMatrix[13] <= 4.1))) {
			Log.w("LogMatrice", "Phrase 2" );
            myFinalePhrase.add(maPhrase[1]);
            are_finished[1]= true;
            if(are_finished[0] && are_finished[1] && are_finished[2] && are_finished[3])
                return myFinalePhrase;


        }}
        if(!are_finished[2]){
            if(((mLightModelMatrix[12] >= -1.8) && 	(mLightModelMatrix[12] <= 0.0))&& ((mLightModelMatrix[13] <= -3.8) && (mLightModelMatrix[13] >= -4.0))){
			Log.w("LogMatrice", "Phrase 3" );
            myFinalePhrase.add(maPhrase[2]);
            are_finished[2]= true;
            if(are_finished[0] && are_finished[1] && are_finished[2] && are_finished[3])
                return myFinalePhrase;

		}}
        if(!are_finished[3]){
        if((mLightModelMatrix[12] > -3.0 && 	mLightModelMatrix[12] < -1.0)&& (mLightModelMatrix[13] > 0.0 && 	mLightModelMatrix[13] < 0.8)){
			Log.w("LogMatrice", "Phrase 4" );
            myFinalePhrase.add(maPhrase[0]);
            are_finished[3]= true;
            if(are_finished[0] && are_finished[1] && are_finished[2] && are_finished[3])
                return myFinalePhrase;
		}}

        return null;

    }

}
