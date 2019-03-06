package tw.tcnr21.tcnrlibrary;

public class Tcnrlibrary {

    //---methods測試---------
    public String tc_getMessage() {

        return "Tcnrlibrary 測試成功";
    }

    public String tc_chinano(int input_i) {
        String c_number = "";
        String china_no[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        c_number = china_no[input_i % 10];

        return c_number;
    }

    public String tc_chinayear(int input_i) {
        String c_number = "";
        String china_no[] = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
        c_number = china_no[input_i % 10];

        return c_number;
    }


    //*--- 英文數字轉換成中文國字
    public String tc_cmoney(int input_i) {
        String ls_str = "";
        int m = 0;
        String CAMT = "";
        String china_ones[] = {"零", "壹", "貳", "參", "肆", "伍", "陸", "柒", "捌", "玖"};
        String china_tens[] = {"拾", "佰", "仟", "萬", "億"};

        String num[] = {"零", "壹", "貳", "參", "肆", "伍", "陸", "柒", "捌", "玖"};
        String xx[] = {"拾", "佰", "仟"};
        String yy[] = {"萬", "億", "兆", "京"};
        long v;


        return ls_str;
    }

    /*** 將阿拉伯數字轉換成中文**/
    public String tc_numToCht(String intString) {
        int chtNumLength = 16;
        String fullTypeSapce = " ";
        final String[] CHT_NUM = {"零", "壹", "貳", "參", "肆", "伍", "陸", "柒", "捌", "玖"};
        String[] newChtNum = new String[chtNumLength];
        String[] chtNum = new String[chtNumLength];
        chtNum[0] = fullTypeSapce;
        chtNum[1] = "仟";
        chtNum[2] = fullTypeSapce;
        chtNum[3] = "佰";
        chtNum[4] = fullTypeSapce;
        chtNum[5] = "拾";
        chtNum[6] = fullTypeSapce;
        chtNum[7] = "萬";
        chtNum[8] = fullTypeSapce;
        chtNum[9] = "仟";
        chtNum[10] = fullTypeSapce;
        chtNum[11] = "佰";
        chtNum[12] = fullTypeSapce;
        chtNum[13] = "拾";
        chtNum[14] = "";
        chtNum[15] = "元";
        try {
            /* 判斷是否為正整數 */
            Integer.parseInt(intString);
            /* 將阿拉伯數字轉換成中文, 再存入 String Array */
            int strLength = intString.length();
            int index = chtNumLength - strLength - strLength;
            for (int i = 0; i < strLength; i++) {
                String indexValue = intString.substring(i, i + 1);
                newChtNum[i + i] = CHT_NUM[Integer.parseInt(indexValue)];
                newChtNum[i + i + 1] = chtNum[index + i + i + 1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 將 String Array 重新組成字串 */
        StringBuffer sbCHT = new StringBuffer();
        for (int i = 0; newChtNum[i] != null; i++) {
            sbCHT.append(newChtNum[i]);
        }

        return sbCHT.toString();
    }

    //*--- 中文國字轉換成英文數字
    public int tc_cnumber2Int(String chineseNumber) {
        int result = 0;
        int temp = 1; // 存放一個單位的數字如：十萬
        int count = 0; // 判斷是否有chArr
        char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
        char[] chArr = new char[]{'十', '百', '千', '萬', '億'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true; // 判斷是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) { // 非單位，即數字
                if (c == cnArr[j]) {
                    if (0 != count) { // 添加下一個單位之前，先把上一個單位值添加到結果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下標+1，就是對應的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if (b) { // 單位{'十','百','千','萬','億'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) { // 遍歷到最後一個字符
                result += temp;
            }

        }
        return result;
    }
}
