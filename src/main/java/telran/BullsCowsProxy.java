package telran;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class BullsCowsProxy implements BullsCowsService {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public BullsCowsProxy(String serverAddress, int serverPort) throws Exception {
        socket = new Socket(serverAddress, serverPort);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public Long createNewGame() {
        out.println("START");
        try {
            String response = in.readLine();
            return Long.parseLong(response.split(": ")[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MoveResult> getResults(long gameId, Move move) {
        out.println("MOVE " + gameId + " " + move.getClientSequence());
        try {
            String response = in.readLine();
            
            return null; 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean isGameOver(long gameId) {
        out.println("RESULTS " + gameId);
        try {
            String response = in.readLine();
            return response.contains("Game Over");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() throws Exception {
        in.close();
        out.close();
        socket.close();
    }
}