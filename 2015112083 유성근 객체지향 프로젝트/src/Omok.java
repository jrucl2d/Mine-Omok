import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.io.IOException; // IOException�� ����
import java.util.Random; // ������ ����ϱ� ����
import javax.swing.Timer; // Ÿ�̸� ����� ����



class Gamer{ // ���̸ӵ��� ������ �����ϴ� Ŭ����
	static String omokScore[]; // ���� ������ ������ ���ڿ� �迭
	static int index; // ���� ���� ���ڿ� �迭�� �ε���
	static String g1; // ���̸� 1, 2 �̸�
	static String g2;
	static String winner;
	
	static int size; // ������ ������
	static boolean turn = false; // �� �� ���� ������ ���� (���ʿ� false, ���� ����)
	static int sooB = 0; // �� �� ���� ������ ���� ó���� 0���� �ʱ�ȭ
	static int sooW = 0;
	static boolean bpan[][]; // ������ ���� �迭
	static boolean wpan[][]; // �� ���� �迭
	static int winSoo; // �̱� ��
	static boolean finish = false; // ���� �� ����, ���� false�� �ʱ�ȭ
	
	static void clear() { // ������ �ʱ�ȭ(static ������ �ʱ�ȭ)
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				bpan[i][j] = false;
				wpan[i][j] = false;
			}
		}
		g1 = null; // ���̸� 1, 2 �̸�
		g2 = null;
		winner = null;
		size = 0; // ������ ������
		turn = false; // �� �� ���� ������ ���� (���ʿ� false, ���� ����)
		sooB = 0; // �� �� ���� ������ ���� ó���� 0���� �ʱ�ȭ
		sooW = 0;
		winSoo = 0; // �̱� ��
		finish = false; // ���� �� ����
	}
}

public class Omok extends JFrame implements ActionListener {
	
	JPanel inputOmok; // �Է��ϴ� �г�
	JButton exitGame;
	JButton order; // �Է��� ������ ���� ����
	JTextField f1,f2, f3, f4, f5, f6, info;

	Omok(){
		// ������Ʈ ����
		inputOmok = new JPanel();
		exitGame = new JButton ("���� ����");
		f1 = new JTextField("����� 1 �̸�");
		f2 = new JTextField("����� 2 �̸�");
		f3 = new JTextField(); // ����� 1 �̸� �Է¹���
		f4 = new JTextField(); // ����� 2 �̸� �Է¹���
		f5 = new JTextField("�ٵ��� ũ��(20~50)");
		f6 = new JTextField();
		info = new JTextField("   ** ���� ���� ���� �����ϴ� **");
		order = new JButton("���� ���ϱ�");
		
		// ������ ����
		this.setTitle("���� ����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.add(inputOmok);
		inputOmok.setLayout(null);
		
		// ������Ʈ ��ġ ����
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
		
		// ������Ʈ ����
		inputOmok.add(exitGame);
		inputOmok.add(f1);
		inputOmok.add(f2);
		inputOmok.add(f3);
		inputOmok.add(f4);
		inputOmok.add(f5);
		inputOmok.add(f6);
		inputOmok.add(info);
		inputOmok.add(order);
		
		exitGame.addActionListener(this); // ���� ����
		order.addActionListener(this); // ���� ���ϱ�
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitGame) {
			GameStartMenu game = new GameStartMenu();
			this.setVisible(false);
		}
		if(e.getSource() == order) {
			Gamer.g1 = f3.getText(); // �Է¹��� �̸� ���̸� ������ ����
			Gamer.g2 = f4.getText();
			Gamer.size = Integer.parseInt(f6.getText()) + 2; // ���� �����ڸ��� �� ���� ���� ����ؼ� ����
			
			Order o = new Order();
			this.setVisible(false);
		}
	}
}
class Order extends JFrame implements ActionListener{ // ���� �İ� ���� ���ϴ� Ŭ����
	JPanel p1;
	JButton b1, b2, b3;
	JTextField f1,f2;
	ImageIcon foreim, backim;
	JLabel p1lb, p2lb, firstlb;
	
