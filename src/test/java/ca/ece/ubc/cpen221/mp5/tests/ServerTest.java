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
                    assertEquals("ERR: INVALID_QUERY", reply);
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });

        Thread query3 = new Thread(new Runnable(){
            public void run() {
                try{
                    YelpDBClient client = new YelpDBClient("localhost", 4949);
                    client.sendRequest("ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/the-toaster-oven-berkeley\", \"longitude\": -122.2590117, \"neighborhoods\": [\"Telegraph Ave\", \"UC Campus Area\"], \"business_id\": \"cookie\", \"name\": \"TEST\", \"categories\": [\"Sandwiches\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.5, \"city\": \"Berkeley\", \"full_address\": \"2309 Telegraph Ave\\nTelegraph Ave\\nBerkeley, CA 94704\", \"review_count\": 55, \"photo_url\": \"http://s3-media2.ak.yelpcdn.com/bphoto/1IirdUSB0yi4TorBV5JPoA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8684118, \"price\": 1}");
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });

        Thread query4 = new Thread(new Runnable(){
            public void run() {
                try{
                    YelpDBClient client = new YelpDBClient("localhost", 4949);
                    client.sendRequest("GETRESTAURANT notarestaurant");
                    String reply = client.getReply();
                    assertEquals("ERR: NO_SUCH_RESTAURANT", reply);
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });

        Thread query9 = new Thread(new Runnable(){
            public void run() {
                try{
                    YelpDBClient client = new YelpDBClient("localhost", 4949);
                    client.sendRequest("GETRESTAURANT cookie");
                    String reply = client.getReply();
                    assertNotEquals("ERR: NO_SUCH_RESTAURANT", reply);
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });

        Thread query5 = new Thread(new Runnable(){
            public void run() {
                try{
                    YelpDBClient client = new YelpDBClient("localhost", 4949);
                    client.sendRequest("QUERY name( TEST )");
                    String reply = client.getReply();
                    System.out.println(reply);
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });

        Thread query6 = new Thread(new Runnable(){
            public void run() {
                try{
                    YelpDBClient client = new YelpDBClient("localhost", 4949);
                    client.sendRequest("QUERY name( RANDOM )");
                    String reply = client.getReply();
                    System.out.println(reply);
                    assertEquals("ERR: NO_MATCH", reply);
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });

        Thread query7 = new Thread(new Runnable(){
            public void run() {
                try{
                    YelpDBClient client = new YelpDBClient("localhost", 4949);
                    client.sendRequest("ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"text\": \"Great pizza and salads!\", \"stars\": 4, \"user_id\": \"Tq13lhXLgpOQHmp9CxoIkA\", \"date\": \"2012-05-25\"}");
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });


        Thread query8 = new Thread(new Runnable(){
            public void run() {
                try{
                    YelpDBClient client = new YelpDBClient("localhost", 4949);
                    client.sendRequest("ADDUSER {\"name\": \"bob\"}");
                    String reply = client.getReply();
                    System.out.println(reply);
                    assertNotNull(reply);
                    client.close();
                } catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });


        testServer.start();
        query1.run();
        query2.run();
        query3.run();
        query3.join();
        query4.run();
        query5.run();
        query6.run();
        query7.run();
        query8.run();
        query9.run();
        testServer.join(1);
    }

}
