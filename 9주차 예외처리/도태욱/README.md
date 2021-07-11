## 9. 예외 처리

목표 : 자바의 예외 처리에 대해 학습한다.

### **학습할 것**

- Exception과 Error의 차이는?
- 자바가 제공하는 예외 계층 구조
- RuntimeException과 RE가 아닌 것의 차이는?
- 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
- 커스텀한 예외 만드는 방법

---

<br/>

### 9-1. Exception과 Error의 차이는?

---

***" Error vs. Exception"***

- **Throwable** (에러) : 프로그램이 오작동하게 초래하는 원인
    - Compile-time error : 컴파일 에러. 컴파일 시에 발생하는 에러
    - Runtime error : 실행 시에 발생하는 에러
    - Logical error : 논리적 에러. 실행은 되지만, 의도와 다르게 동작하는 것

- Java는 **Runtime error**를 **Error**(에러)와 **Exception**(예외)로 구분한다.
    - **Error** (에러) : 프로그램 코드에 의해 수습될 수 없는 심각한 오류
        - ex. OutOfMemoryError, StackOverflowError, VirtualMachineError
    - **Exception** (예외) : 프로그램 코드에 의해 수습될 수 있는 다소 미약한 오류
        - 크게 RuntimeException속과 Exception속으로 나뉜다.

*즉, 에러는 컨트롤할 수 없는 것이기에, 발생하는 예외에 대해서만 프로그램이 비정상적으로 종료되지 않도록 처리해야한다.*

</br>

### 9-2. 자바가 제공하는 예외 계층 구조

---

> Java에서는 실행 시 발생할 수 있는 Exception과 Error를 클래스로 정의하였고, 이 역시 Object 클래스의 subclass이다.

![1](https://user-images.githubusercontent.com/69946102/125198803-601aa080-e29e-11eb-928d-0dcfbd4a58bc.png)
- 모든 예외는 Exception 클래스의 subclass이다.
- Exception은 크게, RuntimeException인지 아닌지로 나뉘게 된다.

</br>

### 9-3. RuntimeException과 RE가 아닌 것의 차이는?

---

앞으로, RuntimeException클래스에 속하는 Exception들을 **'RuntimeException'**, 속하지 않는 Exception들을 **'Exception'**이라고 하겠다.

> RuntimeException : 개발자의 실수로 발생하는 예외
Exception : 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외

- Exception은 컴파일 시 예외를 체크하기 때문에 **CheckedException**, RuntimeException은 예외를 체크하지 않기 때문에 **UncheckedException**이라고 한다.

</br>

### 9-4. 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

---

**" 예외 처리 방법 "**

1. 직접 처리하는 방법

    : 오류가 발생한 곳에서 직접 `try` - `catch`로 예외 처리

2. 선언적 예외 처리

    : 메서드 선언부에 `throws`로 처리 → 메서드 호출한 곳으로 예외를 던져서 예외 처리하도록 위임

    - 목적
        - 예외 처리에 대한 다양성 제공
        - 메서드의 정상적인 실행결과는 `return`을 통해서 전달하고, 비정상적인 결과는 예외를 `throws`해서 전달한다.
        - 한 프로그램 내에서 예외 처리하는 방법이 같으므로, 모든 메서드에서 예외처리하지 않고 한 곳으로 전달해 한꺼번에 예외 처리
3. 사용자 정의 Exception
    - CheckedException

        : RuntimeException을 제외한 Exception을 상속받으면 된다.

    - UncheckedException

        : RuntimeException을 상속받으면 된다.

- `try` - `catch` - `finally`

    ```java
    /* try - catch - finally.
    try {
    	// 예외 발생 가능성이 있는 문장들
    } catch (Exception1 e1) {
    	// Exception1이 발생했을 경우, 이를 처리할 문장들
    } catch (Exception2 e2) {
    	// Exception2가 발생했을 경우, 이를 처리할 문장들
    } catch (Exception3 e3) {
    	// Exception3이 발생했을 경우, 이를 처리할 문장들
    } finally {
    	// 항상 수행되어야하는 문장들
    }
    */
    ```

    - `try`블럭에서 예외 발생시, 해당 예외와 일치하는 `catch`블럭 내의 문장들이 수행된다.
    - 일치하는 예외처리가 여러개라도, 단 하나의 `catch`블럭만 수행된다.
    - `try`블럭에서 예외가 발생하지 않았을 경우, `try` - `catch`구문을 빠져나온다.
    - `finally`가 마지막에 존재한다면, 예외 발생 여부와 상관없이 내부 문장들을 수행한다.
- `throw` 키워드

    ```java
    try{
    	throw new Exception("msg");
    } catch(Exception e){
    	System.out.println(e.getMessage());
    }

    /* result.
    msg
    */

    ```

    - 예외를 발생시켜야 할 때 해당 키워드를 사용한다.
    - Throwable 클래스의 하위 클래스인 모든 예외 발생가능.
- `throws` 키워드

    ```java
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println(br.readLine());
    }

    ```

</br>

### 9-5. 커스텀한 예외 만드는 방법

---

```java
class myException extends Exception{
	myException(){}
	myException(String msg){
		super(msg);
	}
}

try {
	throw new myException("msg");
} catch(myException e){
	System.out.println(e.getMessage());
}

/* result.
msg
*/
```

</br>
