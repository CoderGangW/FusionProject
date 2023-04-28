package com.example;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;

public class AdminGUI {

	private JFrame admLogin;
	private JTextField admnum_Field;
	private JPasswordField pass_Field;
	private JTable order_list;
	private JTable selected_order;
	private JScrollPane scrolledTable;
	private ArrayList<String> orderIds = new ArrayList<>();
	

	JavaSocketServer JSS = new JavaSocketServer();
	private JTextField rectangle_ps_amt;
	private JTextField square_ps_amt;
	private JTextField hexagon_ps_amt;
	private JTextField octagon_ps_amt;

	public JLabel rectangle_amt = new JLabel("");
	public JLabel square_amt = new JLabel("");
	public JLabel hexagon_amt = new JLabel("");
	public JLabel octagon_amt = new JLabel("");

	public void refresh() {
		int[] cntamt = JSS.load_amount();
		rectangle_amt.setText(Integer.toString(cntamt[0])+" 개");
		square_amt.setText(Integer.toString(cntamt[1])+" 개");
		hexagon_amt.setText(Integer.toString(cntamt[2])+" 개");
		octagon_amt.setText(Integer.toString(cntamt[3])+" 개");
	}

	/**
	 * Launch the application.
	 */
	public void admin_page() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI window = new AdminGUI();
					window.admLogin.setVisible(true);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminGUI() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		admLogin = new JFrame("관리자 접근 시스템");
		admLogin.setBounds(100, 100, 900, 600);
		admLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admLogin.getContentPane().setLayout(null);
		
		final JPanel mat_panel = new JPanel();
		mat_panel.setBackground(new Color(255, 255, 255));
		mat_panel.setBounds(0, 0, 884, 561);
		admLogin.getContentPane().add(mat_panel);
		mat_panel.setVisible(false);
		mat_panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("님이 관리중입니다");
		lblNewLabel_1.setBounds(733, 24, 138, 31);
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		mat_panel.add(lblNewLabel_1);
		
		final JLabel adminName_Label_1 = new JLabel("");
		adminName_Label_1.setBounds(625, 24, 102, 31);
		adminName_Label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		adminName_Label_1.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		mat_panel.add(adminName_Label_1);
		
		JButton OrderControl_1 = new JButton("주문 관리");
		OrderControl_1.setBounds(12, 13, 160, 40);
		OrderControl_1.setFont(new Font("아임크리수진", Font.PLAIN, 25));
		mat_panel.add(OrderControl_1);
		
		JButton MaterialControl_1 = new JButton("자재 관리");
		MaterialControl_1.setBounds(175, 13, 160, 40);
		MaterialControl_1.setFont(new Font("아임크리수진", Font.PLAIN, 25));
		mat_panel.add(MaterialControl_1);
		
		JLabel squareimg = new JLabel("");
		squareimg.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\square.png"));
		squareimg.setBounds(160, 130, 120, 150);
		squareimg.setOpaque(false);
		mat_panel.add(squareimg);
		
		JLabel rectangleimg = new JLabel("");
		rectangleimg.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\rectangle.png"));
		rectangleimg.setOpaque(false);
		rectangleimg.setBounds(301, 131, 120, 150);
		mat_panel.add(rectangleimg);
		
		JLabel hexagonimg = new JLabel("");
		hexagonimg.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\hexagon.png"));
		hexagonimg.setOpaque(false);
		hexagonimg.setBounds(440, 130, 120, 150);
		mat_panel.add(hexagonimg);
		
		JLabel octagonimg = new JLabel("");
		octagonimg.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\octagon.png"));
		octagonimg.setOpaque(false);
		octagonimg.setBounds(580, 130, 120, 150);
		mat_panel.add(octagonimg);
		
		JLabel currentamt_label = new JLabel("현재 수량");
		currentamt_label.setHorizontalAlignment(SwingConstants.CENTER);
		currentamt_label.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		currentamt_label.setBounds(52, 300, 102, 50);
		mat_panel.add(currentamt_label);
		
