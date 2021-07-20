## 10. 멀티쓰레드 프로그래밍

목표 : 자바의 멀티쓰레드 프로그래밍을 학습한다.

### **학습할 것**

- Thread 클래스와 Runnable 인터페이스
- 쓰레드의 상태
- 쓰레드의 우선순위
- Main 쓰레드
- 동기화
- 데드락

---

<br/>

### 10-1. Thread 클래스와 Runnable 인터페이스

---

***cf. 프로세스와 쓰레드***

***Process** : 실행 중인 프로그램. 자원 (데이터와 메모리)+ 쓰레드로 구성.*

***Thread** : 프로세스의 자원을 이용해 실제로 작업을 수행하는 것*

*멀티쓰레드 = 자원 + 쓰레드 + 쓰레드 + ...*

**멀티쓰레딩의 장점**

*DOS와 달리 multi-tasking을 지원하는 대부분의 OS를 생각해보자.*

*ex. 메신저(멀티쓰레딩) : 채팅 + 파일 다운로드 + 음성대화 + ...*

- CPU의 사용률을 향상시킨다.
- 자원을 보다 효율적으로 사용할 수 있다.
- 사용자에 대한 응답성이 향상된다.
- 작업이 분리되어 코드가 간결해진다.

> 쓰레드를 구현하는 두가지 방법 : **Thread** 클래스와 **Runnable** 인터페이스

*Thread 클래스를 상속받게 될 경우, 다른 클래스를 상속받을 수 없다. (클래스 다중상속 지원X)*

*그러므로, Runnable 인터페이스를 구현하는 방법이 일반적이다.*

1. Thread 클래스

    ```java
    class Thread1 extends Thread {
    	@Override
    	public void run() {
    		// 작업내용
    		for(int i=0;i<30;i++) {
    			System.out.print("T");
    		}
    	}
    }
    ```

2. Runnable 인터페이스

    ```java
    class Thread2 implements Runnable {
    	@Override
    	public void run() {
    		// 작업내용
    		for(int i=0;i<30;i++) {
    			System.out.print("R");
    		}
    	}
    }
    ```

    `Runnable` 인터페이스는 본래 `run()`만 정의되어 있는 간단한 인터페이스이다.

3. 쓰레드 인스턴스 생성 후 실행

    ```java
    public class ThreadTest{
    	public static void main(String[] args) {
    		/* 쓰레드 인스턴스 생성 */
    		// 1. Thread 클래스 상속받은 경우
    		Thread1 t1 = new Thread1();
    		// 2. Runnable 인터페이스 구현한 경우
    		Thread t2 = new Thread(new Thread2());
    		
    		/* 쓰레드 실행 */
    		t1.start();
    		t2.start();
    	}
    }

    /* result.
    TTTTTRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRTTTTTTTTTTTTTTTTTTTTTTTTT
    */
    ```

    ~~코드 실행 결과는 실행시마다 달라질 수 있다.~~

</br>

### 10-2. 쓰레드의 상태

---

> `Thread`의 `getState()`메서드를 호출하여 쓰레드의 상태를 확인할 수 있다.

**쓰레드의 5가지 상태**

1. **NEW**

    : 쓰레드가 생성되고 아직 start()가 호출되지 않은 상태

2. **RUNNABLE**

    : 실행 중 또는 실행 가능한 상태

3. **BLOCKED**

    : 동기화블럭에 의해서 일시정지된 상태 (lock이 풀릴 때까지 기다리는 상태)

4. **WAITING, TIMED_WAITING**

    : 쓰레드의 작업이 종료되지는 않았지만, 실행가능하지 않은(unrunnable) 일시정지 상태. TIMED_WAITING은 일시정지시간이 지정된 경우를 의미한다.

5. **TERMINATED**

    : 쓰레드의 작업이 종료된 상태

</br>

### 10-3. 쓰레드의 우선순위

---

> `Thread`는 `priority`(우선순위) 멤버 변수를 가지고 있다.

우선순위의 범위는 1부터 10까지이며 숫자가 높을수록 우선순위가 높다.

`Thread`에서 priority와 관련해 사용가능한 메서드

- `setPriority(int priority)` : 쓰레드 실행 전에만 호출할 수 있다.
- `getPriority()`

```java
// Test.java
public class Test{
	public static void main(String[] args) {
		/* 쓰레드 인스턴스 생성 */
		// 1. Thread 클래스 상속받은 경우
		Thread1 t1 = new Thread1();
		// 2. Runnable 인터페이스 구현한 경우
		Thread t2 = new Thread(new Thread2());
		t1.setPriority(2);
		t2.setPriority(9);
		System.out.println("t1의 priority: " + t1.getPriority());
		System.out.println("t2의 priority: " + t2.getPriority());
		
		
		/* 쓰레드 실행 */
		t1.start();
		t2.start();
	}
}

/* result.
t1의 priority: 2
t2의 priority: 9
RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
*/
```

