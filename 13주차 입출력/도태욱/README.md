## 13. I/O

목표 : 자바의 Input과 Output에 대해 학습한다.

### **학습할 것**

- 스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O
- InputStream과 OutputStream
- Byte와 Character 스트림
- 표준 스트림 (System.in, System.out, System.err)
- 파일 읽고 쓰기

---

<br/>

### 13-1**.** 스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O

---

**"What is I/O?"**

- I/O(Input/Output. **입출력**) : 컴퓨터 내부나 외부의 장치와 프로그램 사이 데이터를 주고받는 것
- 예시) 키보드: 입력, 스피커: 출력

1. **스트림(Stream)**
    - 데이터를 운반하는데 사용되는 연결 통로
    - 데이터의 흐름을 물에 빗대어 표현
    - **단방향통신**만을 지원한다. → 하나의 스트림이 입력과 출력을 동시에 수행할 수 없음
    - 입출력을 처리하려면 총 2개의 입력, 출력 스트림이 필요하다.
    - **FIFO**(First In First Out) 형식으로 먼저 보내진 데이터를 먼저 받게 되어있다.
    - 연속된 데이터를 처리함으로, 입출력 진행시 다른 작업을 할 수 없는 **Blocking**상태가 된다.

2. **채널(Channel)**
    - NIO에서 사용되는 방식
    - 스트림과 유사한 용도를 보인다.
    - 스트림과 달리 **양방향통신**을 지원한다.
    - 채널에서 데이터를 주고 받을 때 항상 버퍼를 사용한다.

3. **버퍼(Buffer)**

    ***cf. 보조스트림***

    - `FilterInputStream`, `FilterOutputStream` : 모든 보조스트림의 super.
    - 스트림의 기능을 **보완**하기 위해 제공된다. (스트림 기능향상, 새로운 기능 추가)
    - 데이터를 주고받는 스트림은 아니기에 직접 데이터를 입출력할 수 없다.
    - 보조스트림 생성 방법

        ```java
        // 1. 스트림 생성
        FileInputStream fis = new FileInputStream("test.txt");
        // 2. 생성된 스트림을 이용해 보조스트림을 생성한다.
        BufferedInputStream bis = new BufferedInputStream(fis);

        bis.read();  // 보조스트림인 BufferedInputStream으로부터 데이터를 읽는다.
        ```

    - 위의 예시에서 사용된 보조스트림인 `BufferedInputStream`은 버퍼를 이용하여, 기존 스트림의 입출력 성능을 향상시킨다.
    - 버퍼를 사용한 입출력과 사용하지 않은 입출력의 성능차이가 상당하여, 대부분의 경우 버퍼를 이용하게 된다.

    **"What is Buffer?"**

    - 임시저장공간. 모든 primitive 타입을 저장한다.
    - array와 마찬가지로 제한된 크기를 가지고, 순서대로 저장한다.
    - 채널을 통해 데이터를 주고받을 때 쓰인다.
    - **non-buffer**(버퍼를 *사용하지 않는* I/O)는 입출력에 대해 OS에 직접 요청하게 된다.

        → 프로그램의 효율성이 떨어지게 된다.

***cf) Java NIO(New I/O)?***

- JDK 1.4에서 추가된 API
- **Non-blocking** 처리가 가능하며, 스트림이 아닌 **채널**을 사용한다.
- 버퍼를 사용하여 여러 바이트를 저장했다가 한 번에 출력할 수 있다.

**"Blocking vs. non-Blocking"**

- Blocking
    - 입출력마다 개별 Thread를 할당한다.
    - 각 Thread가 I/O 데이터를 기다리며, 무한정 대기상태에 빠질 수 있다. → **리소스 낭비**
- non-Blocking
    - 구현이 어렵다
    - I/O에 대해 **Selector** 클래스를 사용함으로 한 개의 Thread만 사용하게 된다.
    - Selector는 여러 동시 입출력을 한 번에 처리할 수 있다. → **개선된 리소스 관리**

</br>

### 13-2**.** InputStream과 OutputStream

---

> 추상클래스이며, 모든 바이트기반 스트림의 super class이다.

- 버퍼, 파일, 네트워크에서 입출력되는 데이터를 처리하는 기능을 수행한다.
- **입출력 대상**에 따라 추상메서드를 구현한 클래스들이 존재한다.

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c6ba03df-1f3d-47fb-b7f1-dadd303b9eac/git.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c6ba03df-1f3d-47fb-b7f1-dadd303b9eac/git.png)

</br>

### 13-3**.** Byte와 Character 스트림

---

> Java에서는 한 문자인 `char`가 2byte이므로, 이를 처리하기 위한 Character 스트림을 제공한다.

- 기본적으로, InputStream은 **Reader**로, OutputStream은 **Writer**로 바꿔 사용한다.
- 두 스트림의 차이점은 매개변수가 **byte배열**과 **char배열**로 구분된다는 것 뿐이다.

</br>

### 13-4**.** 표준 스트림 (System.in, System.out, System.err)

---

```java
// package java.lang;
public final class System {
	public static final InputStream in = null;
	public static final PrintStream out = null;
	public static final PrintStream err = null;
}
```

- `System.in` : 입력을 받아들이기 위해서 사용되는 입력 스트림
- `System.out` : 콘솔에 문자열을 출력하기 위한 용도로 사용되는 출력 스트림
- `System.err`
    - error 출력 스트림
    - 이것은 err가 정확하고 빠르게 출력되어야 하기 때문에 버퍼링을 지원하지 않음
- 실제로 사용할 때는 `BufferedInputStream`과 `BufferedOutputStream`의 인스턴스를 사용함
- `setIn()`, `setOut()`, `setErr()`을 사용하여, 콘솔 이외에 다 입출력 대상으로 변경할 수 있다.

</br>

### 13-5. 파일 읽고 쓰기

---

```java
BufferedReader br = new BufferedReader(new FileReader("now.txt"));
BufferedWriter bw = new BufferedWriter(new FileWriter("next.txt"));
String str;
while ((str = br.readLine()) != null) {
    bw.write(str + "\n");
}
```

위의 예시는 `FileReader`를 이용해 파일을 읽고, `BufferedReader`를 이용하여 입출력 효율을 높였다.

읽어들인 한 줄씩을 `String`으로 받고, `FileWriter`를 이용해 파일 쓰기를 수행할 수 있다.

</br>
