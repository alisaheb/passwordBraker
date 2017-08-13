

/**
 *This class store the password limit for every 61 password limit
 *
 * @category Ali Saheb
 * @author Ali Saheb
 * @since 1.0.0
 * @see
 * @link
 */

public class AliPasswordSloat {
	private String UP;
    private String LP;
    private int sloat_no;
    private String clientNumber = "0";
    private String clientStatus = "Not Found";
    private long startTime;

    public void setStartTime(long startTime){
		this.startTime = startTime;
		}
	public long getStartTime(){
		return this.startTime;
		}
    /**
	  * This getLP method can get the lower limit of the password
	  *
	  * @author Ali Saheb
	  * @return int LP
	  * @since 1.0.0
	  * @see
	  */
    public String getLP() {
       return this.LP;
    }
    /**
	  * This setLP method set the lower limit of the password
	  *
	  * @author Ali Saheb
	  * @return void
	  * @since 1.0.0
	  * @see
	  */

    public void setLP(String name) {
        this.LP = name;
    }

    /**
	  * This setUP method set the upper limit of the password
	  *
	  * @author Ali Saheb
	  * @return void
	  * @since 1.0.0
	  * @see
	  */

    public void setUP(String name){
		this.UP = name;
		}
    /**
	  * This getUP method get the upper limit of the password
	  *
	  * @author Ali Saheb
	  * @return String UP
	  * @since 1.0.0
	  * @see
	  */
	public String getUP(){
		return this.UP;
		}
	/**
	  * This setSloatNo method set the serial of password limit
	  *
	  * @author Ali Saheb
	  * @return void
	  * @since 1.0.0
	  * @see
	  */
	public void setSloatNo(int name){
		this.sloat_no = name;
		}
	/**
	  * This getSloatNo method get the serial of password limit
	  *
	  * @author Ali Saheb
	  * @return int sloat_no
	  * @since 1.0.0
	  * @see
	  */
	public int getSloatNo(){
		return this.sloat_no;
		}
	/**
	  * This setClient method set the assign for a client
	  *
	  * @author Ali Saheb
	  * @return void
	  * @since 1.0.0
	  * @see
	  */
	public void setClient(String clientName){
		this.clientNumber = clientName;

	}
	/**
	  * This getClient method get the assign client
	  *
	  * @author Ali Saheb
	  * @return String clientNumber
	  * @since 1.0.0
	  * @see
	  */
	public String getClient(){
		return this.clientNumber;
	}
	public void setClientStatus(String clientSta){
		this.clientStatus = clientSta;
	}
	public String getClientStatus(){
		return this.clientStatus;
	}
}
