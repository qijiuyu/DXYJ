package com.bian.dan.dxyj.enumer;

public enum ManagerEnum {
    直线压接前钢管(0), 直线压接前铝管(1), 直线压接后钢管(2), 直线压接后铝管(3), 直线弯曲度(4),耐张压接前钢管(5), 耐张压接前铝管(6), 耐张压接后钢管(7), 耐张压接后铝管(8), 耐张弯曲度(9), 其他(100);

    private final int value;

    ManagerEnum(int value) {
        this.value = value;
    }

    public static ManagerEnum valueOf(int value) {
        switch (value) {
            case 0:
                return 直线压接前钢管;
            case 1:
                return 直线压接前铝管;
            case 2:
                return 直线压接后钢管;
            case 3:
                 return 直线压接后铝管;
            case 4:
                return 直线弯曲度;
            case 5:
                return 耐张压接前钢管;
            case 6:
                return 耐张压接前铝管;
            case 7:
                return 耐张压接后钢管;
            case 8:
                return 耐张压接后铝管;
            case 9:
                return 耐张弯曲度;
            default:
                return 其他;
        }
    }

    public int value() {
        return value;
    }
}