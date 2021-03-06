# 1. 변수형

## 기본형 (Primitive type)

기본형 변수는 참조하지 않고 실제 값(리터럴)을 저장하며 컴파일 시점에서 할당 메모리의 크기를 검사합니다.

- 자바의 기본형

```java
boolean     1byte       true, false
byte        1byte       -128 ~ 127
short       2byte       -32_768 ~ 32767
int         4byte       -2147483648 ~ 2147483647
long        8byte       -9223372036854775808 ~ 9223372036854775807
float       4byte       1.4E-45 ~ 3.4028235E38 (근사값)
double      8byte       4.9E-324 ~ 1.7976931348623157E308 (근사값)
char        2byte       '\u0000' ~ '\uFFFF'
```

## 리터럴과 상수

### 리터럴

`1, 0.1, 'c'` 와 같은 변수에 저장될 수 있는 **데이터 자체를 의미합니다.** 리터럴 값들은   불변성을 지니며 값은 실제 할당 이 후 변하지 않습니다.

자바에서 각 변수형 별로 리터럴을 표기하는 법은 다음과 같습니다.

- 정수형 (short ~ long)
    - 2진수 : `0b10`
    - 8진수 : `02`
    - 10진수 : `2`
    - 16진수 : `0x2`
- 부동소수점
    - float : `2.0F`
    - double : `2.0D`
- 문자형 및 문자열
    - char : `'2'`
    - String : `"2"`

### 상수

상수도 불변성을 지닌다는 관점에서 리터럴과 비슷한 점을 갖고 있습니다. 하지만 상수는 불변성을 지니는 **변수**라는 점에서 리터럴과 차이를 갖습니다.

한번 초기화된 상수는 이 후에 다시 초기화 할 수 없으며 자바에서는 `final` 키워드로 상수를 선언합니다.

## 참조형 (Reference type)

참조형은 기본형을 제외한 모든 타입을 칭하며 실제 데이터 값이 아닌 객체의 주소값(해시코드값)을 저장하는 변수를 말합니다.

- `Class` `Interface` `array` `Enumeration`...

참조형 변수는 항상 4byte의 같은 크기를 가지며 해당 객체의 해시코드값 혹은 null 값을 저장합니다. 

- 선언 시 기본 값은 null로 초기화 됩니다.
    - null을 참조하는 레퍼런스의 객체에 접근하려하면 런타임 에러(NullPointerException)가 발생할 수 있습니다.

이 때 `new` 키워드로 생성된 객체는 힙(Heap) 메모리에 저장됩니다.

```jsx
Person man = new Person();
/* 
new를 통해 객체를 생성하고 new의 반환값으로 생성된 객체의 해시코드를 받습니다.
그 후 그 주소를 man 참조변수에 저장하게 됩니다.
*/
```

### 참조 변수 연산

자바에서는 기본형 타입끼리만 산술 연산 및 논리연산이 가능하며 레퍼런스 타입은 산술연산 및 논리연산을 할 수 없습니다.

예외적으로 `==` 연산자를 통해 가르키고 있는 해시코드의 값을 비교할 수 있습니다. 이 때는 참조하고 있는 레퍼런스가 같다면 `true` 를 반환합니다.

### equals, comparable

레퍼런스끼리 값 비교가 필요할 경우 `Object.equals` 메소드를 오버라이딩하여 원하는 값끼리 비교하게 할 수 있습니다.

- ex) `String.equals(String str2)` ⇒ 참조 해시코드가 다르더라도 저장된 문자열 값이 같다면 `true` 반환

대소 비교가 필요하다면 `Comparable` 인터페이스를 구현하여 `compareTo(object O)` 메소드를 재정의하여 레퍼런스 간 비교를 할 수 있습니다.

### String Class 연산

`String Class` 는 예외적으로 `+` 로 산술연산이 가능합니다. 어떤 연산식에 `String` 객체 혹은 `String` 리터럴이 포함된다면 해당 수식의 모든 값은 String으로 비교하여 반환됩니다.

기본형 변수는 true 혹은 그 값이 가르키고 있는 실제 값이 String으로 반환되어 연산되며 참조형 변수는 해당 객체의 `toString()` 메소드를 통해 반환된 문자열로 치환됩니다.