이전에 비해, 우선순위가 더 높은 t2가 t1보다 더 많이 실행되는 것을 볼 수 있다.

</br>

### 10-4. Main 쓰레드

---

> `main()`메서드도 하나의 쓰레드이며, 이를 Main Thread라고 한다.

이전 과정들에서, 쓰레드의 `start()`메서드를 통해 쓰레드를 실행시켰다. `start()`메서드의 역할은 해당 쓰레드가 작업을 하기 위한 호출 스택(call stack)을 생성하고, 생성된 호출 스택에 `run()`을 올리도록 하는 것이다.

- `run()`메서드의 수행이 종료된 쓰레드는 호출스택이 비워지고, 해당 호출 스택도 소멸된다.
- 만일 하나의 쓰레드에 대해 `start()`를 두 번 이상 호출하게 되면 `IllegalThreadStateException`이 발생한다.

    ```java
    // 코드 상하단부 생략.
    Thread t1 = new Thread1();
    t1.start();
    t1.start();

    /* result.
    Exception in thread "main" TTTjava.lang.IllegalThreadStateException
    	at java.lang.Thread.start(Thread.java:708)
    	at Test4.main(Test.java:31)
    */
    ```

**Main Thread**

- 프로그램이 시작하면 가장 먼저 실행되는 쓰레드이다.
- 다른 쓰레드를 생성해서 실행하지 않으면, Main Thread가 종료되는 순간 프로그램이 종료된다.

</br>

### 10-5. 동기화

---

> 한 쓰레드가 진행 중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것을 **'쓰레드의 동기화'**라고 한다.

*멀티쓰레딩의 경우, 여러 쓰레드가 같은 프로세스 내의 자원을 공유하여 작업하기 때문에 서로의 작업에 영향을 줄 수 있다. 이러한 일이 발생하는 것을 방지하기 위해 다른 쓰레드에 의해 방해받지 않도록 하는 것이 필요하다. 그래서 도입된 개념이 **'임계 영역(critical section)'**과 **'잠금(락, lock)'**이다.*

`synchronized`를 이용한 동기화

1. 메서드 앞에 synchronized를 붙인다.
    - 메서드 전체가 임계 영역으로 설정된다.
    - 해당 메서드가 호출된 시점에서부터 객체의 lock을 얻어 작업을 수행하다가 메서드가 종료되면 lock을 반환한다.
2. 메서드 내부 코드 일부를 블럭으로 감싸고 블럭 앞에 'synchronized(참조변수)'를 붙인다.
    - 참조변수는 락을 걸고자하는 객체를 참조하는 것이어야 한다.
    - 블럭의 영역 안으로 들어갈 때 객체의 lock을 얻게 되고, 블럭을 벗어날 때 lock을 반납한다.

```java
// 메서드 전체를 임계영역으로 설정
public synchronized void method(){ }
// 특정한 영역을 임계영역으로 설정
synchronized(parameter) {}
```

모든 객체는 lock을 하나씩 가지고 있으며, 해당 객체의 lock을 가지고 있는 쓰레드만 임계 영역의 코드를 수행할 수 있다. lock이 없는 쓰레드들은 기다려야 한다.

</br>

### 10-6. 데드락

---

> 교착상태. 한 자원을 여러 시스템이 사용하려고 할 때 발생할 수 있다.

*둘 이상의 쓰레드가 lock의 획득을 위해 대기하게 되었을 때, lock을 가지고 있는 쓰레드들도 똑같이 다른 lock의 획득을 위해 대기하면서 서로 block 상태에 놓이는 것을 말한다.*

- **데드락의 발생조건**

    아래 네 조건 중 하나라도 성립하지 않도록 만듦으로서 교착 상태를 해결할 수 있다.

    1. **Mutual Exclusion**(상호 배제) : 한 자원에 대해 한 번에 한 프로세스만이 사용한다.
    2. **Hold and wait**(점유 대기) : 공유된 자원을 가진 상태에서 다른 자원을 요구한다.
    3. **No preemption**(비선점) : 이미 할당된 자원을 강제로 빼앗을 수 없다.
    4. **Circular wait**(순환 대기) : 대기 프로세스의 집합이 순환 형태로 자원을 대기한다.
- **데드락의 해결법**
    1. **Prevention** : 데드락의 발생에 대해 미리 예방한다.
    2. **Avoidance** : 데드락을 적절하게 회피한다.
    3. **Detection & Recovery** : 데드락을 탐지하여, 데드락에서 회복한다.
