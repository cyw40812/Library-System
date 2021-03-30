
public class Book 
{
	String name ;
	long number;
	int branch ;
	
	int state = 0;
	
	int times_Borrow = 0;
	String currentUserid = null;
	Date the_Date_Borrow = new Date();
	
	int times_Renew = 0;
	
	Date the_Date_Return = new Date();
	
	int reservations = 0;
	String[] reservationUsersid = new String[3];
	int[] reservationbranch = new int[3];
	Date the_Date_Reserve = new Date();
	
	public Book(){}
	public Book(Book B)
	{
		name = B.name ;
		number = B.number ;
		branch = B.branch ;
		state = B.state ;
		times_Borrow = B.times_Borrow ;
		currentUserid = B.currentUserid ;
		the_Date_Borrow = B.the_Date_Borrow ;
		times_Renew = B.times_Renew ;
		the_Date_Return = B.the_Date_Return ;
		reservations = B.reservations ;
		reservationUsersid = B.reservationUsersid ;
		reservationbranch = B.reservationbranch ;
		the_Date_Reserve = B.the_Date_Reserve ;
	}
}
