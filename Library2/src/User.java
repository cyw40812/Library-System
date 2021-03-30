
public class User 
{
	String id ;
	//long idnumber ;
	
	int right ;
	Date right_Date = new Date();
	int right_panalty_Day=0;
	
	int booksBorrowed=0;
	int timesBorrowed=0;
	long[] booksBorrowing = new long[10];
	
	Date reserve_Date = new Date();
	int timesreserved;
	
	public User(){}
	public User(User U) 
	{
		id = U.id;
		//idnumber = U.idnumber;
		right = U.right ;
		right_Date = U.right_Date;
		right_panalty_Day = U.right_panalty_Day;
		booksBorrowed = U.booksBorrowed;
		timesBorrowed = U.timesBorrowed;
		booksBorrowing = U.booksBorrowing;
		reserve_Date = U.reserve_Date;
		timesreserved = U.timesreserved;
	}
	public boolean isBorrow(long b)
	{
		for(int i =0; i<booksBorrowing.length ;i++)
		{if(booksBorrowing[i] == b){return true;}}
		return false;
	}
}
