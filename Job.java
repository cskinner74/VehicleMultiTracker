import java.io.Serializable;
import java.text.DecimalFormat;


public class Job implements Serializable{

	private static final long serialVersionUID = 1L;
	String name;
	double miles;
	
	public Job(String n) {
		name = n;
		miles = 0;
	}
	public void setJobName(String n) {
		name = n;
	}
	public void addMiles(double m) {
		miles = miles + m;
	}
	@Override
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		return("Job: "+name+"\nTotal miles: "+formatter.format(miles));
	}
	
	
}
