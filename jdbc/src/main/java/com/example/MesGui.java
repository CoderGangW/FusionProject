package com.example;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MesGui {
	private JFrame frame;

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
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
