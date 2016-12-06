/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import java.util.Arrays;

/**
 *
 * @author Adne
 */
public class sortTest 
{

    public void test (int[] list)
    {
        int[] sortedList = new int[list.length];
        int temp = 0;
        
        for(int i = 0; i < list.length-1; i++)
        {
            for(int j = 1; j < list.length-i; j++)
            {
                if(list[j-1] > list[j])
                {
                    temp = list[j-1];
                    list[j-1] = list[j];
                    list[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(list));
    }
}
