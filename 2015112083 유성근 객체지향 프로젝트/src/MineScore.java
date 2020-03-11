import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MineScore extends JFrame {
		
	JPanel p;
	JTextField word;
	JTextArea sc;
	Font f, f2;
	JButton exit;
	MineScore(){
		this.setTitle("����ã�� ����ǥ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,700);
		
		p = new JPanel();
		p.setLayout(null);
		word = new JTextField("����ǥ");
		f = new Font("San Serif", Font.BOLD, 30);
		f2 = new Font("San Serif", Font.PLAIN, 20);
		sc = new JTextArea();
		exit = new JButton("�ݱ�");
		
		word.setFont(f);
		word.setEditable(false);
		word.setHorizontalAlignment(JTextField.CENTER); // �ؽ�Ʈ �������
		
		word.setBounds(130, 10, 120, 40);
		sc.setBounds(10,60,360,500);
		exit.setBounds(130,610,120,40);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // â ����
			}
		
		});
		
		try {
			GameRun.readMine(); // ����ã�� ���� ������ �ҷ�����
		} catch (IOException e) {
			// TODO Auto-generated catch block, IOException ��� ���� try-catch��
			e.printStackTrace();
		} 
		
		String line = ""; // line�� ������ ������� ��� ����
		for(int i = 0; i < Gamerm.index; i++) {
			line += (i+1)+"���� : " + Gamerm.mineScore[i] +"\n";
		}
		Gamerm.mineScore = null; Gamerm.index = 0; // ���� �ʱ�ȭ
		sc.setText(line); // �ؽ�Ʈ������ line ���
		sc.setFont(f2);
		sc.setBackground(null);
		
		p.add(word);
		p.add(sc);
		p.add(exit);
		this.add(p);
		this.setVisible(true);
	}
}