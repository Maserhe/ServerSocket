import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        // (1) 创建Socket对象。
        Socket client = null;
        // (2) 获取输出流 --> 请求
        DataOutputStream dos = null;
        //（3）获取输入流 --> 响应
        DataInputStream dis = null;
        try {
            client = new Socket("localhost", 8888);
            dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF("我是客户端，服务器你好");

            // （3）获取输入流 --> 响应。
            dis = new DataInputStream(client.getInputStream());
            System.out.println(dis.readUTF());
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            // (4) 关闭流

            IOClose.closeAll(dis,dos,client);
        }

    }
}

// c/s 结构：客户端 与 服务端
// b/s 结构：浏览器 与 服务器