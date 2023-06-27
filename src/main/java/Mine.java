import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random; // 랜덤을 사용하기 위해
import java.io.IOException; // IOException을 위해
import javax.swing.Timer; // 타이머 기능을 위해

class Gamerm{ // 지뢰 게임 static 변수들
	
	static String mineScore[]; // 지뢰찾기 점수 기록
	static int index; // 지뢰찾기 점수 인덱스
	static int size; // 지뢰찾기 판의 사이즈
	static int time; // 깬 시간
	static boolean mine[][]; // 지뢰의 위치를 저장할 배열, 있으면 true 없으면 false
	static int mineNum; // 지뢰의 개수를 저장할 변수
	static int reNum; // 게임 재시작 시 지뢰 개수를 저장할 변수
	static boolean finish= false; // 게임 끝 여부, 최초 false로 초기화
	static boolean gameover = false; // 게임오버 여부, 최초 false로 초기화
	static String name; // 이름을 입력받을 변수

	static int isMine(int i, int j) { // 팔방을 검사해서 지뢰의 개수를 리턴하는 함수
		int n = 0; // 지뢰의 개수를 셀 변수
		if(i>=1 && j>=1 && i<size-1 && j<size-1) { // 배열의 index out of bounds를 방지 가장 자리 제외
			if(mine[i-1][j-1]) n++;
			if(mine[i-1][j]) n++;
			if(mine[i-1][j+1]) n++;
			if(mine[i][j-1]) n++;
			if(mine[i][j+1]) n++;
			if(mine[i+1][j-1]) n++;
			if(mine[i+1][j]) n++;
			if(mine[i+1][j+1]) n++;
		}
		return n;
	}
	
	static void clear() { // static 변수 초기화 함수
		
		for(int i = 0; i<size;i++) {
			for(int j = 0; j<size; j++) {
				mine[i][j] = false;
			}
		}
		time = 0;
		size = 0;
		mineNum = 0;
		reNum = 0;
		finish = false;
		gameover = false;
		name = null;
	}
	
}

public class Mine extends JFrame implements ActionListener {
	
	JPanel inputMine; // 사이즈, 지뢰 개수 입력받는 패널
	JButton exitGame;
	JButton enter; // 입력한 값으로 게임 생성
	JTextField f1,f2, f3, f4, f5, f6;

	
	Mine(){
		
		// 컴포넌트 생성
		inputMine = new JPanel();
		exitGame = new JButton ("게임 종료");
		f1 = new JTextField("가로 X세로 버튼 개수 입력 (10~50 사이의 숫자)");
		f2 = new JTextField("지뢰 개수 입력");
		f3 = new JTextField(); // 버튼 수 입력받음
		f4 = new JTextField(); // 지뢰 개수 입력
		enter = new JButton("게임 시작");
		
		// 프레임 설정
		this.setTitle("지뢰 찾기 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.add(inputMine);
		inputMine.setLayout(null);
		
		// 컴포넌트 위치 설정
		f1.setEditable(false);
		f2.setEditable(false);
		exitGame.setBounds(120, 20, 140, 30);
		f1.setBounds(10,60, 270, 30);
		f2.setBounds(10,140, 270, 30);
		f3.setBounds(10,100, 200, 30);
		f4.setBounds(10,180, 200, 30);
		enter.setBounds(120, 240, 140, 30);
		
		//패널에 컴포넌트 부착
		inputMine.add(exitGame);
		inputMine.add(f1);
		inputMine.add(f2);
		inputMine.add(f3);
		inputMine.add(f4);
		inputMine.add(enter);
		
		exitGame.addActionListener(this); // 게임 종료
		enter.addActionListener(this); // 게임 실행
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitGame) { // 종료 버튼이 눌리면
			GameStartMenu game = new GameStartMenu();
			this.dispose(); // 입력창 종료
		}
		if(e.getSource() == enter) { // 게임 시작 버튼이 눌리면
			int size = 0; int mineNum = 0;
			size = Integer.parseInt(f3.getText())+ 2; // 입력받은 사이즈 저장, 가장자리 포함 2개 더함
			mineNum = Integer.parseInt(f4.getText()); // 입력받은 지뢰 개수 저장
			MineFrame gf = new MineFrame(size, mineNum); // 매개 변수를 갖는 지뢰 찾기 게임 생성
			this.dispose(); // 입력창 종료
		}
	}
}
class MineFrame extends JFrame implements ActionListener{
	
