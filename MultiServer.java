import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class MultiServer {  
    private static void running(final Socket client) throws IOException {  
        new Thread(new Runnable() {  
            public void run() {    
                try {  
                	BufferedReader in = new BufferedReader(new InputStreamReader(
                			client.getInputStream()));  
                	PrintWriter out = new PrintWriter(client.getOutputStream());  
  
                    while (true) {  
                        String str = in.readLine();    
                        for (int i =str.length()-1; i >= 0; i--) {
                        	System.out.print(str.charAt(i));
                        }
                        out.flush();  
                        if (str.equals("end")) {  
                            break;  
                        }  
                    }  
                    client.close();
                } catch(IOException ex) {  
                    ex.printStackTrace();  
                } finally {    
                    }   
                
            }  
        }).start();  
    }  
    
    public static void main(String[] args) throws IOException {  
        ServerSocket server = new ServerSocket(3333);  
        
        while (true) {  
            Socket socket = server.accept();  
            running(socket);  
        }  
    }  
}  