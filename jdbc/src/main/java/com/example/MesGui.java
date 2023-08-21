package com.example;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class MesGui {
	private JFrame frame;
	private JTable processingOrder_TB;

	/**
	 * Launch the application.
	 */
	public void Mes_gui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MesGui window = new MesGui();
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
	public MesGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Manufacturing Execution System");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel processingOrder_LB = new JLabel("진행중인 주문 : ");
		processingOrder_LB.setHorizontalAlignment(SwingConstants.CENTER);
		processingOrder_LB.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processingOrder_LB.setBounds(12, 38, 160, 67);
		frame.getContentPane().add(processingOrder_LB);
		
		processingOrder_TB = new JTable();
		processingOrder_TB.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		processingOrder_TB.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "NAME", "PHONE", "MATERIAL", "DATE"
			}
		));
		processingOrder_TB.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		processingOrder_TB.setBounds(171, 38, 701, 67);
		frame.getContentPane().add(processingOrder_TB);
		
		JPanel process_1 = new JPanel();
		process_1.setForeground(Color.BLACK);
		process_1.setToolTipText("");
		process_1.setBackground(Color.RED);
		process_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
		process_1.setBounds(12, 126, 160, 290);
		frame.getContentPane().add(process_1);
		process_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("자재 이동");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 128, 136, 35);
		process_1.add(lblNewLabel);
		
		JPanel process_2 = new JPanel();
		process_2.setForeground(Color.BLACK);
		process_2.setToolTipText("");
		process_2.setBackground(Color.RED);
		process_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
		process_2.setBounds(242, 126, 160, 290);
		frame.getContentPane().add(process_2);
		process_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("스탬프");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(12, 128, 136, 35);
		process_2.add(lblNewLabel_1);
		
		JPanel process_3 = new JPanel();
		process_3.setForeground(Color.BLACK);
		process_3.setToolTipText("");
		process_3.setBackground(Color.RED);
		process_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
		process_3.setBounds(483, 126, 160, 290);
		frame.getContentPane().add(process_3);
		process_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("상품 검사");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(12, 128, 136, 35);
		process_3.add(lblNewLabel_2);
		
		JPanel process_4 = new JPanel();
		process_4.setForeground(Color.BLACK);
		process_4.setToolTipText("");
		process_4.setBackground(Color.RED);
		process_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
		process_4.setBounds(712, 126, 160, 290);
		frame.getContentPane().add(process_4);
		process_4.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("출하중");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(12, 128, 136, 35);
		process_4.add(lblNewLabel_3);
		
		JLabel allow = new JLabel("");
		allow.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\img\\allowimg.png"));
		allow.setBounds(184, 250, 45, 45);
		frame.getContentPane().add(allow);
		
		JLabel allow_1 = new JLabel("");
		allow_1.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\img\\allowimg.png"));
		allow_1.setBounds(420, 250, 45, 45);
		frame.getContentPane().add(allow_1);
		
		JLabel allow_2 = new JLabel("");
		allow_2.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Personal\\Programing\\JAVA\\vscode\\jdbc\\img\\allowimg.png"));
		allow_2.setBounds(655, 250, 45, 45);
		frame.getContentPane().add(allow_2);
		
		JLabel processing_time_1 = new JLabel("0 초");
		processing_time_1.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processing_time_1.setHorizontalAlignment(SwingConstants.CENTER);
		processing_time_1.setBounds(12, 441, 160, 36);
		frame.getContentPane().add(processing_time_1);
		
		JLabel processing_time_2 = new JLabel("0 초");
		processing_time_2.setHorizontalAlignment(SwingConstants.CENTER);
		processing_time_2.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processing_time_2.setBounds(243, 441, 160, 36);
		frame.getContentPane().add(processing_time_2);
		
		JLabel processing_time_3 = new JLabel("0 초");
		processing_time_3.setHorizontalAlignment(SwingConstants.CENTER);
		processing_time_3.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processing_time_3.setBounds(484, 441, 160, 36);
		frame.getContentPane().add(processing_time_3);
		
		JLabel processing_time_4 = new JLabel("0 초");
		processing_time_4.setHorizontalAlignment(SwingConstants.CENTER);
		processing_time_4.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processing_time_4.setBounds(712, 441, 160, 36);
		frame.getContentPane().add(processing_time_4);
	}
}
