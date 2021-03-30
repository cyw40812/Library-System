import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class Library 
{
	public static Date today = new Date(2010,1,1);
	static ArrayList<ArrayList> UIDun =   ReadCSVtoArrayList.readCSVToArrayList("UID.csv","UTF-8");
	static ArrayList<ArrayList> bAndBun = ReadCSVtoArrayList.readCSVToArrayList("booksAndBranches.csv","unicode");
	//static ArrayList<ArrayList> historyLogun = ReadCSVtoArrayList.readCSVToArrayList("historyLog.csv");
	//static ArrayList<ArrayList> deptNumun =    ReadCSVtoArrayList.readCSVToArrayList("deptNum.csv");
	
	static int BorrowingTimes = 0;
	static int BorrowingUsers = 0;
	
	static User[] UID = new User[UIDun.size()];
	static Book[] bAndB = new Book[bAndBun.size()];
	
	static
	{		
		for(int i = 0 ; i < UIDun.size() ; i++)
		{
			UID[i] = new User();
			UID[i].id =                            (String) UIDun.get(i).get(0);
			UID[i].right =         Integer.valueOf((String) UIDun.get(i).get(1));
			UID[i].right_Date =           new Date((String) UIDun.get(i).get(2));
			UID[i].booksBorrowed = Integer.valueOf((String) UIDun.get(i).get(3));
			UID[i].timesBorrowed = Integer.valueOf((String) UIDun.get(i).get(4));
			UID[i].reserve_Date =         new Date((String) UIDun.get(i).get(5));
			UID[i].timesreserved = Integer.valueOf((String) UIDun.get(i).get(6));
		}
		for(int i = 0 ; i < bAndBun.size() ; i++)
		{
			bAndB[i] = new Book();
			bAndB[i].branch =                Integer.valueOf((String) bAndBun.get(i).get( 0));
			bAndB[i].name =                                  (String) bAndBun.get(i).get( 1);
			bAndB[i].number =                   Long.valueOf((String) bAndBun.get(i).get( 2));
			bAndB[i].times_Borrow =          Integer.valueOf((String) bAndBun.get(i).get( 3));
			bAndB[i].state =                 Integer.valueOf((String) bAndBun.get(i).get( 4));
			bAndB[i].currentUserid =                         (String) bAndBun.get(i).get( 5);
			bAndB[i].the_Date_Return =              new Date((String) bAndBun.get(i).get( 6));
			bAndB[i].times_Renew =           Integer.valueOf((String) bAndBun.get(i).get( 7));
			bAndB[i].reservations =          Integer.valueOf((String) bAndBun.get(i).get( 8));
			bAndB[i].reservationUsersid[0] =                 (String) bAndBun.get(i).get( 9);
			bAndB[i].reservationUsersid[1] =                 (String) bAndBun.get(i).get(10);
			bAndB[i].reservationUsersid[2] =                 (String) bAndBun.get(i).get(11);
			bAndB[i].the_Date_Reserve =             new Date((String) bAndBun.get(i).get(12));
			bAndB[i].reservationbranch[0] =  Integer.valueOf((String) bAndBun.get(i).get(13));
			bAndB[i].reservationbranch[1] =  Integer.valueOf((String) bAndBun.get(i).get(14));
			bAndB[i].reservationbranch[2] =  Integer.valueOf((String) bAndBun.get(i).get(15));
		}
	}
	
	public static User findUser(String finduserid)
	{
		for(User U : UID){if(U.id.equals(finduserid)){return U;}}
		System.out.println("Can not find the user.");
		return  null;
	}
	public static Book[] findBook(String findbookname)
	{
		ArrayList<Book> b = new ArrayList<Book>();
		for(Book B : bAndB){if(has(findbookname,B.name)){b.add(B);}}
		Book[] bbb = new Book[b.size()];
		for(int i = 0; i<b.size() ; i++){bbb[i] = b.get(i);}
		return  bbb;
	}
	public static Book findBook(long findbooknumber)
	{
		Book b = new Book();
		for(Book B : bAndB)
		{
			if(B.number == findbooknumber)
			{
				b=B;
				return b;
			}
		}
		System.out.println("Can not find the book.");
		return  null;
	}
	
	public static Boolean has(String find,String befind)
	{
		if (befind.indexOf(find) != -1) return true;
		else return false;
	}
	
	public static User[] findNowRankUser()
	{
		User[] Rank = new User[10];for(int i = 0 ; i < Rank.length ; i++){Rank[i] = UID[i];}
		for(int i = 0 ; i < UID.length ; i++)
		{
			for(int j = 0 ; j < Rank.length ; j++)
			{
				if(Rank[j].booksBorrowed < UID[i].booksBorrowed)
				{
					for(int k = j ; k < Rank.length-1  ; k++)
					{
						Rank[j+1] = Rank[j];
					}
					Rank[j] = UID[i];
					break;
				}
			}
		}
		return Rank;
	}
	public static User[] findAllRankUser()
	{
		User[] Rank = new User[10];for(int i = 0 ; i < Rank.length ; i++){Rank[i] = UID[i];}
		for(int i = 0 ; i < UID.length ; i++)
		{
			for(int j = 0 ; j < Rank.length ; j++)
			{
				if(Rank[j].timesBorrowed < UID[i].timesBorrowed)
				{
					for(int k = j ; k < Rank.length-1  ; k++)
					{
						Rank[j+1] = Rank[j];
					}
					Rank[j] = UID[i];
					break;
				}
			}
		}
		return Rank;
	}
	
//	public static void main(String args[])
//	{
//		User s = findUser("B01608172");
//		System.out.println("B01608172" == UID[0].id);
//		System.out.println("|B01608172|");
//		System.out.println("|"+UID[0].id+"|");
//		System.out.println("|"+s.id+"|");
//	}
	
}