	Order() {
		
	this.setTitle("���� ���ϱ�");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(400, 400);
	
	b1 = new JButton("�� �� ����");
	p1lb = new JLabel(); 
	foreim = new ImageIcon("fore.jpg");
	p2lb = new JLabel();
	backim = new ImageIcon("back.jpg");
	firstlb = new JLabel(); // ������ ����
	p1 = new JPanel();
	p1.setLayout(null);
	f1 = new JTextField(Gamer.g1 +"player�� ����");
	f2 = new JTextField(Gamer.g2 +"player�� ����" );
	b2 = new JButton("������?");
	b3 = new JButton("���� ����");

	Random rand = new Random(); // ���� ��ü ����
	boolean r = rand.nextBoolean(); // true or false�� �������� ����
	boolean first = rand.nextBoolean(); // true�� �ո�, false��
	b1.setBounds(120, 20, 140, 30);
	f1.setBounds(10,60, 150, 30);
	f2.setBounds(210,60, 150, 30);
	b2.setBounds(10,240,140,30);
	p1lb.setBounds(30,60,200,200);
	p2lb.setBounds(240,60,200,200);
	firstlb.setBounds(200, 200, 180, 200);
	b3.setBounds(10,300,140,30);
	
	if(r) { // �������� �ո� �޸��� �ش� �÷��̾� �Ʒ��� ������
		p1lb.setIcon(foreim);
		p2lb.setIcon(backim);
	}
	else {
		p1lb.setIcon(backim);
		p2lb.setIcon(foreim);
	}
	if(first) { // �ո��̸�
		firstlb.setIcon(foreim);
	}
	else { // �޸��̸�
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
	
	f1.setVisible(false); // ��ư�� ���� �� �������� ���ؼ� �񰡽�ȭ�� ������ ������Ʈ��
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
		if(e.getSource() == b1) { // ��, �ڸ� ����
			f1.setVisible(true); f2.setVisible(true);
			p1lb.setVisible(true); p2lb.setVisible(true);
			b2.setVisible(true);
		}
		if(e.getSource() == b2) {
			firstlb.setVisible(true); b3.setVisible(true);
			if(firstlb.getIcon() == p1lb.getIcon()) { // firstlb�� ���� �̹����� ���� �÷��̾ ����
				 System.out.println("p1�� ����"); 
				 Gamer.turn = false;
			}
			else if(firstlb.getIcon() == p2lb.getIcon()) {
				 System.out.println("p2�� ����");
				 String tmp = Gamer.g1; // ������ �̸��� g1��, �İ��� �̸��� g2�� �ٲ�
				 Gamer.g1 = Gamer.g2;
				 Gamer.g2 = tmp;
				 Gamer.turn = false;
			}
		}
		if(e.getSource() == b3) {
			this.dispose();
			OmokFrame of = new OmokFrame(Gamer.size); // ���� ���� ����
		}
	}
}
class OmokFrame extends JFrame implements ActionListener{ // �������� �ִ� ���� ������
	JPanel omokP = new JPanel();
	JButton [][] bt;
	JPanel info;
	JTextField name1, name2; // ������� ����, �İ�
	ImageIcon w,b;
	ImageIcon p,t,nt;
	JLabel t1, t2;
	JButton mool, exit;
	JButton backSoo; // ���� ��
	JLabel time; // �ð��� ǥ�õ� ��
	int sec; // �ð�
	int sizeforIndex; // �ε����� ���� ������
	
	int mCountb = 1; // ���� �� �ִ� Ƚ�� �� ��
	int mCountw = 1;
	int indexa = 0; // Ŭ���� ��ư�� �ε��� ����
	int indexb = 0; 
	Timer timer;
	JTextField end; // ������ ���� ǥ���� ����
	Font f;
	
