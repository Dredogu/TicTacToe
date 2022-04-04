import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class TicTacToe implements ActionListener{

	JFrame frame = new JFrame("Tic Tac Toe");
	JButton[] buttons = new JButton[9];
	JLabel label;
	JPanel title_panel;
	JPanel body_panel;
	JMenuBar menuBar;
	JMenu Menu;
	JMenuItem retry,exit;
	String playerAnswer = " ";
	int a = 0;
	
	TicTacToe(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,565);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		Menu = new JMenu("...");
		retry = new JMenuItem("Reset");
		exit = new JMenuItem("Exit");
		
		Menu.setMnemonic(KeyEvent.VK_ENTER);// alt +  Enter
		retry.setMnemonic(KeyEvent.VK_R);// alt + R
		exit.setMnemonic(KeyEvent.VK_E);// alt + E
		
		retry.addActionListener(this);
		exit.addActionListener(this);
		
		title_panel = new JPanel();
		title_panel.setPreferredSize(new Dimension(500,100));
		title_panel.setLayout(new FlowLayout());
		title_panel.setBackground(Color.BLACK);
		title_panel.setVisible(true);
		
		body_panel = new JPanel();
		body_panel.setPreferredSize(new Dimension(485,405));
		//body_panel.setLayout(new FlowLayout());
		body_panel.setLayout(new GridLayout(3,3,0,0));
		body_panel.setVisible(true);
		
		label = new JLabel();
		label.setText("X turn");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("MV Boli",Font.BOLD,50));
		
		
		for(int i=0;i<9;i++) {
			buttons[i] = new JButton();
			buttons[i].setPreferredSize(new Dimension(150,115));
			buttons[i].setText(" ");
			buttons[i].setEnabled(true);
			buttons[i].setFocusable(false);
			buttons[i].setBackground(Color.white);
			buttons[i].setFont(new Font("MV Boli",Font.PLAIN,75));
			buttons[i].addActionListener(this);
			buttons[i].setVisible(true);
			body_panel.add(buttons[i]);
		}
		
		Menu.add(retry);
		Menu.add(exit);
		menuBar.add(Menu);
		title_panel.add(label);
		
		frame.setJMenuBar(menuBar);
		frame.add(title_panel);
		frame.add(body_panel);
		frame.setVisible(true);
		
	}
	
	private void check() {
		
		if(buttons[0].getText() == playerAnswer && buttons[1].getText() == playerAnswer && buttons[2].getText() == playerAnswer ) {
			win(0,1,2);
		}
		else if(buttons[3].getText() == playerAnswer && buttons[4].getText() == playerAnswer && buttons[5].getText() == playerAnswer ) {
			win(3,4,5);
		}
		else if(buttons[6].getText() == playerAnswer && buttons[7].getText() == playerAnswer && buttons[8].getText() == playerAnswer ) {
			win(6,7,8);
		}
		else if(buttons[0].getText() == playerAnswer && buttons[3].getText() == playerAnswer && buttons[6].getText() == playerAnswer ) {
			win(0,3,6);
		}
		else if(buttons[1].getText() == playerAnswer && buttons[4].getText() == playerAnswer && buttons[7].getText() == playerAnswer ) {
			win(1,4,7);
		}
		else if(buttons[2].getText() == playerAnswer && buttons[8].getText() == playerAnswer && buttons[5].getText() == playerAnswer ) {
			win(2,8,5);
		}
		else if(buttons[0].getText() == playerAnswer && buttons[4].getText() == playerAnswer && buttons[8].getText() == playerAnswer ) {
			win(0,4,8);
		}
		else if(buttons[2].getText() == playerAnswer && buttons[4].getText() == playerAnswer && buttons[6].getText() == playerAnswer ) {
			win(2,4,6);
		}
		else if(a == 9) {
			label.setText("Draw!");
			playRetry();
		}
	}
	
	private void win(int x,int y,int z) {
		buttons[x].setBackground(Color.green);
		buttons[y].setBackground(Color.green);
		buttons[z].setBackground(Color.green);
		label.setText(playerAnswer+" win");
		playRetry();
	}
	

	private void playRetry() {
		
		String[] response = {"Yes","No"};
		
		int answer = JOptionPane.showOptionDialog(null, "Tekrar oynamak ister misiniz?", label.getText(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, response, null);
		
		if(answer == 0) {
			a = 0;
			label.setText("X turn");
			for(int i=0;i<9;i++) {
				buttons[i].setText(" ");
				buttons[i].setBackground(Color.white);
				
			}
		}
		else {
			System.exit(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		a++;
		
		if(e.getSource() == retry) {
			
			a = 0;
			label.setText("X turn");
			for(int i=0;i<9;i++) {
				buttons[i].setText(" ");
				buttons[i].setBackground(Color.white);
				
			}
		}
		else if(e.getSource() == exit) {
			System.exit(0);
		}
		else {
			
			for(int i=0;i<9;i++) {
				if(e.getSource() == buttons[i]) {
					if(buttons[i].getText() == " ") {
						
						if(a%2 == 1) {
							label.setText("O turn");
							buttons[i].setText("X");
							buttons[i].setForeground(Color.red);
							playerAnswer = "X";
							
						}
						if(a%2 == 0) {
							label.setText("X turn");
							buttons[i].setText("O");
							buttons[i].setForeground(Color.blue);
							playerAnswer = "O";
							
						}
						check();
					}
					else {
						a--;
						JOptionPane.showMessageDialog(null, "Illegal act", "Warning !!!", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
			}
		}
	}
}