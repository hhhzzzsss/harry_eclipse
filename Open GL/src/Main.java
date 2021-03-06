import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import input.KeyboardHandler;
import input.MouseHandler;

import java.nio.ByteBuffer;
 
import org.lwjgl.LWJGLUtil;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
 
public class Main {
 
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    private GLFWCursorPosCallback mouseCallback;
 
    // The window handle
    private long window;
 
    public void run() {
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
 
        try {
            init();
            loop();
 
            // Release window and window callbacks
            glfwDestroyWindow(window);
            keyCallback.release();
        } finally {
            // Terminate GLFW and release the GLFWerrorfun
            glfwTerminate();
            errorCallback.release();
        }
    }
 
    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
 
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
 
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
 
        int WIDTH = 300;
        int HEIGHT = 300;
 
        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
 
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new KeyboardHandler());
        
        glfwSetCursorPosCallback(window, mouseCallback = new MouseHandler());
 
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (GLFWvidmode.width(vidmode) - WIDTH) / 2,
            (GLFWvidmode.height(vidmode) - HEIGHT) / 2
        );
 
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
 
        // Make the window visible
        glfwShowWindow(window);
    }
    
    public void update(){
    	if(KeyboardHandler.isKeyDown(GLFW_KEY_SPACE))
    		System.out.println("Space Key Pressed");
    }
 
    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
 
        // Set the clear color
        glClearColor(0f, 1.0f, 1.0f, 0.0f);
 
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
 
            glfwSwapBuffers(window); // swap the color buffers
 
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
            
            update();
            
        }
    }
 
    public static void main(String[] args) {
        new Main().run();
    }
 
    // Gives us our orthographic matrix
 	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far){
 		Matrix4f matrix = new Matrix4f();
 		
 		matrix.elements[0 + 0 * 4] = 2.0f / (right - left);
 		matrix.elements[1 + 1 * 4] = 2.0f / (top - bottom);
 		matrix.elements[2 + 2 * 4] = 2.0f / (near - far);

 		matrix.elements[0 + 3 * 4] = (left + right) / (left - right);
 		matrix.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
 		matrix.elements[2 + 3 * 4] = (far + near) / (far - near);
 		
 		matrix.elements[3 + 3 * 4] = 1.0f;
 		
 		return matrix;
 	}
}
