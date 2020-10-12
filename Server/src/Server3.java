import java.awt.image.CropImageFilter;
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
 * 应用层协议： HTTP, FTP, TELNET, SNMP, DNS
 * 传输层协议： TCP， UDP
 * 网络层：IP
 */

public class Server3 {
    public static void main(String[] args) {
        String CRLF = "\r\n";
        String BLANK = " ";

        ServerSocket server = null;
        Socket client = null;
        // 改成字节
        InputStream is = null;

        try {
            server = new ServerSocket(8888);
            // 监听。
            client = server.accept();
            // 获取 Socket对象。

            is = client.getInputStream();
            byte[] buf = new byte[1024 * 20];
            int len = is.read(buf);
            System.out.println(new String(buf, 0, len));

            StringBuilder sbContent = new StringBuilder(); //响应的 文本，
            // 由于响应的是一个html页面。
            sbContent.append("<html><head><title>响应的页面</title></head>");
            sbContent.append("<body>登陆成功</body></html>");

            // 对web 浏览器请求做出响应。
            StringBuilder sb = new StringBuilder();
            // 拼接响应头。
            sb.append("HTTP/1.1").append(BLANK).append(200).append(BLANK).append("OK");
            sb.append(CRLF);
            sb.append("Content-Type: text/html;charset=utf-8");
            sb.append(CRLF);
            sb.append("Content-Length").append(sbContent.toString().getBytes().length).append(CRLF);
            sb.append(CRLF); // 与正文的空行。
            sb.append(sbContent);

            //通过流 输出。
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(sb.toString());

            bw.flush();
            bw.close();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            // 关闭流。
            IOClose.closeAll(is,client, server);
        }
    }
}
