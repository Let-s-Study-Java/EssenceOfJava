## 11. Enum

목표 : 자바의 열거형을 학습한다.

### **학습할 것**

- enum 정의하는 방법
- enum이 제공하는 메소드 (values()와 valueOf())
- java.lang.Enum
- EnumSet

---

<br/>

### 11-1. enum 정의하는 방법

---

> 열거형(enum) : 서로 관련된 상수를 편리하게 선언하기 위한 것. 코드의 가독성을 높이고, 논리적인 오류를 줄이게 해 준다.

- Java에서의 열거형
    - 원래 존재하지 않았으나, JDK1.5부터 새로 추가되었다.
    - C언어의 열거형과 달리, 열거형이 갖는 값뿐만 아니라 타입도 관리하여 보다 논리적인 오류를 줄일 수 있다.
    - 열거형 상수간의 비교에서 **'=='**를 사용할 수 있다.
    - `enum`은 클래스이기에, 생성자와 메서드를 추가할 수 있다.

- 열거형 정의하는 방법

    ```java
    // enum 열거형이름 { 상수명1, 상수명2, ... }

    enum Direction {
    EAST,SOUTH,WEST,NORTH;
    };
    enum NewDirection {LEFT,RIGHT};
    ```

- 열거형 사용 예시
    - **'열거형이름.상수명'**과 같이 사용한다.

    ```java
    // EnumTest.java
    public class EnumTest{
    	enum Direction {EAST,SOUTH,WEST,NORTH};
    	enum NewDirection {LEFT,RIGHT};

    	public static void main(String[] args) {		
    		Direction dir;
    		NewDirection nDir;
    		
    		dir = Direction.EAST;
    		nDir = NewDirection.LEFT;
    		
    		/* 컴파일 에러(Incompatible operand types) 발생
    		if(dir == nDir) {
    				System.out.println(dir);
    		}
    		*/

    		System.out.println(dir);

    		switch(dir) {
    		case EAST: // Direction.EAST라고 쓰면 안 된다.
    			System.out.println("올바른 출력입니다.");
    			break;
    		}
    	}
    }

    /* result.
    EAST
    올바른 출력입니다.
    */
    ```

</br>

### 11-2. enum이 제공하는 메소드 (values()와 valueOf())

---

> 컴파일러가 자동으로 추가해주는 메서드

```java
static E values()
static E valueOf(String name)
```

위 두 메서드는 컴파일러가 자동으로 추가해주기 때문에 별다른 입력없이 사용할 수 있다.

- values() : 열거형의 모든 상수를 배열에 담아 반환한다.
- valueOf() : 열거형 상수의 이름으로 문자열 상수에 대한 참조를 얻을 수 있게 해준다.

~~앞서 사용한 예시에 이어지는 내용이다.~~

```java
// Main Method of EnumTest.java
Direction[] dirArray = Direction.values();
for(Direction d : dirArray) {
	System.out.println(d.name());
}

/* result.
EAST
SOUTH
WEST
NORTH
*/
```

```java
// Main Method of EnumTest.java
Direction dir = Direction.EAST;
Direction newDir = Direction.valueOf("EAST");
System.out.println(dir == newDir);

newDir = Direction.valueOf("WEST");
System.out.println(dir == newDir);

/* result.
true
false
*/
```

</br>

### 11-3. java.lang.Enum

---

> java.lang.Enum : 모든 열거형의 superclass이다.

- `extends`를 이용하여 선언할 수는 없다.

아래 표는, `java.lang.Enum`에 정의된 메서드들이다.

![1 (1)](https://user-images.githubusercontent.com/69946102/127725617-ef7ca19e-71f7-499a-a402-a5303a546c2b.png)

</br>

### 11-4. EnumSet

---

> 열거형을 위해 고안된 특별한 Set 인터페이스 구현체

`HashSet`과 비교했을 때, 성능상 이점이 많으므로 열거형 데이터를 위한 Set은 `EnumSet`을 사용하는 것이 좋다.

- 메서드
    1. allOf : Enum 클래스 전체를 담는다.
    2. noneOf : 빈 컬렉션을 만들 수 있다.
    3. range : Enum의 하위 집합을 만든다.
    4. complementOf : 매개 변수로 전달된 요소를 제외한다.
    5. copyOf : 다른 EnumSet의 모든 요소를 복사하여 EnumSet을 만들 수 있다.

사용 예시

```java
EnumSet<Direction> eSet = EnumSet.allOf(Direction.class);
System.out.println(eSet);

/* result.
[EAST, SOUTH, WEST, NORTH]
*/

```
