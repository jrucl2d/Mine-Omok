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
		this.setTitle("오목 순위표");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,700);
		
		p = new JPanel();
		p.setLayout(null);
		word = new JTextField("순위표");
		f = new Font("San Serif", Font.BOLD, 30);
		f2 = new Font("San Serif", Font.PLAIN, 20);
		sc = new JTextArea();
		exit = new JButton("닫기");
		
		word.setFont(f);
		word.setEditable(false);
		word.setHorizontalAlignment(JTextField.CENTER); // 텍스트 가운데정렬
		
		word.setBounds(130, 10, 120, 40);
		sc.setBounds(10,60,360,500);
		exit.setBounds(130,610,120,40);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // 창 닫음
			}
		
		});
		
		try {
			GameRun.readOmok(); // 오목 순위 정보를 불러오고
		} catch (IOException e) {
			// TODO Auto-generated catch block, IOException 잡기 위한 try-catch문
			e.printStackTrace();
		} 
		String line = ""; // line에 순위를 순서대로 모두 저장
		for(int i = 0; i < Gamer.index; i++) {
			line += (i+1)+"순위 (이긴 수, 이름) : " + Gamer.omokScore[i] +"\n";
		}
		Gamer.omokScore = null; Gamer.index = 0; // 변수 초기화
		sc.setText(line); // 텍스트공간에 line 출력
		sc.setFont(f2);
		sc.setBackground(null);
		
		p.add(word);
		p.add(sc);
		p.add(exit);
		this.add(p);
		this.setVisible(true);
	}
}
