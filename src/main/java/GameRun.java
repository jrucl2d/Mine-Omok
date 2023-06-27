import java.io.*;
import java.util.StringTokenizer; // 문자열 토큰 사용

public class GameRun {

	public static void main(String[] args) {
		GameStartMenu game = new GameStartMenu();
	}
	//////////////////////////////////////////////////////////////////////// 지뢰찾기 파일처리
	
	static void readMine() throws IOException{ // 지뢰 순위 읽기
		Gamerm.mineScore = new String[100]; // 적당히 큰 문자열 배열로 생성
		
		File file = new File("MineScore.txt"); // 파일 객체 생성
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = null;
		int n = 0;
		while((str = br.readLine()) != null) { // 지뢰 순위에 저장된 문자열을 한 문장씩 읽어옴
			Gamerm.mineScore[n] = str;
			n++;
		}
		Gamerm.index = n; // 인덱스를 저장
		br.close();
	}
	static void putMine(String ns) { // 지뢰 순위 문자열에 새로운 문자열 넣기
		if(Gamerm.mineScore[0] == null) {Gamerm.mineScore[0] = ns; Gamerm.index++; return;} // nofile이라면 새로운 순위 넣고 종료
		int num = Gamerm.index;

		int number[] = new int [num]; // 순위만 모아놓은 배열
		for(int i = 0; i< num; i++) { // score 배열에 순위와 이름 저장하는 부분
			StringTokenizer tokens = new StringTokenizer(Gamerm.mineScore[i]); // 문자열을 한 줄씩 불러들여서
			String tmp = tokens.nextToken("sec"); // 순위(시간)
			number[i] = Integer.parseInt(tmp); // 순위를 숫자로 변경
		}
		StringTokenizer tokens = new StringTokenizer(ns); // 추가할 문자열의 순위 추출
		String tmp = tokens.nextToken("sec"); 
		int want = Integer.parseInt(tmp); // 넣고싶은 순위 
		int i = 0;
		while(i<num) {
			if(number[0]>=want) {i = 0; break;} // 새로운 순위가 제일 처음이면 i(0)으로 설정
			else if(number[num-1]<=want) {Gamerm.mineScore[num++] = ns; Gamerm.index++; return;} // 만약 마지막에 넣어야 하면 마지막에 순위 추가 후 리턴
			else if(number[i]<want && number[i+1]>=want) {i = i+1; break;} // 새로운 순위가 가운데에 있으면 넣어야할 위치를 i로 설정
		} // 넣어야 할 위치 i를 찾았음
		num++; // 지뢰 순위 인덱스 하나 증가
		Gamerm.index++;
		for(int j = num; j>i;j--) {
			Gamerm.mineScore[j] = Gamerm.mineScore[j-1]; // 순위 하나씩 뒤로 미룸
		}
		Gamerm.mineScore[i] = ns; // i위치에 ns 넣음
		
		number = null; num = 0; // 변수 초기화
	}
	static void WriteMine() throws IOException { // 지뢰 순위 쓰기
		File file = new File("MineScore.txt"); // 파일 객체 생성
		BufferedWriter bf = new BufferedWriter(new FileWriter(file));
		int i = 0;
		while(i<Gamerm.index) {
		bf.write(Gamerm.mineScore[i]+"\n"); // 지뢰 순위에 저장된 문자열을 파일에 써넣는다.
		i++;
		}
		bf.flush(); // 버퍼를 비워줌
		bf.close();
		Gamerm.mineScore = null; Gamerm.index = 0; // 사용한 문자열 배열, 인덱스 초기화
	}
	////////////////////////////////////////////////////////////////////// 오목 파일처리
	static void readOmok() throws IOException{ // 오목 순위 읽기
		Gamer.omokScore = new String[100]; // 적당히 큰 문자열 배열로 생성
		File file = new File("OmokScore.txt"); // 파일 객체 생성
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = null;
		int n = 0;
		while((str = br.readLine()) != null) { // 오목 순위에 저장된 문자열을 한 문장씩 읽어옴
			Gamer.omokScore[n] = str;
			n++;
		}
		Gamer.index = n; // 인덱스를 저장
		br.close();
	}
	static void putOmok(String ns) { // 순위표에 새로운 문자열을 더하는 함수
		if(Gamer.omokScore[0] == null) {Gamer.omokScore[0] = ns; Gamer.index++; return;} // nofile이라면 새로운 순위 넣고 종료
		int num = Gamer.index;

		int number[] = new int [num]; // 순위만 모아놓은 배열
		for(int i = 0; i< num; i++) { // score 배열에 순위와 이름 저장하는 부분
			StringTokenizer tokens = new StringTokenizer(Gamer.omokScore[i]); // 문자열을 한 줄씩 불러들여서
			String tmp = tokens.nextToken(" "); // 순위
			number[i] = Integer.parseInt(tmp); // 순위를 숫자로 변경
		}
		StringTokenizer tokens = new StringTokenizer(ns); // 추가할 문자열의 순위 추출
		String tmp = tokens.nextToken(" "); 
		int want = Integer.parseInt(tmp); // 넣고싶은 순위 
		int i = 0;
		while(i<num) {
			if(number[0]>=want) {i = 0; break;} // 새로운 순위가 제일 처음이면 i(0)으로 설정
			else if(number[num-1]<=want) {Gamer.omokScore[num++] = ns; Gamer.index++; return;} // 만약 마지막에 넣어야 하면 마지막에 순위 추가 후 리턴
			else if(number[i]<want && number[i+1]>=want) {i = i+1; break;} // 새로운 순위가 가운데에 있으면 넣어야할 위치를 i로 설정
		} // 넣어야 할 위치 i를 찾았음
		num++; // 오목 순위 인덱스 하나 증가
		Gamer.index++;
		for(int j = num; j>i;j--) {
			Gamer.omokScore[j] = Gamer.omokScore[j-1]; // 순위 하나씩 뒤로 미룸
		}
		Gamer.omokScore[i] = ns; // i위치에 ns 넣음
		
		number = null; num = 0; // 변수 초기화
	}
	static void WriteOmok() throws IOException { // 오목 순위 쓰기
		File file = new File("OmokScore.txt"); // 파일 객체 생성
		BufferedWriter bf = new BufferedWriter(new FileWriter(file));
		int i = 0;
		while(i<Gamer.index) {
		bf.write(Gamer.omokScore[i]+"\n"); // 오목 순위에 저장된 문자열을 파일에 써넣는다.
		i++;
		}
		bf.flush(); // 버퍼를 비워줌
		bf.close();
		Gamer.omokScore = null; Gamer.index = 0;
	}
}