package myfirstprocessing;

import processing.core.*;

public class Collision{
	public PVector v1,v2,un,ut,v1nf,v2nf,v1tf,v2tf,v1f,v2f;//v1 initial, v2i, unit normal, unit tang, v1 normal final,v2nf,v1 tan final,v2tf,v1 final,v2 final
	public void setup() {
		
	}
	
	public void calc(int x1, int x2, int y1, int y2, int m1, int m2, int sx1, int sx2, int sy1, int sy2) {
	    un=new PVector(x1-x2,y1-y2);
	    un.normalize();
	    ut=new PVector(-un.y,un.x);
	    
	    v1=new PVector(sx1*m1,sy1*m1);
	    v2=new PVector(sx2*m2,sy2*m2);
	    float v1n,v1t,v2n,v2t;//v1 normal, v1 tang etc.
	    v1n=un.dot(v1);
	    v1t=ut.dot(v1);
	    v2n=un.dot(v2);
	    v2t=ut.dot(v2);
	    float v1nFinal,v2nFinal;
	    v1nFinal=(v1n*(m1-m2)+(2*m2*v2n))/(m1+m2);
	    v2nFinal=(v2n*(m2-m1)+(2*m1*v1n))/(m1+m2);
	    v1nf=un.get();
	    v2nf=un.get();
	    v1nf.mult(v1nFinal);
	    v2nf.mult(v2nFinal);
	    v1tf=ut.get();
	    v2tf=ut.get();
	    v1tf.mult(v1t);
	    v2tf.mult(v2t);
	    
	    v1f=PVector.add(v1nf, v1tf);//momentum vector
	    v2f=PVector.add(v2nf, v2tf);//momentum vector
	    
	}
	
	public void draw() {
	    //ellipse(v1.x,v1.y,12,12);
	}
    public static void collide(Ellipses a, Ellipses b){
        double f_m1=a.mass;
        double f_vx1=a.vx;
        double f_vy1=a.vy;
        double f_x1=a.x;
        double f_y1=a.y;
        double f_m2=b.mass;
        double f_vx2=b.vx;
        double f_vy2=b.vy;
        double f_x2=b.x;
        double f_y2=b.y;

        double f_v1 = Math.sqrt(f_vx1 * f_vx1 + f_vy1 * f_vy1);
        double f_a1 = Math.atan2(f_vy1, f_vx1);
        double f_v2 = Math.sqrt(f_vx2 * f_vx2 + f_vy2 * f_vy2);
        double f_a2 = Math.atan2(f_vy2, f_vx2);
        double f_dx = f_x1 - f_x2;
        double f_dy = f_y1 - f_y2;
        double f_da = Math.atan2(f_dy, f_dx);
        double tf_a1 = f_a1 - f_da;
        double tf_a2 = f_a2 - f_da;
        double tf_vx1 = f_v1 * Math.cos(tf_a1);
        double tf_vy1 = f_v1 * Math.sin(tf_a1);
        double tf_vx2 = f_v2 * Math.cos(tf_a2);
        double tf_vy2 = f_v2 * Math.sin(tf_a2);
        double f_d1 = 2 * (tf_vx2 - tf_vx1) / (1 + f_m1 / f_m2);
        double f_d2 =  - f_m1 / f_m2 * f_d1;
        tf_vx1 += f_d1;
        tf_vx2 += f_d2;
        f_v1 = Math.sqrt(tf_vx1 * tf_vx1 + tf_vy1 * tf_vy1);
        tf_a1 = Math.atan2(tf_vy1, tf_vx1);
        f_v2 = Math.sqrt(tf_vx2 * tf_vx2 + tf_vy2 * tf_vy2);
        tf_a2 = Math.atan2(tf_vy2, tf_vx2);
        f_a1 = tf_a1 + f_da;
        f_a2 = tf_a2 + f_da;
        f_vx1 = f_v1 * Math.cos(f_a1);
        f_vy1 = f_v1 * Math.sin(f_a1);
        f_vx2 = f_v2 * Math.cos(f_a2);
        f_vy2 = f_v2 * Math.sin(f_a2);

/*        a.vx=f_vx1;
        a.vy=f_vy1;
        b.vx=f_vx2;
        b.vy=f_vy2;
        */
        a.speed = f_v1;
        b.speed = f_v2;
        a.direction = f_a1 / Math.PI * 180;
        b.direction = f_a2 / Math.PI * 180;
    }

}
