static public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.toUpperCase();

        if (s.contains("E")) {
            int split = s.indexOf("E");
            String first = s.substring(0, split);
            String second = s.substring(split + 1);
            return (isDecimal(first) || isInteger(first)) && isInteger(second);
        }
        return isDecimal(s) || isInteger(s);
    }

    static private boolean isDecimal(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int cursor = 0;
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            cursor = 1;
        }
        int pointCount = 0;
        boolean numberExist = false;
        while (cursor < s.length()) {
            if (!Character.isDigit(s.charAt(cursor))) {
                if (s.charAt(cursor) == '.') {
                    pointCount++;
                    if (pointCount > 1) {
                        return false;
                    } else {
                        cursor++;
                        continue;
                    }
                } else {
                    return false;
                }
            } else {
                numberExist = true;
            }
            cursor++;
        }
        return pointCount == 1 && numberExist;
    }

    static private boolean isInteger(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int cursor = 0;
        boolean numberExist = false;
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            cursor = 1;
        }
        while (cursor < s.length()) {
            if (!Character.isDigit(s.charAt(cursor))) {
                return false;
            } else {
                numberExist = true;
            }
            cursor++;
        }
        return numberExist;
    }