	JPanel mineP; // 지뢰찾기 판
	JPanel info; // 정보가 표시될 부분
	JButton bt[][];
	JLabel mineNum; // 지뢰의 개수가 표시될 부분
	JButton face; // 스마일 버튼
	JButton exit; // 종료 버튼
	int fakeNum; // 표시용 지뢰 개수
	int realSize; // 인덱스의 최대값
	
	//////////////////////////// 이미지 아이콘
	ImageIcon sm;// 스마일 아이콘
	ImageIcon sunsm; // 기본 선글라스 낀 스마일
	ImageIcon minenormal; // 기본적인 이미지
	ImageIcon flag; // 깃발, 우클릭 한 번 시
	ImageIcon question; // 물음표, 우클릭 두 번 시
	ImageIcon unfoundmine; // 못 찾은 지뢰
	ImageIcon clickedmine; // 지뢰를 누름, 게임오버
	ImageIcon nomine; // 지뢰 없는 부분
	ImageIcon gameover; // 게임오버시 표시될 우는 스마일
	ImageIcon wrong; // 잘못 찾은 부분
	ImageIcon m1, m2, m3, m4, m5, m6, m7, m8; // 지뢰  숫자
	
	JLabel time; // 시간이 표시될 라벨
	int sec; // 시간
	Timer timer;
	boolean firstClick = false; // 처음 클릭되는 것을 파악
	
	void numPop(int indexa, int indexb) { // 인덱스를 매개변수로 받아서 해당 숫자로 이미지를 바꿔주는 함수
		if(Gamerm.mine[indexa][indexb]) return;  // 해당 인덱스가 지뢰이지 않은 경우에 한 해서(지뢰 이미지가 바뀌면 안 되므로)
		int num = Gamerm.isMine(indexa, indexb);
		switch(num) { // 주변 지뢰 개수에 따라서 숫자로 표시해줌
		case 1:
			bt[indexa][indexb].setIcon(m1); break;
		case 2:
			bt[indexa][indexb].setIcon(m2); break;
		case 3:
			bt[indexa][indexb].setIcon(m3); break;
		case 4:
			bt[indexa][indexb].setIcon(m4); break;
		case 5:
			bt[indexa][indexb].setIcon(m5); break;
		case 6:
			bt[indexa][indexb].setIcon(m6); break;
		case 7:
			bt[indexa][indexb].setIcon(m7); break;
		case 8:
			bt[indexa][indexb].setIcon(m8); break;
		}
	}
	
