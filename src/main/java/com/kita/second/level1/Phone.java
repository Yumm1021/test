package com.kita.second.level1;

public class Phone {

	//필드(속성)
	String phoneName;
	String owner;
	String color;
	int memory;
	String phoneNumber;
	
	//생성자
	public Phone() {}
	
	public Phone(String phoneName, String color, int memory) {
		this.phoneName = phoneName;
		this.color = color;
		this.memory = memory;
	}
	
	//메소드(동작)
	//call타입
	//person에게 전화를 건다.
	void call(String person) {
		System.out.println(person + "에게 전화를 건다.");
	}
	
	void message(String person) {
		System.out.printf("%s에게 %s가 문자를 보낸다.", person, owner);
	}
}
