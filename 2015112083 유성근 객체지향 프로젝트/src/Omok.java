import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.io.IOException; // IOException을 위해
import java.util.Random; // 랜덤을 사용하기 위해
import javax.swing.Timer; // 타이머 기능을 위해



class Gamer{ // 게이머들의 정보를 저장하는 클래스
	static String omokScore[]; // 오목 순위를 저장할 문자열 배열
	static int index; // 오목 순위 문자열 배열의 인덱스
	static String g1; // 게이머 1, 2 이름
	static String g2;
	static String winner;
	
	static int size; // 오목판 사이즈
	static boolean turn = false; // 흑 백 턴을 결정할 변수 (최초에 false, 백의 차례)
	static int sooB = 0; // 흑 백 수를 저장할 변수 처음에 0으로 초기화
	static int sooW = 0;
	static boolean bpan[][]; // 검은돌 판의 배열
	static boolean wpan[][]; // 흰돌 판의 배열
	static int winSoo; // 이긴 수
	static boolean finish = false; // 게임 끝 여부, 최초 false로 초기화
	
	static void clear() { // 게임을 초기화(static 변수들 초기화)
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				bpan[i][j] = false;
				wpan[i][j] = false;
			}
		}
		g1 = null; // 게이머 1, 2 이름
		g2 = null;
		winner = null;
		size = 0; // 오목판 사이즈
		turn = false; // 흑 백 턴을 결정할 변수 (최초에 false, 백의 차례)
		sooB = 0; // 흑 백 수를 저장할 변수 처음에 0으로 초기화
		sooW = 0;
		winSoo = 0; // 이긴 수
		finish = false; // 게임 끝 여부
	}
}

public class Omok extends JFrame implements ActionListener {
	
	JPanel inputOmok; // 입력하는 패널
	JButton exitGame;
	JButton order; // 입력한 값으로 게임 생성
	JTextField f1,f2, f3, f4, f5, f6, info;

	Omok(){
		// 컴포넌트 설정
		inputOmok = new JPanel();
		exitGame = new JButton ("게임 종료");
		f1 = new JTextField("사용자 1 이름");
		f2 = new JTextField("사용자 2 이름");
		f3 = new JTextField(); // 사용자 1 이름 입력받음
		f4 = new JTextField(); // 사용자 2 이름 입력받음
		f5 = new JTextField("바둑판 크기(20~50)");
		f6 = new JTextField();
		info = new JTextField("   ** 백이 먼저 돌을 놓습니다 **");
		order = new JButton("순서 정하기");
		
		// 프레임 설정
		this.setTitle("오목 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.add(inputOmok);
		inputOmok.setLayout(null);
		
		// 컴포넌트 위치 설정
		f1.setEditable(false);
		f2.setEditable(false);
		f5.setEditable(false);
		info.setEditable(false);
		info.setBackground(Color.red);
		info.setForeground(Color.white);
		exitGame.setBounds(120, 20, 140, 30);
		f1.setBounds(10,60, 150, 30);
		f2.setBounds(10,140, 150, 30);
		f3.setBounds(10,100, 150, 30);
		f4.setBounds(10,180, 150, 30);
		f5.setBounds(200,60, 150, 30);
		f6.setBounds(200,100, 150, 30);
		info.setBounds(100, 240, 180, 30);
		order.setBounds(120, 280, 140, 30);
		
		// 컴포넌트 부착
		inputOmok.add(exitGame);
		inputOmok.add(f1);
		inputOmok.add(f2);
		inputOmok.add(f3);
		inputOmok.add(f4);
		inputOmok.add(f5);
		inputOmok.add(f6);
		inputOmok.add(info);
		inputOmok.add(order);
		
		exitGame.addActionListener(this); // 게임 종료
		order.addActionListener(this); // 순서 정하기
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitGame) {
			GameStartMenu game = new GameStartMenu();
			this.setVisible(false);
		}
		if(e.getSource() == order) {
			Gamer.g1 = f3.getText(); // 입력받은 이름 게이머 정보에 저장
			Gamer.g2 = f4.getText();
			Gamer.size = Integer.parseInt(f6.getText()) + 2; // 판의 가장자리를 안 쓰는 것을 고려해서 잡음
			
			Order o = new Order();
			this.setVisible(false);
		}
	}
}
class Order extends JFrame implements ActionListener{ // 선공 후공 순서 정하는 클래스
	JPanel p1;
	JButton b1, b2, b3;
	JTextField f1,f2;
	ImageIcon foreim, backim;
	JLabel p1lb, p2lb, firstlb;
	
