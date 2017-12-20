package ca.ece.ubc.cpen221.mp5.tests;
import ca.ece.ubc.cpen221.mp5.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;

public class ServerTest {
    @Test
    public void test0() throws IOException, InterruptedException {
        Thread testServer = new Thread (new Runnable() {
            public void run() {
                try {
                    YelpDBServer server = new YelpDBServer(4949);
                    server.serve();
                } catch (IOException e) {

                }
            }

        });

        Thread query1 = new Thread(new Runnable(){
           public void run() {
               try{
                   YelpDBClient client = new YelpDBClient("localhost", 4949);
                   client.sendRequest("INVALID");
                   String reply = client.getReply().toString();
                   assertEquals("INVALID REQUEST", reply);
                   client.close();

               } catch (Exception e){
                   System.out.println("ERROR");
               }
           }
        });

        Thread query2 = new Thread(new Runnable(){
            public void run() {
                try{
                    YelpDBClient client = new YelpDBClient("localhost", 4949);
                    client.sendRequest("QUERY rating < 1 &&");
                    String reply = client.getReply().toString();
                    System.out.println(reply);
                    assertEquals("ERR: INVALID_QUERY", reply);
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });


        testServer.start();
        query1.run();
        query2.run();
        testServer.join(1);
    }

}
