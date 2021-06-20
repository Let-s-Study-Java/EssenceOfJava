## 7. 패키지

목표 : 자바의 패키지를 학습한다.

### **학습할 것**

- package 키워드
- import 키워드
- 클래스패스
- CLASSPATH 환경변수
- classpath 옵션
- 접근지시자

---

<br/>

### 7-1. package 키워드

---

> 패키지 : 클래스의 묶음

`package 패키지명;`

- 하나의 소스파일에는 첫번째 문장으로 단 한 번의 패키지 선언만을 허용한다.
- 모든 클래스는 반드시 하나의 패키지에 속해야 한다.
- 패키지는 점(.)을 구분자로 하여 계층구조로 구성할 수 있다.
- 패키지는 물리적으로 클래스 파일(.class)을 포함하는 하나의 디렉토리이다.

**" 이름없는 패키지 "**

자바의 모든 클래스는 하나의 패키지에 포함되어야 한다. 만일 소스파일을 작성할 때 패키지를 지정하지 않으면, 자바에서 기본적으로 제공하는 **이름없는 패키지**에 포함된다. 따라서 패키지를 지정하지 않은 모든 클래스들은 같은 패키지에 속하게 된다.

**" 빌트-인 패키지(Built-in Package) "**

자바에서 기본적으로 제공하는 패키지. ex) `java.lang`, `java.util`

`java.lang`의 경우, 기본적인 기능을 제공하기에 따로 import하지 않아도사용가능하다.

</br>

*cf. **FQCN(Fully Qualified Class Name)** : 모든 클래스에는 정의된 **클래스 이름**과 **패키지 이름**이 있다.*

*이 둘을 합쳐야 완전하게 한 클래스를 표현한다고 할 수 있고, 이를 FQCN이라 칭한다.*

*예를 들어, String 클래스의 패키지는 `java.lang`이며 FQCN은 `java.lang.String`이 된다.*

</br>

### 7-2. import 키워드

---

> import : 다른 패키지에 있는 클래스나 인터페이스를 참조할 때 사용한다.

`import 패키지명.클래스명;` / `import 패키지명.*`

- 동일 패키지의 클래스나 `java.lang` 패키지의 클래스는 `import`문 없이 참조할 수 있다.

```java
package dev.dotaeuk.story; // package문

import java.util.*; // built-in package import문
import dev.other.animal; // 다른 package의 class import문
```

</br>

*cf. import문은 프로그램의 성능에 전혀 영향을 미치지 않는다. import문을 많이 사용하면 컴파일 시간이 아주 조금 더 걸릴 뿐이다. 또한, wildcard(`*`)를 사용하는 것도 실행 시 성능상의 차이는 전혀없다.*

</br>

### 7-3. 클래스패스

---

> 클래스패스 : 클래스를 찾기위한 경로

**" 자바프로그램 실행과정 "**

: 소스코드(.java) 컴파일 → 소스코드가 바이트코드(.class)로 변환됨

- 이 과정에서 JVM이 클래스나 패키지를 찾을 때 **기준이 되는 경로**를 뜻한다.
- 클래스패스는 .class 파일이 포함된 디렉토리와 파일을 콜론으로 구분한 목록이다.

위와 같은 클래스패스를 지정할 수 있는 방법은 두 가지가 있다.

- **CLASSPATH 환경변수** 사용
- **classpath 옵션** 사용

</br>

### 7-4. CLASSPATH 환경변수

---

> Windows 10 기준, '제어판 - 시스템 - 고급 시스템 설정 - 고급탭 - 환경 변수'

- JAVA_HOME : JDK가 설치된 홈 디렉토리를 설정하기 위한 환경변수명이다.
- CLASSPATH : 필수 클래스들이 위치한 디렉토리를 등록한다.

아래의 예시처럼, 시스템 변수에 두 변수를 등록한다.

- **JAVA_HOME** - " C:\Program Files\Zulu\zulu-8 "
- **CLASSPATH** - " %JAVA_HOME%\bin\

운영체제 변경하면 클래스패스가 사라지기에 재설정해줘야 하는 번거로움이 있다.

따라서 최근에는 해당 방식을 지양하고, IDE나 빌드도구를 통해 클래스패스를 설정한다.

</br>

### 7-5. classpath 옵션

---

> 컴파일하기 위해 참조할 클래스 파일들을 찾을 파일 경로를 지정해주는 옵션

`javac <options> <source files>`

*ex. `C:\Java\Store`에서 클래스파일을 가져오고, `C:\Java\test.java`를 컴파일하기 위한 명령어*

```powershell
javac -classpath C:\Java\Store C:\Java\test.java
```

`-classpath` 대신 단축어인 `-cp`를 사용할 수 있다.

</br>

### 7-6. 접근지시자

---

> 정보 은닉을 위해 사용할 수 있는 접근 제어자를 뜻한다.

[JAVA의 접근제어자](https://www.notion.so/647c5b07a8894a0d859ab5eb6dc2fb2e)

- 접근 제어자를 사용함으로, 클래스 내부의 데이터를 보호할 수 있다.
- 데이터의 변경이나 외부의 접근을 제어할 수 있게 된다.
- 객체지향개념의 캡슐화(encapsulation)에 해당하는 내용이다.
