import java.io.Closeable;
import java.io.IOException;

public class IOClose {
    public static void closeAll(Closeable...c){
        for (Closeable i: c){
            if (c != null){
                try {
                    i.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}