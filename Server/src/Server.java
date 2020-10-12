import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {
            server = new ServerSocket(8888);
            // 监听。
            client = server.accept();
            // 获取 Socket对象。

            // 获取输入流。
            dis = new DataInputStream(client.getInputStream());
            System.out.println(dis.readUTF());

            // 获取输出流。给客户端做出响应结果。
            dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF("客户端你好，我是服务器。我收到了你的请求。");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            // 关闭流。
            IOClose.closeAll(dis,dos,client, server);
        }
    }
}
