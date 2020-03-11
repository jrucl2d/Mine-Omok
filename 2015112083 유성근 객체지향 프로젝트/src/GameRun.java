import java.io.*;
import java.util.StringTokenizer; // ���ڿ� ��ū ���

public class GameRun {

	public static void main(String[] args) {
		GameStartMenu game = new GameStartMenu();
	}
	//////////////////////////////////////////////////////////////////////// ����ã�� ����ó��
	
	static void readMine() throws IOException{ // ���� ���� �б�
		Gamerm.mineScore = new String[100]; // ������ ū ���ڿ� �迭�� ����
		
		File file = new File("MineScore.txt"); // ���� ��ü ����
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = null;
		int n = 0;
		while((str = br.readLine()) != null) { // ���� ������ ����� ���ڿ��� �� ���徿 �о��
			Gamerm.mineScore[n] = str;
			n++;
		}
		Gamerm.index = n; // �ε����� ����
		br.close();
	}
	static void putMine(String ns) { // ���� ���� ���ڿ��� ���ο� ���ڿ� �ֱ�
		if(Gamerm.mineScore[0] == null) {Gamerm.mineScore[0] = ns; Gamerm.index++; return;} // nofile�̶�� ���ο� ���� �ְ� ����
		int num = Gamerm.index;

		int number[] = new int [num]; // ������ ��Ƴ��� �迭
		for(int i = 0; i< num; i++) { // score �迭�� ������ �̸� �����ϴ� �κ�
			StringTokenizer tokens = new StringTokenizer(Gamerm.mineScore[i]); // ���ڿ��� �� �پ� �ҷ��鿩��
			String tmp = tokens.nextToken("sec"); // ����(�ð�)
			number[i] = Integer.parseInt(tmp); // ������ ���ڷ� ����
		}
		StringTokenizer tokens = new StringTokenizer(ns); // �߰��� ���ڿ��� ���� ����
		String tmp = tokens.nextToken("sec"); 
		int want = Integer.parseInt(tmp); // �ְ���� ���� 
		int i = 0;
		while(i<num) {
			if(number[0]>=want) {i = 0; break;} // ���ο� ������ ���� ó���̸� i(0)���� ����
			else if(number[num-1]<=want) {Gamerm.mineScore[num++] = ns; Gamerm.index++; return;} // ���� �������� �־�� �ϸ� �������� ���� �߰� �� ����
			else if(number[i]<want && number[i+1]>=want) {i = i+1; break;} // ���ο� ������ ����� ������ �־���� ��ġ�� i�� ����
		} // �־�� �� ��ġ i�� ã����
		num++; // ���� ���� �ε��� �ϳ� ����
		Gamerm.index++;
		for(int j = num; j>i;j--) {
			Gamerm.mineScore[j] = Gamerm.mineScore[j-1]; // ���� �ϳ��� �ڷ� �̷�
		}
		Gamerm.mineScore[i] = ns; // i��ġ�� ns ����
		
		number = null; num = 0; // ���� �ʱ�ȭ
	}
	static void WriteMine() throws IOException { // ���� ���� ����
		File file = new File("MineScore.txt"); // ���� ��ü ����
		BufferedWriter bf = new BufferedWriter(new FileWriter(file));
		int i = 0;
		while(i<Gamerm.index) {
		bf.write(Gamerm.mineScore[i]+"\n"); // ���� ������ ����� ���ڿ��� ���Ͽ� ��ִ´�.
		i++;
		}
		bf.flush(); // ���۸� �����
		bf.close();
		Gamerm.mineScore = null; Gamerm.index = 0; // ����� ���ڿ� �迭, �ε��� �ʱ�ȭ
	}
	////////////////////////////////////////////////////////////////////// ���� ����ó��
	static void readOmok() throws IOException{ // ���� ���� �б�
		Gamer.omokScore = new String[100]; // ������ ū ���ڿ� �迭�� ����
		File file = new File("OmokScore.txt"); // ���� ��ü ����
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = null;
		int n = 0;
		while((str = br.readLine()) != null) { // ���� ������ ����� ���ڿ��� �� ���徿 �о��
			Gamer.omokScore[n] = str;
			n++;
		}
		Gamer.index = n; // �ε����� ����
		br.close();
	}
	static void putOmok(String ns) { // ����ǥ�� ���ο� ���ڿ��� ���ϴ� �Լ�
		if(Gamer.omokScore[0] == null) {Gamer.omokScore[0] = ns; Gamer.index++; return;} // nofile�̶�� ���ο� ���� �ְ� ����
		int num = Gamer.index;

		int number[] = new int [num]; // ������ ��Ƴ��� �迭
		for(int i = 0; i< num; i++) { // score �迭�� ������ �̸� �����ϴ� �κ�
			StringTokenizer tokens = new StringTokenizer(Gamer.omokScore[i]); // ���ڿ��� �� �پ� �ҷ��鿩��
			String tmp = tokens.nextToken(" "); // ����
			number[i] = Integer.parseInt(tmp); // ������ ���ڷ� ����
		}
		StringTokenizer tokens = new StringTokenizer(ns); // �߰��� ���ڿ��� ���� ����
		String tmp = tokens.nextToken(" "); 
		int want = Integer.parseInt(tmp); // �ְ���� ���� 
		int i = 0;
		while(i<num) {
			if(number[0]>=want) {i = 0; break;} // ���ο� ������ ���� ó���̸� i(0)���� ����
			else if(number[num-1]<=want) {Gamer.omokScore[num++] = ns; Gamer.index++; return;} // ���� �������� �־�� �ϸ� �������� ���� �߰� �� ����
			else if(number[i]<want && number[i+1]>=want) {i = i+1; break;} // ���ο� ������ ����� ������ �־���� ��ġ�� i�� ����
		} // �־�� �� ��ġ i�� ã����
		num++; // ���� ���� �ε��� �ϳ� ����
		Gamer.index++;
		for(int j = num; j>i;j--) {
			Gamer.omokScore[j] = Gamer.omokScore[j-1]; // ���� �ϳ��� �ڷ� �̷�
		}
		Gamer.omokScore[i] = ns; // i��ġ�� ns ����
		
		number = null; num = 0; // ���� �ʱ�ȭ
	}
	static void WriteOmok() throws IOException { // ���� ���� ����
		File file = new File("OmokScore.txt"); // ���� ��ü ����
		BufferedWriter bf = new BufferedWriter(new FileWriter(file));
		int i = 0;
		while(i<Gamer.index) {
		bf.write(Gamer.omokScore[i]+"\n"); // ���� ������ ����� ���ڿ��� ���Ͽ� ��ִ´�.
		i++;
		}
		bf.flush(); // ���۸� �����
		bf.close();
		Gamer.omokScore = null; Gamer.index = 0;
	}
}