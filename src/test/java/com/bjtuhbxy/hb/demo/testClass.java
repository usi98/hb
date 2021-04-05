package com.bjtuhbxy.hb.demo;

public class testClass {
    public static void main(String[] args) {

        for (int i = 0 ;i<3;i++){
            int max = 880;
            int min = 300;
            int d = (int)(Math.random()*(max - min)+min);

            System.out.println(d);
        }
    }
}
