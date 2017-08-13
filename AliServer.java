
/**
 *This class run the serverSocket and it will appear as a host server.
 *
 *
 * @category Ali Saheb
 * @author Ali Saheb
 * @since 1.0.0
 * @see
 * @link
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.*;
import java.util.Random;

public class AliServer {
	static final int PORT = 8000;

	/**
	  * This is the main function of server which run the server at port 8000
	  *
	  *
	  * @author Ali Saheb
	  * @return  void
	  * @since 1.0.0
	  * @see
	  */
	public static void main(String []args) throws IOException{

		ServerSocket serverSocket = null;
        Socket socket = null;

        /*generating hash password from server*/
        String passwordhash = inputHash();

        /*generating Server Socket*/
        try{
        	serverSocket = new ServerSocket(PORT);
        }
        catch(IOException e){
        	e.printStackTrace();
        }
        /*Counting Client and Identify*/
        int countClient =0;
        /*This control loop accept request from client and generate different thread for every client */
        while(true){
        	try {
                socket = serverSocket.accept();
                //countClient++;
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
        	/*Send the client identity and socket object to the */
        	new AliServerThread(socket,countClient,passwordhash).start();
        	countClient++;
        	if(countClient == 60){
        		countClient=0;
        	}


        }

	}

	/**
	  * This generateRandomPass method generate 5 character password with 36 character 0 to 9 and A to Z
	  * and add the current with this password
	  * when the server run.
	  *
	  *
	  * @author Ali Saheb
	  * @return  String ali
	  * @since 1.0.0
	  * @see
	  */

	public static String generateRandomPass(){
		Random rand = new Random();
		String pass = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String ali="";
		for(int i=0;i<=4;i++){
			int  n = rand.nextInt(35) + 0;
			char a = pass.charAt(n);
			ali += a;
		}

		Date date = new Date();
		SimpleDateFormat ft =
			      new SimpleDateFormat ("dd-MM-yyyy");

		String saheb = ft.format(date).toString();
		ali +=saheb;
		ali = Hashmd5(ali);
		/*String test = "00000";
		test +=saheb;
		test = Hashmd5(test);*/
		return ali;
	}

	/**
	  * This Hashmd5 hash the password with MD5.
	  *
	  *
	  * @author Ali Saheb
	  * @return  String hashtext
	  * @since 1.0.0
	  * @see
	  */
	public static String Hashmd5(String input){
		try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] messageDigest = md.digest(input.getBytes());
	        BigInteger number = new BigInteger(1, messageDigest);
	        String hashtext = number.toString(16);
	        // Now we need to zero pad it if you actually want the full 32 chars.
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }
	        return hashtext;
	    }
	    catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException(e);
	    }
	}

	public static String inputHash()throws IOException{
					String key = readInputFromFile();
					String[] keys = key.split(" ");
					String[] parts = keys[1].split("\"");
					//System.out.println(parts[1]);
					return parts[1];
		}

	public static String readInputFromFile() throws IOException{
				FileReader input = null;
				 String inputString="";
			   try {
				   input = new FileReader("input.txt");

			      int inputInt;

			      while ((inputInt = input.read()) != -1) {
			    	  inputString += new Character((char) inputInt).toString();

			      }

			   }finally {
			      if (input != null) {
			    	  input.close();
			      }
			   }


			   return inputString;

			}


}