		final JLabel rectangle_amt = new JLabel("");
		rectangle_amt.setHorizontalAlignment(SwingConstants.CENTER);
		rectangle_amt.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		rectangle_amt.setBounds(321, 300, 80, 50);
		mat_panel.add(rectangle_amt);
		
		final JLabel square_amt = new JLabel("");
		square_amt.setHorizontalAlignment(SwingConstants.CENTER);
		square_amt.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		square_amt.setBounds(180, 300, 80, 50);
		mat_panel.add(square_amt);
		
		final JLabel hexagon_amt = new JLabel("");
		hexagon_amt.setHorizontalAlignment(SwingConstants.CENTER);
		hexagon_amt.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		hexagon_amt.setBounds(460, 300, 80, 50);
		mat_panel.add(hexagon_amt);
		
		final JLabel octagon_amt = new JLabel("");
		octagon_amt.setHorizontalAlignment(SwingConstants.CENTER);
		octagon_amt.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		octagon_amt.setBounds(600, 300, 80, 50);
		mat_panel.add(octagon_amt);
		
		JLabel currentamt_label_1 = new JLabel("추가 수량");
		currentamt_label_1.setHorizontalAlignment(SwingConstants.CENTER);
		currentamt_label_1.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		currentamt_label_1.setBounds(52, 380, 102, 50);
		mat_panel.add(currentamt_label_1);
		
		rectangle_ps_amt = new JTextField();
		rectangle_ps_amt.setHorizontalAlignment(SwingConstants.CENTER);
		rectangle_ps_amt.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		rectangle_ps_amt.setBounds(320, 376, 80, 54);
		mat_panel.add(rectangle_ps_amt);
		rectangle_ps_amt.setColumns(10);
		
		JButton rec_up_Btn = new JButton("");
		rec_up_Btn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\up.png"));
		rec_up_Btn.setBounds(400, 376, 21, 54);
		rec_up_Btn.setFocusPainted(false);
		rec_up_Btn.setOpaque(false);
		rec_up_Btn.setBorderPainted(false);
		rec_up_Btn.setContentAreaFilled(false);
		mat_panel.add(rec_up_Btn);
		
		JButton rec_down_Btn = new JButton("");
		rec_down_Btn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\down.png"));
		rec_down_Btn.setBounds(297, 376, 21, 54);
		rec_down_Btn.setFocusPainted(false);
		rec_down_Btn.setOpaque(false);
		rec_down_Btn.setBorderPainted(false);
		rec_down_Btn.setContentAreaFilled(false);
		mat_panel.add(rec_down_Btn);
		
		JButton sqa_up_Btn = new JButton("");
		sqa_up_Btn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\up.png"));
		sqa_up_Btn.setBounds(261, 376, 21, 54);
		sqa_up_Btn.setFocusPainted(false);
		sqa_up_Btn.setOpaque(false);
		sqa_up_Btn.setBorderPainted(false);
		sqa_up_Btn.setContentAreaFilled(false);
		mat_panel.add(sqa_up_Btn);
		
		square_ps_amt = new JTextField();
		square_ps_amt.setHorizontalAlignment(SwingConstants.CENTER);
		square_ps_amt.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		square_ps_amt.setColumns(10);
		square_ps_amt.setBounds(181, 376, 80, 54);
		mat_panel.add(square_ps_amt);
		
		JButton sqa_down_Btn = new JButton("");
		sqa_down_Btn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\down.png"));
		sqa_down_Btn.setBounds(158, 376, 21, 54);
		sqa_down_Btn.setFocusPainted(false);
		sqa_down_Btn.setOpaque(false);
		sqa_down_Btn.setBorderPainted(false);
		sqa_down_Btn.setContentAreaFilled(false);
		mat_panel.add(sqa_down_Btn);
		
		JButton hex_up_Btn = new JButton("");
		hex_up_Btn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\up.png"));
		hex_up_Btn.setBounds(540, 376, 21, 54);
		hex_up_Btn.setFocusPainted(false);
		hex_up_Btn.setOpaque(false);
		hex_up_Btn.setBorderPainted(false);
		hex_up_Btn.setContentAreaFilled(false);
		mat_panel.add(hex_up_Btn);
		