	Order() {
		
	this.setTitle("순서 정하기");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(400, 400);
	
	b1 = new JButton("앞 뒤 선택");
	p1lb = new JLabel(); 
	foreim = new ImageIcon("fore.jpg");
	p2lb = new JLabel();
	backim = new ImageIcon("back.jpg");
	firstlb = new JLabel(); // 선공할 동전
	p1 = new JPanel();
	p1.setLayout(null);
	f1 = new JTextField(Gamer.g1 +"player의 동전");
	f2 = new JTextField(Gamer.g2 +"player의 동전" );
	b2 = new JButton("선공은?");
	b3 = new JButton("게임 시작");

	Random rand = new Random(); // 랜덤 객체 생성
	boolean r = rand.nextBoolean(); // true or false를 랜덤으로 생성
	boolean first = rand.nextBoolean(); // true면 앞면, false면
	b1.setBounds(120, 20, 140, 30);
	f1.setBounds(10,60, 150, 30);
	f2.setBounds(210,60, 150, 30);
	b2.setBounds(10,240,140,30);
	p1lb.setBounds(30,60,200,200);
	p2lb.setBounds(240,60,200,200);
	firstlb.setBounds(200, 200, 180, 200);
	b3.setBounds(10,300,140,30);
	
	if(r) { // 랜덤으로 앞면 뒷면을 해당 플레이어 아래에 보여줌
		p1lb.setIcon(foreim);
		p2lb.setIcon(backim);
	}
	else {
		p1lb.setIcon(backim);
		p2lb.setIcon(foreim);
	}
	if(first) { // 앞면이면
		firstlb.setIcon(foreim);
	}
	else { // 뒷면이면
		firstlb.setIcon(backim);
	}
	
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	
	p1.add(f1);
	p1.add(f2);
	p1.add(p1lb);
	p1.add(p2lb);
	p1.add(b1);
	p1.add(b2);
	p1.add(firstlb);
	p1.add(b3);
	
	f1.setVisible(false); // 버튼이 눌린 후 보여지기 위해서 비가시화로 설정한 컴포넌트들
	f2.setVisible(false);
	p1lb.setVisible(false);
	p2lb.setVisible(false);
	b2.setVisible(false);
	firstlb.setVisible(false);
	b3.setVisible(false);
	
	this.add(p1);
	this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) { // 앞, 뒤를 선택
			f1.setVisible(true); f2.setVisible(true);
			p1lb.setVisible(true); p2lb.setVisible(true);
			b2.setVisible(true);
		}
		if(e.getSource() == b2) {
			firstlb.setVisible(true); b3.setVisible(true);
			if(firstlb.getIcon() == p1lb.getIcon()) { // firstlb와 같은 이미지를 가진 플레이어가 선공
				 System.out.println("p1이 선공"); 
				 Gamer.turn = false;
			}
			else if(firstlb.getIcon() == p2lb.getIcon()) {
				 System.out.println("p2이 선공");
				 String tmp = Gamer.g1; // 선공의 이름을 g1로, 후공의 이름을 g2로 바꿈
				 Gamer.g1 = Gamer.g2;
				 Gamer.g2 = tmp;
				 Gamer.turn = false;
			}
		}
		if(e.getSource() == b3) {
			this.dispose();
			OmokFrame of = new OmokFrame(Gamer.size); // 오목 게임 실행
		}
	}
}
class OmokFrame extends JFrame implements ActionListener{ // 오목판이 있는 게임 프레임
	JPanel omokP = new JPanel();
	JButton [][] bt;
	JPanel info;
	JTextField name1, name2; // 순서대로 선공, 후공
	ImageIcon w,b;
	ImageIcon p,t,nt;
	JLabel t1, t2;
	JButton mool, exit;
	JButton backSoo; // 무를 수
	JLabel time; // 시간이 표시될 라벨
	int sec; // 시간
	int sizeforIndex; // 인덱스를 위한 사이즈
	
	int mCountb = 1; // 무를 수 있는 횟수 한 번
	int mCountw = 1;
	int indexa = 0; // 클릭된 버튼의 인덱스 저장
	int indexb = 0; 
	Timer timer;
	JTextField end; // 게임의 끝을 표시할 문구
	Font f;
	
