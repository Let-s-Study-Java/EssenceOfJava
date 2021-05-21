## 2. 자바 데이터 타입, 변수 그리고 배열

##### 목표 : 자바의 프리미티브 타입, 변수 그리고 배열을 사용하는 방법을 익힙니다.

##### 학습할 것

- 프리미티브 타입 종류와 값의 범위 그리고 기본 값
- 프리미티브 타입과 레퍼런스 타입
- 리터럴
- 변수 선언 및 초기화하는 방법
- 변수의 스코프와 라이프타임
- 타입 변환, 캐스팅 그리고 타입 프로모션
- 1차 및 2차 배열 선언하기
- 타입 추론, var

---

</br>

#### 2-1. 프리미티브 타입 종류와 값의 범위 그리고 기본 값

---

> Primitive type of Java : boolean, byte, short, int, long, float, double, char

|   자료형    |  기본값  |     저장 가능한 값의 범위     | 크기 (byte) |
| :---------: | :------: | :---------------------------: | :---------: |
| **boolean** |  false   |          false,true           |      1      |
|  **char**   | '\u0000' | '\u0000' ~ '\uffff' (0~65535) |      2      |
|  **byte**   |    0     |          -128 ~ 127           |      1      |
|  **short**  |    0     |        -2^15 ~ 2^15-1         |      2      |
|   **int**   |    0     |       -2^31  ~  2^31-1        |      4      |
|  **long**   |    0L    |       -2^63  ~  2^63-1        |      8      |
|  **float**  |   0.0f   |       1.4E-45 ~ 3.4E38        |      4      |
| **double**  |   0.0    |      4.9E-324 ~ 1.8E308       |      8      |

</br>

#### 2-2. 프리미티브 타입과 레퍼런스 타입

---

> Primitive type (기본형 변수) : 실제 값(data)을 저장한다.

- JVM Runtime Data Area의 Stack 메모리에 저장된다.

- 논리형 (boolean) : true/false의 값으로, 조건식과 논리적 계산에 사용한다.

- 문자형 (char) : 문자를 저장하는데 사용되며, 변수에 하나의 문자만 저장할 수 있다.

- 정수형 (byte, short, **int**, long) : 정수를 저장하며, 주로 int를 사용한다.

- 실수형 (float, **double**) : 실수를 저장하며, 주로 double을 사용한다.

</br>

> Reference type(참조형 변수) : 값이 저장되어 있는 주소(memory address)를 저장한다.

- Heap 메모리에 저장된다.
- `Java.lang.Object`를 상속받은 값으로 기본형이 아니면 모두 참조형 변수이다.
- 참조형 변수를 선언할 때 변수의 타입으로 클래스의 이름을 사용하므로, 클래스의 이름이 참조변수의 타입이 된다.

```java
// ClassName name;
Date today = new Date();
```

</br>

*cf. 정적 변수(static)와 인스턴스 변수는 Heap 메모리에 저장된다.*

</br>

#### 2-3. 리터럴

---

> literal : 그 자체로 값을 의미하는 것

```java
//  변수  리터럴 
int year = 2021;
```

- long 타입의 리터럴에 접미사 'L'을 붙인다.

- float 타입의 리터럴에 접미사 'f'를 붙인다.

```java
// 정수 26을 표현하는 여러 방법
int num = 26;		// 10진법. 일반적인 형태
int octNum = 032;	// 0으로 시작하면 8진법.
int hexNum = 0x1a;    // 0x로 시작하면 16진법.
int binNum = 0b11010;	// 0b로 시작하면 2진법.
long longNum = 26L;	// long 타입.
```

</br>

*cf. 타입의 불일치*

*리터럴의 타입은 저장될 변수의 타입과 일치하는 것이 일반적이지만,*

*타입이 달라도 저장범위가 **넓은 타입에 좁은 타입의 값을 저장하는 것**이 허용된다.*

