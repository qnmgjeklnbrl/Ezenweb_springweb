package com.Ezenweb.controller.test;

public class 테스트 {
    public static void main(String[] args) {


        제품 제품1 = new 제품();

        제품1.제품명 = "제품명1";

        이미지 이미지1 = new 이미지();
        이미지1.이미지명 = "이미지1";
        //단방향
        이미지1.제품= 제품1;
        //양방향
        제품1.list.add(이미지1);


    }



}