```java
Object o1 = new Object();
Object o2 = new Object();
System.out.println(o1 + o2);
// println 메소드는 기본적으로 레퍼런스의 toString을 호출하지만 객체 간 산술연산은 불가능하므로 에러가 발생합니다.

String str = "hello";
System.out.println(str + o1 + o2);
//hello o1.toString() o2.toString()
//산술연산식에 String이 각 레퍼런스는 toString()을 호출하여 반환된 String으로 연산합니다.

System.out.println(o1 + o2 + "");
//o1.toString() o2.toString()
//이 역시 연산식에 String이 포함되었으므로 연산이 가능합니다.
```

## 배열

Java에서는 배열또한 참조형으로 사용하며 배열 객체가 저장되어 있는 해시코드를 참조하게 됩니다.

이 때 다중 배열은 해당 배열보다 한단계 낮은 배열의 해시코드를 참조합니다. 또한 직접적으로 배열의 차원 크기만큼 메모리를 할당하지 않기 때문에 다중배열은 물리적으로 정렬되어 있지 않습니다.

```java
int[][] arr = new int[2][];
arr[0] = new int[4];
arr[1] = new int[234];
/*
c언어처럼 물리적으로 2차원 배열을 형성하는게 아닌 
참조를 갖는 배열을 만드는 것이므로 각각의 배열은 연속되어 있지 않으며
다른 크기를 가질 수 있음
*/
```

---

# 2. 변수의 스코프

## 변수의 스코프

자바의 변수는 선언된 위치(블록) 및 키워드에 따라 참조할 수 있는 범위 및 생명주기가 결정됩니다. 이는 크게 **로컬 변수**, **인스턴스 변수, 클래스 변수**로 나뉩니다.

## 로컬 변수

로컬 변수(지역 변수)는 클래스 및 객체 내부의 메서드 블럭 및 초기화 블럭 등의 블럭 내부에서만 사용되는 변수입니다. 

### 생명 주기

로컬 변수들은 해당 블럭을 벗어나게 되면 생명주기가 끝나며, 더이상 호출할 수 없습니다. 이는 메서드 블럭을 포함한 반복문과 같은 모든 블럭에서 해당합니다.

```java
for(int i = 0 ; i < N ; i++)
{
	int cnt = i;
}
//컴파일 에러, 두 변수 모두 for 블럭 이후 소멸됨
System.out.println(i + cnt);
```

### 기본형 변수

로컬 기본형 변수는 기본값을 초기화하지 않습니다. (쓰레기 값으로 사용할 수 없음) 따라서 반드시 명시적으로 초기화해야하고, 그렇지 않으면 컴파일 에러가 발생합니다.

이 때 선언된 기본형 변수는 해당 쓰레드의 스택 메모리에 저장되어 생명주기가 다하면 메모리에서 제거됩니다.

### 참조형 변수

참조형 변수의 레퍼런스도 스택 메모리에 저장되게 됩니다. 

## 인스턴스 변수

인스턴스 변수는 클래스의 동적 속성으로 선언되는 변수들을 말하며, `static` 키워드가 없는 클래스의 변수 속성을 말합니다.

### 생명 주기

인스턴스 변수들은 객체가 `new` 키워드를 통해 생성되었을 때 힙에 메모리가 할당됩니다. 객체와 생명주기를 같이하여 객체의 사용이 만료되어 GC에 의해 수거될 때 메모리에서 제거됩니다.

### 기본형 변수

인스턴스 변수로 선언된 기본형 변수들은 초기 값을 `false` 혹은 `0` 에 준하는 값을 갖게됩니다.

## 클래스 변수

클래스 변수(정적 변수)는 클래스 내에서 선언되며, `static` 키워드를 작성하여 정적으로 선언된 변수를 말합니다. 

### 생명 주기

클래스 변수는 작성된 클래스의 로딩 과정에서 메모리 올라가게 됩니다. 그래서 해당 클래스를 더이상 참조하지 않게되어 `GC` 가 수거하게 되면 그 때 소멸되게 됩니다.

정적 속성은 객체가 아닌 클래스에 종속되어있으므로, 모든 같은 클래스로 생성된 객체에서 값을 공유합니다. 또한 객체 종속적이 아니므로 객체의 생성 없이도 호출할 수 있습니다.

```java
class A{
	static int a;
}
//...
A var1 = new A();
A var2 = new A();
var1.a++;
var2.a++;
//2
System.out.println(var1.a);
//2
System.out.println(var2.a);
//2
System.out.println(A.a);
```

---

# 3. 형변환

자바에서 형변환은 기본형 변수 및 리터럴에 서로 값의 범위가 다른 값을 넣거나, 부모 자식의 상속관계를 갖는 레퍼런스들의 참조관계를 변경할 때를 말하며 명시적 형변환(캐스팅)과 묵시적 형변환(프로모션)으로 구분됩니다.

