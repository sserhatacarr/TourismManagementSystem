package com.Helper;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class Helper {
    // This class is used to store frequent methods used in the project.
    public static void setLayout() {
        boolean gtkLookAndFeelFound = false;

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("GTK+".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    gtkLookAndFeelFound = true;
                    break;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    System.out.println("Metal theme could not be loaded.");
                }
            }
        }

        // If GTK+ theme is not found, use default system theme.
        if (!gtkLookAndFeelFound) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                System.out.println("Default system theme could not be loaded.");
            }
        }
    }

    public static int screenCenterPoint(String axis, Dimension size) {
        int point;
        switch (axis) {
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static void showMsg(String str) {
        String msg;
        String title;
        switch (str) {
            case "fill":
                msg = "Please Fill All Lines!";
                title = "Warning";
                break;
            case "success":
                msg = "Successful!";
                title = "Message";
                break;
            case "error":
                msg = "Something went wrong!";
                title = "Message";
                break;
            case "email":
                msg = "Please enter a valid email address!";
                title = "Warning";
                break;
            default:
                msg = str;
                title = "Message";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldEmpty(JTextArea area) {
        return area.getText().trim().isEmpty();
    }

    public static boolean confirm(String str) {
        String msg;
        String title;
        switch (str) {
            case "sure":
                msg = "Are you sure?";
                title = "Confirmation";
                break;
            default:
                msg = str;
                title = "Confirmation";
        }
        return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPageTR() {
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
    }

    public static boolean isEmailValid(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPasswordsMatch(String pass, String pass_again) {
        if (!(pass.equals(pass_again))) {
            Helper.showMsg("Passwords do not match!");
            return false;
        } else {
            return true;
        }
    }

    public static Date convertDate(JDateChooser dateChooser) {
        return new Date(dateChooser.getDate().getTime());

    }
}
