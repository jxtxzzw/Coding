import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		try {
			Date d = sdf.parse(s);
			if (sdf.format(d).toString().equals(s)) {
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				System.out.println(c.get(Calendar.DAY_OF_YEAR));
			} else {
				System.out.println(-1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}