## 6. 상속

목표 : 자바의 상속을 학습한다.

### **학습할 것**

- 자바 상속의 특징
- super 키워드
- 메소드 오버라이딩
- 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
- 추상 클래스
- final 키워드
- Object 클래스

---

</br>

### 6-1. 자바 상속의 특징

---

> 상속 : 기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것

 **" 자바 상속의 특징 "**

- 코드의 재사용성을 높이고, 코드의 중복을 제거할 수 있다. → 생산성과 유지보수 😊
- 클래스의 계층구조를 분류하고 관리하기 용이하다.
- 상속 관계에 있는 두 클래스에 대해, 상속해주는 클래스 : **super** class / 상속받는 클래스 : **sub** class

    ```java
    class Animal{ }  // super class.
    class Bird extends Animal{ }  // sub class.
    ```

- 자바는 오직 **단일 상속**만을 허용한다. *cf) C++에서는 다중상속이 가능하다.*

    ```java
    class DogBird extends Mammal, Bird{
    	// 에러(다중상속). super class는 하나만 허용된다.
    }
    ```

- 생성자와 초기화 블럭은 상속되지 않는다. 멤버(변수, 메서드)만 상속된다.

    접근 제어자가 `private`, `default`인 멤버인 경우 상속은 받게 되지만, sub class에서 접근이 제한된다.

</br>

### 6-2. super 키워드

---

> sub class에서 super class에 접근하기 위한 참조변수

- `super()` : super class의 생성자를 호출하기 위해 사용한다.
    - `Object` 클래스를 제외한 모든 클래스의 생성자 첫 줄에 this() 또는 super()를 호출해야 한다.

    ```java
    // Animal.java
    public class Animal{
    	String name;
    	public Animal(){}
    	public Animal(String name){
    		this.name = name;
    	}
    	@Override
    	public String toString() {
    		return "Animal [name=" + name + "]";
    	}
    }

    // Mammal.java
    public class Mammal extends Animal{
    	int code;
    	public Mammal(){}
    	public Mammal(String name, int code){
    		super(name);
    		this.code = code;
    	}
    	@Override
    	public String toString() {
    		return "Mammal [code=" + code + ", name=" + name + "]";
    	}
    }

    // Test.java
    public class Test {
    	public static void main(String[] args) {
    		Animal ani = new Animal("Bird");
    		Animal ani2 = new Mammal("Dog",1001);
    		
    		System.out.println(ani);
    		System.out.println(ani2);
    	}
    }

    /* result.
    Animal [name=Bird]
    Mammal [code=1001, name=Dog]
    */
    ```

</br>

***cf. super vs. this***

*super class로부터 상속받은 멤버도 sub class 자신의 멤버이므로 super대신 this를 사용할 수 있다.*

*조상의 멤버와 자신의 멤버를 구별하는 데 사용된다는 점을 제외하고는 super와 this는 근본적으로 같다.*

</br>

### 6-3. 메서드 오버라이딩

---

> super class로부터 상속받은 메서드의 내용을 변경하는 것

```java
// Mammal.java
public class Mammal{
	// ...
	public String bark(){
		return "woo";
	} 
	// ...
}

// Dog.java
public class Dog extends Mammal{
	// ...
	@Override
	public String bark(){
		return "bow-wow";
	}
	// ...
}

// Test.java
public class Test {
	public static void main(String[] args) {
		Mammal mam = new Mammal("mamm",1001);
		System.out.println(mam.bark());
		
		mam = new Dog("coco",1002);
		System.out.println(mam.bark());
	}
}

/* result.
woo
bow-wow
*/
```

- 오버라이딩을 사용하는 이유 : 상속받은 메서드를 sub class에 맞게 변경하여 사용하기 위함.
- **오버라이딩의 조건 (sub class에서 오버라이딩하는 메서드와 기존의 super class 메서드는 ...)**
    - 이름이 같아야 한다.
    - 매개변수가 같아야 한다.
    - 반환타입이 같아야 한다.
- `@Override` 어노테이션 : 필수는 아니지만, 사용함으로써 명시적인 프로그램이 될 수 있다.

    오버라이드하는 메서드에 `@Override`가 붙게 될 경우에는 컴파일 에러 발생.

</br>

