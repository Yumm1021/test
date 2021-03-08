package com.kita.second.level1;

import java.util.Scanner;

public class PhoneTest {


public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);

//	기본형 변수
//	char : 빈칸  숫자로 변환 시 0
//	숫자형 : 0
//	boolean : false
//
//	참조형 변수
//	null

	Phone myPhone = new Phone();
	myPhone.color = "파랑";
	myPhone.phoneName = "아이폰 12";
	myPhone.owner = "박연진";
	myPhone.memory = 512;
	
	Phone p1 = new Phone("아이폰 12", "파랑", "256")

	myPhone.call("엄마");

	Phone yourPhone = new Phone();



}

}
