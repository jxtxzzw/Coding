
class Solution {
    private int[] extract(String num) {
        int sign = 1;
        int cursor = 0;
        if (num.charAt(cursor) == '-') {
            sign = -1;
            cursor++;
        } else if (num.charAt(cursor) == '+') {
            cursor++;
        }
        int real = 0;
        while (Character.isDigit(num.charAt(cursor))) {
            real = real * 10 + (num.charAt(cursor) - '0');
            cursor++;
        }
        real *= sign;

        sign = 1;
        assert num.charAt(cursor) == '+';
        cursor++;
        if (num.charAt(cursor) == '-') {
            sign = -1;
            cursor++;
        }
        int image = 0;
        while (Character.isDigit(num.charAt(cursor))) {
            image = image * 10 + (num.charAt(cursor) - '0');
            cursor++;
        }
        image *= sign;
        assert num.charAt(cursor) == 'i';

        return new int[]{real, image};

    }

    public String complexNumberMultiply(String num1, String num2) {
        int[] tmp1, tmp2;
        tmp1 = extract(num1);
        tmp2 = extract(num2);

        int real = tmp1[0] * tmp2[0] - tmp1[1] * tmp2[1];
        int image = tmp1[0] * tmp2[1] + tmp1[1] * tmp2[0];

        return "" + real + "+" + image + "i";
    }
}
