package com.company;

public enum AnsiColors {
    Red("\u001b[31m"),
    Blue("\u001b[34m"),
    Green("\u001b[32m"),
    Yellow("\u001b[33m"),
    Magenta("\u001b[35m"),
    Cyan("\u001b[36m"),
    BrightRed("\u001b[31;1m"),
    BrightGreen("\u001b[32;1m"),
    BrightYellow("\u001b[33;1m"),
    BrightBlue("\u001b[34;1m"),
    BrightMagenta("\u001b[35;1m"),
    BrightCyan("\u001b[36;1m");

    private String code;
    AnsiColors(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
