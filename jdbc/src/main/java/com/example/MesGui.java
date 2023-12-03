package com.example;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MesGui {
	private JFrame frame;
	private JTable processingOrder_TB;
	
	public JavaSocketServer JSS = new JavaSocketServer();

	public boolean process_1_flag = false;

	public void receiveMessagesFromUnity() {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("\n√  동기화 서버가 시작되었습니다");

			while (true) {
				Socket socket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String message = in.readLine();
				System.out.println("\n▣  동기화 데이터 : " + message);

				if (message.equals("RobotStart")) {
					process_1_flag = true;
				} else if (message.equals("RobotEnd")) {
					process_1_flag = false;
				} else if (message.equals("ObjectCheck")) {
					JavaSocketServer.CameraCheck = true;
				}

				socket.close();
			}
		} catch (Exception e) {
			System.out.println("\n⊙  동기화 서버와의 연결에 문제가 생겼습니다 - ERRCODE : " + e);

		}
	}

	/**
	 * Launch the application.
	 */
	public void Mes_gui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MesGui window = new MesGui();
					window.frame.setVisible(false);
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
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocation(1000, 480);

		JLabel processingOrder_LB = new JLabel("진행중인 주문 : ");
		processingOrder_LB.setHorizontalAlignment(SwingConstants.CENTER);
		processingOrder_LB.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processingOrder_LB.setBounds(12, 38, 160, 67);
		frame.getContentPane().add(processingOrder_LB);

		processingOrder_TB = new JTable();
		processingOrder_TB.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		processingOrder_TB.setModel(new DefaultTableModel(
				new Object[][] {
						{ null, null, null, null, null },
				},
				new String[] {
						"ID", "NAME", "PHONE", "MATERIAL", "DATE"
				}));
		processingOrder_TB.setFont(new Font("아임크리수진", Font.PLAIN, 15));
		processingOrder_TB.setBounds(171, 38, 701, 67);
		frame.getContentPane().add(processingOrder_TB);

		JPanel process_1 = new JPanel();
		process_1.setForeground(Color.BLACK);
		process_1.setToolTipText("");
		process_1.setBackground(Color.GRAY);
		process_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
		process_1.setBounds(12, 150, 160, 167);
		frame.getContentPane().add(process_1);
		process_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("자재 이동");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 69, 136, 35);
		process_1.add(lblNewLabel);

		JPanel process_2 = new JPanel();
		process_2.setForeground(Color.BLACK);
		process_2.setToolTipText("");
		process_2.setBackground(Color.RED);
		process_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
		process_2.setBounds(242, 150, 160, 167);
		frame.getContentPane().add(process_2);
		process_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("스탬프");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(12, 69, 136, 35);
		process_2.add(lblNewLabel_1);

		JPanel process_3 = new JPanel();
		process_3.setForeground(Color.BLACK);
		process_3.setToolTipText("");
		process_3.setBackground(Color.RED);
		process_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
		process_3.setBounds(483, 150, 160, 167);
		frame.getContentPane().add(process_3);
		process_3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("상품 검사");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(12, 70, 136, 35);
		process_3.add(lblNewLabel_2);

		JPanel process_4 = new JPanel();
		process_4.setForeground(Color.BLACK);
		process_4.setToolTipText("");
		process_4.setBackground(Color.RED);
		process_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK));
		process_4.setBounds(712, 150, 160, 167);
		frame.getContentPane().add(process_4);
		process_4.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("출하중");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(12, 69, 136, 35);
		process_4.add(lblNewLabel_3);

		JLabel allow = new JLabel("");
		allow.setIcon(new ImageIcon(
				"C:\\Users\\User\\Desktop\\Personal\\Programing\\FusionProject\\JAVA\\vscode\\jdbc\\img\\allowimg.png"));
		allow.setBounds(184, 214, 45, 45);
		frame.getContentPane().add(allow);

		JLabel allow_1 = new JLabel("");
		allow_1.setIcon(new ImageIcon(
				"C:\\Users\\User\\Desktop\\Personal\\Programing\\FusionProject\\JAVA\\vscode\\jdbc\\img\\allowimg.png"));
		allow_1.setBounds(420, 214, 45, 45);
		frame.getContentPane().add(allow_1);

		JLabel allow_2 = new JLabel("");
		allow_2.setIcon(new ImageIcon(
				"C:\\Users\\User\\Desktop\\Personal\\Programing\\FusionProject\\JAVA\\vscode\\jdbc\\img\\allowimg.png"));
		allow_2.setBounds(655, 214, 45, 45);
		frame.getContentPane().add(allow_2);

		JLabel processing_time_1 = new JLabel("0 초");
		processing_time_1.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processing_time_1.setHorizontalAlignment(SwingConstants.CENTER);
		processing_time_1.setBounds(12, 327, 160, 36);
		frame.getContentPane().add(processing_time_1);

		JLabel processing_time_2 = new JLabel("0 초");
		processing_time_2.setHorizontalAlignment(SwingConstants.CENTER);
		processing_time_2.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processing_time_2.setBounds(243, 327, 160, 36);
		frame.getContentPane().add(processing_time_2);

		JLabel processing_time_3 = new JLabel("0 초");
		processing_time_3.setHorizontalAlignment(SwingConstants.CENTER);
		processing_time_3.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processing_time_3.setBounds(484, 327, 160, 36);
		frame.getContentPane().add(processing_time_3);

		JLabel processing_time_4 = new JLabel("0 초");
		processing_time_4.setHorizontalAlignment(SwingConstants.CENTER);
		processing_time_4.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		processing_time_4.setBounds(712, 327, 160, 36);
		frame.getContentPane().add(processing_time_4);

		JLabel allTime_LB = new JLabel("총 걸린 시간  : ");
		allTime_LB.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		allTime_LB.setHorizontalAlignment(SwingConstants.CENTER);
		allTime_LB.setBounds(503, 406, 160, 45);
		frame.getContentPane().add(allTime_LB);

		JLabel allTime_LB_1 = new JLabel("0 초");
		allTime_LB_1.setHorizontalAlignment(SwingConstants.CENTER);
		allTime_LB_1.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		allTime_LB_1.setBounds(655, 406, 160, 45);
		frame.getContentPane().add(allTime_LB_1);

		JLabel allTime_LB_2 = new JLabel("총 이동 시간  : ");
		allTime_LB_2.setHorizontalAlignment(SwingConstants.CENTER);
		allTime_LB_2.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		allTime_LB_2.setBounds(52, 406, 160, 45);
		frame.getContentPane().add(allTime_LB_2);

		JLabel allTime_LB_1_1 = new JLabel("0 초");
		allTime_LB_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		allTime_LB_1_1.setFont(new Font("아임크리수진", Font.PLAIN, 20));
		allTime_LB_1_1.setBounds(204, 406, 160, 45);
		frame.getContentPane().add(allTime_LB_1_1);

	}
}