```java
int i = 'A';    // 문자 'A'의 유니코드인 65가 변수 i에 저장된다.
long l = 123;   // int보다 long타입이 더 범위가 넓으므로 가능하다.
double d = 3.14f;   // float보다 double타입이 더 범위가 넓으므로 가능하다.
```

</br>

#### 2-4. 변수 선언 및 초기화하는 방법

---

```java
// 변수 선언. 저장공간을 확보한다.
int num;
// 초기화. 선언된 저장공간에 원하는 값을 저장한다.
num = 7;
// 변수 선언과 초기화를 동시에 할 수 있다.
int num = 7;
```

</br>

#### 2-5. 변수의 스코프와 라이프타임

---

> 스코프(Scope) : 선언한 변수를 사용할 수 있는 영역의 범위를 뜻한다.
>
> 라이프타임(Life time) : 변수가 메모리에 언제까지 존재하는 가를 의미한다.

1. **Instance Variable** : 클래스 영역에 선언되는 변수
   - Scope - `static` 메서드를 제외한 클래스 전체
   - Life time - 클래스를 instance화한 객체가 메모리에서 사라질 때
2. **Class Variables** :  `static`으로 클래스 안에서 선언된 변수
   - Scope - 클래스 전체
   - Life time - 프로그램 종료시
3. **Local Variables** : 지역변수.
   - Scope - 변수가 선언된 block  `{ }` 내부
   - Life time - 선언된 메서드의 종료와 함께 소멸된다. 반복문 또한 선언된 block을 벗어나게 된다면 소멸된다.

</br>

#### 2-6. 타입 변환, 캐스팅 그리고 타입 프로모션

---

> 타입 변환 : 변수나 리터럴의 타입을 다른 타입으로 변환하는 것
>
> Primitive type 변수는 `boolean`을 제외하고 서로 형변환이 가능하다.

- **Type casting** : 크기가 더 큰 자료형을 더 작은 자료형으로 변환한다.
  이 경우, 데이터 손실이 올 수 있다는 점을 유의하여야 한다.

  ``` java
  int a = 10000;
  byte b = (byte) a;	//표현범위를 벗어나는 캐스팅으로 데이터에 변형을 준다.
  System.out.println(b);	// 16
  ```

  

- **Type promotion** : 크기가 더 작은 자료형을 더 큰 자료형에 대입하는 것을 의미한다.

  ```java
  byte a = 10;
  int b = a;
  System.out.println(b);	// 10 😊
  ```

</br>

*cf. Reference type에서도 **상속관계**에 있을 때 형변환이 가능하다.*

- Upcast : subclass -> superclass (자동 형변환)
  - 이 때 객체 내의 모든 멤버(변수, 메서드)가 아닌, 부모의  멤버에만 접근이 가능하다.
- Downcast : superclass -> subclass
  - 명시적으로 타입을 지정해야 캐스팅할 수 있다.
  - **오류 발생의 위험이 있다.**


</br>

#### 2-7. 1차 및 2차 배열 선언하기

---

> 배열(Array) : 동일한 type의 데이터를 연속된 공간에 저장하는 자료구조

**1차 배열 선언하기**

```java
// 타입[] Array이름 = new 타입[길이];
int[] numArray = new int[10];
// 타입[] Array이름 = {Elements};
int[] newArray = {1,2,3};		//선언과 동시에 초기화를 시켜주어야 한다.
```

**2차 배열 선언하기**

```java
int[][] array = new int[3][4];
```

</br>

#### 2-8. 타입 추론, var

---

> 메서드 호출 및 선언, variable이나 object 선언을 통해 실제 타입을 추론할 수 있다.

- 타입 추론으로 Generic 메서드를 사용할 때 타입을 명시하지 않고 사용 가능하다.

  ```java
  List<String> newList = new ArrayList<>();
  ```

- `var`라는 type keyword를 사용하여 실제 타입을 추론할 수 있다.

  ```java
  var a = 5;
  var list = new ArrayList<String>();
  ```
