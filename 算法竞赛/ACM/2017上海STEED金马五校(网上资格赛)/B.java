import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
			for (int i = 0; i < m; i++) {
				Integer a = in.nextInt();
				Integer b = in.nextInt();
				boolean isNew = true;
				for (int j = 0; j < list.size(); j++) {
					HashSet<Integer> hs = list.get(j);
					if (hs.contains(a) || hs.contains(b)) {
						hs.add(a);
						hs.add(b);
						isNew = false;
					}
				}
				if (isNew) {
					HashSet newHS = new HashSet<Integer>();
					newHS.add(a);
					newHS.add(b);
					list.add(newHS);
				}
			}
			// for (int k=0;k<list.size();k++){
			// System.out.println(list.get(k));
			// }
			for (int idx1 = 0; idx1 < list.size(); idx1++) {
				for (int idx2 = 0; idx2 < list.size(); idx2++) {
					if (idx1 != idx2) {
						HashSet<Integer> hs1 = list.get(idx1);
						HashSet<Integer> hs2 = list.get(idx2);
						Iterator it = (Iterator) hs1.iterator();
						while (it.hasNext()) {
							Integer a = (Integer) it.next();
							if (hs2.contains(a)) {
								hs1.addAll(hs2);
								hs2.addAll(hs1);
								break;
							}
						}
					}
				}
			}
			// for (int k=0;k<list.size();k++){
			// System.out.println(list.get(k));
			// }
			boolean flag = true;
			for (int k = 0; k < list.size(); k++) {
				if (list.get(k).size() % 3 != 0)
					flag = false;
			}
			if (m != 0 && flag) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
}
