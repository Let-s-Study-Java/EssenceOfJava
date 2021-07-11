# 추상클래스

추상클래스는 완성되지 않은 클래스를 말합니다. 추상클래스는 `abstract` 제어자로 클래스를 표현할 수 있으며 추상클래스로는 객체를 생성할 수 없습니다. 이런 추상클래스의 존재의의는 다른 클래스의 상속을 통한 완성이며 추상클래스는 상속에 대한 틀을 정의하기 위해 사용됩니다.

### 추상클래스의 조건

추상메서드가 하나라도 포함되어 있다면 클래스는 추상클래스로 정의해야합니다. 추상클래스는 객체를 생성할 수 없지만, 생성자와 실제 구현부가 있는 다른 메서드들의 작성에는 제한이 없습니다.

# 추상메서드

추상메서드는 반드시 추상클래스나 인터페이스 내에 작성되어야하는 메서드로, 메서드의 선언부만 작성하고 실제 구현부는 작성하지 않은 메서드를 말합니다. `abstract` 제어자를 메서드 선언부에 작성해서 사용합니다. 

### 추상메서드 오버라이딩

추상 메서드를 갖는 추상클래스/인터페이스를 구현 및 상속하는 클래스는 **반드시 추상메서드의 몸체를 구현해야합니다**.

메서드 구현부 작성 역시 오버라이딩이므로 반환형, 인자, 접근지정자를 알맞게 작성해야합니다.

```java
abstract class MyAbstractClass {
	public abstract void abstractMethod();
}

class MyClass extends MyAbstractClass{
	@Override
	public abstract void abstractMethod(){
		System.out.println("hello :)");
	}
}
```

---

추상클래스는 객체를 생성할 수 없지만 생성자는 작성할 수 있습니다. 추상클래스를 상속하는 하위클래스의 객체를 생성할 때 사용될 수 있습니다. 또한 추상클래스는 객체를 생성할 수 없는 것이지 레퍼런스를 사용할 수 없는 것은 아닙니다. 매개변수 및 반환타입, 제네릭의 요소로 사용할 수 있습니다.

## 추상클래스 및 추상메서드를 사용하는 이유

- 일반 상속으로 사용 시 구현의 강제 의무가 없으므로 메서드의 구현을 강제하여 설계 오류 제한
- 필요 없는 메서드를 상속하지 않고 필요한 것만 상속하여 가볍게 상속 가능
- Virtual Invocation을 통한 성능 향상
- 상속을 통해 다형성을 구현하면서 서브클래스의 메서드 통일 및 슈퍼클래스 추상화

---

# 인터페이스

인터페이스 역시 추상메서드를 포함하고 있지만 추상클래스와 다르게 추상메서드가 아닌 구현부를 가진 메서드 혹은 멤버변수를 가질 수 없습니다. 인터페이스는 `Interface` 키워드로 작성하며 인터페이스의 작성 조건은 다음과 같습니다.

- 모든 멤버 변수는 `public static final` 이어야 하며 이를 생략 가능
- 모든 메서드는 `public abstract` 여야 하며 이를 생략 가능
- JDK 1.8 부터 `static` , `default` 메서드를 사용 가능
- 생략된 제어자들은 컴파일러에 의해 자동으로 추가

## 인터페이스 상속

인터페이스는 다른 인터페이스를 `extends` 키워드로 상속받을 수 있습니다. 이 때는 다중상속이 가능합니다. 이 때는 상속받은 인터페이스가 부모 인터페이스의 메서드를 `Override` 할 의무는 없습니다

---

## 인터페이스 구현

인터페이스 또한 추상메서드를 포함하므로 객체를 생성할 수 없습니다. `implements` 키워드를 통해 해당 인터페이스를 구현하는 클래스를 통해 객체를 생성할 수 있습니다. 인터페이스의 구현도 다중구현이 가능합니다.

