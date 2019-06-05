package com.spring.springcloudlibraryproduct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudLibraryProductApplicationTests {

    @Test
    public void test01(){
        int [] numbers = {12,56,13,18,49,67,57};
        for(int i = 0; i < numbers.length-1;i++){
            for (int j = 0; j < numbers.length-i-1; j++) {
                if (numbers[j]>numbers[j+1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"遍:");
            System.out.println(numbers);
        }
        System.out.println("最后:");
        System.out.println(numbers);
    }

}
