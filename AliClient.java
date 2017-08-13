
/**
 *This class create a client and send request to the server which start at port 8000.
 *
 *
 * @category Ali Saheb
 * @author Ali Saheb
 * @since 1.0.0
 * @see
 * @link
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AliClient {
	@SuppressWarnings("unchecked")
	/**
	  * This is the main function of client which run the client.
	  *
	  *
	  * @author Ali Saheb
	  * @return  void
	  * @since 1.0.0
	  * @see
	  */
	public static void main(String []args) throws UnknownHostException, IOException, ClassNotFoundException{
		ArrayList<String> fainalCheck = new ArrayList<String>();

		while(true){
			fainalCheck = creatRequesr();
			boolean check = Boolean.parseBoolean(fainalCheck.get(1));
			if(check == true){
				System.out.println("Key found And password is: "+fainalCheck.get(0));
				break;
			}
			else{
				System.out.println("key not found");
			}
		}

	}

	/**
	  * This function creating request to the server to get the password limit
	  * and send response to the server.
	  *
	  *
	  * @author Ali Saheb
	  * @return  ArrayList  checkingArr
	  * @since 1.0.0
	  * @see
	  */
	public static ArrayList<String> creatRequesr() throws ClassNotFoundException, IOException{
		Socket client = new Socket("127.0.0.1", 8000);
		/*accept parameter from server*/
		ObjectInputStream objectInput = new ObjectInputStream(client.getInputStream());

		Object object = objectInput.readObject();
		ArrayList<String> checkingArr = new ArrayList<String>();

		ArrayList<String> ali = new ArrayList<String>();
		ali = (ArrayList<String>) object;

		/*First request for breaking*/
		//System.out.println(ali.get(0));
		String LP = ali.get(0);
		String UP = ali.get(1);
		String password = ali.get(2);

		checkingArr = checkPassword(password,LP,UP);
		boolean check =  Boolean.parseBoolean(checkingArr.get(1));
		if(check == true){
			String error = "Key found and Key is :"+checkingArr.get(0);
			PrintStream p = new PrintStream(client.getOutputStream());
			p.println(error);
			p.close();
		}else{
			String errors = "Key not found";
			PrintStream ps = new PrintStream(client.getOutputStream());
			ps.println(errors);
			ps.close();
		}

		return checkingArr;

	}


	/**
	  * This checkPassword method check the password matching
	  *
	  *
	  * @author Ali Saheb
	  * @param String hash
	  * @param String LP
	  * @param String UP
	  * @return  ArrayList  returnArr
	  * @since 1.0.0
	  * @see
	  */
	public static ArrayList<String> checkPassword(String hash, String LP, String UP){

        String password = LP;
        String findPasswords ="";

        ArrayList<String> returnArr = new ArrayList<String>();

        int password_int = Integer.parseInt(password, Character.MAX_RADIX);
        do{
        	findPasswords = password;
        	password = Hashmd5(password);

            int result = hash.compareTo(password);

            if(result == 0){
            	returnArr.add(findPasswords);
            	returnArr.add("true");
                return returnArr;
            }

            if(password.compareTo("ZZZZZ") == 0){
                break;
            }

            password_int += 1; // Integer.parseInt(password, Character.MAX_RADIX)+1;
            password = Integer.toString(password_int, Character.MAX_RADIX).toUpperCase();

            if(password.length() < 5){
                password = ("00000" + password).substring(password.length());
            }


        }while(password.compareTo(UP) <= 0);

        returnArr.add("password does not found");
    	returnArr.add("false");
        return returnArr;

    }

	/**
	  * This  function hash the password and return the hash
	  *
	  *
	  * @author Ali Saheb
	  * @param String input
	  * @return  String  hashtext
	  * @since 1.0.0
	  * @see
	  */
	public static String Hashmd5(String input){
		Date date = new Date();
		SimpleDateFormat ft =
			      new SimpleDateFormat ("dd-MM-yyyy");

		String saheb = ft.format(date).toString();
		//input += saheb;
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
}