***cf. 오버라이딩 vs. 오버로딩***

*오버로딩(overloading) : 기존에 없는 새로운 메서드를 정의하는 것*

```java
public class OverloadingTest {
	public void method() {}
	public void method(String str) {}
	public void method(int a) {}
}
```

</br>

### 6-4. 다이나믹 메서드 디스패치 (Dynamic Method Dispatch)

---

> Compile time에 정의된 메서드가 아닌, Runtime에 오버라이딩된 메서드를 호출하는 메커니즘

**" 메서드 디스패치 "**

- Static Dispatch

    → **컴파일 시점**에서, 컴파일러가 특정 메서드를 호출할 것이라고 명확하게 알고있는 경우이다.

- Dynamic Dispatch

    → 정적 디스패치와 반대로 컴파일러가 어떤 메소드를 호출하는지 모르는 경우이다. 동적 디스패치는 호출할 메서드를 **런타임 시점**에서 결정한다.

```java
public class StaticDispatch {
	StaticDispatch() {}
	void run() {
		System.out.println("It's StaticDispatch !");
	}
	
	static class DynamicDispatch extends StaticDispatch{
		public DynamicDispatch() {}
		void run() {
			System.out.println("It's DynamicDispatch !");
		}
	}
	public static void main(String[] args) {
		// 기본적인 Static Dispatch.
		StaticDispatch service = new StaticDispatch();
		service.run();
		// Dynamic Dispatch. sub class에서 Override한 메서드 호출
		service = new DynamicDispatch();
		service.run();
	}
}

/* result.
It's StaticDispatch !
It's DynamicDispatch !
*/
```

</br>

### 6-5. 추상 클래스

---

> 클래스 : 설계도 / 추상 클래스 : 미완성 설계도 (틀)

`abstract` 키워드를 붙여 추상클래스를 선언한다.

→ 추상메서드가 있으니 상속을 통해 구현해주어야 한다는 것을 알릴 수 있다.

```java
abstract class Animal{
	public abstract void bark();
}

class Dog extends Animal{
	// 추상메서드는 반드시 오버라이딩해주어야 한다.
	public void bark() {
		System.out.println("bow-wow");	
	}
}

class Cat extends Animal{
	// 추상메서드는 반드시 오버라이딩해주어야 한다.
	public void bark() {
		System.out.println("meow");	
	}
}
```

</br>

**" 추상 클래스 "**

- **객체 생성 불가**
- 상속과 다형성을 위해
- 클래스 내에 **추상 메서드가 한개라도 있으면** 반드시 추상 클래스로 선언해야 한다.

**" 추상 메서드 "**

- 메서드의 **바디(구현부) 없이 선언**
→ sub 클래스는 반드시 추상 메서드를 override해야 한다.
- 목적

    → sub 클래스에 모두 동일한 기능이 있지만 그 기능이 천차만별이라, super에서 작성한 내용을 사용하지 않을 경우

    1. 가볍게 상속 할 수 있다.
    2. 설계시에 sub에서 반드시 override하도록 설계할 수 있다.
    3. JVM 성능을 조금 향상 시킬수 있다.

</br>

***cf. 인터페이스(interface)***

*일종의 추상클래스. **오직 추상메서드와 상수**로만 구성되어 있으며, 그 외의 다른 요소는 허용되지 않는다.*

`*implements` 키워드를 이용해 인터페이스를 구현할 수 있으며, 다중상속을 허용한다.*

```java
interface Animal{
	void bark();
}

class Dog implements Animal{
	public void bark() {
		System.out.println("bow-wow");
	}
}
```

</br>

### 6-6. final 키워드

---

> `final` 키워드를 사용한 클래스는 super class가 될 수 없다.

</br>

### 6-7. Object 클래스

---

> `java.lang.Object` 는 자바의 모든 클래스의 super class이다.

- 암묵적으로, 자바의 모든 클래스는 최상위 클래스인 `Object`의 sub class이다.

    그렇기에 Object 클래스에 정의된 멤버들을 사용할 수 있다. ex) `toString()`, `equals()`

    ```java
    class Animal{ }
    // 위와 같이 어떠한 클래스도 상속받지 않은 경우, 컴파일하게 되면 자동으로 아래의 코드가 된다. 
    class Animal extends Object{ }
    ```