## 프로모션

프로모션은 값을 담으려는 기본형 변수의 범위가 더 크거나, 슈퍼클래스(인터페이스) 레퍼런스에 서브클래스의 레퍼런스를 참조하려고 할 때 명시적으로 캐스팅하지 않아도 자동으로 형변환이 되는 것을 말합니다.

### 기본형

기본형 타입의 산술 연산에서는 더 큰 범위를 갖는 타입으로 자동 형변환되어 실행됩니다. 

- `boolean` 타입은 다른 타입과 치환될 수 없으므로 형변환이 불가능합니다.

```java
int a = 10;
long b = a;
// b에 자동형변환 된 a 가 저장됩니다.
a = b;

char c = 'a';
a = c;
// a == 61
//char type도 unsigned short와 같은 범위를 갖고 있기 때문에 자동형변환됩니다.
```

### 참조형

참조형에서 프로모션은 슈퍼클래스(인터페이스)의 레퍼런스가 서브클래스의 레퍼런스를 참조하는 것을 말하며 이 때도 명시적으로 작성하지 않아도 사용할 수 있습니다.

다만 이 때는 슈퍼클래스에서 서브클래스로 오버라이딩 된 속성만 접근이 가능합니다.

```java
class A {
	int a;
	@Override
	public boolean equals(Object o){
		return (A)o.a == this.a;
	}
	public void happy(){
		System.out.println("happy");
	}
}
//...

//모든 클래스는 Object를 상속하므로 프로모션 가능
Object var1 = new A();

//오버라이딩된 equals 호출 가능
var1.equals(new A());

//슈퍼클래스에 선언되지 않은 속성 접근 불가능(컴파일 에러)
var1.happy();
```

## 캐스팅

하지만 반대의 경우는 명시적 형변환을 해야합니다. 또한 기본형 변수의 캐스팅 시 값의 범위를 초과하는 값은 버려질 수 있습니다.

### 기본형

기본형 변수는 타입 범위로 형변환이 결정되므로 더 큰 범위에서 작은 범위의 값으로 형 변환 시 캐스팅이 필요하며 값이 유실될 수 있습니다.

```java
int a;
long b;
// 이 경우는 명시적형변환을 하지 않으면 컴파일 에러가 발생합니다.
// int 범위를 초과하는 값을 b가 갖고있다면 그 값들은 유실됩니다.
a = (int) b;
```

### 참조형

레퍼런스의 캐스팅은 슈퍼클래스의 레퍼런스를 서브클래스가 참조하는 것을 말합니다.

하지만 이 때는 모든 클래스가 캐스팅을 할 수 있는 것이 아닌 슈퍼클래스가 참조하고 있는 실제 인스턴스가 서브클래스의 인스턴스여야만 캐스팅이 가능합니다

```java
class A {
	int a;
	@Override
	public boolean equals(Object o){
		return (A)o.a == this.a;
	}
	public void happy(){
		System.out.println("happy");
	}
}
//...

Object var1 = new A();
//var1 의 실제 인스턴스가 A 이므로 캐스팅 가능
A var2 = (A)var1;

Object var3 = new Object();
//var3의 인스턴스는 Object이므로 ClassCastException
var2 = (A)Object;
```

- 이 때 실제 참조 객체가 캐스팅할 레퍼런스의 객체와 같지 않다면 ClassCastException 예외 발생
- `instanceof` 를 사용해서 객체 인스턴스 확인

# 4. 타입 추론

타입 추론이란 코드 작성 시 명시적으로 변수형을 작성하지 않고도 컴파일 시 변수형을 대입하는 것을 말합니다.

## var

JVM 10 버전에서는 자바에서도 타입 추론이 가능하게 되었습니다. 이 때 `var` 로 변수형을 작성하여 추론이 가능하도록 합니다.

이 때는 선언과 동시에 리터럴을 통한 초기화가 이루어져야 하며 해당 리터럴의 형태로 컴파일러가 변수형을 대입하게 됩니다.

```java
//int 형으로 자동 대입
var i = 5;

//string으로 대입
var str = "hi";

// 선언과 동시에 초기화하지 않으므로 컴파일 에러
var a;
a = 3.5;

```

 `var` 속성에서 사용할 수 없고 로컬 변수로만 사용이 가능합니다. 그리고 컴파일 시 타입이 결정되므로, `JavaScript` 와 같이 런타입 타입 변경이 불가능합니다.

---

