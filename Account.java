//Code for all the panels(login panel, profile panel, etc.)
//Implements the Minesweeper.java code
//Also stores account/user information
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.event.ActionEvent;

public class Account extends JFrame {

	private JPanel contentPane;
	private final JLabel lblUsername = new JLabel("Username");
	private JTextField signupusernamebox;
	private JPasswordField signuppasswordbox;
	private JPasswordField signupcpasswordbox;
	private final JLabel lblUsername_1 = new JLabel("Username");
	private JTextField loginusernamebox;
	private JPasswordField loginpasswordbox;
	private final JLabel profileusername = new JLabel("Username");
	private JTextField easypersonalbest;
	private JTextField mediumpersonalbest;
	private JTextField hardpersonalbest;
	private JTable scoreTable;
	private DefaultTableModel model;
	private String username;
	JPanel signuppanel = new JPanel();
	JPanel profilepanel = new JPanel();
	public static JPanel difficultypanel = new JPanel();
	JPanel loginpanel = new JPanel();
	private String curusername, curlevel;
	private static Thread t1;
	public static Clip clip, clip1;
	private static AudioInputStream sound;
	JButton profilebutton = new JButton("Profile");
	public static boolean inGame = false;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AudioInputStream stream = AudioSystem.getAudioInputStream(new File("IntroMusic.wav"));
					clip = AudioSystem.getClip();
					clip.open(stream);
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					Account frame = new Account();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Account() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		setBounds(center.x - 450/2, center.y- 324/2, 450, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel welcomepanel = new JPanel();
		contentPane.add(welcomepanel, "name_239611729152100");
		welcomepanel.setLayout(null);
		
        
		
		JLabel topmsg = new JLabel("Welcome to Minesweeper");
		topmsg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		topmsg.setBounds(102, 11, 225, 41);
		welcomepanel.add(topmsg);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomepanel.setVisible(false);
				signuppanel.setVisible(true);
			}
		});
		btnNewButton.setBounds(29, 220, 89, 23);
		welcomepanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Log In");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginpanel.setVisible(true);
				welcomepanel.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(162, 220, 89, 23);
		welcomepanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Guest");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultypanel.setVisible(true);
				welcomepanel.setVisible(false);
				profilebutton.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(297, 220, 89, 23);
		welcomepanel.add(btnNewButton_2);
		
		JLabel welcomepicture = new JLabel("");
		welcomepicture.setFont(new Font("Tahoma", Font.PLAIN, 18));
		welcomepicture.setBounds(39, 63, 348, 134);
		welcomepanel.add(welcomepicture);
		Image img = new ImageIcon(this.getClass().getResource("/welcomepicture.png")).getImage();
		Image newImage = img.getScaledInstance(350, 150, Image.SCALE_DEFAULT);
		welcomepicture.setIcon(new ImageIcon(newImage));
		
		contentPane.add(signuppanel, "name_239638431397500");
		signuppanel.setLayout(null);
		lblUsername.setBounds(66, 71, 95, 34);
		signuppanel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(66, 105, 95, 34);
		signuppanel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(66, 137, 160, 34);
		signuppanel.add(lblConfirmPassword);
		
		signupusernamebox = new JTextField();
		signupusernamebox.setBounds(224, 80, 96, 20);
		signuppanel.add(signupusernamebox);
		signupusernamebox.setColumns(10);
		
		signuppasswordbox = new JPasswordField();
		signuppasswordbox.setBounds(224, 114, 96, 20);
		signuppanel.add(signuppasswordbox);
		
		signupcpasswordbox = new JPasswordField();
		signupcpasswordbox.setBounds(224, 146, 96, 20);
		signuppanel.add(signupcpasswordbox);
		
		JLabel lblCreateAnAccount = new JLabel("Create an Account");
		lblCreateAnAccount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCreateAnAccount.setBounds(136, 11, 160, 34);
		signuppanel.add(lblCreateAnAccount);
		
		JButton signupbackbutton = new JButton("Back");
		signupbackbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomepanel.setVisible(true);
				signuppanel.setVisible(false);
			}
		});
		signupbackbutton.setBounds(86, 201, 89, 23);
		signuppanel.add(signupbackbutton);
		
		JButton signupbutton = new JButton("Sign Up");
		signupbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = signupusernamebox.getText();
				try {
					Scanner scnr = new Scanner(new File("accounts.txt"));
					boolean alreadyexists = false;
					while(scnr.hasNextLine()) {
						String s = scnr.nextLine();
						if(s.substring(0, s.indexOf(" ")).equals(username)) {
							alreadyexists = true;
							break;
						}
						
					}
					if(alreadyexists) {
						JOptionPane.showMessageDialog(null, "This Username already exists!");
					}
					else {
						char[] temp = signuppasswordbox.getPassword();
						String temp1 = new String(temp);
						char[] temp2 = signupcpasswordbox.getPassword();
						String temp3 = new String(temp2);
						if(temp1.equals(temp3) == false) {
							JOptionPane.showMessageDialog(null, "Passwords don't match!");
						}
						else {
							try {
								FileWriter fw = new FileWriter("accounts.txt", true);
								fw.write(username + " " + temp1 + "\n");
								JOptionPane.showMessageDialog(null, "Sign Up Successful!");
								fw.close();

							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "Something went wrong...");
								e1.printStackTrace();
							}
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		signupbutton.setBounds(231, 201, 89, 23);
		signuppanel.add(signupbutton);
		
		contentPane.add(loginpanel, "name_240005976489800");
		loginpanel.setLayout(null);
		lblUsername_1.setBounds(76, 65, 87, 40);
		loginpanel.add(lblUsername_1);
		lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPassword_1_1 = new JLabel("Password");
		lblPassword_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword_1_1.setBounds(76, 116, 87, 40);
		loginpanel.add(lblPassword_1_1);
		
		JButton btnNewButton_5 = new JButton("Log In");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 username = loginusernamebox.getText();
				 char[] temp = loginpasswordbox.getPassword();
				 String password = new String(temp);
				 try {
					Scanner scnr = new Scanner(new File("accounts.txt"));
					boolean alreadyexists = false;
					while(scnr.hasNextLine()) {
						String s = scnr.nextLine();
						if(s.substring(0, s.indexOf(" ")).equals(username) && s.substring(s.indexOf(" ")+1, s.length()).equals(password)) {
							alreadyexists = true;
							break;
						}
					}
					if(alreadyexists) {
						JOptionPane.showMessageDialog(null, "Log In Successful");
						loginpanel.setVisible(false);
						difficultypanel.setVisible(true);
						curusername = username;
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Account not found");
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				 
			}
		});
		btnNewButton_5.setBounds(236, 192, 89, 23);
		loginpanel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Back");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginpanel.setVisible(false);
				welcomepanel.setVisible(true);
			}
		});
		btnNewButton_6.setBounds(74, 192, 89, 23);
		loginpanel.add(btnNewButton_6);
		
		loginusernamebox = new JTextField();
		loginusernamebox.setBounds(229, 77, 96, 20);
		loginpanel.add(loginusernamebox);
		loginusernamebox.setColumns(10);
		
		loginpasswordbox = new JPasswordField();
		loginpasswordbox.setBounds(229, 128, 96, 20);
		loginpanel.add(loginpasswordbox);
		
		JLabel lblLogIn = new JLabel("Log In To Your Account");
		lblLogIn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogIn.setBounds(123, 11, 233, 34);
		loginpanel.add(lblLogIn);
		
		contentPane.add(difficultypanel, "name_240050262276700");
		difficultypanel.setLayout(null);
		
		JLabel lblChooseYourDifficulty = new JLabel("Choose Your Difficulty");
		lblChooseYourDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChooseYourDifficulty.setBounds(119, 11, 204, 34);
		difficultypanel.add(lblChooseYourDifficulty);
		
		JButton easybutton = new JButton("Easy");
		easybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inGame) {
					inGame = true;
					BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
					if(muteControl != null)
						muteControl.setValue(true);
					clip.loop(0);
					clip.flush();
					curlevel = "Easy";
					
					new Minesweeper(0.05, curusername, curlevel);
					AudioInputStream stream;
					try {
						stream = AudioSystem.getAudioInputStream(new File("GamePlayMusic.wav"));
						clip1 = AudioSystem.getClip();
						clip1.open(stream);
						clip1.start();
						clip1.loop(Clip.LOOP_CONTINUOUSLY);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		easybutton.setBounds(26, 111, 89, 23);
		difficultypanel.add(easybutton);
		
		JButton mediumbutton = new JButton("Medium");
		mediumbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inGame) {
					inGame = true;
					BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
					if(muteControl != null)
						muteControl.setValue(true);
					clip.loop(0);
					clip.flush();
					curlevel = "Medium";
					
					new Minesweeper(0.1, curusername, curlevel);
					AudioInputStream stream;
					try {
						stream = AudioSystem.getAudioInputStream(new File("GamePlayMusic.wav"));
						clip1 = AudioSystem.getClip();
						clip1.open(stream);
						clip1.start();
						clip1.loop(Clip.LOOP_CONTINUOUSLY);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		mediumbutton.setBounds(162, 111, 89, 23);
		difficultypanel.add(mediumbutton);
		
		JButton hardbutton = new JButton("Hard");
		hardbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inGame) {
					inGame = true;
					BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
					if(muteControl != null)
						muteControl.setValue(true);
					clip.loop(0);
					clip.flush();
					curlevel = "Hard";
					
					new Minesweeper(0.15, curusername, curlevel);
					AudioInputStream stream;
					try {
						stream = AudioSystem.getAudioInputStream(new File("GamePlayMusic.wav"));
						clip1 = AudioSystem.getClip();
						clip1.open(stream);
						clip1.start();
						clip1.loop(Clip.LOOP_CONTINUOUSLY);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		hardbutton.setBounds(307, 111, 89, 23);
		difficultypanel.add(hardbutton);
		
		profilebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultypanel.setVisible(false);
				profilepanel.setVisible(true);
				profileusername.setText(curusername);
				Scanner scnr;
				try {
					scnr = new Scanner(new File("highscores.txt"));
					String easyhigh = "null", mediumhigh = "null", hardhigh = "null";
					while(scnr.hasNextLine()) {
						String s = scnr.nextLine();
						s += " ";
						if(s.substring(0, s.indexOf(" ")).equals(curusername)) {
							s = s.substring(s.indexOf(" ")+1, s.length());
							if(s.substring(0, s.indexOf(" ")).equals("Easy")) {
								easyhigh = s.substring(s.indexOf(" ")+1, s.length());
							}
							else if(s.substring(0, s.indexOf(" ")).equals("Medium")) {
								mediumhigh = s.substring(s.indexOf(" ")+1, s.length());
							}
							else {
								hardhigh = s.substring(s.indexOf(" ")+1, s.length());
							}
						}
					}
					if(easyhigh.equals("null"))
						easypersonalbest.setText("No Score Yet");
					else
						easypersonalbest.setText(easyhigh);
					if(mediumhigh.equals("null"))
						mediumpersonalbest.setText("No Score Yet");
					else
						mediumpersonalbest.setText(mediumhigh);
					if(hardhigh.equals("null"))
						hardpersonalbest.setText("No Score Yet");
					else
						hardpersonalbest.setText(hardhigh);
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			}
		});
		profilebutton.setBounds(108, 220, 89, 23);
		difficultypanel.add(profilebutton);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BooleanControl muteControl = (BooleanControl) Account.clip.getControl(BooleanControl.Type.MUTE);
				if(muteControl != null)
					muteControl.setValue(true);
				clip.loop(0);
				clip.flush();
				dispose();
				
			}
		});
		btnNewButton_3.setBounds(237, 220, 89, 23);
		difficultypanel.add(btnNewButton_3);
		
		
		contentPane.add(profilepanel, "name_240076336621500");
		profilepanel.setLayout(null);
		profileusername.setBounds(160, 0, 214, 41);
		profilepanel.add(profileusername);
		profileusername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		easypersonalbest = new JTextField();
		easypersonalbest.setBounds(10, 110, 96, 20);
		profilepanel.add(easypersonalbest);
		easypersonalbest.setColumns(10);
		
		JLabel lblPersonalBest = new JLabel("Personal Best");
		lblPersonalBest.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPersonalBest.setBounds(10, 43, 96, 34);
		profilepanel.add(lblPersonalBest);
		
		JLabel lblEasy = new JLabel("Easy");
		lblEasy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEasy.setBounds(10, 75, 96, 34);
		profilepanel.add(lblEasy);
		
		JLabel lblMedium = new JLabel("Medium");
		lblMedium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedium.setBounds(10, 133, 96, 34);
		profilepanel.add(lblMedium);
		
		mediumpersonalbest = new JTextField();
		mediumpersonalbest.setBounds(10, 176, 96, 20);
		profilepanel.add(mediumpersonalbest);
		mediumpersonalbest.setColumns(10);
		
		JLabel lblHard = new JLabel("Hard");
		lblHard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHard.setBounds(10, 207, 96, 34);
		profilepanel.add(lblHard);
		
		hardpersonalbest = new JTextField();
		hardpersonalbest.setBounds(10, 246, 96, 20);
		profilepanel.add(hardpersonalbest);
		hardpersonalbest.setColumns(10);
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHistory.setBounds(170, 43, 71, 34);
		profilepanel.add(lblHistory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(174, 87, 229, 109);
		profilepanel.add(scrollPane);
		
		scoreTable = new JTable();
		scrollPane.setViewportView(scoreTable);
		
		JButton loadscoresbutton = new JButton("Load Scores");
		loadscoresbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner scnr = new Scanner(new File("scorehistory.txt"));
					while(scnr.hasNextLine()) {
						String s = scnr.nextLine();
						if(s.substring(0, s.indexOf(" ")).equals(username)) {
							s = s.substring(s.indexOf(" ")+1, s.length());
							String[] arr = s.split(" ");
							model.addRow(arr);
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});
		loadscoresbutton.setBounds(287, 51, 116, 23);
		profilepanel.add(loadscoresbutton);
		
		JButton backbutton = new JButton("Back");
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profilepanel.setVisible(false);
				difficultypanel.setVisible(true);
				model.setRowCount(0);
			}
		});
		backbutton.setBounds(314, 245, 89, 23);
		profilepanel.add(backbutton);
		model = (DefaultTableModel) scoreTable.getModel();
		String[] headers = {"Difficulty", "Time", "Result"};
		model.setColumnIdentifiers(headers);
	}
}
