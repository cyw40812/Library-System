
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
										
										messege = Uid + " �ɮѦ��\," + b.the_Date_Return.toString() + ",���i��" + (10-U.booksBorrowed) + "��";
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
											
											messege = Uid + " �ɮѦ��\," + b.the_Date_Return.toString() + ",���i��" + (10-U.booksBorrowed) + "��";
											
											if (U.booksBorrowed == 1)Library.BorrowingUsers = Library.BorrowingUsers + 1;
											Library.BorrowingTimes = Library.BorrowingTimes + 1;
											

										}	
										else{messege ="�Ѥw�Q��L�H�w�w";}
									}
								}
								else{messege = "�Ѥ��i�ɥX";}
							}
							else{messege = "�Ѥ��b���w�]��";}
						}
						else{messege = Uid +" �w�ɦP�Ѹ�����";}
					}
					else{messege = "�Ѥ��s�b";}
					
				}
				else
				{
					if(U.timesBorrowed == 10) messege = Uid + " �w�ɼƶq�F�W��" ;
					else messege = Uid + " �L�ɮ��v��" ;
				}
			}
			else{messege = Uid + " ���s�b";}
			
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
						
						if(U.right_panalty_Day == 0){messege = Uid + " �ٮѦ��\" + ",���i��" + (10-U.booksBorrowed) + "��";}
						else{messege = Uid + " �ٮѦ��\," + "�Ȱ��ɮ��v" + U.right_panalty_Day*2 + "��,���i��" + (10-U.booksBorrowed) + "��";}
					}
					else{messege = Uid + " ���ɸӮ�";}
				}
				else{messege = "�Ѥ��s�b";}
			}
			else{messege = Uid + " ���s�b";}
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
									messege = "�ӮѤw�g�Q�T��Ū�̹w��";
								}
								else
								{
									b.reservationUsersid[b.reservations] = U.id ;
									b.reservationbranch[b.reservations] = 0;
									b.reservations = b.reservations + 1;
									
									U.timesreserved = U.timesreserved + 1;
									
									messege = Uid + " �w�����\,�e����" + (b.reservations-1) + "�H�w��";
								}
							}
							else{messege = Uid + " �w���q�w�F�W��";}
						}
						else{messege = Uid + " �L�w���v��";}
					}
					else{messege = Uid +" �w�ɦP�Ѹ�����";}
				}
				else{messege = "�Ѥ��s�b";}
			}
			else{messege = Uid + " ���s�b";}
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
										messege = Uid + " ��ɦ��\," + b.the_Date_Return.toString() + ",���i��" + (10-U.booksBorrowed) + "��";
									}
									else{messege = Uid + " �w��ɦ��ѤG��" ;}
								}
								else{messege = "�Ѥw�Q��L�H�w��" ;}
							}
							else{messege = "�Ѥw�L�ٮѮɭ�" ;}
						}
						else{messege = Uid + " ���ɸӮ�";}
					}
					else{messege = Uid + " �L�ɮ��v��" ;}
				}
				else{messege = "�Ѥ��s�b";}
			}
			else{messege = Uid + " ���s�b";}
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
