import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class FinalProject
{
	private JFrame f ;
	private JPanel pu,pd,pd1,pd2,c ;
	private JButton First,Lookup,bAndr,rAndr;
	private JTextArea Today ;
	
	public static void main(String args[]) throws IOException
	{
		FinalProject z = new FinalProject();
		z.Menu(); z.First();
	}
	
	public void Menu() throws IOException
	{
		f = new JFrame("Libary 2016");f.setSize(600,400);
		f.setLocationRelativeTo(null);f.setResizable(false);
		
		pu = new JPanel(); f.add(pu,BorderLayout.NORTH);
		pu.setLayout(new FlowLayout());
		pu.setBackground(new Color(255,229,122));
		
		pd = new JPanel(); f.add(pd,BorderLayout.SOUTH);
		pd.setLayout(new BorderLayout());
		pd.setBackground(new Color(205,49,49));
		
		pd1 = new JPanel(); pd.add(pd1,BorderLayout.EAST);
		pd1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pd1.setBackground(new Color(205,49,49));
		
		pd2 = new JPanel(); pd.add(pd2,BorderLayout.WEST);
		pd2.setLayout(new FlowLayout(FlowLayout.LEFT));
		pd2.setBackground(new Color(205,49,49));
		
		First   = new JButton("首頁");  First.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0){try {f.dispose();Menu();  First();} catch (IOException e) {e.printStackTrace();}}});
		Lookup  = new JButton("查詢"); Lookup.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0){try {f.dispose();Menu(); Lookup();} catch (IOException e) {e.printStackTrace();}}});
		bAndr   = new JButton("借/還書"); bAndr.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0){try {f.dispose();Menu();  bAndr();} catch (IOException e) {e.printStackTrace();}}});
		rAndr   = new JButton("續借/預約"); rAndr.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0){try {f.dispose();Menu();  rAndr();} catch (IOException e) {e.printStackTrace();}}});
		
		pu.add(First);pu.add(Lookup);pu.add(bAndr);pu.add(rAndr);
		
		Today = new JTextArea("今日日期：" + Library.today.toString());
		Today.setBackground(new Color(205,49,49));Today.setEditable(false);
		Today.setFont(new Font("Dialog", Font.PLAIN, 10));Today.setForeground(Color.white);
		Today.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));
		pd1.add(Today);
		
			
		{
			
			JTextArea date_text = new JTextArea("請輸入跳轉日期 :");
			JTextArea bar1 = new JTextArea("/");JTextArea bar2 = new JTextArea("/");
			JTextField date_Y = new JTextField();
			JTextField date_M = new JTextField();
			JTextField date_D = new JTextField();
			JButton date_b = new JButton("Jump!!");
			
			date_text.setBackground(new Color(205,49,49));date_text.setFont(new Font("Dialog", Font.PLAIN, 10));date_text.setForeground(Color.white);date_text.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));date_text.setEditable(false);
			bar1.setBackground(new Color(205,49,49));bar1.setFont(new Font("Dialog", Font.PLAIN, 10));bar1.setForeground(Color.white);bar1.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));bar1.setEditable(false);
			bar2.setBackground(new Color(205,49,49));bar2.setFont(new Font("Dialog", Font.PLAIN, 10));bar2.setForeground(Color.white);bar2.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));bar2.setEditable(false);
			
			date_Y.setBackground(Color.white);date_Y.setFont(new Font("Dialog", Font.PLAIN, 10)); date_Y.setBorder(BorderFactory.createLineBorder(Color.BLACK));date_Y.setPreferredSize(new Dimension(30, 15));
			date_M.setBackground(Color.white);date_M.setFont(new Font("Dialog", Font.PLAIN, 10)); date_M.setBorder(BorderFactory.createLineBorder(Color.BLACK));date_M.setPreferredSize(new Dimension(20, 15));
			date_D.setBackground(Color.white);date_D.setFont(new Font("Dialog", Font.PLAIN, 10)); date_D.setBorder(BorderFactory.createLineBorder(Color.BLACK));date_D.setPreferredSize(new Dimension(20, 15));
			
			date_b.setBackground(Color.white);date_b.setFont(new Font("Dialog", Font.PLAIN, 10)); date_b.setPreferredSize(new Dimension(80, 15));
			
			pd2.add(date_text);pd2.add(date_Y);pd2.add(bar1);pd2.add(date_M);pd2.add(bar2);pd2.add(date_D);pd2.add(date_b);
			
			
			date_b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0)
				{
					int Y = Integer.valueOf(date_Y.getText());
					int M = Integer.valueOf(date_M.getText());
					int D = Integer.valueOf(date_D.getText());
					Date newD = new Date(Y,M,D);
					try 
					{
						if(Date.validDate(newD))
						{
							if(newD.PassDate(Library.today) >= 0)
							{
								Library.today = newD;
								f.dispose();Menu(); First();
							}
						}
						else{ f.dispose();Menu();Text("錯誤日期");}
						
					}
					catch (IOException e) {e.printStackTrace();} 
				} 
			});
		}
		
		
		
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent arg0) 
			{
				super.windowClosing(arg0);
				System.exit(0);
			}
		});
		f.setVisible(true);
	}

	
	
	public void First() throws IOException
	{
		c = new JPanel(); f.add(c,BorderLayout.CENTER);
		c.setBackground(new Color(205,49,49));
		c.setLayout(new GridLayout(3, 1));
		
		JLabel l1,l2,l3;
		l1 = new JLabel("  2016  物件導向程式語言 \n 期  末  專  題");
		l1.setFont(new Font("Dialog", Font.PLAIN, 25));l1.setForeground(Color.white);
		l2 = new JLabel("     Library   ");
		l2.setFont(new Font("Dialog", Font.PLAIN, 100));l2.setForeground(Color.white);
		l3 = new JLabel("  組員 : 張翊威、謝嘉軒、楊肇焓");
		l3.setFont(new Font("Dialog", Font.PLAIN, 25));l3.setForeground(Color.white);
		c.add(l1);c.add(l2);c.add(l3);
	}
	
	public void Lookup() throws IOException
	{
		c = new JPanel(); f.add(c,BorderLayout.CENTER);
		c.setBackground(new Color(205,49,49));
		c.setLayout(null);
		
		String[] ms = {"書號","書總借出數","總借出人數","當前借書量排名","總借書次數排名"};
		JTextField t = new JTextField();  
		JComboBox<String> m = new JComboBox<String>(ms);
		JButton b = new JButton("查詢"); 
		
		m.setBounds(50,20,90,30); 
		
		t.setBounds(170,20,260,30); t.setFont(new Font("Dialog", Font.PLAIN, 20)); 
		t.setBackground(Color.white);
		
		b.setBounds(450,20,90,30); 
		
		c.add(m);c.add(t);c.add(b);
					
		b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
				
				if(m.getSelectedItem().equals("書總借出數")){f.dispose();Menu();Lookup();addText(" 當前所有圖書館的總借出數量：" + Library.BorrowingTimes);}
				else if(m.getSelectedItem().equals("總借出人數")){f.dispose();Menu();Lookup();addText(" 當前所有圖書館的總借書人數：" + Library.BorrowingUsers);}
				else if(m.getSelectedItem().equals("當前借書量排名")){f.dispose();Menu();Lookup();NowRank();}
				else if(m.getSelectedItem().equals("總借書次數排名")){f.dispose();Menu();Lookup();AllRank();}
				else
				{
					long check = Long.valueOf(t.getText());
					f.dispose();Menu();Lookup();LookUpdata(check);
				}
			}
			catch (IOException e) {e.printStackTrace();}
			catch (NumberFormatException e) {}
		} 
		});
		
	}
	public void LookUpdata(long check) throws IOException
	{
		Book b = Library.findBook(check);
		String s = null;
		if(b == null) s = "書不存在";
		else
		{
			if(b.state == 0) s = b.number + "被借出";
			else if(b.state == b.branch) s = b.number + "在原館內(第" + b.state +"分館)";
				 else s = b.number + "在別館(第" + b.state +"分館)";
		}
		addText(s);
	}
	public void NowRank() throws IOException
	{
		User[] U = Library.findNowRankUser();
		String[] s = new String[10];
		for(int j = 0 ; j < U.length ; j++)
		{
			s[j] = "第" + (j+1) + "名   " + U[j].id + U[j].booksBorrowed;
			if(j<9) s[j] = "  " + s[j];
		}
		addsText(s);
	}
	public void AllRank() throws IOException
	{
		User[] U = Library.findAllRankUser();
		String[] s = new String[10];
		for(int j = 0 ; j < U.length ; j++)
		{
			s[j] = "第" + (j+1) + "名   " + U[j].id + U[j].timesBorrowed;
			if(j<9) s[j] = "  " + s[j];
		}
		addsText(s);
	}
	
	public void bAndr() throws IOException
	{
		c = new JPanel(); f.add(c,BorderLayout.CENTER);
		c.setBackground(new Color(205,49,49));
		c.setLayout(null);
		
		JTextArea uid =    new JTextArea("使用者帳號(ID) :");
		JTextArea branch = new JTextArea("分館 : 第");
		JTextArea brback = new JTextArea("分館");
		JTextArea number = new JTextArea("書碼(Serial Number) :");
		JTextArea date =   new JTextArea("日期 :");
		
		JTextField uidin =    new JTextField();  
		JTextField branchin = new JTextField();
		JTextField numberin = new JTextField();
		
		JTextArea bar1 = new JTextArea("/");
		JTextArea bar2 = new JTextArea("/");
		JTextField date_Y = new JTextField();
		JTextField date_M = new JTextField();
		JTextField date_D = new JTextField();
		
		
		
		String[] ms = {"借書","還書"};
		JComboBox<String> m = new JComboBox<String>(ms);
		JButton b = new JButton("GO!!"); 
		
		m.setBounds(60,20,90,30); 
		c.add(m);
		
		b.setBounds(450,20,90,30); 
		c.add(b);
		
		uid.setBounds(60,65,90,30); uid.setBackground(new Color(205,49,49));
		uid.setFont(new Font("Dialog", Font.PLAIN, 12));uid.setForeground(Color.white);
		uid.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));uid.setEditable(false);
		uid.setPreferredSize(new Dimension(80, 15));
		
		branch.setBounds(115,105,50,30); branch.setBackground(new Color(205,49,49));
		branch.setFont(new Font("Dialog", Font.PLAIN, 12));branch.setForeground(Color.white);
		branch.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));branch.setEditable(false);
		
		brback.setBounds(210,105,120,30); brback.setBackground(new Color(205,49,49));
		brback.setFont(new Font("Dialog", Font.PLAIN, 12));brback.setForeground(Color.white);
		brback.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));brback.setEditable(false);
		
		number.setBounds(25,145,120,30); number.setBackground(new Color(205,49,49));
		number.setFont(new Font("Dialog", Font.PLAIN, 12));number.setForeground(Color.white);
		number.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));number.setEditable(false);
		
		date.setBounds(115,185,50,30); date.setBackground(new Color(205,49,49));
		date.setFont(new Font("Dialog", Font.PLAIN, 12));date.setForeground(Color.white);
		date.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));date.setEditable(false);
		c.add(uid);c.add(branch);c.add(brback);c.add(number);c.add(date);
		
		
		uidin.setBounds(170,60,260,30); uidin.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		uidin.setBackground(Color.white);
		
		branchin.setBounds(170,100,30,30); branchin.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		branchin.setBackground(Color.white);
		
		numberin.setBounds(170,140,260,30); numberin.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		numberin.setBackground(Color.white);
		
		date_Y.setBounds(170,180,60,30);date_Y.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		date_Y.setBackground(Color.white);date_Y.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bar1.setBounds(240,180,10,30);bar1.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		bar1.setEditable(false);bar1.setBackground(new Color(205,49,49));bar1.setForeground(Color.white);
		bar1.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));
		
		date_M.setBounds(250,180,30,30);date_M.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		date_M.setBackground(Color.white);date_M.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bar2.setBounds(285,180,10,30);bar2.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		bar2.setEditable(false);bar2.setBackground(new Color(205,49,49));bar2.setForeground(Color.white);
		bar2.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));
		
		date_D.setBounds(300,180,30,30);date_D.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		date_D.setBackground(Color.white);date_D.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		c.add(uidin);c.add(branchin);c.add(numberin);
		c.add(date_Y);c.add(bar1);c.add(date_M);c.add(bar2);c.add(date_D);
		
		
		b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
			int date_Yin = Integer.valueOf(date_Y.getText());
			int date_Min = Integer.valueOf(date_M.getText());
			int date_Din = Integer.valueOf(date_D.getText());
			
			String uids = uidin.getText();
			int branchs = Integer.valueOf(branchin.getText());
			long numbers = Long.valueOf(numberin.getText());
			Date dated = new Date(date_Yin,date_Min,date_Din);
			
			String messege = null;
			if(Date.validDate(dated))
			{
				if(dated.PassDate(Library.today) >= 0)
				{
					Library.today = dated;	
				
				
					if(m.getSelectedItem().equals("借書")){messege = BooksAndUsers.Borrow(uids,numbers,branchs,dated);}
					else{messege = BooksAndUsers.Return(uids,numbers,branchs,dated);}
				}
				else{messege = "輸入日期須在今日日期之後";}
			}
			else{messege = "錯誤日期";}
			
			
			f.dispose();Menu();bAndr();addText2(messege);
			
			}
			catch (IOException e) {e.printStackTrace();}
			catch (NumberFormatException e) {}
		} 
		});
		
	}
	public void rAndr() throws IOException
	{
		c = new JPanel(); f.add(c,BorderLayout.CENTER);
		c.setBackground(new Color(205,49,49));
		c.setLayout(null);
		
		JTextArea uid =    new JTextArea("使用者帳號(ID) :");
		JTextArea number = new JTextArea("書碼(Serial Number) :");
		JTextArea date =   new JTextArea("日期 :");
		
		JTextField uidin =    new JTextField();  
		JTextField numberin = new JTextField();
		
		JTextArea bar1 = new JTextArea("/");
		JTextArea bar2 = new JTextArea("/");
		JTextField date_Y = new JTextField();
		JTextField date_M = new JTextField();
		JTextField date_D = new JTextField();
		
		
		
		String[] ms = {"續借","預約"};
		JComboBox<String> m = new JComboBox<String>(ms);
		JButton b = new JButton("GO!!"); 
		
		m.setBounds(60,20,90,30); 
		c.add(m);
		
		b.setBounds(450,20,90,30); 
		c.add(b);
		
		uid.setBounds(60,65,90,30); uid.setBackground(new Color(205,49,49));
		uid.setFont(new Font("Dialog", Font.PLAIN, 12));uid.setForeground(Color.white);
		uid.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));uid.setEditable(false);
		uid.setPreferredSize(new Dimension(80, 15));
		
		number.setBounds(25,145,120,30); number.setBackground(new Color(205,49,49));
		number.setFont(new Font("Dialog", Font.PLAIN, 12));number.setForeground(Color.white);
		number.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));number.setEditable(false);
		
		date.setBounds(115,185,50,30); date.setBackground(new Color(205,49,49));
		date.setFont(new Font("Dialog", Font.PLAIN, 12));date.setForeground(Color.white);
		date.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));date.setEditable(false);
		c.add(uid);c.add(number);c.add(date);
		
		
		uidin.setBounds(170,60,260,30); uidin.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		uidin.setBackground(Color.white);
		
		numberin.setBounds(170,140,260,30); numberin.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		numberin.setBackground(Color.white);
		
		date_Y.setBounds(170,180,60,30);date_Y.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		date_Y.setBackground(Color.white);date_Y.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bar1.setBounds(240,180,10,30);bar1.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		bar1.setEditable(false);bar1.setBackground(new Color(205,49,49));bar1.setForeground(Color.white);
		bar1.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));
		
		date_M.setBounds(250,180,30,30);date_M.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		date_M.setBackground(Color.white);date_M.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bar2.setBounds(285,180,10,30);bar2.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		bar2.setEditable(false);bar2.setBackground(new Color(205,49,49));bar2.setForeground(Color.white);
		bar2.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));
		
		date_D.setBounds(300,180,30,30);date_D.setFont(new Font("Dialog", Font.PLAIN, 25)); 
		date_D.setBackground(Color.white);date_D.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		c.add(uidin);c.add(numberin);c.add(date_Y);c.add(bar1);c.add(date_M);c.add(bar2);c.add(date_D);
		
		
		b.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
			int date_Yin = Integer.valueOf(date_Y.getText());
			int date_Min = Integer.valueOf(date_M.getText());
			int date_Din = Integer.valueOf(date_D.getText());
			
			String uids = uidin.getText();
			long numbers = Long.valueOf(numberin.getText());
			Date dated = new Date(date_Yin,date_Min,date_Din);
			
			String messege = null;
			
			
			if(Date.validDate(dated))
			{
				if(dated.PassDate(Library.today) >= 0)
				{
					Library.today = dated;	
					
					if(m.getSelectedItem().equals("續借")){messege = BooksAndUsers.Renew(uids,numbers,dated);}
					else{messege = BooksAndUsers.Reserve(uids,numbers,dated);}
				}
				else{messege = "輸入日期須在今日日期之後";}
			}
			else{messege = "錯誤日期";}
			
			f.dispose();Menu();rAndr();addText2(messege);
			}
			catch (IOException e) {e.printStackTrace();}
			catch (NumberFormatException e) {}
		} 
		});
	}

	public void Text(String messege) throws IOException
	{
		c = new JPanel(); f.add(c,BorderLayout.CENTER);
		c.setBackground(new Color(205,49,49));
		c.setLayout(new BorderLayout());
		
		JTextArea lc = new JTextArea(messege);
		lc.setBounds(150,130,300,60); lc.setBackground(new Color(205,49,49));
		lc.setFont(new Font("Dialog", Font.PLAIN, 50));lc.setForeground(Color.green);
		lc.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));lc.setEditable(false);
		c.add(lc,BorderLayout.CENTER);
	}
	public void addText(String messege) throws IOException
	{
		JTextArea lc = new JTextArea(messege);
		lc.setBounds(50,130,400,60); lc.setBackground(new Color(205,49,49));
		lc.setFont(new Font("Dialog", Font.PLAIN, 20));lc.setForeground(Color.green);
		lc.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));lc.setEditable(false);
		c.add(lc);
	}
	public void addText2(String messege) throws IOException
	{
		JTextArea lc = new JTextArea(messege);
		lc.setBounds(20,250,450,60); lc.setBackground(new Color(205,49,49));
		lc.setFont(new Font("Dialog", Font.PLAIN, 20));lc.setForeground(Color.green);
		lc.setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));lc.setEditable(false);
		c.add(lc);
	}
	public void addsText(String[] messege) throws IOException
	{
		JTextArea[] lc = new JTextArea[10];
		for(int j = 0 ; j < messege.length ; j++)
		{
			lc[j] = new JTextArea(messege[j]);
			if(j<5) lc[j].setBounds(100,80+25*j,150,20); 
			else lc[j].setBounds(300,80+25*(j-5),150,20); 
			
			lc[j].setBackground(new Color(205,49,49));
			lc[j].setFont(new Font("Dialog", Font.PLAIN, 15));lc[j].setForeground(Color.green);
			lc[j].setBorder(BorderFactory.createLineBorder(new Color(205,49,49)));lc[j].setEditable(false);
			c.add(lc[j]);
		}
		
		
	}
	
}
