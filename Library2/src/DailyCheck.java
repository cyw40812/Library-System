
public class DailyCheck 
{
	public static void CheckPassReturn_Date(Book b,Date d)
	{
		if (d.PassDate(b.the_Date_Return) > 0)
		{
			(Library.findUser(b.currentUserid)).right = -1;
		}
	}
	public static boolean CheckCanBorrow_Date(Book b,Date d)
	{
		if (d.PassDate(b.the_Date_Borrow) >= 0){return true;}
		else{return false;}
	}
	public static boolean CheckPassReservate_Date(Book b,Date d)
	{
		if (d.PassDate(b.the_Date_Reserve) > 0)
		{
			while(d.PassDate(b.the_Date_Reserve) > 0)
			{
				Library.findUser(b.reservationUsersid[0]).reserve_Date = d.addDay(89);
			
				b.reservations = b.reservations - 1;
				b.reservationUsersid[0] = b.reservationUsersid[1];
				b.reservationUsersid[1] = b.reservationUsersid[2];
				b.reservationbranch[0] = b.reservationbranch[1];
				b.reservationbranch[1] = b.reservationbranch[2];	
				if (b.reservations == 0) break;
				else 
				{
					BooksAndUsers.Transfer(b.reservationbranch[0],b);
					b.the_Date_Borrow = d.addDay(1);
					b.the_Date_Reserve = b.the_Date_Reserve.addDay(5);
				}
			}
			return true;
		}
		else{return false;}
	}
	public static void CheckCanBorrow_User(User U,Date d)
	{
		if (d.PassDate(U.right_Date) > 0 && U.right==0)
		{
			U.right = 1;
			U.right_panalty_Day = 0;
		}
	}
	
	
}
