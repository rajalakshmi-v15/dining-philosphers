package dining;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Dimension;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class SampleGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SampleGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
//DINING PHILOSOPHERS ( using threads )
public class DiningPhilo extends Thread{
	
	private int philo;
	private static boolean me = true;
	private static int[] s = new int[5];
	private static int[] pflag = new int[5];// 0-think 1-hungry 2-eat
	public static SampleGUI frame;
		Random rand = new Random();
		
		
		
	DiningPhilo(int p){
		philo = p;
		System.out.println("Philosopher"+philo+" is at the table.");
		
		//INTRODUCE PHILOSOPHER'S PICTURE
		
	}
	
	public void run(){
		int n = rand.nextInt(10)+1;
		System.out.println("Philosopher "+philo+" thinking for the next"+n+"minutes.");
		
		
		//INSERT THINKING PICTURE
		
		
		try{Thread.sleep(n);
		}catch(InterruptedException e){
			System.out.println(e);
		}
		takeChopsticks(philo);
		
		
		dropChopsticks(philo);
	}
	
	
	public void takeChopsticks(int p){
		me = false;
		if  (pflag[p]==0){
			pflag[p] = 1;
		}
		test(p,p);
		me = true;
		s[p] = 0;
	}
	
	
	public void test(int p,int k){
		int n;
		
		//for debugging:
		//System.out.println("philo "+p+"....flag "+pflag[p]+"called by philo"+k);
		
		
		int philoMinus;
		if (p == 0)
		{
		   philoMinus = 4;
		}
		else
		{
			philoMinus = p - 1;
		}
		if (pflag[p]==1)
		{
			
			//INSERT HUNGRY PICTURE
			
			if( pflag[philoMinus]!=2 && pflag[(p+1)%5]!=2)
			{
			
			pflag[p]=2;
			
			//INSERT EATING PICTURE
			
			s[p]=1;
			n=rand.nextInt(10)+1;
			System.out.println("Philosopher "+p+" eating for the next"+n+"minutes.");
			try{Thread.sleep(n);
			}catch(InterruptedException e){
				System.out.println(e);
			}
			}
			else
			{
				
				System.out.println("Philosopher "+p+" is unable to eat." );
				
				
				// UNABLE TO EAT
				
				
				try{
					Thread.sleep(50);
				}catch(InterruptedException e){
					System.out.println(e);
				}
				 takeChopsticks(p);
				}
			}
		
	}
	
	
	public void dropChopsticks(int p)
	{
		int philoMinus;
		if (p == 0)
		{
		   philoMinus = 4;
		}
		else
		{
			philoMinus = p - 1;
		}
		me = false;
		pflag[p]=0;
		System.out.println("Philopher "+p+" says: FINISHED EATING!");
		
		//INSERT THINKING PICTURE
		
		test(philoMinus,p);
		test((p+1)%5,p);
		me = true;
		
		
		
		int n=rand.nextInt(50)+100;
		
		try{Thread.sleep(n);
		}catch(InterruptedException e){
			System.out.println(e);
		}
		run();
	}
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SampleGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		DiningPhilo dp1 = new DiningPhilo(0);
		DiningPhilo dp2 = new DiningPhilo(1);
		DiningPhilo dp3 = new DiningPhilo(2);
		DiningPhilo dp4 = new DiningPhilo(3);
		DiningPhilo dp5 = new DiningPhilo(4);
		
		// SHOW DINING TABLE WITH CHOPSTICKS AND BOWLS
		
		
		dp1.start();
		dp2.start();
		dp3.start();
		dp4.start();
		dp5.start();
		//dp1.cleanUp();
		
		
	}
}
