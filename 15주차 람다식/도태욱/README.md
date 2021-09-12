## 15. 람다식

목표 : 자바의 람다식을 학습한다.

### **학습할 것**

- 람다식 사용법
- 함수형 인터페이스
- Variable Capture

---

<br/>

### 15-1. 람다식 사용법

---

> 람다식(Lambda Expression) :  메서드를 하나의 '식(expression)'으로 표현한 것

- 메서드를 람다식으로 표현하게 되면 메서드의 **이름**과 **반환값**이 없어지므로, 람다식을 '**익명 함수**(anonymous function)'이라고 한다.
- 메서드에서 이름, 반환타입을 떼어내고 parameter 선언부와 body`{ }` 사이에 `->`를 추가한다.
- parameter가 하나일 경우, 괄호 `( )`를 생략할 수 있다. 하지만, 타입이 있을 경우 생략할 수 없다.
parameter의 타입이 추론 가능한 경우 타입또한 생략할 수 있다.

    ```java
    a -> a * a
    (int a) -> a * a
    ```

- 괄호 `{ }`안의 문장이 한 줄일 경우 생략 가능하다. 이 때, 끝에 `;`를 붙이지 않아야 한다.
하지만, return문일 경우 괄호 `{ }`를 생략할 수 없다.

    ```java
    (String name, int i) -> System.out.println(name + ":" + i)
    (int a, int b) -> { return a > b ? a : b ; }
    ```

- Example 1.

    ```java
    // LambdaTest.java
    public class LambdaTest{
    	// 메서드 선언 
    	static int method(){
    		return (int) (Math.random()*5)+1;
    	}

    	public static void main(String[] args) {
    		int[] arr = new int[5];
    		// 기존 메서드 사용 
    		for(int i : arr) {
    			i = method();
    			System.out.print(i);
    		}
    		System.out.println();

    		// 람다식 표현
    		Arrays.setAll(arr, i -> (int) (Math.random()*5)+1);
    		for(int a: arr) {
    			System.out.print(a);
    		}
    		System.out.println();
    	}
    }

    /* result.
    21412
    12355
    */
    ```

- Example 2.

    ```java
    // 삼각형의 넓이(S) = 밑변(a) * 높이(h) * 1/2

    /* method :
    returnType methodName (parameter) {
    	return ... ;
    }
    */
    int solve(int a, int h){
    	return a * h * 0.5 ;
    }

    /* lambda :
    (parameter) -> ... ;
    */
    (a,h) -> a * h * 0.5;
    ```

</br>

### 15-2. 함수형 인터페이스 (Functional Interface)

---

> 람다식은 메서드가 아닌, 익명 클래스의 객체와 동등하다.

**" 함수형 인터페이스 "**

- 추상 메서드를 하나만 가지고 있는 인터페이스
- `@FunctionalInterface` 어노테이션을 가지고 있는 인터페이스
- 람다식을 다루기 위한 인터페이스

```java
// 0. 람다식을 익명 클래스의 객체에 넣기
new Object() {
	int max(int a, int b){
		return a > b ? a : b ;	
	}
}

// 1. 위 람다식으로 정의된 익명 객체의 메서드를 어떻게 호출할 것인가?
'타입' f = (int a, int b) -> a > b ? a : b ;

// 2-a. 참조변수의 타입은 무엇인가? 클래스? 인터페이스?
interface MyFunction {
	public abstract int max(int a, int b);
}

// 2-b. 그렇다면, 위 인터페이스를 구현한 익명클래스의 객체는 ...
MyFunction f = new MyFunction() {
								public int max(int a, int b){
									return a > b ? a : b ;	
								}
							};

// 3. 익명 객체를 람다식으로 대체할 수 있다.
MyFunction f = (int a, int b) -> a > b ? a : b ;
```

람다식도 익명 객체이고, 'MyFunction' 인터페이스를 구현한 익명 객체의 메서드 max()와 람다식의 매개변수 타입, 갯수, 리턴타입이 일치하기에 3번이 가능하다.

위와 같이 인터페이스를 통해 람다식을 다룰 수 있음을 알았고, 람다식을 다루기 위한 인터페이스를 **함수형 인터페이스**라고 한다.

```java
@FunctionalInterface
interface MyFunction {
	public abstract int max(int a, int b);
}
```

</br>

### 15-3. Variable Capture

---

자바 8부터는 final을 생략할 수 있는 경우가 있다. 이를 effective final이라고 하는데, effective final은 해당하는 변수가 어디에서도 변경하지 않는 경우 컴파일러가 final이라고 판단한다.

만약 익명의 인스턴스에서 외부의 변수를 참조할 때 final 키워드가 없다면 에러를 발생시킨다. 위에서 봤듯, 이때 명시적으로 final을 붙여주거나 컴파일러가 effective final이라고 판단하면 사용이 가능하다.

이처럼 익명 클래스, 로컬 클래스, 람다 총 세개의 식은 컴파일러가 필요한 정보를 외부의 변수를 복사하여 넘겨주는데 이를 variable capture라고 한다.

</br>
