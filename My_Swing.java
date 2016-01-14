package zad1lab4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.TextArea;
import java.awt.Color;
import java.awt.Choice;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class My_Swing {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					My_Swing window = new My_Swing();
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
	Queue<Message> queue = new Queue<Message>();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtYourMessages;
	private JTextField txtWriteId;
	private JTextField txtWriteMessage;
	private JTextField txtSelectPriority;
	private JButton btnWriteCurrentQueue;

	public My_Swing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 616, 429);
		frame.getContentPane().setLayout(null);

		textField_1 = new JTextField();
		textField_1.setBounds(0, 21, 600, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(0, 69, 600, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		Choice choice = new Choice();
		choice.setBackground(Color.ORANGE);
		choice.setBounds(0, 117, 600, 20);
		frame.getContentPane().add(choice);
		choice.add("LOW");
		choice.add("NORMAL");
		choice.add("URGENT");

		JButton btnSendingFifo = new JButton("Sending Fifo");
		btnSendingFifo.setBackground(new Color(204, 153, 153));
		btnSendingFifo.setBounds(0, 143, 171, 79);
		btnSendingFifo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				queue.enqueueFifo(new Message(Integer.parseInt(textField_1
						.getText()), textField_2.getText(), Priority
						.valueOf(choice.getSelectedItem())));

			}

		});
		frame.getContentPane().add(btnSendingFifo);

		JButton btnSendingLifo = new JButton("Sending Lifo");
		btnSendingLifo.setBackground(new Color(153, 153, 204));
		btnSendingLifo.setBounds(429, 143, 171, 79);
		btnSendingLifo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				queue.enqueueLifo(new Message(Integer.parseInt(textField_1
						.getText()), textField_2.getText(), Priority
						.valueOf(choice.getSelectedItem())));

			}
		});
		frame.getContentPane().add(btnSendingLifo);

		TextArea textArea = new TextArea();
		textArea.setBounds(0, 308, 600, 73);
		frame.getContentPane().add(textArea);

		JButton btnReceiving = new JButton("Receiving");
		btnReceiving.setBackground(Color.YELLOW);
		btnReceiving.setBounds(221, 139, 154, 86);
		btnReceiving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (queue.hasItems()) {
					Message mes = queue.desqueue();
					textArea.append(mes.id + " " + mes.text + " "
							+ mes.priority + "\n");
				}
			}
		});
		frame.getContentPane().add(btnReceiving);

		txtYourMessages = new JTextField();
		txtYourMessages.setBackground(Color.PINK);
		txtYourMessages.setEditable(false);
		txtYourMessages.setText("Your Messages:");
		txtYourMessages.setBounds(33, 283, 154, 20);
		frame.getContentPane().add(txtYourMessages);
		txtYourMessages.setColumns(10);

		txtWriteId = new JTextField();
		txtWriteId.setBackground(new Color(255, 255, 204));
		txtWriteId.setText("Write id");
		txtWriteId.setBounds(0, 0, 86, 20);
		frame.getContentPane().add(txtWriteId);
		txtWriteId.setColumns(10);

		txtWriteMessage = new JTextField();
		txtWriteMessage.setBackground(new Color(255, 255, 153));
		txtWriteMessage.setText("Write message");
		txtWriteMessage.setBounds(0, 42, 86, 27);
		frame.getContentPane().add(txtWriteMessage);
		txtWriteMessage.setColumns(10);

		txtSelectPriority = new JTextField();
		txtSelectPriority.setBackground(new Color(255, 255, 0));
		txtSelectPriority.setText("Select priority");
		txtSelectPriority.setBounds(0, 100, 86, 20);
		frame.getContentPane().add(txtSelectPriority);
		txtSelectPriority.setColumns(10);

		btnWriteCurrentQueue = new JButton(
				"Write current queue to file before receiving");
		btnWriteCurrentQueue.setBackground(new Color(204, 255, 0));
		btnWriteCurrentQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PrintWriter save = new PrintWriter("yourQueue.txt");
					save.println("Your queue " + queue.list);
					save.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnWriteCurrentQueue.setBounds(296, 246, 294, 50);
		frame.getContentPane().add(btnWriteCurrentQueue);

	}
}