		hexagon_ps_amt = new JTextField();
		hexagon_ps_amt.setHorizontalAlignment(SwingConstants.CENTER);
		hexagon_ps_amt.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		hexagon_ps_amt.setColumns(10);
		hexagon_ps_amt.setBounds(460, 376, 80, 54);
		mat_panel.add(hexagon_ps_amt);
		
		JButton hex_down_Btn = new JButton("");
		hex_down_Btn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\down.png"));
		hex_down_Btn.setBounds(437, 376, 21, 54);
		hex_down_Btn.setFocusPainted(false);
		hex_down_Btn.setOpaque(false);
		hex_down_Btn.setBorderPainted(false);
		hex_down_Btn.setContentAreaFilled(false);
		mat_panel.add(hex_down_Btn);
		
		JButton oct_up_Btn = new JButton("");
		oct_up_Btn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\up.png"));
		oct_up_Btn.setBounds(680, 376, 21, 54);
		oct_up_Btn.setFocusPainted(false);
		oct_up_Btn.setOpaque(false);
		oct_up_Btn.setBorderPainted(false);
		oct_up_Btn.setContentAreaFilled(false);
		mat_panel.add(oct_up_Btn);
		
		octagon_ps_amt = new JTextField();
		octagon_ps_amt.setHorizontalAlignment(SwingConstants.CENTER);
		octagon_ps_amt.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		octagon_ps_amt.setColumns(10);
		octagon_ps_amt.setBounds(600, 376, 80, 54);
		mat_panel.add(octagon_ps_amt);
		
		JButton oct_down_Btn = new JButton("");
		oct_down_Btn.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\btnImage\\down.png"));
		oct_down_Btn.setBounds(577, 376, 21, 54);
		oct_down_Btn.setFocusPainted(false);
		oct_down_Btn.setOpaque(false);
		oct_down_Btn.setBorderPainted(false);
		oct_down_Btn.setContentAreaFilled(false);
		mat_panel.add(oct_down_Btn);
		
		JButton Save_amt = new JButton("저장");
		Save_amt.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		Save_amt.setBounds(720, 300, 90, 130);
		mat_panel.add(Save_amt);
		
	// control page
	
		final JPanel cnt_panel = new JPanel();
		cnt_panel.setBackground(new Color(255, 255, 255));
		cnt_panel.setBounds(0, 0, 884, 561);
		admLogin.getContentPane().add(cnt_panel);
		cnt_panel.setLayout(null);
		cnt_panel.setVisible(false);
		
		// 선택 불가능한 행과 열 인덱스
		selected_order = new JTable();
		selected_order.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		selected_order.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selected_order.setFont(new Font("아임크리수진", Font.PLAIN, 13));
		selected_order.setModel(new DefaultTableModel(
				new Object[][] {
					{" 주문번호", null},
					{" 이름", null},
					{" 전화번호", null},
					{" 스타일", null},
					{" 커스텀여부", null},
					{" 날짜", null},
				},
				new String[] {
					"Info", "ORDER_INFO"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, String.class
				};
			});
		selected_order.getColumnModel().getColumn(0).setPreferredWidth(83);
		selected_order.getColumnModel().getColumn(0).setMinWidth(30);
		selected_order.getColumnModel().getColumn(1).setPreferredWidth(244);
		selected_order.getColumnModel().getColumn(1).setMinWidth(30);
		selected_order.setBounds(577, 65, 294, 180);
		selected_order.setRowHeight(30);
		cnt_panel.add(selected_order);

		
		JLabel lblNewLabel = new JLabel("님이 관리중입니다");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		lblNewLabel.setBounds(733, 24, 138, 31);
		cnt_panel.add(lblNewLabel);
		
		JButton startBtn = new JButton("공정 시작");
		startBtn.setFont(new Font("아임크리수진", Font.PLAIN, 25));
		startBtn.setBounds(577, 265, 294, 85);
		cnt_panel.add(startBtn);
		
