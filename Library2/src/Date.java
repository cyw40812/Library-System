public class Date 
{
	int year ;
	int month ;
	int day ;
	
	int yeartype = 0 ;
	int monthtype = 0 ;
	
	public Date()
	{
		year = 1990;
		month = 1;
		day = 1;
		
		yeartype = 0 ;
		monthtype = 0 ;
	}
	public Date(int y ,int m ,int d)
	{
		year = y;
		month = m;
		day = d;
		
		Yeartype();
		Monthtype();
	}
	public Date(String D)
	{
		String[] item = D.split("/");
		year = Integer.valueOf(item[0]);
		month = Integer.valueOf(item[1]);
		day = Integer.valueOf(item[2]);
		
		Yeartype();
		Monthtype();
		
	}
		
	public static boolean validDate(Date D)
	{
		if(D.month <= 12 && D.month >= 1 && D.day <= D.LastDay() && D.day >= 1){return true;}
		else return false;
	}
	
	public Date NextMonth()
	{
		int y ;
		int m ;
		int d ;
		
		if (month == 12)  
		{
			if(day == 1)
			{
				y = year;
				m = 12;
				d = 31;
			}
			else
			{
				y = year + 1;
				m = 1;
				d = day + 30 - 31;
			}
			
		}
		else 
		{
			y = year;
			if (monthtype == 1 && day == 1) 
			{
				m = month;
				d = day + 30;
			}
			else 
			{
				m = month + 1;
				if (monthtype == 1)      d = day + 30 - 31;
				else if (monthtype == 0) d = day + 30 - 30;
				else 
				{
					if (yeartype == 0)   d = day + 30 - 28;
					else                 d = day + 30 - 29;
				}
			}
		}
		Date date = new Date(y , m , d);	 
		return date;
	}
	
	public void Monthtype()
	{
		if (month == 2) 
			monthtype = -1 ;
		else if (month == 1 && month == 3 && month == 5 && month == 7 && month == 8 && month == 10 && month == 12) 
			monthtype = 1 ;
		else 
			monthtype = 0 ;
	}
	public int  Monthtype(int month)
	{
		int monthtype;
		if (month == 2) 
			monthtype = -1 ;
		else if (month == 1 && month == 3 && month == 5 && month == 7 && month == 8 && month == 10 && month == 12) 
			monthtype = 1 ;
		else 
			monthtype = 0 ;
		return monthtype;
	}
	public void Yeartype()
	{
		if (year % 4 == 0) 
		{
			if (year % 100 == 0)
			{
				if (year % 400 == 0)
				{
					yeartype = 1 ;
				}
				else 
					yeartype = 0 ;
			}
			else 
				yeartype = 1 ;
		}
		else
			yeartype = 0 ;
	}
	public int  Yeartype(int year)
	{
		int yeartype;
		if (year % 4 == 0) 
		{
			if (year % 100 == 0)
			{
				if (year % 400 == 0)
				{
					yeartype = 1 ;
				}
				else 
					yeartype = 0 ;
			}
			else 
				yeartype = 1 ;
		}
		else
			yeartype = 0 ;
		return yeartype;
	}
	public int PassDate(Date D)
	{
		int d = 0;
		if (this.year > D.year) 
		{
			
			for(int i = 0; i < (this.year - D.year);i++)
			{
				if (Yeartype(D.year+i)==1) d = d + 366;
				else d = d + 365;
			}
		}
		else if (this.year < D.year) 
		{
			
			for(int i = 0; i < (D.year - this.year);i++)
			{
				if (Yeartype(this.year+i)==1) d = d - 366;
				else d = d - 365;
			}
		}
		else 
		{
			if (this.month > D.month) 
			{
				for(int i = 0; i < (this.month - D.month);i++)
				{
					if (Monthtype(D.month+i)==1) d = d + 31;
					else if (Monthtype(D.month+i)==1)d = d + 30;
					else if (Yeartype(D.year)==1) d = d + 29;
					else d = d + 28;
				}
			}
			else if (this.month < D.month) 
			{
				for(int i = 0; i < (D.month - this.month);i++)
				{
					if (Monthtype(this.month+i)==1) d = d - 31;
					else if (Monthtype(this.month+i)==1)d = d - 30;
					else if (Yeartype(this.year)==1) d = d - 29;
					else d = d - 28;
				}
			}
			else 
			{
				if (this.day > D.day) 
				{
					d = d + this.day - D.day;
				}
				else 
				{
					d = d + this.day - D.day;
				}
			}
		}
		return d;
	}
	public int LastDay()
	{
		if (monthtype==0) return 30;
		else if (monthtype==1) return 31;
		else if (monthtype==1) return 31;
		else if (yeartype==0) return 28;
		else return 29;
	}
	
	public Date addDay(int n)
	{
		Date D = new Date(year,month,day);
		if (n>0)
		{
			while(n>366)
			{
				if (Yeartype(D.year)==1) n = n - 366;
				else n = n - 365;
				D.year = D.year + 1;
			}
			while(n>31)
			{
				if (D.month == 12)
				{
					n = n - 31;
					D.month = 1;
					D.year = D.year + 1;
				}
				else
				{
					if (Monthtype(D.month)==1) n = n - 31;
					else if (Monthtype(D.month)==0)n = n - 30;
					else if (Yeartype(D.year)==0) n = n - 28;
					else n = n - 29;
					D.month = D.month + 1;
				}
			}
			if (D.day + n <= D.LastDay()) D.day = D.day + n;
			else
			{
				D.day = D.day + n - D.LastDay();
				if (D.month == 12) D.month = 1;
				else D.month = D.month + 1;
			}
		}
		else
		{
			while(n<-366)
			{
				if (Yeartype(D.year-1)==1) n = n + 366;
				else n = n + 365;
				D.year = D.year + 1;
			}
			while(n<-31)
			{
				if (D.month == 1)
				{
					n = n + 31;
					D.month = 12;
					D.year = D.year - 1;
				}
				else
				{
					if (Monthtype(D.month-1)==1) n = n + 31;
					else if (Monthtype(D.month-1)==0)n = n + 30;
					else if (Yeartype(D.year)==0) n = n + 28;
					else n = n + 29;
					D.month = D.month - 1;
				}
			}
			if (D.day + n >= 1) D.day = D.day + n;
			else
			{
				if (D.month == 1) D.month = 12;
				else D.month = D.month - 1;
				D.day = D.day + n + D.LastDay();
			}
		}
		return D;
	}
	
	public String toString()
	{
		return year + "/" + month + "/" + day;
	}
	
//	public static void main(String args[]) throws IOException
//	{
//		Date D1 = new Date(2010,3,2);
//		Date D2 = new Date(2011,3,2);
//		System.out.println(D1.PassDate(D2));
//	}
}
