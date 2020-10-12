import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1 , 创建ServerSocket 对象。
 * 2 , 监听是否有客户端发送请求
 * 3 , 获取Socket对象
 * 4 , 获取输入流--》 得到客户端请求
 * 5 , 获取输出流--》 给客户端响应请求。
 *
 */

public class Server2 {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        BufferedReader br = null;

        try {
            server = new ServerSocket(8888);
            // 监听。
            client = server.accept();
            // 获取 Socket对象。
            br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));

            String str = null;
            while ((str = br.readLine()).length() > 0 ){
                System.out.println(str);
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            // 关闭流。
            IOClose.closeAll(br,client, server);
        }
    }
}
