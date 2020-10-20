package com.garnagaaa.lesson08.task01.app1;

import com.garnagaaa.lesson08.task01.app1.factorial.Factor;

/**
 * @author Aleksei Garnaga
 */
public class Program {
    public static void main(String[] args) throws Exception {

        int[] arr=new int[]{3,9,5,4,12,3,4,20,30,45,46,56,89,23,200,300,301,14,56,678,23,68,23,8678,334,2,112,3638,431,5688};
        Factor factor = new Factor();
        factor.inputData(arr);
        factor.Start();
        factor.Show();
    }
}