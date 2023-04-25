package com.example;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class OrderGui {
	private JFrame frame;
	private JTextField Nametxtfield;
	private JTextField phoneNumtxtfield;

	boolean stamp_Check;
	String select_shape = " ";
	public String name = " ";
	public String phone = " ";
	public String shape ;
	public boolean stamp;
	
	JavaSocketServer JSS = new JavaSocketServer();
	/**
	 * Launch the application.
	 */
	public void order_pannel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderGui window = new OrderGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("사용자 주문 시스템");
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(1000,200);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(null);
		
		JPanel name_space = new JPanel();
		name_space.setBackground(new Color(255, 255, 255));
		name_space.setBounds(72, 10, 461, 52);
		bodyPanel.add(name_space);
		
		JLabel NameLabel = new JLabel("    이름    ");
		NameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		name_space.add(NameLabel);
		NameLabel.setFont(new Font("카페24 써라운드", Font.PLAIN, 30));
		
		Nametxtfield = new JTextField();
		name_space.add(Nametxtfield);
		Nametxtfield.setFont(new Font("카페24 써라운드", Font.PLAIN, 30));
		Nametxtfield.setHorizontalAlignment(SwingConstants.LEFT);
		Nametxtfield.setColumns(10);
		
		JPanel phonenum_space = new JPanel();
		phonenum_space.setBackground(new Color(255, 255, 255));
		phonenum_space.setBounds(72, 72, 461, 52);
		bodyPanel.add(phonenum_space);
		
		JLabel phoneNumLabel = new JLabel(" 전화번호 ");
		phoneNumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		phoneNumLabel.setFont(new Font("카페24 써라운드", Font.PLAIN, 30));
		phonenum_space.add(phoneNumLabel);
		
		phoneNumtxtfield = new JTextField();
		phoneNumtxtfield.setHorizontalAlignment(SwingConstants.LEFT);
		phoneNumtxtfield.setFont(new Font("카페24 써라운드", Font.PLAIN, 30));
		phoneNumtxtfield.setColumns(10);
		phonenum_space.add(phoneNumtxtfield);
		
		final JButton squareBtn = new JButton("");
		squareBtn.setSelectedIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\selected_square.png"));
		squareBtn.setBackground(new Color(255, 255, 255));
		squareBtn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\square.png"));
		squareBtn.setBounds(35, 134, 120, 150);
		squareBtn.setFocusPainted(false);
		squareBtn.setOpaque(false);
		squareBtn.setBorderPainted(false);
		squareBtn.setContentAreaFilled(false);
		bodyPanel.add(squareBtn);
		
		final JButton hexagonBtn = new JButton("");
		hexagonBtn.setSelectedIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\selected_hexagon.png"));
		hexagonBtn.setBackground(new Color(255, 255, 255));
		hexagonBtn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\hexagon.png"));
		hexagonBtn.setBounds(325, 134, 120, 150);
		hexagonBtn.setFocusPainted(false);
		hexagonBtn.setOpaque(false);
		hexagonBtn.setBorderPainted(false);
		hexagonBtn.setContentAreaFilled(false);
		bodyPanel.add(hexagonBtn);
		
		final JButton octagonBtn = new JButton("");
		octagonBtn.setSelectedIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\selected_octagon.png"));
		octagonBtn.setBackground(new Color(255, 255, 255));
		octagonBtn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\octagon.png"));
		octagonBtn.setBounds(470, 134, 120, 150);
		octagonBtn.setFocusPainted(false);
		octagonBtn.setOpaque(false);
		octagonBtn.setBorderPainted(false);
		octagonBtn.setContentAreaFilled(false);
		bodyPanel.add(octagonBtn);
		
		final JButton rectangleBtn = new JButton("");
		rectangleBtn.setSelectedIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\selected_rectangle.png"));
		rectangleBtn.setForeground(new Color(0, 0, 0));
		rectangleBtn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\rectangle.png"));
		rectangleBtn.setBackground(new Color(255, 255, 255));
		rectangleBtn.setBounds(180, 134, 120, 150);
		rectangleBtn.setFocusPainted(false);
		rectangleBtn.setOpaque(false);
		rectangleBtn.setBorderPainted(false);
		rectangleBtn.setContentAreaFilled(false);
		bodyPanel.add(rectangleBtn);
		
		final JCheckBox stampCheck = new JCheckBox("스탬프 찍기");
		stampCheck.setForeground(new Color(0, 0, 0));
		stampCheck.setHorizontalAlignment(SwingConstants.CENTER);
		stampCheck.setBackground(new Color(255, 151, 153));
		stampCheck.setFont(new Font("아임크리수진", Font.PLAIN, 30));
		stampCheck.setBounds(35, 302, 265, 52);
		bodyPanel.add(stampCheck);
		
		JButton orderBtn = new JButton("주문하기");
		orderBtn.setBackground(new Color(128, 128, 255));
		orderBtn.setFont(new Font("아임크리수진", Font.PLAIN, 30));
		orderBtn.setBounds(325, 302, 265, 52);
		bodyPanel.add(orderBtn);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel Titlelabel = new JLabel("[ 주문정보 시스템 ]");
		Titlelabel.setFont(new Font("아임크리수진", Font.PLAIN, 40));
		titlePanel.add(Titlelabel);
		
		squareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				select_shape = "Square";
				squareBtn.setSelected(true);
				rectangleBtn.setSelected(false);
				hexagonBtn.setSelected(false);
				octagonBtn.setSelected(false);
			}
		});
		rectangleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				select_shape = "Rectangle";
				squareBtn.setSelected(false);
				rectangleBtn.setSelected(true);
				hexagonBtn.setSelected(false);
				octagonBtn.setSelected(false);
			}
		});
		hexagonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				select_shape = "Hexagon";
				squareBtn.setSelected(false);
				rectangleBtn.setSelected(false);
				hexagonBtn.setSelected(true);
				octagonBtn.setSelected(false);
			}
		});
		octagonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				select_shape = "Octagon";
				squareBtn.setSelected(false);
				rectangleBtn.setSelected(false);
				hexagonBtn.setSelected(false);
				octagonBtn.setSelected(true);
			}
		});
		
		stampCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				stamp_Check = stampCheck.isSelected();
			}
		});

		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = Nametxtfield.getText();
				phone = phoneNumtxtfield.getText();
				shape = select_shape;
				stamp =stamp_Check;
				JSS.ordergui(name, phone, select_shape, stamp);
				
				AdminGUI adgui = new AdminGUI();
				int[] cntamt = JSS.load_amount();
				for(int i = 0; i<4; i++){
					System.out.println(cntamt[i]);
				}
				adgui.rectangle_amt.setText(Integer.toString(cntamt[0])+" 개");
				adgui.square_amt.setText(Integer.toString(cntamt[1])+" 개");
				adgui.hexagon_amt.setText(Integer.toString(cntamt[2])+" 개");
				adgui.octagon_amt.setText(Integer.toString(cntamt[3])+" 개");

				Nametxtfield.setText(null);
				phoneNumtxtfield.setText(null);
				squareBtn.setSelected(false);
				rectangleBtn.setSelected(false);
				hexagonBtn.setSelected(false);
				octagonBtn.setSelected(false);
				stampCheck.setSelected(false);

			}
		});
	}
	
}