```java
interface Test{
	int a = 10;
//error, final 이므로 할당과 동시에 초기화 필요
  int b;
	void testMethod();
}
interface Test2{
	void testMethod();
}
interface Test3 extends Test, Test2{
	void testMethod2();
}

class TestClass implements Test3, Test2{
	
	@Override
	public void testMethod() {
		System.out.println("hello :)");
	}
	@Override
	public void testMethod2() {
		System.out.println("bye :(");
	}
}
```

---

인터페이스를 구현하는 클래스는 일반적으로 모든 메서드를 구현해야하지만 일부 메서드를 구현하지 않고 `abstract` 메서드로 남겨놓을 수 있습니다. 이 때 `abstract` 메서드가 존재하므로 `abstract` 클래스로 선언해야합니다.

인터페이스를 구현한 것으로 해당 클래스의 기능을 보장할 수 있습니다.

- `implements Clonnable`  해당 클래스의 객체를 `Clone` 가능

## 익명 클래스 구현

인터페이스 레퍼런스를 통해 객체를 선언할 경우 해당 인터페이스를 완전히 구현한 익명 클래스 형태로 객체를 생성할 수 있습니다.

```java
Test2 test = new Test2(){
	@Override
	public void testMethod(){
		System.out.println("테스트 !");
	}
};
```

## 람다식 구현( 함수형 인터페이스 )

`@FunctionalInterface` 어노테이션이 작성된 인터페이스라면 람다식을 작성하여 인터페이스의 객체를 생성할 수 있습니다.

- ex) Comparator

이 때 `@FunctionalInterface` 어노테이션이 작성된 인터페이스는 `Object` 클래스의 메서드를 제외하고 단 하나의 추상 메서드만 지정해야합니다.

---

## 인터페이스 레퍼런스로 객체 사용

인터페이스와 그를 구현하는 객체는 상속관계이므로 구현 객체는 인터페이스 레퍼런스에 프로모션되어 사용할 수 있습니다.

클래스 간 상속과 마찬가지로 레퍼런스에서 오버라이딩한 속성만 실제 호출 가능합니다.

```java
// ArrayList 및 LinkedList는 List의 구현체이므로 다음과 같이 사용가능
List<Integer> list1 = new ArrayList<>();
List<Integer> list2 = new LinkedList<>();

//pop() 메서드는 Collection에 정의되지 않은 메서드 이므로 사용 불가능
Collection<Integer> collection = new PriorityQueue<>();
collection.pop();
```

---

## 인터페이스를 통한 다중상속

자바는 기본적으로 클래스 간 다중상속이 금지되어 있지만 인터페이스를 여럿 구현하거나, 인터페이스 구현 및 클래스 상속으로 다중상속을 구현할 수 있습니다. 

다만 이때는 실제 구현체가 없는 인터페이스를 구현하므로 인터페이스와 같은 동작을 하는 클래스를 내부에 선언하므로써 사용합니다.

```java
interface Walkable{
	void walk();
}

class Airplane{
	public void fly() {
		System.out.println("flying~!!");
	}
}

class Human implements Walkable{
	public void walk() {
		System.out.println("walking~!!");
	}
}

class Robot extends Airplane implements Walkable{
//	Human human = new Human();
	public void walk() {
//		human.walk();
		System.out.println("walking~!!");
	}
}
```

이렇게 사용하는 방법의 장점은 다형성을 이용하여 상위 인터페이스로 레퍼런싱이 가능하다는 점입니다.

```java
public class InterfaceTester {
	public static void main(String[] args) {
		Walkable robot1 = new Robot();
		robot1.walk();
		Airplane robot2 = new Robot();
		robot2.fly();
	}
	/* 출력 :
	 * walking~!!
	 * flying~!!
	 */
}
```

---

## 인터페이스 다형성

위의 예제에서 봤던 것처럼 인터페이스 레퍼런스도 구현하는 하위 클래스의 인스턴스를 업캐스팅하여 레퍼런싱할 수 있습니다. 이는 매개변수, 반환타입, 제네릭 타입으로도 사용할 수 있다는 것을 말합니다.

