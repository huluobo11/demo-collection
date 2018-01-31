package com.date.test;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class MyFrame extends JFrame {
	static int n,n1,n2,n3;
	public static JTextField text1;
	static JTextField text2=new JTextField();
	static JTextField text3=new JTextField();
	static JTextField text4=new JTextField();
	static JTextField text5=new JTextField("0");
	static JTextField text6=new JTextField("17");
	static JTextField text7=new JTextField();
    static JTextField text8=new JTextField("0");
	static JTextField text9=new JTextField();
	static JTextField text10=new JTextField();
	JTextField text11=new JTextField();
	JTextField text12=new JTextField();
	
	public static JButton enter=new JButton("开始校验");
	Font butfont=new Font("宋体", Font.BOLD, 20);
	
	
	private JFrame frame=new JFrame();
	private JPanel jpl=new JPanel();
	 
	private Image image;
	
	public MyFrame(){
		
		
		JLabel label2 = new JLabel("请输入UDP数据部分:");
		label2.setBounds(20, 65, 300, 50);
		label2.setFont(new Font("隶书", Font.BOLD, 18));
		frame.add(label2);

		JLabel label3 = new JLabel("请输入UDP伪首部：");
		label3.setBounds(20, 125, 300, 50);
		label3.setFont(new Font("隶书", Font.BOLD, 18));
		frame.add(label3);
		
		JLabel label4 = new JLabel("请输入UDP首部：");
		label4.setBounds(20, 235, 300, 50);
		label4.setFont(new Font("隶书", Font.BOLD, 18));
		frame.add(label4);
		
		JLabel label5 = new JLabel("按二进制反码进行求和运算:");
		label5.setBounds(20, 305, 300, 50);
		label5.setFont(new Font("隶书", Font.BOLD, 18));
		frame.add(label5);
		
		JLabel label6 = new JLabel("对结果求反，得到校验和:");
		label6.setBounds(20, 355, 300, 50);
		label6.setFont(new Font("隶书", Font.BOLD, 18));
		frame.add(label6);
	
		
		text1 = new JTextField();
		text1.setBounds(200,80, 240, 30);
		text1.setFont(new Font("黑体", Font.BOLD, 18));
		frame.add(text1);

		
		text2.setBounds(200, 135, 240, 30);
		text2.setFont(new Font("", Font.BOLD, 24));
		frame.add(text2);
		
		text3.setBounds(200, 165, 240, 30);
		text3.setFont(new Font("", Font.BOLD, 24));
		frame.add(text3);
		
		text5.setBounds(200, 195, 80, 30);
		text5.setFont(new Font("", Font.BOLD, 24));
		frame.add(text5);
		text6.setBounds(280, 195, 80, 30);
		text6.setFont(new Font("", Font.BOLD, 24));
		frame.add(text6);
		text4.setBounds(360, 195, 80, 30);
		text4.setFont(new Font("", Font.BOLD, 24));
		frame.add(text4);
		
		text7.setBounds(200, 275, 120, 30);
		text7.setFont(new Font("", Font.BOLD, 24));
		frame.add(text7);
		text8.setBounds(320, 275, 120, 30);
		text8.setFont(new Font("", Font.BOLD, 24));
		frame.add(text8);
		
		text9.setBounds(200, 245, 120, 30);
		text9.setFont(new Font("", Font.BOLD, 24));
		frame.add(text9);
		text10.setBounds(320, 245, 120, 30);
		text10.setFont(new Font("", Font.BOLD, 24));
		frame.add(text10);
		
		text11.setBounds(280, 315, 240, 30);
		text11.setFont(new Font("", Font.BOLD, 24));
		frame.add(text11);
		text12.setBounds(280, 365, 240, 30);
		text12.setFont(new Font("", Font.BOLD, 24));
		frame.add(text12);
		
		frame.add(jpl);
		JLabel label = new JLabel(new ImageIcon("image\\background.jpg"));
		label.setSize(600, 565);
		jpl.add(label);
		frame.add(jpl);
		frame.setIconImage(image);
		jpl.setLayout(null);
		jpl.add(enter);
		frame.setTitle("UPD校验和计算");
		frame.setSize(600, 565);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		enter.setBounds(385, 450, 120, 40); // 设置按钮大小
		enter.setBackground(Color.WHITE);
		enter.setVisible(true);
		enter.setLayout(null);
		enter.setFont(butfont);
		
	}
	public static void main(String[] args) {
		new MyFrame();//调用Login()方法
		enter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String str2=text1.getText();
				String str3=text2.getText();
				String str0=text3.getText();
				
				String str7 = null;
				String str8 = null;
				
				String str10 = null;
				
				String str12 = null;
				String[] nums=new String[str2.length()];
				
				
				//UDP数据报的数据二进制转换
				for(int i=0;i<str2.length();i++){
					char ch=str2.charAt(i);
					n=(int)ch;     	
						nums[i]=Integer.toBinaryString(n);
				}
				//第一行的数
						String str4=nums[0];
						if(str4.length()==7&&str4.length()!=8){
							str7="0"+str4;
						}
						if(str4.length()==8){
							str7=str4;
						}
						//第二行的数
						String str5=nums[1];
						if(str5.length()==7&&str5.length()!=8){
							str8="0"+str5;
						}
						if(str5.length()==8){
							str8=str5;
						}
						String str6=str7+str8;
						System.out.println(str6);
						//第三行的数
						String str9=nums[2];
						if(str9.length()==7&&str9.length()!=8){
							str10="0"+str9;
						}
						if(str9.length()==8){
							str10=str9;
						}
						//第四行的数
						String str11=nums[3];
						if(str11.length()==7&&str9.length()!=8){
							str12="0"+str11;
						}
						if(str11.length()==8){
							str12=str11;
						}
						
						String str13=str10+str12;
						System.out.println(str13);
						
						//校验和text8转换二进制
						String q="0";
						int a=Integer.parseInt(text8.getText());
						String s=Integer.toBinaryString(a);
						if(s.length()<16){
							for(int j=0;j<15-s.length();j++){
							q="0";
							System.out.print(q);
							}
							System.out.println(q+s);
						}
						//长度的计算
						String lengh="0";
						String len=text7.getText();
						//int leng=len.length();
					    int	leng=str2.length()+8;
						text7.setText(String.valueOf(leng));
						String lengt=Integer.toBinaryString(leng);
						if(lengt.length()<16){ 
							for(int k=0;k<15-lengt.length();k++){
								lengh="0";
								System.out.print(lengh);
							}
							
							System.out.println(lengh+lengt);
						}
						
						//目的地址
						String moun="0";
						int mo=Integer.parseInt(text10.getText());
						String mou=Integer.toBinaryString(mo);
						if(mou.length()<16){
							for(int m=0;m<15-mou.length();m++){
								moun="0";
								System.out.print(moun);
							}
							System.out.println(moun+mou);
						}
						//源地址
						String yuanz="0";
						int yua=Integer.parseInt(text9.getText());
						String yuan=Integer.toBinaryString(yua);
						if(yuan.length()<16){
							for(int y=0;y<15-yuan.length();y++){
								yuanz="0";
								System.out.print(yuanz);
							}
							System.out.println(yuanz+yuan);
						}
						//伪首部的长度
						String zon="0";
						String t=text7.getText();
						//text4.setText(t);
						int zo=Integer.parseInt(t);
						String zong=Integer.toBinaryString(zo);
						if(zong.length()<16){ 
							for(int z=0;z<15-lengt.length();z++){
								zon="0";
								System.out.print(zon);
							}
							
							System.out.println(zon+zong);
						}
						
						//协议的转换
						String x="0";
						int xa=Integer.parseInt(text6.getText());
						String xie=Integer.toBinaryString(xa);
						if(xie.length()<8){
							for(int xi=0;xi<7-xie.length();xi++){
							x="0";
							System.out.print(x);
							}
						}
						String xieyi=x+xie;
						//System.out.println(xieyi);
						
						//伪首部中协议和
						String w="0";
						int wei=Integer.parseInt(text5.getText());
						String shou=Integer.toBinaryString(wei);
						if(shou.length()<8){
							for(int j=0;j<7-shou.length();j++){
							w="0";
							System.out.print(w);
							}
							
						}
						String weishou=w+shou;
						//System.out.println(weishou);
						
						//把伪首部的协议和校验和加一起   有用
						String h=weishou+xieyi;
						System.out.println(h);
							
						//目的IP地址
						String da1="0";
						String da2="0";
						String da3="0";
						String da4="0";
						 StringTokenizer st = new StringTokenizer(str0, "."); 
						 List<String> data = new ArrayList<String>(); 
						 while (st.hasMoreElements()) { 
							 data.add(st.nextToken()); 
							} 
						 //第一个数值转换
						 da1=data.get(0);
						 String lin="0";
						 int da11=Integer.parseInt(da1);
						 String da111=Integer.toBinaryString(da11);
						 if(da111.length()<8){
							 for(int d1=0;d1<7-da111.length();d1++){
								 lin="0";
								 System.out.print(lin);
							 }
							 }
						 String fin1=lin+da111;
						 if(da111.length()==8){
							 fin1=da111;
						 }
						 //第二个数值转换
						 da2=data.get(1);
						 String lin1="0";
						 int da22=Integer.parseInt(da2);
						 String da222=Integer.toBinaryString(da22);
						 if(da222.length()<8){
							 for(int d2=0;d2<7-da222.length();d2++){
								 lin1="0";
								 System.out.print(lin1);
							 }
						 }
						 String fin2=lin1+da222;
						 
//						 String f=fin1+fin2;
						 String f=fin1.concat(fin2);
						 System.out.println(f);
			}	
			
		});
	}
}