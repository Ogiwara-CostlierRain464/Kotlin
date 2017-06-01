/**
 * Created by ogiwara on 2017/05/30.
 */
public class SynchronizedPoint2 {

    private int x;
    private int y;

    synchronized void rightUp(){
        x++;
        y++;
    }

    synchronized int getX(){
        return x;
    }

    synchronized public int getY() {
        return y;
    }
}