	OmokFrame(int a){ // 사이즈를 매개변수로 받는 생성자
		
		sizeforIndex = a-1; // 인덱스를 위한 사이즈, 사이즈-1
		sec = 20; // 20초의 시간을 줌
		timer = new Timer(1000, new ActionListener () { // 타이머 설정
			public void actionPerformed(ActionEvent e) {
				if(sec<0) { // 타이머가 0이 되는 순간
					timer.stop(); // 타이머 리셋
					sec = 20;
					timer.restart();
					if(Gamer.turn) { // 검은색 차례였다면
						Gamer.turn = false; // 흰색으로 턴이 넘어감
						t1.setIcon(t); // 흰 돌을 빨간 불로
						t2.setIcon(nt);
					}
					else { // 흰색 차례였다면
						Gamer.turn = true; // 검은색으로 턴이 넘어감
						t1.setIcon(nt);
						t2.setIcon(t); // 검은 돌을 빨간 불로
					}
				}
				time.setText(Integer.toString(sec));
				sec -= 1; // 1초마다 1씩 줄어듬
			}
		}); // 타이머 설정
		timer.start(); // 타이머 실행
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 버튼
		this.setTitle("오목 게임"); 
		this.setLayout(new BorderLayout()); // BorderLayout으로 프레임 설정
		this.setSize(a*22,a*23); // 매개변수로 받은 a 값에 따라 프레임 크기를 조절
		this.setResizable(false); // 프레임 사이즈 변경 불가
		
		Gamer.bpan = new boolean[a][a]; // 버튼의 상태가 저장될 배열
		Gamer.wpan = new boolean[a][a];
		
		omokP = new JPanel(); // 오목 패널
		omokP.setLayout(new GridLayout(a,a)); // a*a 개의 버튼을 갖는 패널
		bt = new JButton[a][a]; // 버튼의 a행 a열의 이차원 배열 생성
		w = new ImageIcon("wdol.jpg");
		b = new ImageIcon("bdol.jpg");
		p = new ImageIcon("pandegi.jpg");
		
		info = new JPanel(); // 시간 등 정보가 표시되는 패널
		name1 = new JTextField(Gamer.g1+"  "); name1.setEditable(false); // 선공 이름
		name2 = new JTextField(Gamer.g2+"  "); name2.setEditable(false); // 후공 이름
		t = new ImageIcon("turn.jpg"); // 턴이 돌아오면 빨간불
		nt = new ImageIcon("noturn.jpg"); // 턴이 아니면 회색불
		t1 = new JLabel(); t1.setIcon(t); // 선공은 빨간불
		t2 = new JLabel(); t2.setIcon(nt); // 후공은 희색불
		mool = new JButton("한 수 무르기"); // 물리기 버튼
		exit = new JButton("게임종료"); // 게임 종료 버튼
		time = new JLabel("시간");
		end = new JTextField("끝");
		f = new Font("San Serif", Font.BOLD, 13);
		
		info.add(name1);
		info.add(t1); // 선공 상태
		info.add(name2);
		info.add(t2); // 후공 상태
		info.add(mool);
		info.add(exit);
		info.add(time);
		info.add(end); // 게임 끝나면 안내 문구 표시
		
		end.setFont(f);
		end.setBackground(Color.red);
		end.setBorder(null);
		end.setForeground(Color.white);
		end.setVisible(false);
		
		for(int i = 0; i <a; i++) { // 행만큼 반복
			for(int j = 0; j <a; j++) { // 열만큼 반복
				bt[i][j] = new JButton(); // 버튼 생성, 버튼 이미지 부착
				omokP.add(bt[i][j]); // 패널에 부착
				if(i>0&&i<a-1 && j>0 && j<a-1) {
				bt[i][j].setIcon(p); // 오목 판 이미지를 붙임
				bt[i][j].setBorderPainted(false); // 버튼 경계선 안 보이도록 설정
				bt[i][j].addActionListener(this); // 모든 버튼에 이벤트 설정
				}
				else {
					bt[i][j].setVisible(false); // 가장자리 버튼들 안 보이도록 설정
				}
			}
		}
		
		mool.addActionListener(this); // 이벤트 추가
		exit.addActionListener(this);
		
		add(omokP, BorderLayout.CENTER); // 오목판 패널을 프레임 중앙에 부착
		add(info, BorderLayout.NORTH); // 정보가 표시되는 패널을 프레임 위쪽에 부착
		setVisible(true); // 가시화
	}
	public void actionPerformed(ActionEvent e) {	
		
		timer.stop(); // 버튼이 눌리면 타이머 초기화
		sec = 20;
		timer.restart();
		
		if(e.getSource() == exit) { // 종료버튼 누르면
			Gamer.clear(); // 게임 정보 초기화
			this.dispose(); // 게임창 종료
			GameStartMenu g = new GameStartMenu();
		}
		if(e.getSource() == mool) { // 한수 무르기 누르면
			if(Gamer.turn) { // 검은 돌 차례였으면
				if(mCountw>0 && backSoo != null) { // 무르기 사용 횟수가 존재할 때만, backSoo가 null이면 방금 전에 무르기를 했다는 것(연속 무르기 방지)
					mCountw--; // 흰 돌이 무르기 사용했으므로 카운트 감소
					backSoo.setIcon(p); // 최근에 눌린 버튼을 다시 오목판 그림으로 바꾸고
					backSoo = null; // backSoo를 null로 만들어 방금 전에 무르기를 했다는 사실을 표시
					Gamer.turn = false; // 다시 흰 돌 차례로 바꿔줌
					Gamer.sooW--; // 흰 돌의 수를  하나 줄임
					t1.setIcon(t); // 흰 돌을 빨간 불로
					t2.setIcon(nt);
					Gamer.wpan[indexa][indexb] = false; // 흰돌에 눌린 것으로 표시된 것을 다시 초기화
					System.out.println(Gamer.sooW); // 수 출력
					System.out.println(Gamer.sooB);
					}
				}
				else { // 흰 돌 차례였으면
					if(mCountb>0 && backSoo != null) { // 무르기 사용 횟수가 존재할 때만, 연속 무르기 방지
					mCountb--; // 검은돌이 무르기 사용했으므로 카운트 감소
					backSoo.setIcon(p); // 최근에 눌린 버튼을 다시 오목판 그림으로 바꾸고
					backSoo = null; // backSoo를 null로 만들어 방금 전에 무르기를 했다는 사실을 표시
					Gamer.turn = true; // 다시 검은 돌 차례로 바꿔줌
					Gamer.sooB--; // 검은 돌의 수를 하나 줄임
					t1.setIcon(nt);
					t2.setIcon(t); // 검은 돌을 빨간 불로
					Gamer.bpan[indexa][indexb] = false; // 검은돌에 눌린 것으로 표시된 것을 다시 초기화
					System.out.println(Gamer.sooW); // 수 출력
					System.out.println(Gamer.sooB);
					}
				}
			}
		for(int i = 0;i<Gamer.size;i++) { // 돌을 놓는 경우
			for(int j = 0;j<Gamer.size;j++) {
				
			if(!Gamer.bpan[i][j] && !Gamer.wpan[i][j]) { // 이미 눌린 버튼이면 넘어감
				if(e.getSource() == bt[i][j]) { // 특정 버튼이 눌렸는데
					backSoo = bt[i][j]; // 가장 최근에 눌린 버튼 저장
					indexa = i; indexb = j; // 인덱스 저장
					
					System.out.println(i +" "+j);
					if(!Gamer.turn) { // 흰 돌이 놓을 차례였으면
						bt[i][j].setIcon(w); // 흰 돌이 놓여진 그림으로 바꿈
						Gamer.wpan[i][j] = true; // 흰 돌이 놓여진 위치 저장
						Gamer.turn = true; // 턴을 검은 돌에게 넘겨줌
						Gamer.sooW++; // 흰 돌 수 증가
						t1.setIcon(nt);
						t2.setIcon(t); // 검은 돌을 빨간 불로
						
						//////////////////////////////////////////////이기는 경우
						
						//// 상하 좌우로 오목인 경우
						int nu = i; // 연결된 제일 위 인덱스
						while(Gamer.wpan[nu][j]) {
							nu--;
						} nu++;
						int nd = i; // 연결된 제일 아래 인덱스
						while(Gamer.wpan[nd][j]) {
							nd++;
						} nd--;
						int nr = j; // 연결된 제일 오른쪽 인덱스
						while(Gamer.wpan[i][nr]) {
							nr++;
						} nr--;
						int nl = j; // 연결된 제일 왼쪽 인덱스
						while(Gamer.wpan[i][nl]) {
							nl--;
						}nl++;
						if((nr-nl== 4) && (nr-nl != 5)) { // 좌우로 오목인 경우, 육목 제외
							time.setVisible(false); // 시간은 안 보읻록
							end.setVisible(true); // 끝났음을 표시
							Gamer.finish = true; 
							Gamer.winner = Gamer.g1; // 선공(백돌) 승리
							Gamer.winSoo = Gamer.sooW; return;
						} 
						else if((nd-nu == 4)&&(nd-nu != 5)) { // 상하로 오목인 경우, 육목제외
							time.setVisible(false); // 시간은 안 보읻록
							end.setVisible(true); // 끝났음을 표시
							Gamer.finish = true; 
							Gamer.winner = Gamer.g1; // 선공(백돌) 승리
							Gamer.winSoo = Gamer.sooW;  return;
						}
						//// 대각선으로 오목인 경우
						// 오른쪽 아래
						int rdx = j; int rdy = i; int lux = j; int luy = i;
						while(Gamer.wpan[rdy][rdx]) { 
							rdx++; rdy++;
						} rdx--; rdy--;
						
						while(Gamer.wpan[luy][lux]) { // 왼쪽 위
							lux--; luy--;
						}lux++; luy++;
						if((rdx-lux == 4)&&(rdx-lux != 5)) { // 어차피 x와 y값이 같이 커지므로 x값 하나만 비교해도 됨
							time.setVisible(false); // 시간은 안 보읻록
							end.setVisible(true); // 끝났음을 표시
							Gamer.finish = true; 
							Gamer.winner = Gamer.g1; // 선공(백돌) 승리
							Gamer.winSoo = Gamer.sooW;  return;
						}
						// 오른쪽 위
						int rux = j; int ruy = i; int ldx = j; int ldy = i;
						while(Gamer.wpan[ruy][rux]) { 
							rux++; ruy--;
						}rux--; ruy++;
						while(Gamer.wpan[ldy][ldx]) { // 왼쪽 아래
							ldx--; ldy++;
						}ldx++; ldy--;
						if((rux-ldx == 4)&&(rux-ldx != 5)) { // 어차피 x와 y값이 같이 커지므로 x값 하나만 비교해도 됨
							time.setVisible(false); // 시간은 안 보읻록
							end.setVisible(true); // 끝났음을 표시
							Gamer.finish = true; 
							Gamer.winner = Gamer.g1; // 선공(백돌) 승리
							Gamer.winSoo = Gamer.sooW; return;
							}
						}					
					else { // 검은돌을 놓을 차례였으면
						bt[i][j].setIcon(b); // 검은 돌이 놓여진 그림으로 바꿈
						Gamer.bpan[i][j] = true; // 검은 돌이 놓여진 위치 저장
						Gamer.turn = false; // 턴을 흰 돌에게 넘겨줌
						Gamer.sooB++; // 검은 돌 수 증가
						t1.setIcon(t); // 흰 돌을 빨간 불로
						t2.setIcon(nt);	
						////////////////////////////////////////////////이기는 경우
						//// 상하 좌우로 오목인 경우
						int nu = i; // 연결된 제일 위 인덱스
						while(Gamer.bpan[nu][j]) {
							nu--;
						}nu++;
						int nd = i; // 연결된 제일 아래 인덱스
						while(Gamer.bpan[nd][j]) {
							nd++;
						}nd--;
						int nr = j; // 연결된 제일 오른쪽 인덱스
						while(Gamer.bpan[i][nr]) {
							nr++;
						}nr--;
						int nl = j; // 연결된 제일 왼쪽 인덱스
						while(Gamer.bpan[i][nl]) {
							nl--;
						}nl++;
						
						if((nr-nl== 4) && (nr-nl != 5)) { // 좌우로 오목인 경우, 육목 제외
							time.setVisible(false); // 시간은 안 보읻록
							end.setVisible(true); // 끝났음을 표시
							Gamer.finish = true; 
							Gamer.winner = Gamer.g2; // 후공(흑돌) 승리
							Gamer.winSoo = Gamer.sooB;  return;
						}
						else if((nd-nu == 4)&&(nd-nu != 5)) { // 상하로 오목인 경우, 육목제외
							time.setVisible(false); // 시간은 안 보읻록
							end.setVisible(true); // 끝났음을 표시
							Gamer.finish = true;
							Gamer.winner = Gamer.g2; // 후공(흑돌) 승리
							Gamer.winSoo = Gamer.sooB;  return;
						}  
						//// 대각선으로 오목인 경우
						// 오른쪽 아래
						int rdx = j; int rdy = i; int lux = j; int luy = i;
						while(Gamer.bpan[rdy][rdx]) { 
							rdx++; rdy++;
						} rdx--; rdy--;
						
						while(Gamer.bpan[luy][lux]) { // 왼쪽 위
							lux--; luy--;
						}lux++; luy++;
						if((rdx-lux == 4)&&(rdx-lux != 5)) { // 어차피 x와 y값이 같이 커지므로 x값 하나만 비교해도 됨
							time.setVisible(false); // 시간은 안 보읻록
							end.setVisible(true); // 끝났음을 표시
							Gamer.finish = true; 
							Gamer.winner = Gamer.g2; // 후공(흑돌) 승리
							Gamer.winSoo = Gamer.sooB; 
							 return;
						}
						// 오른쪽 위
						int rux = j; int ruy = i; int ldx = j; int ldy = i;
						while(Gamer.bpan[ruy][rux]) { 
							rux++; ruy--;
						}rux--; ruy++;
						
						while(Gamer.bpan[ldy][ldx]) { // 왼쪽 아래
							ldx--; ldy++;
						}ldx++; ldy--;
						if((rux-ldx == 4)&&(rux-ldx != 5)) { // 어차피 x와 y값이 같이 커지므로 x값 하나만 비교해도 됨
							time.setVisible(false); // 시간은 안 보읻록
							end.setVisible(true); // 끝났음을 표시
							Gamer.finish = true; 
							Gamer.winner = Gamer.g2; // 후공(흑돌) 승리
							Gamer.winSoo = Gamer.sooB;
							 return;
							}
						}
					if(Gamer.finish) { ////////////////////////////////////////////// 게임이 누군가의 승리로 끝나면
						String s = Integer.toString(Gamer.winSoo)+" "+Gamer.winner;
						
						try {
							GameRun.readOmok(); // 순위를 불러와서
						} catch (IOException e1) {
							// TODO Auto-generated catch block, IOException을 해결하기 위한 try-catch
							e1.printStackTrace();
						}
						System.out.println("ERR2");
						GameRun.putOmok(s); // 새로운 순위를 넣고		
						System.out.println("ERR3");
						try {
						GameRun.WriteOmok(); // 다시 파일에 순위를 써넣는다.
						} catch (IOException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("ERR4");
						Win lol = new Win();
					
						dispose(); // 창 닫기
						}
					}
				}
			}
		}
	}
}
class Win extends JFrame{
	JPanel p;
	JTextField winner;
	Font f;
	JButton exit;
	JLabel winlb;
	ImageIcon winim;
	Win(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 버튼
		this.setTitle("오목 게임 승자"); 
		this.setSize(400,400); // 매개변수로 받은 a 값에 따라 프레임 크기를 조절
		this.setResizable(false); // 프레임 사이즈 변경 불가
		
		p = new JPanel();
		p.setLayout(null);
		
		winner = new JTextField();
		f = new Font("San Serif", Font.BOLD, 30);
		exit = new JButton("게임 종료"); // 게임 종료 버튼
		winlb = new JLabel();
		winim = new ImageIcon("win.jpg"); // 승리 이미지
		winlb.setIcon(winim);
		
		if(Gamer.winSoo == Gamer.sooW) { // 백돌이 이겼으면
			winner.setText("백돌 "+Gamer.winner+Gamer.winSoo+" 수, 승리!!");
		}
		else {
			winner.setText("흑돌 "+Gamer.winner+Gamer.winSoo+" 수, 승리!!");
		}
		winner.setHorizontalAlignment(JTextField.CENTER); // 텍스트 가운데정렬
		winner.setFont(f);
		winner.setBackground(Color.RED);
		winner.setForeground(Color.green);
		
		winner.setBounds(10,10,400,60);
		winlb.setBounds(80, 50, 300, 300);
		exit.setBounds(150, 320, 90,30);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gamer.clear(); // 게임 정보 초기화
				dispose(); // 창 닫기
				GameStartMenu gm = new GameStartMenu();
			}
		});
		
		p.add(winner);
		p.add(winlb);
		p.add(exit);
		this.add(p);
		this.setVisible(true);
	}
}