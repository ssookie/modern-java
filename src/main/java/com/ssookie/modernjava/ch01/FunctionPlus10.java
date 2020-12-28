package com.ssookie.modernjava.ch01;

import java.util.function.Function;

public class FunctionPlus10 implements Function<Integer, Integer>{
    @Override
    public Integer apply(Integer num){
        return num + 10;
    }
}
