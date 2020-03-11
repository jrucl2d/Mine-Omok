import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class OmokScore extends JFrame {
		
	JPanel p;
	JTextField word;
	JTextArea sc;
	Font f, f2;
	JButton exit;
	OmokScore(){
		this.setTitle("���� ����ǥ");
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
			GameRun.readOmok(); // ���� ���� ������ �ҷ�����
		} catch (IOException e) {
			// TODO Auto-generated catch block, IOException ��� ���� try-catch��
			e.printStackTrace();
		} 
		String line = ""; // line�� ������ ������� ��� ����
		for(int i = 0; i < Gamer.index; i++) {
			line += (i+1)+"���� (�̱� ��, �̸�) : " + Gamer.omokScore[i] +"\n";
		}
		Gamer.omokScore = null; Gamer.index = 0; // ���� �ʱ�ȭ
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