	void popAround(int i, int j) {
		if(i>=1 && j>=1 && i<realSize && j<realSize) { // 가장자리 제외			
			bt[i][j].setIcon(nomine); // 자기 위치를 지뢰 없는 이미지로 바꿈
			if(Gamerm.isMine(i-1, j-1) > 0) numPop(i-1, j-1); // 팔방을 지뢰없는 이미지 혹은 숫자 패널로 바꿈
			else if(!Gamerm.mine[i-1][j-1]) bt[i-1][j-1].setIcon(nomine);
			if(Gamerm.isMine(i-1, j) > 0) numPop(i-1, j); 
			else if(!Gamerm.mine[i-1][j]) bt[i-1][j].setIcon(nomine);
			if(Gamerm.isMine(i-1, j+1) > 0) numPop(i-1, j+1); 
			else if(!Gamerm.mine[i-1][j+1]) bt[i-1][j+1].setIcon(nomine);
			if(Gamerm.isMine(i, j-1) > 0) numPop(i, j-1); 
			else if(!Gamerm.mine[i][j-1]) bt[i][j-1].setIcon(nomine);
			if(Gamerm.isMine(i, j+1) > 0) numPop(i, j+1); 
			else if(!Gamerm.mine[i][j+1]) bt[i][j+1].setIcon(nomine);
			if(Gamerm.isMine(i+1, j-1) > 0) numPop(i+1, j-1); 
			else if(!Gamerm.mine[i+1][j-1]) bt[i+1][j-1].setIcon(nomine);
			if(Gamerm.isMine(i+1, j) > 0) numPop(i+1, j); 
			else if(!Gamerm.mine[i+1][j]) bt[i+1][j].setIcon(nomine);
			if(Gamerm.isMine(i+1, j+1) > 0) numPop(i+1, j+1); 
			else if(!Gamerm.mine[i+1][j+1]) bt[i+1][j+1].setIcon(nomine);
		}
	}
	void pop1(int i, int j) { // 8방으로 숫자가 나올 때까지 계속해서 터트림
		if(i>=1 && j>=1 && i<realSize && j<realSize) { // 가장자리 제외
			if(Gamerm.isMine(i, j) > 0) {numPop(i,j); return;} // 숫자 패널을 발견하면 숫자를 표시하고 함수를 나감
			popAround(i,j);
		} 
	}	
	int popStop() {//////////////////////////////////////없을 때까지 미처리 개수 리턴
		int n = 0;
		for(int i = 1; i < realSize;i++) {
			for(int j = 1; j < realSize;j++) { 
				if(bt[i][j].getIcon() == minenormal && bt[i-1][j-1].getIcon() == nomine) n++;
				if(bt[i][j].getIcon() == minenormal && bt[i-1][j].getIcon() == nomine) n++;
				if(bt[i][j].getIcon() == minenormal && bt[i-1][j+1].getIcon() == nomine) n++;
				if(bt[i][j].getIcon() == minenormal && bt[i][j+1].getIcon() == nomine) n++;
				if(bt[i][j].getIcon() == minenormal && bt[i+1][j+1].getIcon() == nomine) n++;
				if(bt[i][j].getIcon() == minenormal && bt[i+1][j].getIcon() == nomine) n++;
				if(bt[i][j].getIcon() == minenormal && bt[i+1][j-1].getIcon() == nomine) n++;
				if(bt[i][j].getIcon() == minenormal && bt[i][j-1].getIcon() == nomine) n++;
			}
		}
		return n;
	}
	MineFrame(int a, int b){ // 사이즈와 지뢰 개수를 매개변수로 받는 생성자
		
		sec = 0; // 0부터 시간을 셈
		timer = new Timer(1000, new ActionListener () { // 타이머 설정
			public void actionPerformed(ActionEvent e) {
				time.setText("시간 : " + Integer.toString(sec));
				sec += 1; // 1초마다 1씩 늘어남
			}
		}); // 타이머 설정
		
		realSize = a-1; // 인덱스의 최대값
		
		Gamerm.size = a; // 지뢰찾기 판 사이즈저장
		Gamerm.mineNum = b; // 지뢰 개수 저장
		Gamerm.reNum = b;
		fakeNum = b;
		Gamerm.mine = new boolean[a][a]; // 사이즈 크기의 지뢰가 매설될 가능성이 있는 배열 생성
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 버튼
		this.setTitle("지뢰 찾기 게임"); 
		this.setLayout(new BorderLayout()); // BorderLayout으로 프레임 설정
		this.setSize(a*22,a*23); // 매개변수로 받은 a 값에 따라 프레임 크기를 조절
		this.setResizable(false); // 프레임 사이즈 변경 불가
		
		mineP = new JPanel(); // 지뢰 페널
		mineP.setLayout(new GridLayout(a,a)); // a*a 개의 버튼을 갖는 패널
		bt= new JButton[a][a]; // 버튼의 ㅁ행 a열의 이차원 배열 생성
		info = new JPanel();
		exit = new JButton("게임 종료");
		mineNum = new JLabel("지뢰 : " + fakeNum); // 지뢰 개수 표시
		time = new JLabel("시간     ");
		face = new JButton();
		
		sm = new ImageIcon("src/main/resources/smile.jpg"); // 스마일 이미지
		sunsm = new ImageIcon("src/main/resources/sunglasssm.jpg"); // 기본 선글라스 낀 스마일 이미지
		minenormal = new ImageIcon("src/main/resources/minenormal.jpg"); // 기본 지뢰찾기 판
		unfoundmine = new ImageIcon("src/main/resources/unfoundmine.jpg"); // 찾은 지뢰 이미지
		flag = new ImageIcon("src/main/resources/flag.jpg"); // 깃발표시
		question = new ImageIcon("src/main/resources/question.jpg"); // 물음표 표시
		clickedmine = new ImageIcon("src/main/resources/clickedmine.jpg"); // 지뢰 누름 게임오버
		nomine = new ImageIcon("src/main/resources/nomine.jpg"); // 지뢰 없는 부분
		gameover = new ImageIcon("src/main/resources/gameover.jpg"); // 게임오버
		wrong = new ImageIcon("src/main/resources/wrong.jpg"); // 잘못 찾은 지뢰
		m1 = new ImageIcon("src/main/resources/1.jpg"); // 지뢰 숫자
		m2 = new ImageIcon("src/main/resources/2.jpg");
		m3 = new ImageIcon("src/main/resources/3.jpg");
		m4 = new ImageIcon("src/main/resources/4.jpg");
		m5 = new ImageIcon("src/main/resources/5.jpg");
		m6 = new ImageIcon("src/main/resources/6.jpg");
		m7 = new ImageIcon("src/main/resources/7.jpg");
		m8 = new ImageIcon("src/main/resources/8.jpg");
	
		
		for(int i = 0; i <a; i++) { // 행만큼 반복
			for(int j = 0; j <a; j++) { // 열만큼 반복
				bt[i][j] = new JButton(); // 버튼 생성
				mineP.add(bt[i][j]); // 패널에 부착
				bt[i][j].setIcon(minenormal); // 지뢰찾기 판 이미지를 부착
				bt[i][j].addActionListener(this); // 버튼에 이벤트 추가
				Gamerm.mine[i][j] = false;// 지뢰를 모두 false로 초기화 
				bt[i][j].addMouseListener(new MouseAdapter() { /////// 마우스 우클릭 시
					   public void mousePressed(MouseEvent e) {
					    if(e.getModifiers() == MouseEvent.BUTTON3_MASK) {  // 우클릭을 실행하면
					    	//////////// 예외처리
					    	if(Gamerm.finish|| Gamerm.gameover) return; // 게임을 이기거나 지면 오른쪽 클릭 금지
					    	int  indexa = 0, indexb = 0; // 눌린 버튼의 인덱스를 저장할 변수
					    	for(int i = 0 ; i <a ;i++) {
					    		for(int j = 0; j< a; j++) {
					    			if(e.getSource() == bt[i][j]) { indexa = i; indexb = j;} // 눌린 버튼의 인덱스 파악
					    		}
					    	}
					    	if(bt[indexa][indexb].getIcon() == minenormal) { // 기본 이미지에서 누를 때 
					    		bt[indexa][indexb].setIcon(flag); // 이미지를 깃발로 바꾸고
					    		fakeNum--; // 지뢰 개수 하나 줄이고 표시
					    		mineNum.setText("지뢰 : " + fakeNum);
					    		///////// 만약 진짜 지뢰를 발견하면
					    		if(Gamerm.mine[indexa][indexb]) {
					    			Gamerm.mineNum--; // 진짜 지뢰 개수를 감소
					    			
					    			//////////////////////////////////////// 게임 승리
					    			if(Gamerm.mineNum == 0 && fakeNum == 0) { // 진짜 남은 지뢰 개수가 0이고 표시된 지뢰 수도 0이면 승리
					    				timer.stop(); // 타이머 멈춤
					    				face.setIcon(sm); // 얼굴 웃는 표시로 변경
					    				exit.setText("지뢰를 모두 찾았습니다. 아무 버튼이나 클릭하십시오."); // 게임 끝나면 버튼 클릭을 유도
					    				exit.setBackground(Color.RED); // 색 변경으로 시선을 끔
					    				exit.setForeground(Color.WHITE);
					    				Gamerm.finish = true; // finish가 true가 됨. 버튼 이벤트에 finish일 경우를 추가해놓음.
					    			}
					    		}
					    	}
					    	else if(bt[indexa][indexb].getIcon() == flag) { // 깃발일 때 다시 클릭하면
					    		bt[indexa][indexb].setIcon(question); // 이미지를 물음표로 바꾸고
					    		fakeNum++; // 지뢰 개수 하나 늘리고 표시
					    		mineNum.setText("지뢰 : " + fakeNum);
					    		///////// 만약 진짜 지뢰를 취소하면
					    		if(Gamerm.mine[indexa][indexb]) {
					    			Gamerm.mineNum++; // 진짜 지뢰 개수를 증가
					    		}
					    	}
					    	else if(bt[indexa][indexb].getIcon() == question) {
					    		bt[indexa][indexb].setIcon(minenormal); // 기본 이미지로 다시 변경
					    	}
					    }
					 }
				});
				if(i == 0 || i == realSize || j == 0 || j == realSize) { // 가장자리의 버튼들은
					bt[i][j].setEnabled(false); // 사용 불가 처리
					bt[i][j].setVisible(false); // 보이지 않게 처리
				}
			}
		}	
		exit.addActionListener(this); // 종료 버튼 이벤트추가
		face.addActionListener(this); // 얼굴 버튼 이벤트 추가
		
		exit.setBackground(Color.green);
		exit.setForeground(Color.blue);
	
		face.setIcon(sunsm); // 선글라스 스마일 그림 버튼에 추가
		face.setBackground(null); // 스마일 버튼 그림만 보이도록 설정
		face.setBorder(null);
		
		info.add(mineNum);
		info.add(face);
		info.add(time);
		
		add(info, BorderLayout.NORTH); // 정보 부분을 프레임 위쪽에 부착
		add(mineP, BorderLayout.CENTER); // 패널을 프레임 중앙에 부착
		add(exit, BorderLayout.SOUTH); // 프레임 아래에 종료 버튼 추가
		setVisible(true); // 가시화
		
		// 지뢰 생성 부분	
		Random random = new Random(); // 랜덤 객체 생성
		
			for(int i = 0; i<b;i++) { // 지뢰 개수만큼 반복
				int x = random.nextInt(a); // 랜덤으로 0부터 a-1 까지의 숫자(인덱스)를 갖는다.
				int y = random.nextInt(a);
				if(Gamerm.mine[x][y] || x == 0 || y == 0 || x == realSize || y == realSize) { // 이미 그 위치에 지뢰가 존재하거나 가장자리면 i를 하나 감소
					i--;
				}
				Gamerm.mine[x][y] = true; // 지뢰의 위치를 저장	
				if( x == 0 || y == 0 || x == realSize || y == realSize) Gamerm.mine[x][y] = false;
			}	
	}
	@Override
	public void actionPerformed(ActionEvent e) {	
		/////////////////////////// 예외처리
		if(Gamerm.gameover) return; // 게임오버 당하면 버튼 이벤트 실행 금지
		if(!firstClick) {timer.start(); firstClick = true;} // 처음 클릭한 순간부터 타이머 실행
		////////////////////////////게임 종료 클릭시
		if(e.getSource() == exit) { 
			dispose();
			Gamerm.clear(); // 게임 정보 초기화 
			GameStartMenu a = new GameStartMenu();
		}
		/////////////////////////// 스마일 아이콘 클릭시
		if(e.getSource() == face) {
			int n1 = Gamerm.size; int n2 = Gamerm.reNum; // 사이즈와 지뢰 개수 저장 후 매개변수로 넣어줌
			Gamerm.clear(); // 게임 초기화 후
			dispose(); // 현재 창 종료 후
			MineFrame gf = new MineFrame(n1, n2); // 매개 변수를 갖는 지뢰 찾기 게임 생성
		}
		//////////////////버튼을 눌렀을 때 인덱스 찾는 부분
		int indexa = 0; int indexb = 0; // 인덱스 저장 변수
		for(int i = 0; i <Gamerm.size;i++) {
			for(int j = 0; j< Gamerm.size;j++) {
				if(e.getSource() == bt[i][j]) {
					indexa = i; indexb = j; // 인덱스 저장
				}
			}
		}	
		/////////////////////// 예외처리 2
		if(bt[indexa][indexb].getIcon() == nomine && !Gamerm.finish) return; // 게임이 안 끝났는데 지뢰 없는 곳 확인된 곳을 클릭시에는 버튼 이벤트 실행 금지
		if(bt[indexa][indexb].getIcon() == flag) return; // 깃발인 부분은 클릭하면 지뢰가 눌리므로 이벤트 실행 금지
		///////////////// 지뢰를 눌렀을 때
		if(Gamerm.mine[indexa][indexb]) {
			Gamerm.gameover = true; // 게임오버 표시
			face.setIcon(gameover); // 얼굴 죽은 스마일로 바꿈
			timer.stop(); // 타이머 멈춤
			for(int i = 0; i < Gamerm.size;i++) {
				for(int j = 0; j<Gamerm.size;j++) {
					if(bt[i][j].getIcon() == flag && !Gamerm.mine[i][j]) { // 지뢰가 아닌데 깃발 친 부분
						bt[i][j].setIcon(wrong); // 잘못 찾음
					}
					if(Gamerm.mine[i][j] && bt[i][j].getIcon() != flag) { // 찾지 못한 지뢰(깃발 안 친 지뢰)
						bt[i][j].setIcon(unfoundmine); // 못 찾음
					} bt[indexa][indexb].setIcon(clickedmine); // 클릭한 지뢰는 따로 표시
				}
			}
			GameOver n = new GameOver(this); // , 현재 게임 객체를 매개변수로 넘겨서 게임 오버 창 띄움
		}
		///////////////// 근처에 지뢰가 없는 곳을 눌렀을 때
		if(Gamerm.isMine(indexa, indexb) == 0 && !Gamerm.mine[indexa][indexb]) { /////////////////////////////////////// 만약 팔방이 다 숫자판이면
			if(Gamerm.isMine(indexa-1, indexb-1) > 0 && Gamerm.isMine(indexa-1, indexb) > 0 && Gamerm.isMine(indexa-1, indexb+1) > 0
					&& Gamerm.isMine(indexa, indexb-1) > 0 && Gamerm.isMine(indexa, indexb+1)  > 0 
					&& Gamerm.isMine(indexa+1, indexb-1) > 0 && Gamerm.isMine(indexa+1, indexb) > 0
					&& Gamerm.isMine(indexa+1, indexb+1) > 0) {
				bt[indexa][indexb].setIcon(nomine); // 가운데는 빈칸으로 처리하고 팔방을 다 숫자 판으로 바꿔준 후
				numPop(indexa-1, indexb-1); numPop(indexa-1, indexb); numPop(indexa, indexb-1); numPop(indexa, indexb+1); 
				numPop(indexa+1, indexb-1); numPop(indexa+1, indexb); numPop(indexa+1, indexb+1); numPop(indexa-1,indexb+1);
			return; // 리턴
			}
			System.out.println("ERR1");
			pop1(indexa,indexb); // 나머지의 경우에 pop 함수로 숫자 판이 나올 때까지 버튼 이미지 변경
			
			int n = popStop(); // 처리 안 된 칸 개수받아서 
			
			for(int k = 0; k < n+3; k ++) { // 적당한 횟수 (그 횟수 + 3)만큼 처리
				for(int i = 1; i < realSize;i++) { 
					for(int j = 1; j < realSize;j++) { //////////////////////////////// 팝하다가 만 곳 발견시 그 곳 부터 다시 팝
						if(bt[i][j].getIcon() == minenormal && bt[i-1][j-1].getIcon() == nomine) pop1(i-1, j-1);
						else if(bt[i][j].getIcon() == minenormal && bt[i-1][j].getIcon() == nomine) pop1(i-1, j);
						else if(bt[i][j].getIcon() == minenormal && bt[i-1][j+1].getIcon() == nomine) pop1(i-1, j+1);
						else if(bt[i][j].getIcon() == minenormal && bt[i][j+1].getIcon() == nomine) pop1(i, j+1);
						else if(bt[i][j].getIcon() == minenormal && bt[i+1][j+1].getIcon() == nomine) pop1(i+1, j+1);
						else if(bt[i][j].getIcon() == minenormal && bt[i+1][j].getIcon() == nomine) pop1(i+1, j);
						else if(bt[i][j].getIcon() == minenormal && bt[i+1][j-1].getIcon() == nomine) pop1(i+1, j-1);
						else if(bt[i][j].getIcon() == minenormal && bt[i][j-1].getIcon() == nomine) pop1(i, j-1);
						
					}
				}
			}	

		}
		///////////////// 근처에 지뢰가 있는 곳을 눌렀을 때
		if(Gamerm.isMine(indexa, indexb) > 0) { // 근처에 지뢰가 존재하면
			numPop(indexa, indexb);
		}
    	/////////////////////// 게임 끝나면
    	if(Gamerm.finish) {
    		Gamerm.time = sec; // 게임을 깬 시간 저장
    		GameWin w = new GameWin(Gamerm.time, this); // 시간초와 현재 진행한 게임 객체를 매개변수로 넘겨줌
    	}
	}
}
class GameWin extends JFrame{ // 게임 승리
	JPanel p;
	JTextArea a, n1, name, info;
	Font f, f2;
	JButton  b2;
	GameWin(int sec, MineFrame mf){ // 시간과 게임 객체를 매개변수로 받음
		this.setTitle("오옷!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		
		p = new JPanel();
		a = new JTextArea("게임 승리!!");
		n1 = new JTextArea("이름 입력: ");
		name = new JTextArea(); // 이름을 입력받음
		info = new JTextArea("맵 사이즈:"+Gamerm.size+" 시간:"+"");
		f = new Font("San Serif", Font.BOLD, 40);
		f2 = new Font("San Serif", Font.PLAIN, 20);
		b2 = new JButton("게임 종료");
		
		p.setLayout(null);
		
		b2.addActionListener(new ActionListener() { ///////////////////////// 종료 버튼 누르면, 최종 작업
			public void actionPerformed(ActionEvent e) {
				Gamerm.name = name.getText(); // 입력받은 이름 저장
				
				String s = Integer.toString(Gamerm.time)+"sec "+ Integer.toString(Gamerm.size-2) +"size, " + Gamerm.name; // 사이즈는 경계 제외(-2) , 'sec '로 시간과 구별
				
				try {
					GameRun.readMine(); // 순위를 불러와서
				} catch (IOException e1) {
					// TODO Auto-generated catch block, IOException을 해결하기 위한 try-catch
					e1.printStackTrace();
				}
				
				System.out.println("ERR2");
				GameRun.putMine(s); // 새로운 순위를 넣고		
				System.out.println("ERR3");
				
				try {
					GameRun.WriteMine(); // 다시 파일에 순위를 써넣는다.
					} catch (IOException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				System.out.println("ERR4");
				
				Gamerm.clear(); // 게임 정보 초기화
				dispose(); // 현재 창 닫고
				mf.dispose(); // 매개변수로 넘겨받은 게임을 종료
				GameStartMenu c = new GameStartMenu(); // 처음 화면으로 돌아감
			}
		});
		
		a.setEditable(false);
		a.setFont(f);
		a.setBackground(null);
		a.setForeground(Color.BLUE);
		n1.setEditable(false);
		n1.setFont(f2);
		name.setFont(f2);
		
		a.setBounds(35,10,300,50);
		n1.setBounds(10,60,100,30);
		name.setBounds(120,60,130,30);
		b2.setBounds(85,100,100,40);
		
		p.add(a);
		p.add(n1);
		p.add(name);
		p.add(b2);
		this.add(p);
		this.setVisible(true);
	}
}
class GameOver extends JFrame{ // 게임 오버시에
	JPanel p;
	JTextArea a;
	Font f;
	JButton b1, b2;
	GameOver(MineFrame mf){
		this.setTitle("ㅠㅠ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		
		p = new JPanel();
		a = new JTextArea("게임오버 ㅠㅠ");
		f = new Font("San Serif", Font.BOLD, 40);
		b1 = new JButton("다시 시작");
		b2 = new JButton("게임 종료");
		b1.addActionListener(new ActionListener() { // 같은 사이즈와 지뢰 개수로 다시 시작
			public void actionPerformed(ActionEvent e) {
				int n1 = Gamerm.size; int n2 = Gamerm.reNum; // 사이즈와 지뢰 개수 저장 후 매개변수로 넣어줌
				Gamerm.clear(); // 게임 초기화 후
				mf.dispose(); // 게임 창 닫고
				dispose(); // 현재 창 종료 후
				MineFrame gf = new MineFrame(n1, n2); // 매개 변수를 갖는 지뢰 찾기 게임 생성
			}
		});
		b2.addActionListener(new ActionListener() { // 게임 초기화면으로 돌아감
			public void actionPerformed(ActionEvent e) {
				mf.dispose(); // 게임 창 닫고
				dispose(); // 창 닫고
				GameStartMenu c = new GameStartMenu(); // 처음 화면으로 돌아감
			}
		});
		
		a.setEditable(false);
		a.setFont(f);
		a.setBackground(null);
		a.setForeground(Color.RED);
		
		p.add(a);
		p.add(b1);
		p.add(b2);
		this.add(p);
		this.setVisible(true);
	}
}