		JButton editBtn = new JButton("주문 수정");
		editBtn.setFont(new Font("아임크리수진", Font.PLAIN, 25));
		editBtn.setBounds(577, 360, 294, 85);
		cnt_panel.add(editBtn);
		
		JButton cancelBtn = new JButton("주문 삭제");
		cancelBtn.setFont(new Font("아임크리수진", Font.PLAIN, 25));
		cancelBtn.setBounds(577, 455, 294, 85);
		cnt_panel.add(cancelBtn);
		
		final JLabel adminName_Label = new JLabel("강윤원");
		adminName_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		adminName_Label.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		adminName_Label.setBounds(625, 24, 102, 31);
		cnt_panel.add(adminName_Label);
		
		JButton panelRpaintBtn = new JButton("새로고침");
		panelRpaintBtn.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		panelRpaintBtn.setBounds(446, 24, 120, 31);
		cnt_panel.add(panelRpaintBtn);

		//PANEL REPAINTER
		panelRpaintBtn.addActionListener(new ActionListener() {
			int [] columnwidth = {38,57,84,90,69,150};
    		public void actionPerformed(ActionEvent e) {
				String[][] list_data = JSS.OrderTable();

				// 테이블 모델에 데이터 적용
				DefaultTableModel model = (DefaultTableModel) order_list.getModel();
				model.setDataVector(list_data, new String[] {"ID", "NAME", "PHONE", "MATERIAL", "CUSTOM", "DATE"});

				TableColumnModel columnModel = order_list.getColumnModel();
				for(int i =0; i<6; i++){
					columnModel.getColumn(i).setPreferredWidth(columnwidth[i]);
				}

				// 테이블 리프레시
				order_list.repaint();

				// orderIds 초기화
				orderIds.clear();
				// 새로운 orderIds 생성
				for (String[] row : list_data) {
					String orderId = row[0];
					if (!orderIds.contains(orderId)) {
					orderIds.add(orderId);
            		}
        		}
    		}
		});

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 65, 554, 475);
		cnt_panel.add(scrollPane);
		
				order_list = new JTable();
				scrollPane.setViewportView(order_list);
				order_list.setSurrendersFocusOnKeystroke(true);
				order_list.setShowVerticalLines(false);
				order_list.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				order_list.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
				order_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final String[][] list_data = JSS.OrderTable();
				
				order_list.setModel(new DefaultTableModel(
					list_data,
					new String[] {
						"ID", "NAME", "PHONE", "MATERIAL", "CUSTOM", "DATE"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				order_list.getColumnModel().getColumn(0).setResizable(false);
				order_list.getColumnModel().getColumn(0).setPreferredWidth(38);
				order_list.getColumnModel().getColumn(1).setPreferredWidth(57);
				order_list.getColumnModel().getColumn(2).setPreferredWidth(84);
				order_list.getColumnModel().getColumn(3).setPreferredWidth(90);
				order_list.getColumnModel().getColumn(4).setPreferredWidth(69);
				order_list.getColumnModel().getColumn(5).setPreferredWidth(150);
				order_list.setFont(new Font("아임크리수진", Font.PLAIN, 12));
				order_list.setRowHeight(30);
				
				JButton OrderControl = new JButton("주문 관리");
				OrderControl.setFont(new Font("아임크리수진", Font.PLAIN, 25));
				OrderControl.setBounds(12, 13, 160, 40);
				cnt_panel.add(OrderControl);
				
				JButton MaterialControl = new JButton("자재 관리");
				MaterialControl.setFont(new Font("아임크리수진", Font.PLAIN, 25));
				MaterialControl.setBounds(175, 13, 160, 40);
				cnt_panel.add(MaterialControl);
				
				order_list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						int selectedRow = order_list.getSelectedRow();
						if (selectedRow < 0) return;
						int rowIndex = order_list.convertRowIndexToModel(selectedRow);
						String orderId = (String) order_list.getModel().getValueAt(rowIndex, 0);
						
						String[] rowData = JSS.getOrderById(orderId);
						
						for(int i = 0; i < rowData.length; i++) {
							selected_order.getModel().setValueAt(rowData[i], i, 1);
						}
					}
				});
				

				
				final JPanel Login_panel = new JPanel();
				Login_panel.setBackground(Color.WHITE);
				Login_panel.setBounds(0, 0, 884, 561);
				admLogin.getContentPane().add(Login_panel);
				Login_panel.setLayout(null);
				
				JLabel numLabel = new JLabel("사원 번호 :");
				numLabel.setBounds(160, 225, 182, 47);
				numLabel.setHorizontalAlignment(SwingConstants.CENTER);
				numLabel.setFont(new Font("아임크리수진", Font.PLAIN, 40));
				Login_panel.add(numLabel);
				
				JLabel pwLabel = new JLabel("비밀 번호 :");
				pwLabel.setBounds(160, 310, 182, 47);
				pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
				pwLabel.setFont(new Font("아임크리수진", Font.PLAIN, 40));
				Login_panel.add(pwLabel);
				
				admnum_Field = new JTextField();
				admnum_Field.setFont(new Font("아임크리수진", Font.PLAIN, 30));
				admnum_Field.setBounds(354, 225, 246, 47);
				admnum_Field.setColumns(10);
				Login_panel.add(admnum_Field);
				
				pass_Field = new JPasswordField();
				pass_Field.setEchoChar('*');
				pass_Field.setFont(new Font("아임크리수진", Font.PLAIN, 30));
				pass_Field.setBounds(354, 309, 246, 48);
				Login_panel.add(pass_Field);
				
				JButton LoginBtn = new JButton("로그인");
				LoginBtn.setBounds(612, 225, 113, 132);
				LoginBtn.setFont(new Font("아임크리수진", Font.PLAIN, 25));
				Login_panel.add(LoginBtn);
				
				JLabel TitleLabel = new JLabel("[  관리자 로그인  ]");
				TitleLabel.setBounds(207, 10, 500, 100);
				Login_panel.add(TitleLabel);
				TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				TitleLabel.setFont(new Font("아임크리수진", Font.PLAIN, 53));
				
				LoginBtn.registerKeyboardAction(LoginBtn.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);
				LoginBtn.registerKeyboardAction(LoginBtn.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);

				// Event code
				LoginBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						Boolean checked = JSS.loginCheck(admnum_Field.getText(), pass_Field.getPassword());
						if (checked == true){
							Login_panel.setVisible(false);
							cnt_panel.setVisible(true);
							adminName_Label.setText(JSS.logined_admin);
							adminName_Label_1.setText(JSS.logined_admin);
							
							int[] cntamt = JSS.load_amount();
							square_amt.setText(Integer.toString(cntamt[0])+" 개");
							rectangle_amt.setText(Integer.toString(cntamt[1])+" 개");
							hexagon_amt.setText(Integer.toString(cntamt[2])+" 개");
							octagon_amt.setText(Integer.toString(cntamt[3])+" 개");

							square_ps_amt.setText("0");
							rectangle_ps_amt.setText("0");
							hexagon_ps_amt.setText("0");
							octagon_ps_amt.setText("0");


						} else {
							JOptionPane.showMessageDialog(null, "시스템에 있는 관리자 정보가 다릅니다.", "경고",JOptionPane.WARNING_MESSAGE );
						}
						
					}
				});

				rec_down_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int recPs = Integer.parseInt(rectangle_ps_amt.getText());
						recPs = recPs - 1;
						rectangle_ps_amt.setText(Integer.toString(recPs));
					}
				});
				rec_up_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int recPs = Integer.parseInt(rectangle_ps_amt.getText());
						recPs = recPs + 1;
						rectangle_ps_amt.setText(Integer.toString(recPs));
					}
				});


				sqa_down_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int sqaPs = Integer.parseInt(square_ps_amt.getText());
						sqaPs = sqaPs - 1;
						square_ps_amt.setText(Integer.toString(sqaPs));
					}
				});
				sqa_up_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int sqaPs = Integer.parseInt(square_ps_amt.getText());
						sqaPs = sqaPs + 1;
						square_ps_amt.setText(Integer.toString(sqaPs));
					}
				});


				hex_down_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int hexPs = Integer.parseInt(hexagon_ps_amt.getText());
						hexPs = hexPs - 1;
						hexagon_ps_amt.setText(Integer.toString(hexPs));
					}
				});
				hex_up_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int hexPs = Integer.parseInt(hexagon_ps_amt.getText());
						hexPs = hexPs +1;
						hexagon_ps_amt.setText(Integer.toString(hexPs));
					}
				});


				oct_down_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int octPs = Integer.parseInt(octagon_ps_amt.getText());
						octPs = octPs - 1;
						octagon_ps_amt.setText(Integer.toString(octPs));
					}
				});
				oct_up_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int octPs = Integer.parseInt(octagon_ps_amt.getText());
						octPs = octPs + 1;
						octagon_ps_amt.setText(Integer.toString(octPs));
					}
				});

				
				OrderControl.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						cnt_panel.setVisible(true);
						mat_panel.setVisible(false);
					}
				});

				OrderControl_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						cnt_panel.setVisible(true);
						mat_panel.setVisible(false);
					}
				});

				MaterialControl.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						cnt_panel.setVisible(false);
						mat_panel.setVisible(true);
					}
				});

				MaterialControl_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						cnt_panel.setVisible(false);
						mat_panel.setVisible(true);
					}
				});

				Save_amt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int sqa = Integer.parseInt(square_ps_amt.getText());
						int rec = Integer.parseInt(rectangle_ps_amt.getText());
						int hex = Integer.parseInt(hexagon_ps_amt.getText());
						int oct = Integer.parseInt(octagon_ps_amt.getText());
						if(sqa == 0 && rec == 0 && hex == 0 && oct == 0){
							JOptionPane.showMessageDialog(null, "추가할 자재갯수는 모두 0개가 될 수 없습니다", "자재 추가 오류",JOptionPane.WARNING_MESSAGE );
						} else{
							int[] upAmount = {rec,sqa,hex,oct};
							JSS.update_mat_amt(upAmount);
	
							int[] cntamt = JSS.load_amount();
							square_amt.setText(Integer.toString(cntamt[0])+" 개");
							rectangle_amt.setText(Integer.toString(cntamt[1])+" 개");
							hexagon_amt.setText(Integer.toString(cntamt[2])+" 개");
							octagon_amt.setText(Integer.toString(cntamt[3])+" 개");
	
							square_ps_amt.setText("0");
							rectangle_ps_amt.setText("0");
							hexagon_ps_amt.setText("0");
							octagon_ps_amt.setText("0");
						}
					}
				});


				
				startBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						JOptionPane.showMessageDialog(null, "기능이 아직 구현되지 않았습니다", "기능 미구현", 0);
					}
				});

				editBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						if(selected_order.getValueAt(0, 1) == null){
							JOptionPane.showMessageDialog(null, "주문을 선택하지않았습니다.", "주문을 선택해 주세요", 0);
						}
						else{
							final JFrame newEditFrame = new JFrame("주문 수정");
							newEditFrame.setSize(400, 300);
							newEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							newEditFrame.setLocation(850,400);
							newEditFrame.setLayout(null);
	
							// 라벨 추가
							JLabel orderNumLabel = new JLabel("주문번호:");
							orderNumLabel.setFont(new Font("아임크리수진", Font.PLAIN, 20));
							orderNumLabel.setBounds(20, 20, 100, 20);
							newEditFrame.add(orderNumLabel);
	
							JLabel nameLabel = new JLabel("이름:");
							nameLabel.setFont(new Font("아임크리수진", Font.PLAIN, 20));
							nameLabel.setBounds(20, 50, 100, 20);
							newEditFrame.add(nameLabel);
	
							JLabel phoneNumLabel = new JLabel("전화번호:");
							phoneNumLabel.setFont(new Font("아임크리수진", Font.PLAIN, 20));
							phoneNumLabel.setBounds(20, 80, 100, 20);
							newEditFrame.add(phoneNumLabel);
	
							JLabel styleLabel = new JLabel("스타일:");
							styleLabel.setFont(new Font("아임크리수진", Font.PLAIN, 20));
							styleLabel.setBounds(20, 110, 100, 20);
							newEditFrame.add(styleLabel);
	
							JLabel customLabel = new JLabel("커스텀여부:");
							customLabel.setFont(new Font("아임크리수진", Font.PLAIN, 20));
							customLabel.setBounds(20, 140, 100, 20);
							newEditFrame.add(customLabel);
	
							JLabel dateLabel = new JLabel("날짜:");
							dateLabel.setFont(new Font("아임크리수진", Font.PLAIN, 20));
							dateLabel.setBounds(20, 170, 100, 20);
							newEditFrame.add(dateLabel);
	
							JLabel orderNumValueLabel = new JLabel(String.valueOf(selected_order.getValueAt(0, 1)));
							orderNumValueLabel.setFont(new Font("아임크리수진", Font.PLAIN, 16));
							orderNumValueLabel.setBounds(130, 20, 200, 20);
							newEditFrame.add(orderNumValueLabel);
	
							final JTextField nameTextField = new JTextField(String.valueOf(selected_order.getValueAt(1, 1)));
							nameTextField.setFont(new Font("아임크리수진", Font.PLAIN, 16));
							nameTextField.setBounds(130, 50, 200, 20);
							newEditFrame.add(nameTextField);
	
							final JTextField phoneTextField = new JTextField(String.valueOf(selected_order.getValueAt(2, 1)));
							phoneTextField.setFont(new Font("아임크리수진", Font.PLAIN, 16));
							phoneTextField.setBounds(130, 80, 200, 20);
							newEditFrame.add(phoneTextField);
	
							JLabel dateValueLabel = new JLabel(String.valueOf(selected_order.getValueAt(5, 1)));
							dateValueLabel.setFont(new Font("아임크리수진", Font.PLAIN, 15));
							dateValueLabel.setBounds(130, 170, 200, 20);
							newEditFrame.add(dateValueLabel);
	
							// 콤보박스 추가
							String[] styleList = {""," Square", " Rectangle", " Hexagon"," Octagon"};
							final JComboBox<String> styleComboBox = new JComboBox<String>(styleList);
							styleComboBox.setFont(new Font("아임크리수진", Font.PLAIN, 16));
							styleComboBox.setBounds(130, 110, 200, 20);
							newEditFrame.add(styleComboBox);
	
							String[] customList = {""," YES", " NO"};
							final JComboBox<String> customComboBox = new JComboBox<String>(customList);
							customComboBox.setFont(new Font("아임크리수진", Font.PLAIN, 16));
							customComboBox.setBounds(130, 140, 200, 20);
							newEditFrame.add(customComboBox);
	
							JButton modifyButton = new JButton("주문 수정");
							modifyButton.setFont(new Font("아임크리수진", Font.PLAIN, 20));
							modifyButton.setBounds(85, 210, 200, 30);
							newEditFrame.add(modifyButton);
	
							newEditFrame.setVisible(true);
	
							modifyButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e){
									if (nameTextField.getText().trim().equals("") ){
										JOptionPane.showMessageDialog(null, "이름을 입력해주세요", "정보 미입력", 0);
									}else{
										if(phoneTextField.getText().trim().equals("")){
											JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요", "정보 미입력", 0);
										}
										else{
											if(styleComboBox.getSelectedItem().toString()==""){
												JOptionPane.showMessageDialog(null, "스타일을 선택해주세요", "정보 미입력", 0);
											}
											else{
												if(customComboBox.getSelectedItem().toString()==""){
													JOptionPane.showMessageDialog(null, "커스텁유무 선택해주세요", "정보 미입력", 0);
												}else{
													boolean amt_check = false;
													String old_shape = null;
													int[] cntamt = JSS.load_amount();
													if (styleComboBox.getSelectedItem().toString()==" Square"){
														if(cntamt[0] == 0 ){
															amt_check = false;
														} else {
															amt_check = true;
															old_shape = "Square";
														}
													}else if (styleComboBox.getSelectedItem().toString()==" Rectangle"){
														if(cntamt[1] == 0 ){
															amt_check = false;
														} else {
															amt_check = true;
															old_shape = "Rectangle";
														}
													}else if(styleComboBox.getSelectedItem().toString()==" Hexagon"){
														if(cntamt[2] == 0 ){
															amt_check = false;
														} else {
															amt_check = true;
															old_shape = "Hexagon";
														}
													}else if(styleComboBox.getSelectedItem().toString()==" Octagon"){
														if(cntamt[3] == 0 ){
															amt_check = false;
														} else {
															amt_check = true;
															old_shape = "Octagon";
														}
													}
													if(amt_check){
														int [] columnwidth = {38,57,84,90,69,150};
														int ans = JOptionPane.showConfirmDialog(null, "정말로 변경하시겠습니까?", "주문정보 변경", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
														if (ans == 0){
															String[] targetOrder={
																String.valueOf(selected_order.getValueAt(0, 1)),
																String.valueOf(" "+nameTextField.getText().trim()),
																String.valueOf(" "+phoneTextField.getText().trim()),
																String.valueOf(styleComboBox.getSelectedItem().toString()),
																String.valueOf(customComboBox.getSelectedItem().toString())
															};
															JSS.editOrder(targetOrder);
															JSS.edtOrder_mat(String.valueOf(selected_order.getValueAt(3, 1)),styleComboBox.getSelectedItem().toString());
								
															String[][] list_data = JSS.OrderTable();
								
															// 테이블 모델에 데이터 적용
															DefaultTableModel model = (DefaultTableModel) order_list.getModel();
															model.setDataVector(list_data, new String[] {"ID", "NAME", "PHONE", "MATERIAL", "CUSTOM", "DATE"});
								
															TableColumnModel columnModel = order_list.getColumnModel();
															for(int i =0; i<6; i++){
																columnModel.getColumn(i).setPreferredWidth(columnwidth[i]);
															}
								
															// 테이블 리프레시
															order_list.repaint();
								
															// orderIds 초기화
															orderIds.clear();
															// 새로운 orderIds 생성
															for (String[] row : list_data) {
																String orderId = row[0];
																if (!orderIds.contains(orderId)) {
																orderIds.add(orderId);
																}
															}
															JOptionPane.showMessageDialog(null, "주문정보가 변경되었습니다.", "변경 완료",JOptionPane.PLAIN_MESSAGE);
															newEditFrame.dispose();
														}
													} else {
														JOptionPane.showMessageDialog(null, "선택한 스타일의 재고가 없습니다", "재고 없음", 0);
													}
												}
											}
										}
									}
								}
							});
						}
					}
				});

				cancelBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						int [] columnwidth = {38,57,84,90,69,150};
						int ans = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?", "주문정보 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (ans == 0){
							String target_id = String.valueOf(selected_order.getValueAt(0, 1));
							String setShape = String.valueOf(selected_order.getValueAt(3, 1));
							JSS.delOrder(target_id);
							JSS.rollbackOrder(setShape);

							String[][] list_data = JSS.OrderTable();

							// 테이블 모델에 데이터 적용
							DefaultTableModel model = (DefaultTableModel) order_list.getModel();
							model.setDataVector(list_data, new String[] {"ID", "NAME", "PHONE", "MATERIAL", "CUSTOM", "DATE"});

							TableColumnModel columnModel = order_list.getColumnModel();
							for(int i =0; i<6; i++){
								columnModel.getColumn(i).setPreferredWidth(columnwidth[i]);
							}

							// 테이블 리프레시
							order_list.repaint();

							// orderIds 초기화
							orderIds.clear();
							// 새로운 orderIds 생성
							for (String[] row : list_data) {
								String orderId = row[0];
								if (!orderIds.contains(orderId)) {
								orderIds.add(orderId);
								}
							}
							JOptionPane.showMessageDialog(null, "주문정보가 삭제되었습니다.", "삭제 완료", 0);
						}
					}
				});
			}
			

}
