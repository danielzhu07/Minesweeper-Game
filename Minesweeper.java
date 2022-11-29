//The Code for the Actual Minesweeper Game
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class Minesweeper extends JFrame {
	Instant starts = Instant.now();
    static String files[] = {"0.png", "1.png", "2.png", "3.png",
        "4.png", "5.png", "6.png", "7.png", "8.png"};
    static JButton buttons[][];
    static boolean minesPresent[][];
    ImageIcon initialIcon, mineIcon, flagIcon;
    ImageIcon icons[];
    final int size = 15;
    static String totaltime = "1";
    static double MINES_PERCENTAGE = 0.1;
    public static Clip clip2, clip3, clip4, clip5;
    int actualMinesLeft = 0, minesLeft = 0;
    private boolean losingbombs = false;

    
    public Minesweeper(double spawn, String username, String level) {
    	MINES_PERCENTAGE = spawn;
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(size, size));
        setLocationRelativeTo(null);
        //setAlwaysOnTop(true);
 
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setBounds(center.x - 450/2, center.y- 324/2, 450, 324);
        
        initialIcon = new ImageIcon("initial.JPG");
        mineIcon = new ImageIcon("9.png");
        flagIcon = new ImageIcon("11.png");
        buttons = new JButton[size][size];
        minesPresent = new boolean[size][size];

        icons = new ImageIcon[files.length];
        for (int i = 0; i < files.length; i++) {
            icons[i] = new ImageIcon(files[i]);
        }

        Random gen = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].addMouseListener(new MouseAdapter(){
                	@Override
                	public void mouseClicked(MouseEvent e) {
                		AudioInputStream stream1;
						try {
							stream1 = AudioSystem.getAudioInputStream(new File("click.wav"));
							clip2 = AudioSystem.getClip();
	    					clip2.open(stream1);
	    					clip2.start();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
 
                		if(e.getButton() == MouseEvent.BUTTON3) {
                			for (int i = 0; i < size; i++) {
                                for (int j = 0; j < size; j++) {
                                    if (e.getSource() == buttons[i][j]) {
                                    	if(buttons[i][j].getIcon() == initialIcon) {
                                    		if(minesPresent[i][j])
                                        		actualMinesLeft--;
                                    		buttons[i][j].setIcon(flagIcon);
                                    		minesLeft--;
                                    	}
                                    	else if(buttons[i][j].getIcon() == flagIcon) {
                                    		if(minesPresent[i][j])
                                    			actualMinesLeft++;
                                    		minesLeft++;
                                    		buttons[i][j].setIcon(initialIcon);
                                    	}
                                    	if(actualMinesLeft == 0 && minesLeft == 0) {
                                    		Instant ends = Instant.now();
                                    		Duration temp = Duration.between(starts, ends);
                                    		totaltime = String.valueOf(temp.toSeconds());
											try {
												Scanner scnr = new Scanner(new File("highscores.txt"));
												String score1 = "9999999";
												while(scnr.hasNextLine()) {
													String s = scnr.nextLine();
													if(s.substring(0, s.indexOf(" ")).equals(username) && s.substring(s.indexOf(" ")+1, s.lastIndexOf(" ")).equals(level)) {
														s = s.substring(s.lastIndexOf(" ")+1, s.length());
														score1 = s;
													}
												}
												if(Integer.parseInt(score1) > Integer.parseInt(totaltime)) {
													FileWriter fw = new FileWriter("highscores.txt", true);
													fw.write(username + " " + level + " " + totaltime + "\n");
													fw.close();
												}
												FileWriter fw1 = new FileWriter("scorehistory.txt", true);
												fw1.write(username + " " + level + " " + totaltime + " " + "Won" + "\n");
												fw1.close();
												AudioInputStream stream3;
												try {
													stream3 = AudioSystem.getAudioInputStream(new File("win.wav"));
													clip4 = AudioSystem.getClip();
													clip4.open(stream3);
													clip4.start();
											
												} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												JOptionPane j1 = new JOptionPane();
												j1.setBounds(1, 1, 300, 200);
												int reply = j1.showConfirmDialog(null, "You won in " + totaltime + " seconds! Play again?", "GG", JOptionPane.YES_NO_OPTION);
												if (reply == JOptionPane.YES_OPTION) {
													Account.inGame = true;
													setVisible(false);
	                                            	new Minesweeper(MINES_PERCENTAGE, username, level);
												} else {
													BooleanControl muteControl = (BooleanControl) Account.clip1.getControl(BooleanControl.Type.MUTE);
                                    				if(muteControl != null)
                                    					muteControl.setValue(true);
                                    				Account.clip1.loop(0);
                                    				Account.clip1.flush();
													dispose();
													AudioInputStream stream;
													try {
														stream = AudioSystem.getAudioInputStream(new File("IntroMusic.wav"));
														Account.clip = AudioSystem.getClip();
                                    					Account.clip.open(stream);
                                    					
                                    					Account.clip.start();
                                    					Account.clip.loop(Clip.LOOP_CONTINUOUSLY);
                                    		
													} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
													
													Account.inGame = false;
												}               
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
            				          
                                    	}
                                    }
                                }
                			}
                    	}
                		else if(e.getButton() == MouseEvent.BUTTON1) {
                			for (int i = 0; i < size; i++) {
                                for (int j = 0; j < size; j++) {
                                    if (e.getSource() == buttons[i][j]) {
                                        if (minesPresent[i][j] && !losingbombs) {
                                        	losingbombs = true;
                                            buttons[i][j].setIcon(mineIcon);
                                            Thread imageLoadingThread = new Thread(new Runnable() {
                                                public void run() {
                                                    for(int i = 0; i < size; i++) {
                                                    	for(int j = 0; j < size; j++) {
                                                    		if(minesPresent[i][j]) {
                                                    			try {
                                        							AudioInputStream stream2 = AudioSystem.getAudioInputStream(new File("explosion.wav"));
                                        							clip3 = AudioSystem.getClip();
                                        	    					clip3.open(stream2);
                                        	    					clip3.start();
                                        						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
                                        							// TODO Auto-generated catch block
                                        							e2.printStackTrace();
                                        						}
                                                        		buttons[i][j].setIcon(mineIcon);
                                                        		try {
                                                        			Thread.sleep(600);
                                                        		} catch (InterruptedException e) {
                                                        			e.printStackTrace();
                                                        		}
                                                    		}
                                                    	}
                                                    }
                                                    FileWriter fw2;
													try {
														Instant ends = Instant.now();
			                                    		Duration temp = Duration.between(starts, ends);
			                                    		totaltime = String.valueOf(temp.toSeconds());
														fw2 = new FileWriter("scorehistory.txt", true);
														fw2.write(username + " " + level + " " + totaltime + " " + "Lost" + "\n");
	    												fw2.close();
													} catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													AudioInputStream stream4;
													try {
														stream4 = AudioSystem.getAudioInputStream(new File("lose.wav"));
														clip5 = AudioSystem.getClip();
														clip5.open(stream4);
														clip5.start();
												
													} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
													losingbombs = false;
                                                    int reply = JOptionPane.showConfirmDialog(null, "You lost! Play again?", "F in the chat", JOptionPane.YES_NO_OPTION);
                                                    if (reply == JOptionPane.YES_OPTION) {
                                                    	setVisible(false);
                                                    	Account.inGame = true;
                                                        new Minesweeper(MINES_PERCENTAGE, username, level);
                                                    } else {
                                                    	BooleanControl muteControl = (BooleanControl) Account.clip1.getControl(BooleanControl.Type.MUTE);
                                        				if(muteControl != null)
                                        					muteControl.setValue(true);
                                        				Account.clip1.loop(0);
                                        				Account.clip1.flush();
                                                        dispose();
                                                        AudioInputStream stream;
														try {
															stream = AudioSystem.getAudioInputStream(new File("IntroMusic.wav"));
															Account.clip = AudioSystem.getClip();
	                                    					Account.clip.open(stream);
	                                    					
	                                    					Account.clip.start();
	                                    					Account.clip.loop(Clip.LOOP_CONTINUOUSLY);
	                                    		
														} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														Account.inGame = false;
                                                        
                                                    }
                                                }
                                            });
                                            imageLoadingThread.start();		
                                        } else {
                                        	if(!losingbombs)
                                        		exposeNeighbors(i,j);
                                        }
                                    }
                                }
                            }
                		}
                	}
                });
                buttons[i][j].setIcon(initialIcon);
                if (gen.nextDouble() < MINES_PERCENTAGE) {
                    minesPresent[i][j] = true;
                    minesLeft++; actualMinesLeft++;
                } else {
                    minesPresent[i][j] = false;
                }
                getContentPane().add(buttons[i][j]);
            }
        }

        setSize(300, 300);
        // Pack and display the window.
        //pack();
        setVisible(true);

    }

    public int countMines(int i, int j) {
        int count = 0;
        if ((i>0) && (j > 0) && (minesPresent[i-1][j-1])) {
            count++;
        }
        if ((i>0) && (minesPresent[i - 1][j])) {
            count++;
        }
        if ((i>0) && (j+1 < size) &&(minesPresent[i - 1][j+1])) {
            count++;
        }
        if ((j > 0) && (minesPresent[i][j-1])) {
            count++;
        }
        if ((j+1 < size) && (minesPresent[i][j+1])) {
            count++;
        }
          if ((i+1 < size) && (j > 0) && (minesPresent[i + 1][j-1])) {
            count++;
        }
        if ((i+1 < size) && (minesPresent[i + 1][j])) {
            count++;
        }
        if ((i+1 < size) && (j+1 < size) && (minesPresent[i + 1][j+1])) {
            count++;
        }      
        return count;
    }
    
    public void exposeNeighbors(int i, int j) {
        
        if ((i < 0) || (j<0) || (i>=size) || (j>= size))
            return;
        if (buttons[i][j].getIcon() != initialIcon)
            return;
        
        int adjCount = countMines(i,j);
        buttons[i][j].setIcon(icons[adjCount]);
        if (adjCount > 0)
            return;
        exposeNeighbors(i-1, j-1);
        exposeNeighbors(i-1, j);
        exposeNeighbors(i-1, j+1);
        exposeNeighbors(i, j-1);
        exposeNeighbors(i, j+1);
        exposeNeighbors(i+1, j-1);
        exposeNeighbors(i+1, j);
        exposeNeighbors(i+1, j+1);
    }
    public static void main(String[] args) {
        //new Minesweeper(MINES_PERCENTAGE, username, level);
    }
}