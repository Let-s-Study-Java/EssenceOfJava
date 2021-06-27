## 8. 인터페이스

목표 : 자바의 인터페이스를 학습한다.

### **학습할 것**

- 인터페이스 정의하는 방법
- 인터페이스 구현하는 방법
- 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 인터페이스 상속
- 인터페이스의 기본 메소드 (Default Method), 자바 8
- 인터페이스의 static 메소드, 자바 8
- 인터페이스의 private 메소드, 자바 9

---

<br/>

### 8-1. 인터페이스 정의하는 방법

---

***" 인터페이스(interface)란? "***

- 추상클래스의 일종으로, 보다 추상화 정도가 높다.
- 오직 **추상메서드**와 **상수**만을 멤버로 가질 수 있고, 그 외의 다른 요소는 허용하지 않는다.
- 인터페이스 자체만으로 사용되기 보다는 다른 클래스를 작성하는데 도움이 될 목적으로 쓰인다.
- 인터페이스를 사용함으로, 개발자마다 다른 메서드 이름 선언 등을 방지할 수 있다.
- `implements` 키워드를 이용해 인터페이스를 구현할 수 있다.
- **다중상속**을 허용한다.

</br>

> 인터페이스 작성하기

```java
/* 인터페이스 작성
interface 인터페이스명 {
	public static final 타입 상수이름 = 값;
	public abstract 메서드이름 (매개변수목록) ;
}
*/

// Animal.java
interface Animal{
	int MAX_AGE = 10; // public static final int MAX_AGE = 10;
	void bark(); // public abstract void bark();
	int move(int pos);
}
```

- 키워드로 `class`가 아닌 `interface`를 사용한다.
- 접근제어자로 `public`이나 `default`를 사용할 수 있다.
    - 모든 **멤버변수(상수)**는 `public static final`이며 생략이 가능하다.
    - 모든 **메서드**는 `public abstract`이며 생략이 가능하다. *(이후 다룰 static, default 메서드는 예외)*

</br>

### 8-2. 인터페이스 구현하는 방법

---

> `implements`키워드를 사용하여 인터페이스 구현이 가능하다.

```java
/*
구현방법 : class 클래스명 implements 인터페이스명 { }
*/

// Dog.java
public class Dog implements Animal{
	@Override
	public void bark() {
		System.out.println("bow-wow");
	}
	@Override
	public int move(int pos) {
		return pos+=1;
	}
}

// Cat.java
public class Cat implements Animal{
	@Override
	public void bark() {
		System.out.println("meow");
	}
	@Override
	public int move(int pos) {
		return pos+=5;
	}
}
```

- 인터페이스에 선언된 **모든 추상메서드**를 무조건 구현해야 한다.
- **여러 인터페이스**를 구현한 클래스를 생성할 수 있다. 이 경우, 클래스는 구현하고자 하는 모든 인터페이스의 추상메서드를 작성해야 한다.

    ```java
    class 클래스명 implements InterfaceA, InterfaceB ...{
    	// InterfaceA에 정의된 추상메서드 구현
    	// InterfaceB에 정의된 추상메서드 구현
    	// ...
    }
    ```

</br>

### 8-3. 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

---

```java
/*
현재 위에서 예시로 다룬,
Animal 인터페이스를 구현한 Dog, Cat 클래스가 존재하는 상태이다.
*/

// Main.java
public class Main {
	public static void main(String[] args) {
		Dog dog = new Dog();
		Cat cat = new Cat();
		
		System.out.println(dog.MAX_AGE);
		dog.bark();
		cat.bark();
		System.out.println("pos : " + dog.move(0));
		System.out.println("pos : " + cat.move(0));
	}
}

/* result.
10
bow-wow
meow
pos : 1
pos : 5
*/
```

- 위의 예시처럼, 인터페이스를 구현한 클래스를 인스턴스화하여 메서드 호출이 가능하다.

```java
Animal dog = new Dog();
Animal cat = new Cat();
```

- 위의 예처럼 super 인터페이스의 타입으로도 인스턴스화가 가능하다.

</br>

### 8-4. 인터페이스 상속

---

- 클래스와 달리 인터페이스끼리는 **다중상속**이 가능하다.
- sub 인터페이스는 super 인터페이스의 메서드까지 모두 구현해야 한다.

```java
// InterfaceA.java
public interface InterfaceA {
	void printA();
}

// InterfaceB.java
public interface InterfaceB {
	void printB();
}

// InterfaceC.java
public interface InterfaceC extends InterfaceA, InterfaceB{
	void printC();
}

// Test.java
public class Test implements InterfaceC{
	@Override
	public void printA() {
		// TODO Auto-generated method stub
	}
	@Override
	public void printB() {
		// TODO Auto-generated method stub
	}
	@Override
	public void printC() {
		// TODO Auto-generated method stub
	}
}
```

</br>

### 8-5. 인터페이스의 기본 메소드 (Default Method), 자바 8

---

> 추상 메서드의 기본적인 구현을 제공하는 메서드

*" JDK 1.8부터 인터페이스에 추상 메서드가 아닌, `default` 메서드와 `static` 메서드를 추가할 수 있게 되었다. "*

- 구현하는 class에서 매번 동일한 구현이 이루어지는 경우, 인터페이스에서 **기본 메서드**로 정의해 사용이 가능하다.

```java
// Animal.java
public interface Animal{
	// ...
	default void plusAge(int age) {
		this.age++;
	}
}
```

</br>

### 8-6. 인터페이스의 static 메소드, 자바 8

---

- static 메서드는 상속이 불가능하며, 구현하는 클래스에서는 재정의할 수 없다.

```java
// Animal.java
public interface Animal{
	// ...
	static void plusAge(int age) {
		this.age++;
	}
}
```

</br>

### 8-7. 인터페이스의 private 메소드, 자바 9

---

- 인터페이스에 `default`, `static` 메소드가 생긴 이후, 이러한 메소드들의 로직을 공통화하고, 재사용하기 위해 생긴 메서드이다.
- 중복되는 코드를 제거하고 캡슐화에 용이해졌다.
- `private`이기에 상속되지 않는다.
- 클래스에서 구현이 되지 않기 때문에, 인터페이스에서 구현을 해야한다.
- 외부에서 호출이 되지 않기에, 인터페이스 내부에서 사용가능하다.

```java
// Animal.java
public interface Animal{
	// ...
	private void bark() {
		System.out.println("bow-wow");
	}
	default void bark2() {
		bark();
		System.out.println("meow");
	}

}
```
