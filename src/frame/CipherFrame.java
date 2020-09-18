package frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import algorithm.AES256;
import algorithm.SHA256;

public class CipherFrame {
	JFrame f = new JFrame();
	JPanel basePanel = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();

	JTextArea ta = new JTextArea();
	JTextArea ta2 = new JTextArea();
	JTextArea ta3 = new JTextArea();
	JScrollPane sp = new JScrollPane(ta);
	JScrollPane sp2 = new JScrollPane(ta2);
	JScrollPane sp3 = new JScrollPane(ta3,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	JTextField tf = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();

	JLabel l1 = new JLabel("내용입력");
	JLabel l2 = new JLabel("암호입력 : ");
	JLabel l3 = new JLabel("해쉬입력");
	JLabel l4 = new JLabel("암호입력");
	JLabel l5 = new JLabel("해쉬값");

	JButton inputB = new JButton("입력창");
	JButton outputB = new JButton("출력창");
	JButton storeB = new JButton("저장");
	JButton deCodeB = new JButton("입력");

	CardLayout card = new CardLayout();


	AES256 aes256 ;
	SHA256 sha256 = new SHA256();
	int fWidth=600;
	int fHeight=850;
	public CipherFrame() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLayout(null);
		f.setBounds(100 , 100 , fWidth , fHeight);

		setTopPanel();

		basePanel.setLayout(card);
		basePanel.setBounds(0, topPanel.getHeight(), fWidth, fHeight-100);

		setInputPanel();
		setOutputPanel();

		f.add(topPanel);
		f.add(basePanel);

		basePanel.add("input",inputPanel);
		basePanel.add("output",outputPanel);

	}
	public static void main(String[] args) {
		CipherFrame cf = new CipherFrame();


	}
	public void setTopPanel() {
		topPanel.setBounds(0, 0, fWidth, fHeight-800);

		inputB.setSize(150, 80);
		inputB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.setText("");
				ta3.setText("");
				tf.setText("");
				card.show(basePanel, "input");
			}
		});

		outputB.setSize(150, 80);
		outputB.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				ta2.setText("");
				tf2.setText("");
				tf3.setText("");
				card.show(basePanel, "output");
			}
		});


		topPanel.add(inputB);
		topPanel.add(outputB);
	}
	public void setInputPanel() {
		inputPanel.setLayout(null);

		l1.setBounds(20, 0, 70, 30);

		ta.setLineWrap(true);

		sp.setBounds(20, 30, 540, 450);


		l2.setBounds(20, 520, 80, 30);

		ta3.setEditable(false);
		tf.setBounds(90, 520, 470, 30);

		storeB.setBounds(100, 580, 380, 60);
		storeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String key =sha256.SHA_Encode(tf.getText()).substring(0,32);
					aes256 = new AES256(key);
					ta3.setText(aes256.AES_Encode(ta.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		l5.setBounds(20, 650, 70, 30);

		sp3.setBounds(20, 680, 540, 40);

		inputPanel.add(l1);
		inputPanel.add(sp);
		inputPanel.add(l2);
		inputPanel.add(tf);
		inputPanel.add(storeB);
		inputPanel.add(l5);
		inputPanel.add(sp3);
	}
	public void setOutputPanel() {
		outputPanel.setLayout(null);

		l3.setBounds(20, 0, 70, 30);

		tf2.setBounds(20, 30, 540, 30);

		l4.setBounds(20, 70, 70, 30);

		tf3.setBounds(20, 100, 540, 30);

		deCodeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String key = sha256.SHA_Encode(tf3.getText()).substring(0,32);
					aes256 = new AES256(key);

					ta2.setText(aes256.AES_Decode (tf2.getText()));
				} catch (Exception e1) {
					newFrame();
				}

			}
		});
		deCodeB.setBounds(20, 150, 540, 50);

		sp2.setBounds(20, 220, 540, 500);

		outputPanel.add(l3);
		outputPanel.add(tf2);
		outputPanel.add(l4);
		outputPanel.add(tf3);
		outputPanel.add(deCodeB);
		outputPanel.add(sp2);

	}
	public void newFrame() {
		JFrame nf = new JFrame();
		JPanel np = new JPanel();
		JLabel nl = new JLabel("해쉬값 혹은 암호가 틀렸습니다.");
		JButton nb = new JButton("확인");


		nf.setVisible(true);
		nf.setBounds(200 , 500 , 400 , 80);

		nb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nf.dispose(); 

			}
		}); 

		nf.add(np);
		np.add(nl);
		np.add(nb);
	}
}
