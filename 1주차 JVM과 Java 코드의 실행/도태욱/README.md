## 1. JVM과  Java 코드의 실행

##### 목표 : 자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기.

##### 학습할 것

- JVM이란 무엇인가

- 컴파일 하는 방법

- 실행하는 방법

- 바이트코드란 무엇인가

- JIT 컴파일러란 무엇이며 어떻게 동작하는지

- JVM 구성 요소

- JDK와 JRE의 차이

---

</br>

#### 1-1. JVM이란 무엇인가

---

> JVM(Java Virtual Machine) : 자바를 실행하기 위한 가상 컴퓨터

- Java로 작성된 application은 모두 JVM에서만 실행되기에 반드시 JVM이 필요하다.

- Java 응용프로그램은 JVM하고만 통신하고, JVM이 운영체제에게 변환하여 전달.
  -> 운영체제에 독립적. **'Write Once, Run Anywhere'**

- 다른 application의 코드와 달리 JVM을 추가로 거치고, 실행 시에 interpret된다.
  -> 속도가 느리다는 단점.

</br>

#### 1-2. 컴파일 하는 방법

---

> Compile : 소스파일(.java)로부터 클래스파일(.class)을 생성하는 과정

- 자바컴파일러 : javac.exe

```
javac xxx.java
```

</br>

#### 1-3. 실행하는 방법

---

> 컴파일하여 얻어낸 클래스파일(.class)을 인터프리터로 실행한다.

- 자바 인터프리터 : java.exe

```
java xxx
```

</br>

#### 1-4. 바이트코드란 무엇인가

---

> 바이트코드 : 컴파일된 자바코드.

- 플랫폼 독립적으로, JVM에서 실행되는 binary code로 2진수로 이루어져 있다.
- 즉, 컴파일이란 고급언어를 JVM이 실행할 수 있는 '바이트코드'로 만드는 작업이다.

</br>

#### 1-5. JIT 컴파일러란 무엇이며 어떻게 동작하는지

---

> JIT 컴파일러(Just-in-time compilation) : 바이트코드를 하드웨어의 기계어로 바로 변환해준다.

- JIT 컴파일러를 통해  Java의 대표적인 단점인 속도문제가 상당히 개선되었다.
- Interpreter는 코드를 한 줄씩 번역하므로, 동일한 바이트코드를 또 번역해야하는 문제가 발생한다.  
이러한 단점 보완을 위해 **번역한 코드를 캐싱**하여 interpret 시간을 단축시킬 수 있도록 한다.

</br>

#### 1-6. JVM 구성 요소

---

> 크게 Class Loader, Runtime Data Area, Execution Engine의 세 부분으로 나눌 수 있다.

1. **Class Loader**

   Runtime에 참조하는 .class 파일들을 Runtime Data Area로 로드하는 작업을 수행한다.

   내부적으로 로딩, 링크, 초기화의 단계가 존재한다.

2. **Runtime Data Area**

   JVM이 프로그램 실행을 위해 OS로부터 할당받은 메모리 영역을 뜻한다.

   Method Area, Heap Area, Stack Area, PC register, Native Method Stack의 5가지 영역으로 나눌 수 있다.

   - Method Area : 모든 Thread가 공유하는 메모리 영역
   - Heap Area : 동적 메모리 할당 영역. new  연산자로 만들어진 객체에 할당되는 영역

3. **Execution Engine**

   주로 Interpreter를 통해 컴파일된 바이트코드를 읽고 실행하는 역할을 한다.

   - JIT 컴파일러 : 코드의 중복 번역을 방지하여 Java의 느린 속도를 개선한다.
   - Garbage Collector : 자동으로 사용하지 않는 메모리를 회수하는 역할을 한다.


</br>

#### 1-7. JDK와 JRE의 차이

---

> JDK(Java Development Kit) : 자바개발도구

- JDK = JRE + 개발에 필요한 실행파일(javac.exe 등)
- Java로 프로그램 개발을 하기 위한 모든 파일을 포함한 kit를 의미한다.

> JRE(Java Runtime Environment) : 자바로 작성된 응용프로그램이 실행되기 위한 최소환경

- JRE = JVM + 클래스라이브러리(Java API)
