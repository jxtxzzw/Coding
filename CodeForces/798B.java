import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numberOfStrings = in.nextInt();
		in.nextLine();
		String[] ss = new String[numberOfStrings];
		for (int i=0;i<numberOfStrings;i++){
			ss[i]=in.nextLine();
		}
		int min = -1;
		for (int i=0;i<ss.length;i++){
			String target = ss[i];
			int ansTmp = 0;
			for (int j=0;j<ss.length;j++){
				if (i!=j) {	
					String s = ss[j];
					if (s.equals(target)) continue;
					String tmp;
					boolean mathFailed = true;
					for (int pos = 1; pos < s.length(); pos++){
						tmp = s.substring(pos)+s.substring(0, pos);
						if (tmp.equals(target)){
							ansTmp += pos;
							mathFailed = false;
							break;
						}
					}
					if (mathFailed) {ansTmp = -1;break;}
				}
			}
			if (ansTmp != -1) {
				if (min == -1) min = ansTmp;
				else min = Math.min(min, ansTmp);
			}
		}
		System.out.println(min);
		
		
				
	}
}
