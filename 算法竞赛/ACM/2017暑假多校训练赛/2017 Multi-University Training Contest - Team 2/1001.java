import java.util.Scanner;

/**
 * @author jxtxzzw
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		for (int t = 0; t < caseNumber; t++) {
			int problemNumber = in.nextInt();
			// ��ʵ�ϣ��������String.length()��û��ʵ������
			int scoreDerek = in.nextInt();
			int scoreAlfia = in.nextInt();
			String answerDerek = in.next();
			String answerAlfia = in.next();
			int wrongCounter = 0;
			int rightCounter = 0;
			for (int i = 0; i < answerDerek.length(); i++) {
				if (answerDerek.charAt(i) != answerAlfia.charAt(i))
					wrongCounter++;
				else
					rightCounter += 2;
			}
			if (Math.abs(scoreDerek - scoreAlfia) > wrongCounter) {
				System.out.println("Lying");
				// �����������x���𰸲�һ������ô��������˲�x�֣�Ҳ���ܲ�0�֡���1�֡�����x-1��
			} else if (rightCounter + wrongCounter < scoreDerek + scoreAlfia) {
				System.out.println("Lying");
				// �����˵Ĵ�һ��������1�֣��𰸲�һ�������ֻ����1���˵�1�֣�Ҳ���������˶����÷�
				// ���Դ����+��ȷ��*2��������ܶ��ٷ֣����������˵���ķ����������������������������˵��
			} else {
				System.out.println("Not lying");
			}
		}

	}

}
