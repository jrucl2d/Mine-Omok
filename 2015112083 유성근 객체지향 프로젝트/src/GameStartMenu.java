import javax.swing.*; // ù ��° ������
import java.awt.*;
import java.awt.event.*;

public class GameStartMenu extends JFrame implements ActionListener { // ���� ���� â
	
	public JPanel p; // �г�
	JButton omok;
	JButton mine;
	JButton omokScore;
	JButton mineScore;
	JButton exitGame;
	ImageIcon omokImg, mineImg; // ���� ����, ����ã�� �׸��� ���� �̹���
	JLabel omoklbl, minelbl; // ���� ����, ����ã�� �׸��� ���� ��
	
	GameStartMenu(){
		// ������Ʈ ����
		p = new JPanel();
		omok = new JButton("���� ����");
		mine = new JButton("���� ã�� ����");
		omokScore = new JButton("���� ����");
		mineScore = new JButton("���� ã�� ����");
		exitGame = new JButton("����");
		omoklbl = new JLabel(); 
		omokImg = new ImageIcon("omok.jpg");
		minelbl = new JLabel();
		mineImg = new ImageIcon("mine.jpg");
		
		// ������ ����
		this.setTitle("����� ����ã�� ����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.add(p);
		p.setLayout(null); // ���� ��ġ�� ����
		p.setBackground(Color.gray);
		
		// ��ư�� ��ġ, ũ��, ���� ����
		omok.setBounds(10, 20, 140, 30);
		omok.setBackground(Color.yellow);
		omokScore.setBounds(10, 60, 140, 30);
		omokScore.setBackground(Color.yellow);
		mine.setBounds(10, 100, 140, 30);
		mine.setBackground(Color.green);
		mineScore.setBounds(10, 140, 140, 30);
		mineScore.setBackground(Color.green);
		exitGame.setBounds(10, 300, 140, 30);
		omoklbl.setBounds(160,50,280,280);
		omoklbl.setIcon(omokImg);
		minelbl.setBounds(160,50,280,280);
		minelbl.setIcon(mineImg);
		omoklbl.setVisible(false); // ���ʿ� ���� �̹��� �� ����
		minelbl.setVisible(false);
		
		// �гο� ������Ʈ ����
		p.add(omok);
		p.add(mine);
		p.add(omokScore);
		p.add(mineScore);
		p.add(exitGame);
		p.add(omoklbl);
		p.add(minelbl);
		
		// ��ư �̺�Ʈ 
		omok.addActionListener(this);
		omokScore.addActionListener(this);
		mine.addActionListener(this);
		mineScore.addActionListener(this);
		exitGame.addActionListener(this);
		omok.addMouseListener(new MouseAdapter() { // ���콺 �̺�Ʈ�� ���� Ŭ������ �ۼ�(���콺 ����ͷ� �ʿ��� �޼ҵ常 ���)
			public void mouseEntered(MouseEvent e) { // ���콺�� ��ư�� ���� ���� �̹��� ����
				omoklbl.setVisible(true);
			}
			public void mouseExited(MouseEvent e) { // ���콺�� ��ư���� ������ ���� �̹��� �����
				omoklbl.setVisible(false);
			}
		});
		mine.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				minelbl.setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				minelbl.setVisible(false);
			}
		});
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitGame) { // ���� ��ư ���� �� ���� ����
			System.exit(0);
		}
		else if(e.getSource() == omok) { // ���� ��ư ������ ���� ���� ����
			Omok g1 = new Omok(); // ���� ���� ����
			this.dispose(); // ������ â�� ����
		}
		else if(e.getSource() == mine) { // ���� ��ư ������ ���� ���� ����
			Mine g2 = new Mine(); // ���� ���� ����
			this.dispose(); // ������ â�� ����
		}
		else if(e.getSource() == omokScore) { // ���� ������ ������ �ϸ�
			OmokScore s1 = new OmokScore();
		}
		else if(e.getSource() == mineScore) { // ���� ������ ������ �ϸ�
			MineScore s2 = new MineScore();
		}
	}

}
