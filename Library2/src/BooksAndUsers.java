
public class BooksAndUsers
{
		//Borrow
		public static String Borrow(String Uid,long bid,int i,Date d)
		{
			User U = Library.findUser(Uid);
			Book b = Library.findBook(bid);
			String messege = null;
			if (U != null)
			{
				System.out.println("B" + d.PassDate(U.right_Date));
				DailyCheck.CheckCanBorrow_User(U,d);
				System.out.println("B" +U.right);
				
				System.out.println("B" + U.right_Date);
				if (U.right == 1)
				{
					if(b != null)
					{
						
						if (!Checkinlist(U,b))
						{
							if (b.state == i)
							{
								if (DailyCheck.CheckCanBorrow_Date(b,d))
								{
									
									
									if (b.reservations == 0)
									{
										b.currentUserid = U.id;
										b.the_Date_Return = d.NextMonth();
										b.state = 0; 
										
										U.booksBorrowing[U.booksBorrowed] = b.number;
										U.booksBorrowed = U.booksBorrowed + 1;
										if (U.booksBorrowed == 10) U.right = -1;
										
										messege = Uid + " 借書成功," + b.the_Date_Return.toString() + ",仍可借" + (10-U.booksBorrowed) + "本";
										if (U.booksBorrowed == 1)Library.BorrowingUsers = Library.BorrowingUsers + 1;
										Library.BorrowingTimes = Library.BorrowingTimes + 1;
										
									}	
									else 
									{
										DailyCheck.CheckPassReservate_Date(b,d);
										if (b.reservationUsersid[0] == U.id)
										{
										
											b.currentUserid = U.id;
											b.reservations = b.reservations - 1;
											b.reservationUsersid[0] = b.reservationUsersid[1];
											b.reservationUsersid[1] = b.reservationUsersid[2];
											b.reservationbranch[0] = b.reservationbranch[1];
											b.reservationbranch[1] = b.reservationbranch[2];
											b.the_Date_Return = d.NextMonth();		
											b.state = 0; 
											
											U.booksBorrowing[U.booksBorrowed] = b.number;
											U.timesreserved = U.timesreserved - 1;
											U.booksBorrowed = U.booksBorrowed + 1;
											if (U.booksBorrowed == 10) U.right = -1;
											
											messege = Uid + " 借書成功," + b.the_Date_Return.toString() + ",仍可借" + (10-U.booksBorrowed) + "本";
											
											if (U.booksBorrowed == 1)Library.BorrowingUsers = Library.BorrowingUsers + 1;
											Library.BorrowingTimes = Library.BorrowingTimes + 1;
											

										}	
										else{messege ="書已被其他人預定";}
									}
								}
								else{messege = "書不可借出";}
							}
							else{messege = "書不在指定館內";}
						}
						else{messege = Uid +" 已借同書號的書";}
					}
					else{messege = "書不存在";}
					
				}
				else
				{
					if(U.timesBorrowed == 10) messege = Uid + " 已借數量達上限" ;
					else messege = Uid + " 無借書權限" ;
				}
			}
			else{messege = Uid + " 不存在";}
			
			return messege;
		}
		
