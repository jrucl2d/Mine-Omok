import javax.swing.*; // 첫 번째 윈도우
import java.awt.*;
import java.awt.event.*;

public class GameStartMenu extends JFrame implements ActionListener { // 게임 선택 창
	
	public JPanel p; // 패널
	JButton omok;
	JButton mine;
	JButton omokScore;
	JButton mineScore;
	JButton exitGame;
	ImageIcon omokImg, mineImg; // 오목 게임, 지뢰찾기 그림을 위한 이미지
	JLabel omoklbl, minelbl; // 오목 게임, 지뢰찾기 그림을 위한 라벨
	
	GameStartMenu(){
		// 컴포넌트 생성
		p = new JPanel();
		omok = new JButton("오목 게임");
		mine = new JButton("지뢰 찾기 게임");
		omokScore = new JButton("오목 점수");
		mineScore = new JButton("지뢰 찾기 점수");
		exitGame = new JButton("종료");
		omoklbl = new JLabel(); 
		omokImg = new ImageIcon("src/main/resources/omok.jpg");
		minelbl = new JLabel();
		mineImg = new ImageIcon("src/main/resources/mine.jpg");
		
		// 프레임 설정
		this.setTitle("오목과 지뢰찾기 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.add(p);
		p.setLayout(null); // 절대 위치로 설정
		p.setBackground(Color.gray);
		
		// 버튼의 위치, 크기, 색상 설정
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
		omoklbl.setVisible(false); // 최초에 게임 이미지 안 보임
		minelbl.setVisible(false);
		
		// 패널에 컴포넌트 부착
		p.add(omok);
		p.add(mine);
		p.add(omokScore);
		p.add(mineScore);
		p.add(exitGame);
		p.add(omoklbl);
		p.add(minelbl);
		
		// 버튼 이벤트 
		omok.addActionListener(this);
		omokScore.addActionListener(this);
		mine.addActionListener(this);
		mineScore.addActionListener(this);
		exitGame.addActionListener(this);
		omok.addMouseListener(new MouseAdapter() { // 마우스 이벤트를 내부 클래스로 작성(마우스 어댑터로 필요한 메소드만 사용)
			public void mouseEntered(MouseEvent e) { // 마우스가 버튼에 들어가면 게임 이미지 등장
				omoklbl.setVisible(true);
			}
			public void mouseExited(MouseEvent e) { // 마우스가 버튼에서 나가면 게임 이미지 사라짐
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
		if(e.getSource() == exitGame) { // 종료 버튼 누를 시 게임 종료
			System.exit(0);
		}
		else if(e.getSource() == omok) { // 오목 버튼 누르면 오목 게임 시작
			Omok g1 = new Omok(); // 오목 게임 실행
			this.dispose(); // 원래의 창은 종료
		}
		else if(e.getSource() == mine) { // 오목 버튼 누르면 오목 게임 시작
			Mine g2 = new Mine(); // 오목 게임 실행
			this.dispose(); // 원래의 창은 종료
		}
		else if(e.getSource() == omokScore) { // 오목 순위를 보고자 하면
			OmokScore s1 = new OmokScore();
		}
		else if(e.getSource() == mineScore) { // 오목 순위를 보고자 하면
			MineScore s2 = new MineScore();
		}
	}

}
