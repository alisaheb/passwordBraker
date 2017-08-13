
/**
 *This class made the password limit   
 *   
 * @category Ali Saheb 
 * @author Ali Saheb 
 * @since 1.0.0
 * @see
 * @link  
 */

import java.util.ArrayList;
import java.util.List;

public class AliSetPasswordLimit {

	/**
	  * This passwordsloat method generate password limit and return an all 61 password limit
	  *
	  * @author Ali Saheb           	  		  
	  * @return List studentList 
	  * @since 1.0.0
	  * @see  
	  */	
public static List passwordsloat(){
		
		//creating object of student
		List<AliPasswordSloat> studentList = new ArrayList<AliPasswordSloat>();
		
		AliPasswordSloat passwordslo = new AliPasswordSloat();
			int PASSWORD_LENGTH = 5;
			int TOTAL_SLOT = (int) Math.ceil(Math.pow(36, 5)/1000000);
			String LP = "00000";
			int LP_INT = Integer.parseInt(LP, Character.MAX_RADIX);
			for(int i=0; i<TOTAL_SLOT; i++){
				
				passwordslo = new AliPasswordSloat();
					
					int UP_INT = LP_INT+1000000;
					
				String UP = Integer.toString(UP_INT, Character.MAX_RADIX).toUpperCase();
				
				//System.out.println("lp: "+LP+" up: "+UP+" label :"+i);
				if(LP.length() < PASSWORD_LENGTH){
					LP = ("00000" + LP).substring(LP.length());
				}
				
				if(UP.length() < PASSWORD_LENGTH){
					UP = ("00000" + UP).substring(UP.length());
				}
				
				if(LP.compareTo(UP) > 0){
					UP = "ZZZZZ";
				}
				//setting value of lp up and serial
				passwordslo.setLP(LP);
				passwordslo.setUP(UP);
				passwordslo.setSloatNo(i);
				studentList.add(passwordslo);
				
				LP_INT = UP_INT+1;
				LP = Integer.toString(LP_INT, Character.MAX_RADIX).toUpperCase();
				
				}
				
				return studentList;
			
			}

}
