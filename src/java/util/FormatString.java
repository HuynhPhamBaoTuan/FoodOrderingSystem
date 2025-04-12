/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.DecimalFormat;

/**
 *
 * @author LENOVO
 */
public class FormatString {
    public static String formatCurrency(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String str = decimalFormat.format(amount) + " VND";
        return str;
    }
}