	OmokFrame(int a){ // ����� �Ű������� �޴� ������
		
		sizeforIndex = a-1; // �ε����� ���� ������, ������-1
		sec = 20; // 20���� �ð��� ��
		timer = new Timer(1000, new ActionListener () { // Ÿ�̸� ����
			public void actionPerformed(ActionEvent e) {
				if(sec<0) { // Ÿ�̸Ӱ� 0�� �Ǵ� ����
					timer.stop(); // Ÿ�̸� ����
					sec = 20;
					timer.restart();
					if(Gamer.turn) { // ������ ���ʿ��ٸ�
						Gamer.turn = false; // ������� ���� �Ѿ
						t1.setIcon(t); // �� ���� ���� �ҷ�
						t2.setIcon(nt);
					}
					else { // ��� ���ʿ��ٸ�
						Gamer.turn = true; // ���������� ���� �Ѿ
						t1.setIcon(nt);
						t2.setIcon(t); // ���� ���� ���� �ҷ�
					}
				}
				time.setText(Integer.toString(sec));
				sec -= 1; // 1�ʸ��� 1�� �پ��
			}
		}); // Ÿ�̸� ����
		timer.start(); // Ÿ�̸� ����
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� ��ư
		this.setTitle("���� ����"); 
		this.setLayout(new BorderLayout()); // BorderLayout���� ������ ����
		this.setSize(a*22,a*23); // �Ű������� ���� a ���� ���� ������ ũ�⸦ ����
		this.setResizable(false); // ������ ������ ���� �Ұ�
		
		Gamer.bpan = new boolean[a][a]; // ��ư�� ���°� ����� �迭
		Gamer.wpan = new boolean[a][a];
		
		omokP = new JPanel(); // ���� �г�
		omokP.setLayout(new GridLayout(a,a)); // a*a ���� ��ư�� ���� �г�
		bt = new JButton[a][a]; // ��ư�� a�� a���� ������ �迭 ����
		w = new ImageIcon("wdol.jpg");
		b = new ImageIcon("bdol.jpg");
		p = new ImageIcon("pandegi.jpg");
		
		info = new JPanel(); // �ð� �� ������ ǥ�õǴ� �г�
		name1 = new JTextField(Gamer.g1+"  "); name1.setEditable(false); // ���� �̸�
		name2 = new JTextField(Gamer.g2+"  "); name2.setEditable(false); // �İ� �̸�
		t = new ImageIcon("turn.jpg"); // ���� ���ƿ��� ������
		nt = new ImageIcon("noturn.jpg"); // ���� �ƴϸ� ȸ����
		t1 = new JLabel(); t1.setIcon(t); // ������ ������
		t2 = new JLabel(); t2.setIcon(nt); // �İ��� �����
		mool = new JButton("�� �� ������"); // ������ ��ư
		exit = new JButton("��������"); // ���� ���� ��ư
		time = new JLabel("�ð�");
		end = new JTextField("��");
		f = new Font("San Serif", Font.BOLD, 13);
		
		info.add(name1);
		info.add(t1); // ���� ����
		info.add(name2);
		info.add(t2); // �İ� ����
		info.add(mool);
		info.add(exit);
		info.add(time);
		info.add(end); // ���� ������ �ȳ� ���� ǥ��
		
		end.setFont(f);
		end.setBackground(Color.red);
		end.setBorder(null);
		end.setForeground(Color.white);
		end.setVisible(false);
		
		for(int i = 0; i <a; i++) { // �ุŭ �ݺ�
			for(int j = 0; j <a; j++) { // ����ŭ �ݺ�
				bt[i][j] = new JButton(); // ��ư ����, ��ư �̹��� ����
				omokP.add(bt[i][j]); // �гο� ����
				if(i>0&&i<a-1 && j>0 && j<a-1) {
				bt[i][j].setIcon(p); // ���� �� �̹����� ����
				bt[i][j].setBorderPainted(false); // ��ư ��輱 �� ���̵��� ����
				bt[i][j].addActionListener(this); // ��� ��ư�� �̺�Ʈ ����
				}
				else {
					bt[i][j].setVisible(false); // �����ڸ� ��ư�� �� ���̵��� ����
				}
			}
		}
		
		mool.addActionListener(this); // �̺�Ʈ �߰�
		exit.addActionListener(this);
		
		add(omokP, BorderLayout.CENTER); // ������ �г��� ������ �߾ӿ� ����
		add(info, BorderLayout.NORTH); // ������ ǥ�õǴ� �г��� ������ ���ʿ� ����
		setVisible(true); // ����ȭ
	}
	public void actionPerformed(ActionEvent e) {	
		
		timer.stop(); // ��ư�� ������ Ÿ�̸� �ʱ�ȭ
		sec = 20;
		timer.restart();
		
		if(e.getSource() == exit) { // �����ư ������
			Gamer.clear(); // ���� ���� �ʱ�ȭ
			this.dispose(); // ����â ����
			GameStartMenu g = new GameStartMenu();
		}
		if(e.getSource() == mool) { // �Ѽ� ������ ������
			if(Gamer.turn) { // ���� �� ���ʿ�����
				if(mCountw>0 && backSoo != null) { // ������ ��� Ƚ���� ������ ����, backSoo�� null�̸� ��� ���� �����⸦ �ߴٴ� ��(���� ������ ����)
					mCountw--; // �� ���� ������ ��������Ƿ� ī��Ʈ ����
					backSoo.setIcon(p); // �ֱٿ� ���� ��ư�� �ٽ� ������ �׸����� �ٲٰ�
					backSoo = null; // backSoo�� null�� ����� ��� ���� �����⸦ �ߴٴ� ����� ǥ��
					Gamer.turn = false; // �ٽ� �� �� ���ʷ� �ٲ���
					Gamer.sooW--; // �� ���� ����  �ϳ� ����
					t1.setIcon(t); // �� ���� ���� �ҷ�
					t2.setIcon(nt);
					Gamer.wpan[indexa][indexb] = false; // �򵹿� ���� ������ ǥ�õ� ���� �ٽ� �ʱ�ȭ
					System.out.println(Gamer.sooW); // �� ���
					System.out.println(Gamer.sooB);
					}
				}
				else { // �� �� ���ʿ�����
					if(mCountb>0 && backSoo != null) { // ������ ��� Ƚ���� ������ ����, ���� ������ ����
					mCountb--; // �������� ������ ��������Ƿ� ī��Ʈ ����
					backSoo.setIcon(p); // �ֱٿ� ���� ��ư�� �ٽ� ������ �׸����� �ٲٰ�
					backSoo = null; // backSoo�� null�� ����� ��� ���� �����⸦ �ߴٴ� ����� ǥ��
					Gamer.turn = true; // �ٽ� ���� �� ���ʷ� �ٲ���
					Gamer.sooB--; // ���� ���� ���� �ϳ� ����
					t1.setIcon(nt);
					t2.setIcon(t); // ���� ���� ���� �ҷ�
					Gamer.bpan[indexa][indexb] = false; // �������� ���� ������ ǥ�õ� ���� �ٽ� �ʱ�ȭ
					System.out.println(Gamer.sooW); // �� ���
					System.out.println(Gamer.sooB);
					}
				}
			}
		for(int i = 0;i<Gamer.size;i++) { // ���� ���� ���
			for(int j = 0;j<Gamer.size;j++) {
				
			if(!Gamer.bpan[i][j] && !Gamer.wpan[i][j]) { // �̹� ���� ��ư�̸� �Ѿ
				if(e.getSource() == bt[i][j]) { // Ư�� ��ư�� ���ȴµ�
					backSoo = bt[i][j]; // ���� �ֱٿ� ���� ��ư ����
					indexa = i; indexb = j; // �ε��� ����
					
					System.out.println(i +" "+j);
					if(!Gamer.turn) { // �� ���� ���� ���ʿ�����
						bt[i][j].setIcon(w); // �� ���� ������ �׸����� �ٲ�
						Gamer.wpan[i][j] = true; // �� ���� ������ ��ġ ����
						Gamer.turn = true; // ���� ���� ������ �Ѱ���
						Gamer.sooW++; // �� �� �� ����
						t1.setIcon(nt);
						t2.setIcon(t); // ���� ���� ���� �ҷ�
						
						//////////////////////////////////////////////�̱�� ���
						
						//// ���� �¿�� ������ ���
						int nu = i; // ����� ���� �� �ε���
						while(Gamer.wpan[nu][j]) {
							nu--;
						} nu++;
						int nd = i; // ����� ���� �Ʒ� �ε���
						while(Gamer.wpan[nd][j]) {
							nd++;
						} nd--;
						int nr = j; // ����� ���� ������ �ε���
						while(Gamer.wpan[i][nr]) {
							nr++;
						} nr--;
						int nl = j; // ����� ���� ���� �ε���
						while(Gamer.wpan[i][nl]) {
							nl--;
						}nl++;
						if((nr-nl== 4) && (nr-nl != 5)) { // �¿�� ������ ���, ���� ����
							time.setVisible(false); // �ð��� �� ���޷�
							end.setVisible(true); // �������� ǥ��
							Gamer.finish = true; 
							Gamer.winner = Gamer.g1; // ����(�鵹) �¸�
							Gamer.winSoo = Gamer.sooW; return;
						} 
						else if((nd-nu == 4)&&(nd-nu != 5)) { // ���Ϸ� ������ ���, ��������
							time.setVisible(false); // �ð��� �� ���޷�
							end.setVisible(true); // �������� ǥ��
							Gamer.finish = true; 
							Gamer.winner = Gamer.g1; // ����(�鵹) �¸�
							Gamer.winSoo = Gamer.sooW;  return;
						}
						//// �밢������ ������ ���
						// ������ �Ʒ�
						int rdx = j; int rdy = i; int lux = j; int luy = i;
						while(Gamer.wpan[rdy][rdx]) { 
							rdx++; rdy++;
						} rdx--; rdy--;
						
						while(Gamer.wpan[luy][lux]) { // ���� ��
							lux--; luy--;
						}lux++; luy++;
						if((rdx-lux == 4)&&(rdx-lux != 5)) { // ������ x�� y���� ���� Ŀ���Ƿ� x�� �ϳ��� ���ص� ��
							time.setVisible(false); // �ð��� �� ���޷�
							end.setVisible(true); // �������� ǥ��
							Gamer.finish = true; 
							Gamer.winner = Gamer.g1; // ����(�鵹) �¸�
							Gamer.winSoo = Gamer.sooW;  return;
						}
						// ������ ��
						int rux = j; int ruy = i; int ldx = j; int ldy = i;
						while(Gamer.wpan[ruy][rux]) { 
							rux++; ruy--;
						}rux--; ruy++;
						while(Gamer.wpan[ldy][ldx]) { // ���� �Ʒ�
							ldx--; ldy++;
						}ldx++; ldy--;
						if((rux-ldx == 4)&&(rux-ldx != 5)) { // ������ x�� y���� ���� Ŀ���Ƿ� x�� �ϳ��� ���ص� ��
							time.setVisible(false); // �ð��� �� ���޷�
							end.setVisible(true); // �������� ǥ��
							Gamer.finish = true; 
							Gamer.winner = Gamer.g1; // ����(�鵹) �¸�
							Gamer.winSoo = Gamer.sooW; return;
							}
						}					
					else { // �������� ���� ���ʿ�����
						bt[i][j].setIcon(b); // ���� ���� ������ �׸����� �ٲ�
						Gamer.bpan[i][j] = true; // ���� ���� ������ ��ġ ����
						Gamer.turn = false; // ���� �� ������ �Ѱ���
						Gamer.sooB++; // ���� �� �� ����
						t1.setIcon(t); // �� ���� ���� �ҷ�
						t2.setIcon(nt);	
						////////////////////////////////////////////////�̱�� ���
						//// ���� �¿�� ������ ���
						int nu = i; // ����� ���� �� �ε���
						while(Gamer.bpan[nu][j]) {
							nu--;
						}nu++;
						int nd = i; // ����� ���� �Ʒ� �ε���
						while(Gamer.bpan[nd][j]) {
							nd++;
						}nd--;
						int nr = j; // ����� ���� ������ �ε���
						while(Gamer.bpan[i][nr]) {
							nr++;
						}nr--;
						int nl = j; // ����� ���� ���� �ε���
						while(Gamer.bpan[i][nl]) {
							nl--;
						}nl++;
						
						if((nr-nl== 4) && (nr-nl != 5)) { // �¿�� ������ ���, ���� ����
							time.setVisible(false); // �ð��� �� ���޷�
							end.setVisible(true); // �������� ǥ��
							Gamer.finish = true; 
							Gamer.winner = Gamer.g2; // �İ�(�浹) �¸�
							Gamer.winSoo = Gamer.sooB;  return;
						}
						else if((nd-nu == 4)&&(nd-nu != 5)) { // ���Ϸ� ������ ���, ��������
							time.setVisible(false); // �ð��� �� ���޷�
							end.setVisible(true); // �������� ǥ��
							Gamer.finish = true;
							Gamer.winner = Gamer.g2; // �İ�(�浹) �¸�
							Gamer.winSoo = Gamer.sooB;  return;
						}  
						//// �밢������ ������ ���
						// ������ �Ʒ�
						int rdx = j; int rdy = i; int lux = j; int luy = i;
						while(Gamer.bpan[rdy][rdx]) { 
							rdx++; rdy++;
						} rdx--; rdy--;
						
						while(Gamer.bpan[luy][lux]) { // ���� ��
							lux--; luy--;
						}lux++; luy++;
						if((rdx-lux == 4)&&(rdx-lux != 5)) { // ������ x�� y���� ���� Ŀ���Ƿ� x�� �ϳ��� ���ص� ��
							time.setVisible(false); // �ð��� �� ���޷�
							end.setVisible(true); // �������� ǥ��
							Gamer.finish = true; 
							Gamer.winner = Gamer.g2; // �İ�(�浹) �¸�
							Gamer.winSoo = Gamer.sooB; 
							 return;
						}
						// ������ ��
						int rux = j; int ruy = i; int ldx = j; int ldy = i;
						while(Gamer.bpan[ruy][rux]) { 
							rux++; ruy--;
						}rux--; ruy++;
						
						while(Gamer.bpan[ldy][ldx]) { // ���� �Ʒ�
							ldx--; ldy++;
						}ldx++; ldy--;
						if((rux-ldx == 4)&&(rux-ldx != 5)) { // ������ x�� y���� ���� Ŀ���Ƿ� x�� �ϳ��� ���ص� ��
							time.setVisible(false); // �ð��� �� ���޷�
							end.setVisible(true); // �������� ǥ��
							Gamer.finish = true; 
							Gamer.winner = Gamer.g2; // �İ�(�浹) �¸�
							Gamer.winSoo = Gamer.sooB;
							 return;
							}
						}
					if(Gamer.finish) { ////////////////////////////////////////////// ������ �������� �¸��� ������
						String s = Integer.toString(Gamer.winSoo)+" "+Gamer.winner;
						
						try {
							GameRun.readOmok(); // ������ �ҷ��ͼ�
						} catch (IOException e1) {
							// TODO Auto-generated catch block, IOException�� �ذ��ϱ� ���� try-catch
							e1.printStackTrace();
						}
						System.out.println("ERR2");
						GameRun.putOmok(s); // ���ο� ������ �ְ�		
						System.out.println("ERR3");
						try {
						GameRun.WriteOmok(); // �ٽ� ���Ͽ� ������ ��ִ´�.
						} catch (IOException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("ERR4");
						Win lol = new Win();
					
						dispose(); // â �ݱ�
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� ��ư
		this.setTitle("���� ���� ����"); 
		this.setSize(400,400); // �Ű������� ���� a ���� ���� ������ ũ�⸦ ����
		this.setResizable(false); // ������ ������ ���� �Ұ�
		
		p = new JPanel();
		p.setLayout(null);
		
		winner = new JTextField();
		f = new Font("San Serif", Font.BOLD, 30);
		exit = new JButton("���� ����"); // ���� ���� ��ư
		winlb = new JLabel();
		winim = new ImageIcon("win.jpg"); // �¸� �̹���
		winlb.setIcon(winim);
		
		if(Gamer.winSoo == Gamer.sooW) { // �鵹�� �̰�����
			winner.setText("�鵹 "+Gamer.winner+Gamer.winSoo+" ��, �¸�!!");
		}
		else {
			winner.setText("�浹 "+Gamer.winner+Gamer.winSoo+" ��, �¸�!!");
		}
		winner.setHorizontalAlignment(JTextField.CENTER); // �ؽ�Ʈ �������
		winner.setFont(f);
		winner.setBackground(Color.RED);
		winner.setForeground(Color.green);
		
		winner.setBounds(10,10,400,60);
		winlb.setBounds(80, 50, 300, 300);
		exit.setBounds(150, 320, 90,30);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gamer.clear(); // ���� ���� �ʱ�ȭ
				dispose(); // â �ݱ�
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