		//Return
		public static String Return(String Uid,long bid,int i,Date d)
		{
			User U = Library.findUser(Uid);
			Book b = Library.findBook(bid);
			String messege = null;
			if (U != null)
			{
				if (b != null)
				{
					if (Checkinlist(U,b))
					{
						DailyCheck.CheckPassReturn_Date(b,d);

						b.state = i; 
						b.times_Borrow = b.times_Borrow + 1;
						b.currentUserid = null;
						b.times_Renew = 0;
						
						long[] l = new long[10]; int n = 0; for (int j = 0; j < l.length; j++) if (U.booksBorrowing[j] != b.number) l[j-n] = U.booksBorrowing[j]; else n = n + 1;
						U.booksBorrowing = l;
						U.booksBorrowed = U.booksBorrowed - 1;
						U.timesBorrowed = U.timesBorrowed + 1;		
						
						int D = d.PassDate(b.the_Date_Return);
						if (D < 0) D = 0;
						if (D > 0)
						{	
							U.right_panalty_Day = U.right_panalty_Day + D;
							if (U.right_panalty_Day > 30) {U.right_panalty_Day = 30;}
							//if (U.right == 1){U.right = -1;}
							U.right_Date = d.addDay(U.right_panalty_Day*2);
							
							System.out.println("R" +U.right_panalty_Day);
							System.out.println("R" +U.right);
							System.out.println("R" +U.right_Date);
							
							if (U.booksBorrowed == 0){U.right = 0;}
						}
						b.the_Date_Return = null;
						
						if(b.reservations != 0)
						{
							Transfer(b.reservationbranch[0],b);
							b.the_Date_Borrow = d.addDay(1);
							b.the_Date_Reserve = d.addDay(5);
						}	
						
						if (U.booksBorrowed == 0)Library.BorrowingUsers = Library.BorrowingUsers - 1;
						Library.BorrowingTimes = Library.BorrowingTimes - 1;
						
						if(U.right_panalty_Day == 0){messege = Uid + " 還書成功" + ",仍可借" + (10-U.booksBorrowed) + "本";}
						else{messege = Uid + " 還書成功," + "暫停借書權" + U.right_panalty_Day*2 + "天,仍可借" + (10-U.booksBorrowed) + "本";}
					}
					else{messege = Uid + " 未借該書";}
				}
				else{messege = "書不存在";}
			}
			else{messege = Uid + " 不存在";}
			return messege;
			
		}
		
		//Transfer
		public static void Transfer(int i,Book b)
		{b.state = i;}
		
		//Reserve
		public static String Reserve(String Uid,long bid,Date d)
		{
			User U = Library.findUser(Uid);
			Book b = Library.findBook(bid);
			String messege = null;
			if (U != null)
			{
				if (b != null)
				{
					if (!Checkinlist(U,b))
					{
						if ( d.PassDate(U.reserve_Date) > 0)
						{
							if (U.timesreserved < 5)
							{
								if (b.reservations == 3)
								{
									messege = "該書已經被三位讀者預約";
								}
								else
								{
									b.reservationUsersid[b.reservations] = U.id ;
									b.reservationbranch[b.reservations] = 0;
									b.reservations = b.reservations + 1;
									
									U.timesreserved = U.timesreserved + 1;
									
									messege = Uid + " 預約成功,前面有" + (b.reservations-1) + "人預約";
								}
							}
							else{messege = Uid + " 預約量已達上限";}
						}
						else{messege = Uid + " 無預約權限";}
					}
					else{messege = Uid +" 已借同書號的書";}
				}
				else{messege = "書不存在";}
			}
			else{messege = Uid + " 不存在";}
			return messege;
			
		}
		
		
		//Renew
		public static String Renew(String Uid,long bid,Date d)
		{
			User U = Library.findUser(Uid);
			Book b = Library.findBook(bid);
			String messege = null;
			if (U != null)
			{
				if (b != null)
				{
					DailyCheck.CheckCanBorrow_User(U,d);
					if (U.right == 1)
					{
						if (Checkinlist(U,b))
						{
							if(d.PassDate(b.the_Date_Return) <= 0)
							{
								if (b.reservations == 0)
								{
									if (b.times_Renew < 3)
									{
										b.times_Renew = b.times_Renew + 1 ;
										b.the_Date_Return = b.the_Date_Return.NextMonth();
										messege = Uid + " 續借成功," + b.the_Date_Return.toString() + ",仍可借" + (10-U.booksBorrowed) + "本";
									}
									else{messege = Uid + " 已續借此書二次" ;}
								}
								else{messege = "書已被其他人預約" ;}
							}
							else{messege = "書已過還書時限" ;}
						}
						else{messege = Uid + " 未借該書";}
					}
					else{messege = Uid + " 無借書權限" ;}
				}
				else{messege = "書不存在";}
			}
			else{messege = Uid + " 不存在";}
		return messege;
			
			
		}
		
		//Check
		public static boolean Checkinlist(User U,Book b)
		{
			boolean check = false;
			for(int i = 0;i < 10;i++){if (U.booksBorrowing[i] == b.number) check = true;}
			return check;
		}
		

}
