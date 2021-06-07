## 4. 제어문

##### 목표 : 자바가 제공하는 제어문을 학습한다.

##### 학습할 것

- 조건문
- 반복문

##### 과제

- Stack 구현

---

</br>

#### 4-1. 조건문

---

> 조건식과 문장을 포함하는 블럭 `{}`으로 구성되어 있다.

- `if`문

  ```java
  if(조건식) {
      // 조건식이 true일 때 수행될 문장들
  }
  
  // example.
  if(num==3)	System.out.println("ok");	// ok
  // 대입연산자(=)가 아닌 등가비교 연산자(==)를 사용해야 함에 주의하자.
  // 블럭 내의 문장이 하나뿐일 경우 블럭 `{}` 생략이 가능하다.
  ```

  - `else`문과의 혼용

    ```java
    if(조건식1){
        //조건식1이 true일 때 수행될 문장들
    }else if(조건식2){
        //조건식2가 true일 때 수행될 문장들
    }else if(조건식3){
        //조건식3이 true일 때 수행될 문장들
    }else{
        //위의 모든 조건식들이 false일 때 수행될 문장들
    }
    ```

    - 위에서부터 순서대로 조건을 검사하다 참인 조건식을 만나면, 

      해당 블럭`{}`만 수행하고, 전체 `if-else`문을 빠져나온다.

</br>

- `switch`문

  ```java
  // 하나의 조건식으로 여러 경우의 수를 처리할 수 있고, if문에 비해 처리시간이 더 짧다.
  switch(조건식){
      case 값1:
          //조건식의 결과가 값1과 일치할 경우 수행될 문장들
          break;
      case 값2:
          //조건식의 결과가 값2와 일치할 경우 수행될 문장들
          break;
      default:
          //조건식의 결과와 일치하는 case문이 없을 때 수행될 문장들
  }
  ```

  - 수행순서

    1. 조건식을 계산한다.
    2. 조건식의 결과와 일치하는 `case`문으로 이동한다.
    3. 이후의 문장들을 수행한다.
    4. `break`문이나 `switch`문의 끝을 만나면 `switch`문 전체를 빠져나간다.

  - `case`문의 값에 대한 제약조건

    - 정수 상수만 가능하다. (실수나 다른 변수는 불가능)
    - 서로 중복되지 않아야 한다.

  - 각각의 `case`는 참일 경우, 코드를 수행 후 `break`문을 만나 전체 `switch`문을 빠져나와야 한다. 하지만 `break`가 없을 경우 종료되지 않고 아래의 `case`를 모두 수행하게 된다.

    ```java
    // example.
    int num = 2 ;
    switch (num) {
        case 1:
            System.out.println("num은 1입니다.");
        case 2:
            System.out.println("num은 2입니다.");
        case 3:
            System.out.println("num은 3입니다.");
        default:
            System.out.println("num을 측정할 수 없습니다.")
    }
    // result.
    // num은 2입니다.
    // num은 3입니다.
    // num을 측정할 수 없습니다.
    ```

</br>

#### 4-2. 반복문

---

> 조건에 해당할 경우 특정 작업을 반복수행하기 위해 사용한다.

- `for`문

  - 반복 횟수를 알고 있을 때 사용하기 적합하다.
  - 처음 1회 '초기화'된 변수는 '조건식'이 참이라면 아래 조건문을 수행하고 '증감식'을 거치게 된다. 이후 다시 '조건식'을 검사하고 조건문을 수행하는 반복을 거치게 된다.

  ```java
  for(초기화;조건식;증감식){
      // 조건식이 참일 경우 수행될 문장들
  }
  
  // example.
  for(int i=0;i<10;i++){
      System.out.print(i+" ");
  }
  // result.
  // 0 1 2 3 4 5 6 7 8 9 
  ```

  - 향상된 `for`문

    - JDK 1.5부터 배열과 컬렉션에 저장된 요소에 접근할 때 더 편리한 방법을 사용할 수 있다.

    ```java
    for(타입 변수명 : 배열 또는 컬렉션) {
        // 반복할 문장
    }
    
    // example.
    int[] nums = {1,2,3,4,5};
    for (int num : nums){
        System.out.print(num+" ");
    }
    // result.
    // 1 2 3 4 5 
    ```

</br>

- `while`문

  ```java
  while(조건식){
      // 조건식이 참일 경우 수행될 문장들
  }
  ```

  - `do-while`문

    - `while`문과 반대로 블럭 `{}`을 먼저 수행한 후에 조건식을 평가한다. (최소 한 번은 실행된다.)

    ```java
    do {
        // 조건식이 참일 경우 수행될 문장들
    } while (조건식);
    ```

    

  - `for`문과 `while`문은 서로 변환이 가능하다. 하지만 `for`문과 달리 `while`문의 조건식은 생략할 수 없다.

    ```java
    for( ; ; ){ // 항상 true
        // 수행할 코드
    }
    while() { // 조건식이 없으므로 에러 발생
        // 수행할 코드
    }
    ```

</br>

*cf. `break`문과 `continue`문의 사용법*

- *`break`문은 자신이 포함된 가장 가까운 반복문을 벗어난다.*

- *`continue`문은 아래의 수행문을 더 이상 수행하지 않고 다음 반복으로 넘어간다.*

</br>



#### 4-3. Stack 구현

---

```java
public class Stack {
//	int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
	int[] stack;
	int idx;
	
	public Stack(int size) {
		stack = new int[size];
		idx = 0;
	}
//	void push(int data)를 구현하세요.
	public void push(int data) {
		stack[idx] = data;
		idx++;
	}
	
//	int pop()을 구현하세요.
	public int pop() {
		if(idx >= 0) {
			return stack[idx--];
		}
        System.out.println("error");
		return -1;
	}
}
```

</br>
