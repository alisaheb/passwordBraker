

/**
 *This class run thread for every client .
 *And this class send the password limit and receive feedback from every client and print the message.
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
//import java.io.IOException;

import java.awt.List;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class AliServerThread extends Thread{
	protected static Socket socket;
	protected static int clientNumber;
	protected static String serverPass;

	static ArrayList timecount = new ArrayList();

/*	set the socket and client number*/
	public AliServerThread(Socket clientSocket,int clientIdenfy,String password ){
		AliServerThread.socket = clientSocket;
		AliServerThread.clientNumber = clientIdenfy;
		AliServerThread.serverPass = password;
	}

/*run thread for every client*/
	public void run(){

		try {
			sendParam();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	  * This sendParam send the parameter of password limit to the client and receive client message
	  *
	  * @author Ali Saheb
	  * @return List studentList
	  * @since 1.0.0
	  * @see
	  */

	public static void sendParam() throws IOException{
		AliSetPasswordLimit passLim = new AliSetPasswordLimit();
		ArrayList<AliPasswordSloat> passSloat = new ArrayList<AliPasswordSloat>();
		ArrayList<String> ali = new ArrayList<String>();


		//ArrayList al = new ArrayList();

		passSloat = (ArrayList<AliPasswordSloat>) passLim.passwordsloat();
		ObjectOutputStream objectOutput;
		AliPasswordSloat name = passSloat.get(clientNumber);
		//starting time
		long start = System.currentTimeMillis( );
		timecount.add(start);
		//System.out.println(name.getLP());

		/*setting client id*/

		name.setClient(Integer.toString(clientNumber));
		/*first try for breaking*/
		ali.add(name.getLP());
		ali.add(name.getUP());
		ali.add(serverPass);

		try{
			objectOutput = new ObjectOutputStream(socket.getOutputStream());
			objectOutput.writeObject(ali);
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("LP : "+name.getLP()+" UP: "+name.getUP()+" Client Number: "+name.getClient());
		try{
			Scanner sc = new Scanner(socket.getInputStream());
			String a = sc.nextLine();
			System.out.println(a);
			//AliPasswordSloat time = passSloat.get(1);
			if(a.length()>17){


				long startTime = (long)timecount.get(0);
				long EndTime = System.currentTimeMillis();
				long dif = EndTime -  startTime;
				System.out.println("Time To find the KEY: "+dif+"ms");

					FileOutputStream out = null;
				try{
					out = new FileOutputStream("output.txt");

					for(int ij =0;ij<a.length();ij++){
					    	   int chars;
					    	   chars= a.charAt(ij);
					    	   out.write(chars);
    	   				}

					}finally{
					if(out != null){
						out.close();
						}
					}
				}

		}catch(IOException e){
			System.out.println();
		}

	}

}
