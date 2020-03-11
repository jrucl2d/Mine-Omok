import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random; // ������ ����ϱ� ����
import java.io.IOException; // IOException�� ����
import javax.swing.Timer; // Ÿ�̸� ����� ����

class Gamerm{ // ���� ���� static ������
	
	static String mineScore[]; // ����ã�� ���� ���
	static int index; // ����ã�� ���� �ε���
	static int size; // ����ã�� ���� ������
	static int time; // �� �ð�
	static boolean mine[][]; // ������ ��ġ�� ������ �迭, ������ true ������ false
	static int mineNum; // ������ ������ ������ ����
	static int reNum; // ���� ����� �� ���� ������ ������ ����
	static boolean finish= false; // ���� �� ����, ���� false�� �ʱ�ȭ
	static boolean gameover = false; // ���ӿ��� ����, ���� false�� �ʱ�ȭ
	static String name; // �̸��� �Է¹��� ����

	static int isMine(int i, int j) { // �ȹ��� �˻��ؼ� ������ ������ �����ϴ� �Լ�
		int n = 0; // ������ ������ �� ����
		if(i>=1 && j>=1 && i<size-1 && j<size-1) { // �迭�� index out of bounds�� ���� ���� �ڸ� ����
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
	
	static void clear() { // static ���� �ʱ�ȭ �Լ�
		
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
	
	JPanel inputMine; // ������, ���� ���� �Է¹޴� �г�
	JButton exitGame;
	JButton enter; // �Է��� ������ ���� ����
	JTextField f1,f2, f3, f4, f5, f6;

	
	Mine(){
		
		// ������Ʈ ����
		inputMine = new JPanel();
		exitGame = new JButton ("���� ����");
		f1 = new JTextField("���� X���� ��ư ���� �Է� (10~50 ������ ����)");
		f2 = new JTextField("���� ���� �Է�");
		f3 = new JTextField(); // ��ư �� �Է¹���
		f4 = new JTextField(); // ���� ���� �Է�
		enter = new JButton("���� ����");
		
		// ������ ����
		this.setTitle("���� ã�� ����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.add(inputMine);
		inputMine.setLayout(null);
		
		// ������Ʈ ��ġ ����
		f1.setEditable(false);
		f2.setEditable(false);
		exitGame.setBounds(120, 20, 140, 30);
		f1.setBounds(10,60, 270, 30);
		f2.setBounds(10,140, 270, 30);
		f3.setBounds(10,100, 200, 30);
		f4.setBounds(10,180, 200, 30);
		enter.setBounds(120, 240, 140, 30);
		
		//�гο� ������Ʈ ����
		inputMine.add(exitGame);
		inputMine.add(f1);
		inputMine.add(f2);
		inputMine.add(f3);
		inputMine.add(f4);
		inputMine.add(enter);
		
		exitGame.addActionListener(this); // ���� ����
		enter.addActionListener(this); // ���� ����
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitGame) { // ���� ��ư�� ������
			GameStartMenu game = new GameStartMenu();
			this.dispose(); // �Է�â ����
		}
		if(e.getSource() == enter) { // ���� ���� ��ư�� ������
			int size = 0; int mineNum = 0;
			size = Integer.parseInt(f3.getText())+ 2; // �Է¹��� ������ ����, �����ڸ� ���� 2�� ����
			mineNum = Integer.parseInt(f4.getText()); // �Է¹��� ���� ���� ����
			MineFrame gf = new MineFrame(size, mineNum); // �Ű� ������ ���� ���� ã�� ���� ����
			this.dispose(); // �Է�â ����
		}
	}
}
class MineFrame extends JFrame implements ActionListener{
	
	JPanel mineP; // ����ã�� ��
	JPanel info; // ������ ǥ�õ� �κ�
	JButton bt[][];
	JLabel mineNum; // ������ ������ ǥ�õ� �κ�
	JButton face; // ������ ��ư
	JButton exit; // ���� ��ư
	int fakeNum; // ǥ�ÿ� ���� ����
	int realSize; // �ε����� �ִ밪
	
	//////////////////////////// �̹��� ������
	ImageIcon sm;// ������ ������
	ImageIcon sunsm; // �⺻ ���۶� �� ������
	ImageIcon minenormal; // �⺻���� �̹���
	ImageIcon flag; // ���, ��Ŭ�� �� �� ��
	ImageIcon question; // ����ǥ, ��Ŭ�� �� �� ��
	ImageIcon unfoundmine; // �� ã�� ����
	ImageIcon clickedmine; // ���ڸ� ����, ���ӿ���
	ImageIcon nomine; // ���� ���� �κ�
	ImageIcon gameover; // ���ӿ����� ǥ�õ� ��� ������
	ImageIcon wrong; // �߸� ã�� �κ�
	ImageIcon m1, m2, m3, m4, m5, m6, m7, m8; // ����  ����
	
	JLabel time; // �ð��� ǥ�õ� ��
	int sec; // �ð�
	Timer timer;
	boolean firstClick = false; // ó�� Ŭ���Ǵ� ���� �ľ�
	
	void numPop(int indexa, int indexb) { // �ε����� �Ű������� �޾Ƽ� �ش� ���ڷ� �̹����� �ٲ��ִ� �Լ�
		if(Gamerm.mine[indexa][indexb]) return;  // �ش� �ε����� �������� ���� ��쿡 �� �ؼ�(���� �̹����� �ٲ�� �� �ǹǷ�)
		int num = Gamerm.isMine(indexa, indexb);
		switch(num) { // �ֺ� ���� ������ ���� ���ڷ� ǥ������
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
		if(i>=1 && j>=1 && i<realSize && j<realSize) { // �����ڸ� ����			
			bt[i][j].setIcon(nomine); // �ڱ� ��ġ�� ���� ���� �̹����� �ٲ�
			if(Gamerm.isMine(i-1, j-1) > 0) numPop(i-1, j-1); // �ȹ��� ���ھ��� �̹��� Ȥ�� ���� �гη� �ٲ�
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
	void pop1(int i, int j) { // 8������ ���ڰ� ���� ������ ����ؼ� ��Ʈ��
		if(i>=1 && j>=1 && i<realSize && j<realSize) { // �����ڸ� ����
			if(Gamerm.isMine(i, j) > 0) {numPop(i,j); return;} // ���� �г��� �߰��ϸ� ���ڸ� ǥ���ϰ� �Լ��� ����
			popAround(i,j);
		} 
	}	
	int popStop() {//////////////////////////////////////���� ������ ��ó�� ���� ����
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
	MineFrame(int a, int b){ // ������� ���� ������ �Ű������� �޴� ������
		
		sec = 0; // 0���� �ð��� ��
		timer = new Timer(1000, new ActionListener () { // Ÿ�̸� ����
			public void actionPerformed(ActionEvent e) {
				time.setText("�ð� : " + Integer.toString(sec));
				sec += 1; // 1�ʸ��� 1�� �þ
			}
		}); // Ÿ�̸� ����
		
		realSize = a-1; // �ε����� �ִ밪
		
		Gamerm.size = a; // ����ã�� �� ����������
		Gamerm.mineNum = b; // ���� ���� ����
		Gamerm.reNum = b;
		fakeNum = b;
		Gamerm.mine = new boolean[a][a]; // ������ ũ���� ���ڰ� �ż��� ���ɼ��� �ִ� �迭 ����
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� ��ư
		this.setTitle("���� ã�� ����"); 
		this.setLayout(new BorderLayout()); // BorderLayout���� ������ ����
		this.setSize(a*22,a*23); // �Ű������� ���� a ���� ���� ������ ũ�⸦ ����
		this.setResizable(false); // ������ ������ ���� �Ұ�
		
		mineP = new JPanel(); // ���� ���
		mineP.setLayout(new GridLayout(a,a)); // a*a ���� ��ư�� ���� �г�
		bt= new JButton[a][a]; // ��ư�� ���� a���� ������ �迭 ����
		info = new JPanel();
		exit = new JButton("���� ����");
		mineNum = new JLabel("���� : " + fakeNum); // ���� ���� ǥ��
		time = new JLabel("�ð�     ");
		face = new JButton();
		
		sm = new ImageIcon("smile.jpg"); // ������ �̹���
		sunsm = new ImageIcon("sunglasssm.jpg"); // �⺻ ���۶� �� ������ �̹���
		minenormal = new ImageIcon("minenormal.jpg"); // �⺻ ����ã�� ��
		unfoundmine = new ImageIcon("unfoundmine.jpg"); // ã�� ���� �̹���
		flag = new ImageIcon("flag.jpg"); // ���ǥ��
		question = new ImageIcon("question.jpg"); // ����ǥ ǥ��
		clickedmine = new ImageIcon("clickedmine.jpg"); // ���� ���� ���ӿ���
		nomine = new ImageIcon("nomine.jpg"); // ���� ���� �κ�
		gameover = new ImageIcon("gameover.jpg"); // ���ӿ���
		wrong = new ImageIcon("wrong.jpg"); // �߸� ã�� ����
		m1 = new ImageIcon("1.jpg"); // ���� ����
		m2 = new ImageIcon("2.jpg");
		m3 = new ImageIcon("3.jpg");
		m4 = new ImageIcon("4.jpg");
		m5 = new ImageIcon("5.jpg");
		m6 = new ImageIcon("6.jpg");
		m7 = new ImageIcon("7.jpg");
		m8 = new ImageIcon("8.jpg");
	
		
		for(int i = 0; i <a; i++) { // �ุŭ �ݺ�
			for(int j = 0; j <a; j++) { // ����ŭ �ݺ�
				bt[i][j] = new JButton(); // ��ư ����
				mineP.add(bt[i][j]); // �гο� ����
				bt[i][j].setIcon(minenormal); // ����ã�� �� �̹����� ����
				bt[i][j].addActionListener(this); // ��ư�� �̺�Ʈ �߰�
				Gamerm.mine[i][j] = false;// ���ڸ� ��� false�� �ʱ�ȭ 
				bt[i][j].addMouseListener(new MouseAdapter() { /////// ���콺 ��Ŭ�� ��
					   public void mousePressed(MouseEvent e) {
					    if(e.getModifiers() == MouseEvent.BUTTON3_MASK) {  // ��Ŭ���� �����ϸ�
					    	//////////// ����ó��
					    	if(Gamerm.finish|| Gamerm.gameover) return; // ������ �̱�ų� ���� ������ Ŭ�� ����
					    	int  indexa = 0, indexb = 0; // ���� ��ư�� �ε����� ������ ����
					    	for(int i = 0 ; i <a ;i++) {
					    		for(int j = 0; j< a; j++) {
					    			if(e.getSource() == bt[i][j]) { indexa = i; indexb = j;} // ���� ��ư�� �ε��� �ľ�
					    		}
					    	}
					    	if(bt[indexa][indexb].getIcon() == minenormal) { // �⺻ �̹������� ���� �� 
					    		bt[indexa][indexb].setIcon(flag); // �̹����� ��߷� �ٲٰ�
					    		fakeNum--; // ���� ���� �ϳ� ���̰� ǥ��
					    		mineNum.setText("���� : " + fakeNum);
					    		///////// ���� ��¥ ���ڸ� �߰��ϸ�
					    		if(Gamerm.mine[indexa][indexb]) {
					    			Gamerm.mineNum--; // ��¥ ���� ������ ����
					    			
					    			//////////////////////////////////////// ���� �¸�
					    			if(Gamerm.mineNum == 0 && fakeNum == 0) { // ��¥ ���� ���� ������ 0�̰� ǥ�õ� ���� ���� 0�̸� �¸�
					    				timer.stop(); // Ÿ�̸� ����
					    				face.setIcon(sm); // �� ���� ǥ�÷� ����
					    				exit.setText("���ڸ� ��� ã�ҽ��ϴ�. �ƹ� ��ư�̳� Ŭ���Ͻʽÿ�."); // ���� ������ ��ư Ŭ���� ����
					    				exit.setBackground(Color.RED); // �� �������� �ü��� ��
					    				exit.setForeground(Color.WHITE);
					    				Gamerm.finish = true; // finish�� true�� ��. ��ư �̺�Ʈ�� finish�� ��츦 �߰��س���.
					    			}
					    		}
					    	}
					    	else if(bt[indexa][indexb].getIcon() == flag) { // ����� �� �ٽ� Ŭ���ϸ�
					    		bt[indexa][indexb].setIcon(question); // �̹����� ����ǥ�� �ٲٰ�
					    		fakeNum++; // ���� ���� �ϳ� �ø��� ǥ��
					    		mineNum.setText("���� : " + fakeNum);
					    		///////// ���� ��¥ ���ڸ� ����ϸ�
					    		if(Gamerm.mine[indexa][indexb]) {
					    			Gamerm.mineNum++; // ��¥ ���� ������ ����
					    		}
					    	}
					    	else if(bt[indexa][indexb].getIcon() == question) {
					    		bt[indexa][indexb].setIcon(minenormal); // �⺻ �̹����� �ٽ� ����
					    	}
					    }
					 }
				});
				if(i == 0 || i == realSize || j == 0 || j == realSize) { // �����ڸ��� ��ư����
					bt[i][j].setEnabled(false); // ��� �Ұ� ó��
					bt[i][j].setVisible(false); // ������ �ʰ� ó��
				}
			}
		}	
		exit.addActionListener(this); // ���� ��ư �̺�Ʈ�߰�
		face.addActionListener(this); // �� ��ư �̺�Ʈ �߰�
		
		exit.setBackground(Color.green);
		exit.setForeground(Color.blue);
	
		face.setIcon(sunsm); // ���۶� ������ �׸� ��ư�� �߰�
		face.setBackground(null); // ������ ��ư �׸��� ���̵��� ����
		face.setBorder(null);
		
		info.add(mineNum);
		info.add(face);
		info.add(time);
		
		add(info, BorderLayout.NORTH); // ���� �κ��� ������ ���ʿ� ����
		add(mineP, BorderLayout.CENTER); // �г��� ������ �߾ӿ� ����
		add(exit, BorderLayout.SOUTH); // ������ �Ʒ��� ���� ��ư �߰�
		setVisible(true); // ����ȭ
		
		// ���� ���� �κ�	
		Random random = new Random(); // ���� ��ü ����
		
			for(int i = 0; i<b;i++) { // ���� ������ŭ �ݺ�
				int x = random.nextInt(a); // �������� 0���� a-1 ������ ����(�ε���)�� ���´�.
				int y = random.nextInt(a);
				if(Gamerm.mine[x][y] || x == 0 || y == 0 || x == realSize || y == realSize) { // �̹� �� ��ġ�� ���ڰ� �����ϰų� �����ڸ��� i�� �ϳ� ����
					i--;
				}
				Gamerm.mine[x][y] = true; // ������ ��ġ�� ����	
				if( x == 0 || y == 0 || x == realSize || y == realSize) Gamerm.mine[x][y] = false;
			}	
	}
	@Override
	public void actionPerformed(ActionEvent e) {	
		/////////////////////////// ����ó��
		if(Gamerm.gameover) return; // ���ӿ��� ���ϸ� ��ư �̺�Ʈ ���� ����
		if(!firstClick) {timer.start(); firstClick = true;} // ó�� Ŭ���� �������� Ÿ�̸� ����
		////////////////////////////���� ���� Ŭ����
		if(e.getSource() == exit) { 
			dispose();
			Gamerm.clear(); // ���� ���� �ʱ�ȭ 
			GameStartMenu a = new GameStartMenu();
		}
		/////////////////////////// ������ ������ Ŭ����
		if(e.getSource() == face) {
			int n1 = Gamerm.size; int n2 = Gamerm.reNum; // ������� ���� ���� ���� �� �Ű������� �־���
			Gamerm.clear(); // ���� �ʱ�ȭ ��
			dispose(); // ���� â ���� ��
			MineFrame gf = new MineFrame(n1, n2); // �Ű� ������ ���� ���� ã�� ���� ����
		}
		//////////////////��ư�� ������ �� �ε��� ã�� �κ�
		int indexa = 0; int indexb = 0; // �ε��� ���� ����
		for(int i = 0; i <Gamerm.size;i++) {
			for(int j = 0; j< Gamerm.size;j++) {
				if(e.getSource() == bt[i][j]) {
					indexa = i; indexb = j; // �ε��� ����
				}
			}
		}	
		/////////////////////// ����ó�� 2
		if(bt[indexa][indexb].getIcon() == nomine && !Gamerm.finish) return; // ������ �� �����µ� ���� ���� �� Ȯ�ε� ���� Ŭ���ÿ��� ��ư �̺�Ʈ ���� ����
		if(bt[indexa][indexb].getIcon() == flag) return; // ����� �κ��� Ŭ���ϸ� ���ڰ� �����Ƿ� �̺�Ʈ ���� ����
		///////////////// ���ڸ� ������ ��
		if(Gamerm.mine[indexa][indexb]) {
			Gamerm.gameover = true; // ���ӿ��� ǥ��
			face.setIcon(gameover); // �� ���� �����Ϸ� �ٲ�
			timer.stop(); // Ÿ�̸� ����
			for(int i = 0; i < Gamerm.size;i++) {
				for(int j = 0; j<Gamerm.size;j++) {
					if(bt[i][j].getIcon() == flag && !Gamerm.mine[i][j]) { // ���ڰ� �ƴѵ� ��� ģ �κ�
						bt[i][j].setIcon(wrong); // �߸� ã��
					}
					if(Gamerm.mine[i][j] && bt[i][j].getIcon() != flag) { // ã�� ���� ����(��� �� ģ ����)
						bt[i][j].setIcon(unfoundmine); // �� ã��
					} bt[indexa][indexb].setIcon(clickedmine); // Ŭ���� ���ڴ� ���� ǥ��
				}
			}
			GameOver n = new GameOver(this); // , ���� ���� ��ü�� �Ű������� �Ѱܼ� ���� ���� â ���
		}
		///////////////// ��ó�� ���ڰ� ���� ���� ������ ��
		if(Gamerm.isMine(indexa, indexb) == 0 && !Gamerm.mine[indexa][indexb]) { /////////////////////////////////////// ���� �ȹ��� �� �������̸�
			if(Gamerm.isMine(indexa-1, indexb-1) > 0 && Gamerm.isMine(indexa-1, indexb) > 0 && Gamerm.isMine(indexa-1, indexb+1) > 0
					&& Gamerm.isMine(indexa, indexb-1) > 0 && Gamerm.isMine(indexa, indexb+1)  > 0 
					&& Gamerm.isMine(indexa+1, indexb-1) > 0 && Gamerm.isMine(indexa+1, indexb) > 0
					&& Gamerm.isMine(indexa+1, indexb+1) > 0) {
				bt[indexa][indexb].setIcon(nomine); // ����� ��ĭ���� ó���ϰ� �ȹ��� �� ���� ������ �ٲ��� ��
				numPop(indexa-1, indexb-1); numPop(indexa-1, indexb); numPop(indexa, indexb-1); numPop(indexa, indexb+1); 
				numPop(indexa+1, indexb-1); numPop(indexa+1, indexb); numPop(indexa+1, indexb+1); numPop(indexa-1,indexb+1);
			return; // ����
			}
			System.out.println("ERR1");
			pop1(indexa,indexb); // �������� ��쿡 pop �Լ��� ���� ���� ���� ������ ��ư �̹��� ����
			
			int n = popStop(); // ó�� �� �� ĭ �����޾Ƽ� 
			
			for(int k = 0; k < n+3; k ++) { // ������ Ƚ�� (�� Ƚ�� + 3)��ŭ ó��
				for(int i = 1; i < realSize;i++) { 
					for(int j = 1; j < realSize;j++) { //////////////////////////////// ���ϴٰ� �� �� �߽߰� �� �� ���� �ٽ� ��
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
		///////////////// ��ó�� ���ڰ� �ִ� ���� ������ ��
		if(Gamerm.isMine(indexa, indexb) > 0) { // ��ó�� ���ڰ� �����ϸ�
			numPop(indexa, indexb);
		}
    	/////////////////////// ���� ������
    	if(Gamerm.finish) {
    		Gamerm.time = sec; // ������ �� �ð� ����
    		GameWin w = new GameWin(Gamerm.time, this); // �ð��ʿ� ���� ������ ���� ��ü�� �Ű������� �Ѱ���
    	}
	}
}
class GameWin extends JFrame{ // ���� �¸�
	JPanel p;
	JTextArea a, n1, name, info;
	Font f, f2;
	JButton  b2;
	GameWin(int sec, MineFrame mf){ // �ð��� ���� ��ü�� �Ű������� ����
		this.setTitle("����!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		
		p = new JPanel();
		a = new JTextArea("���� �¸�!!");
		n1 = new JTextArea("�̸� �Է�: ");
		name = new JTextArea(); // �̸��� �Է¹���
		info = new JTextArea("�� ������:"+Gamerm.size+" �ð�:"+"");
		f = new Font("San Serif", Font.BOLD, 40);
		f2 = new Font("San Serif", Font.PLAIN, 20);
		b2 = new JButton("���� ����");
		
		p.setLayout(null);
		
		b2.addActionListener(new ActionListener() { ///////////////////////// ���� ��ư ������, ���� �۾�
			public void actionPerformed(ActionEvent e) {
				Gamerm.name = name.getText(); // �Է¹��� �̸� ����
				
				String s = Integer.toString(Gamerm.time)+"sec "+ Integer.toString(Gamerm.size-2) +"size, " + Gamerm.name; // ������� ��� ����(-2) , 'sec '�� �ð��� ����
				
				try {
					GameRun.readMine(); // ������ �ҷ��ͼ�
				} catch (IOException e1) {
					// TODO Auto-generated catch block, IOException�� �ذ��ϱ� ���� try-catch
					e1.printStackTrace();
				}
				
				System.out.println("ERR2");
				GameRun.putMine(s); // ���ο� ������ �ְ�		
				System.out.println("ERR3");
				
				try {
					GameRun.WriteMine(); // �ٽ� ���Ͽ� ������ ��ִ´�.
					} catch (IOException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				System.out.println("ERR4");
				
				Gamerm.clear(); // ���� ���� �ʱ�ȭ
				dispose(); // ���� â �ݰ�
				mf.dispose(); // �Ű������� �Ѱܹ��� ������ ����
				GameStartMenu c = new GameStartMenu(); // ó�� ȭ������ ���ư�
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
class GameOver extends JFrame{ // ���� �����ÿ�
	JPanel p;
	JTextArea a;
	Font f;
	JButton b1, b2;
	GameOver(MineFrame mf){
		this.setTitle("�Ф�");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		
		p = new JPanel();
		a = new JTextArea("���ӿ��� �Ф�");
		f = new Font("San Serif", Font.BOLD, 40);
		b1 = new JButton("�ٽ� ����");
		b2 = new JButton("���� ����");
		b1.addActionListener(new ActionListener() { // ���� ������� ���� ������ �ٽ� ����
			public void actionPerformed(ActionEvent e) {
				int n1 = Gamerm.size; int n2 = Gamerm.reNum; // ������� ���� ���� ���� �� �Ű������� �־���
				Gamerm.clear(); // ���� �ʱ�ȭ ��
				mf.dispose(); // ���� â �ݰ�
				dispose(); // ���� â ���� ��
				MineFrame gf = new MineFrame(n1, n2); // �Ű� ������ ���� ���� ã�� ���� ����
			}
		});
		b2.addActionListener(new ActionListener() { // ���� �ʱ�ȭ������ ���ư�
			public void actionPerformed(ActionEvent e) {
				mf.dispose(); // ���� â �ݰ�
				dispose(); // â �ݰ�
				GameStartMenu c = new GameStartMenu(); // ó�� ȭ������ ���ư�
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
