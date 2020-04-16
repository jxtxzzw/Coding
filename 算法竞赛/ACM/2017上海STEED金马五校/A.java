
import java.util.Scanner;

public class Main {
	static String s[] = {
			"",
			"DEEST",
			"DEETS",
			"DESET",
			"DESTE",
			"DETES",
			"DETSE",
			"DSEET",
			"DSETE",
			"DSTEE",
			"DTEES",
			"DTESE",
			"DTSEE",
			"EDEST",
			"EDETS",
			"EDSET",
			"EDSTE",
			"EDTES",
			"EDTSE",
			"EEDST",
			"EEDTS",
			"EESDT",
			"EESTD",
			"EETDS",
			"EETSD",
			"ESDET",
			"ESDTE",
			"ESEDT",
			"ESETD",
			"ESTDE",
			"ESTED",
			"ETDES",
			"ETDSE",
			"ETEDS",
			"ETESD",
			"ETSDE",
			"ETSED",
			"SDEET",
			"SDETE",
			"SDTEE",
			"SEDET",
			"SEDTE",
			"SEEDT",
			"SEETD",
			"SETDE",
			"SETED",
			"STDEE",
			"STEDE",
			"STEED",
			"TDEES",
			"TDESE",
			"TDSEE",
			"TEDES",
			"TEDSE",
			"TEEDS",
			"TEESD",
			"TESDE",
			"TESED",
			"TSDEE",
			"TSEDE",
			"TSEED",
			
	};


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			System.out.println(s[in.nextInt()]);
		}
	}
}