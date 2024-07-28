package telran;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BullsCowsProxy {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public BullsCowsProxy(String serverAddress, int serverPort) throws Exception {
        socket = new Socket(serverAddress, serverPort);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendRequest(String request) {
        out.println(request);
    }

    public String getResponse() throws Exception {
        return in.readLine();
    }

    public void close() throws Exception {
        in.close();
        out.close();
        socket.close();
    }
}