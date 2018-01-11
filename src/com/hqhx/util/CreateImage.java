package com.hqhx.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class CreateImage {

	private static Random r=new Random();
	private static String code;

	public String getCode() {
		return code;
	}

	public static void main(String[] args) {
		BufferedImage img=getImage();
		//创建一个输出的字节流把图片输出到磁盘下
		OutputStream os=null;
		try {
			os=new FileOutputStream(new File("f:\\qq.jpg"));
			ImageIO.write(img, "JPEG", os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//生成验证码图片
	public static BufferedImage getImage(){
		String str=getString();
		code=str;
		BufferedImage image=new BufferedImage(100, 40, BufferedImage.TYPE_INT_BGR);
		//获取一个可以绘制的2d图片
		Graphics g=image.getGraphics();
		//设置颜色为白色
		g.setColor(Color.WHITE);
		//先把图片填充成白色底色
		g.fillRect(0, 0, 100, 40);
		
		//写字符串
		for(int i=0;i<str.length();i++){
			g.setFont(new Font("Times New Roman", Font.BOLD, 25));
			//设置颜色
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawString(str.substring(i,i+1), 10+20*i, 25);
		}
		//画干扰线
		for(int i=0;i<400;i++){
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawOval(r.nextInt(100), r.nextInt(40), 1, 1);
		}
		return image;
	}
	
	//生成验证码
	public static String getString(){
		String str="";
		for(int i=0;i<4;i++){
			str+=getChar();
		}
		return str;
	}
	
	//随机生成一个字符
	public static char getChar(){
		Random r=new Random();
		int i=r.nextInt(62);
		char c;
		if(i>=0&i<=9){
			c=(char)(i+48);
		}else if(i>=10&i<=36){
			c=(char)(i+55);
		}else{
			c=(char)(i+60);
		}
		return c;
	}
}
