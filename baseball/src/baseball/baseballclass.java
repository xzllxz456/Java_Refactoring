package baseball;

import java.util.Arrays;
import java.util.Scanner;

public class baseballclass {
	static Scanner sc = new Scanner(System.in);
	
	int rNum[];
	int uNum[];
	int times;
	int strike;
	int ball;
	boolean b = false;
	boolean clear;
	int count;
	
	
	public int Inputnum() {
		int num;
		String str;
		
		while (true) {
			boolean ok = true;
			str = sc.next();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				int asc = (int) c;
				if (asc < 48 || asc > 57) {
					System.out.println("잘못입력했습니다. 다시입력해줘요");
					ok = false;
					break;
				}
			}
			try {
				if(Integer.parseInt(str) > 9 || 
						Integer.parseInt(str) < 0) {
					System.out.println("잘못입력했습니다. 다시입력해줘요");
					ok = false;
					continue;
				}
			}catch(NumberFormatException e) {
				ok = false;
				continue;
			}
			if (ok == true) {
				break;
			}
		}
		
		num = Integer.parseInt(str);
		
		return num;
	}
	
	public void times() {
		times = Inputnum();
	}
	
	public void randomNumber() {
		rNum = new int[times];
		int w = 0;
		while (w < rNum.length) {
			int n = (int)(Math.random() * 9) + 1;
			b = false;
			check(rNum, n);
			if(b) {
				continue;
			}
			rNum[w] = n;
			w++;
		}
		System.out.println(Arrays.toString(rNum));
	}
	
	public void check(int[] arr, int n) {
		for (int i = 0; i < rNum.length; i++) {
			if(arr[i] == n) {
				b = true;
				break;
			}
		}
	}

	public void userNumber() {
		uNum = new int[times];
		int w = 0;
		
		while (w < uNum.length) {
			System.out.print((w + 1) + "번째 수 입력 : ");
			int n = Inputnum();
			b = false;
			check(uNum, n);
			if(b) {
				System.out.println("숫자 이상 다시 입력");
				continue;
			}
			uNum[w] = n;
			w++;
		}
		System.out.println(Arrays.toString(uNum));
	}
	
	public void strikeCheck() {
		strike = 0;
		for (int i = 0; i < uNum.length; i++) {
			if(uNum[i] == rNum[i]) {
				strike++;
			}
		}
	}
	
	public void ballCheck() {
		ball = 0;
		for (int i = 0; i < rNum.length; i++) {
			for (int j = 0; j < uNum.length; j++) {
				if(rNum[i] == uNum[j] && i != j) {
					ball++;
				}
			}
		}
	}
	public void loop() {
		int w = 0;
		count = 1;
		clear = false;
		while(w < 10) {
			userNumber();
			strikeCheck();
			ballCheck();
			gameClear();
			result();
			if(clear) {
				break;
			}
			w++;
			System.out.println("기회는 " + (10 - (count)));
			count++;
		}
	}
	public void result() {
		if(clear) {
			System.out.println(count + " 만에");
			System.out.println("게임 클리어");
		}
		System.out.println(strike + "스트라이크 " + ball + " ball 입니다.");
	}
	public void gameClear() {
		if(strike >= times) {
			clear = true;
		}
	}
	public void playGame() {
		while (true) {
			boolean replay = true;
			System.out.print("몇개 수 입력? ");
			times();
			randomNumber();
			loop();
			System.out.println("한 번 더할꺼? yes = 1 아무거나");
			int n = Inputnum();
			replay = rePl(n);
			if(replay) {
				System.out.println("한번더함");
				continue;
			}else {
				System.out.println("끝");
				break;
			}
		}
	}

	public boolean rePl(int n) {
		if(n == 1) {
			return true;
		}else {
			return false;
		}
	}
}