```java
static Walkable walkerFactory(String type) {
	if(type.equals("robot")) {
		return new Robot();
	}else if(type.equals("human"){
		return new Human();
	}
}

public static void main(String[] args) {
	Walkable robot1 = new Robot();
	robot1.walk();
	Airplane robot2 = new Robot();
	robot2.fly();
	
	Walkable walker = walkerFactory("robot");
	Walkable walker2 = walkerFactory("human");
}

```

- 선언된 반환형은 `Walkable` 이지만 `Walkable` 을 구현한 `Robot` 객체를 반환할 수 있습니다.
- 실제 반환되는 객체는 `Robot` 이지만 `Walkable` 레퍼런스로 업캐스팅하여 레퍼런싱 할 수 있습니다.
- `type` 매개변수의 값 변환으로 쉽게 다른 객체를 생성할 수 있습니다.

 이 때 인터페이스 레퍼런스를 사용한 매개변수, 반환값, 제네릭 등을 실제로 사용하기 위해서는 해당 인터페이스를 구현한 클래스 인스턴스를 참조하고 있어야합니다.

---

## 인터페이스의 장점

인터페이스를 사용하면서 얻을 수 있는 장점은 다음과 같이 정리해볼 수 있습니다.

- 표준화 개발 가능
    - 기본 틀을 인터페이스로 제공해서 표준화된 개발 가능
- 클래스 간 관계 형성
    - 서로 관련 없는 클래스들을 같은 인터페이스를 구현하여 관계를 맺게함.
    - 이를 통해 인터페이스 레퍼런싱으로 동작할 수 있게함.
- 결합도 낮추기
    - 실제 선언부와 구현부를 분리하면서 메소드를 호출하는 쪽에선 인스턴스를 레퍼런싱하는 인터페이스만 사용하기 때문에 실제 구현 클래스의 변화에 독립적

## default 메서드, static 메서드

JDK 1.8 이후에는 인터페이스 내에 `default` 메서드와 `static` 메서드를 추가할 수 있게 되었습니다. 

인터페이스는 기능 변경을 지양해야하지만 반드시 추가되어야 하는 기능이 있을 수 있습니다. 이 때 `abstract` 메서드를 추가한다면 인터페이스를 구현하는 모든 클래스에서 모두 `abstract` 메서드를 구현해줘야합니다. 

이러한 문제점 때문에 JDK 1.8 버전에서 `default` , `static` 메서드가 추가되었습니다

### static 메서드

`static` 메서드는 인스턴스와는 별개의 메서드기 때문에 인터페이스 내에 선언하는 것은 원래 인터페이스의 원칙에 어긋나는 것은 아니었지만, 구현체가 없게끔 하는 통일성에서 사용이 금지되었습니다. 

하지만 `default` 메서드를 사용하게끔 하면서 `static` 메서드도 사용이 가능해졌습니다.

### default 메서드

위에 언급했던 것과 같은 문제점 때문에 인터페이스 내에 실제 구현부를 작성할 수 있는 `default` 메서드를 사용할 수 있게 되었습니다. 사용할 때는 메서드 앞에 `default` 제어자를 작성해서 사용합니다.

### default 메서드 충돌

앞선 `abstract` 메서드들은 실제 구현부가 없었기 때문에 다중상속에 의한 문제점이 발생되지 않았습니다. 하지만 `default` 메서드들은 구현부가 있기 때문에 다른 인터페이스 및 클래스의 메서드들과 충돌할 수 있습니다. 따라서 다음과 같은 규칙이 존재합니다

- 인터페이스의 `default` 메서드 간 충돌
    - 충돌하는 메서드를 오버라이딩해서 재작성해야함
- 인터페이스의 `default` 메서드와 상속한 클래스 메서드 충돌
    - 인터페이스의 메서드가 무시됨

### private 키워드

인터페이스 내에도 `private` 키워드를 통해서 메서드를 작성할 수 있습니다. JVM 1.9 버전부터 사용할 수 있으며, `default` 및 `static` 메서드 앞에 작성할 수 있습니다.

> 참고 : Java의 정석 1권
