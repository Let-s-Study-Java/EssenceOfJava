# Package

자바에서 패키지란 관련한 `java` 파일을 묶어서 관리할 수 있도록하는 일종의 디렉토리 역할을 합니다.

## package 키워드

`java` 소스 파일에서 패키지에 포함되어 있음을 명시할 때에는 `package` 키워드로 선언하여 표현합니다.

이 때 `package` 키워드는 컴파일할 루트 디렉토리로부터 실제로 해당 경로에 포함되어 있으며 이 때 디렉토리의 구분은 `.` 으로 표현합니다.

```java
package com.myproject.controller;
```

아무런 패키지를 포함하지 않는다면 기본패키지로 설정되며, 기본 패키지에 있는 모든 자바 파일들은 같은 패키지에 포함된 것으로 간주합니다.

# import 키워드

자바에서 동일 패키지에서 작성된 클래스(혹은 동일 패키지의 `.java` 파일) 는 곧바로 참조할 수 있지만 다른 패키지의 클래스라면 `import` 키워드로 사용을 명시해야합니다.

```java
import java.util.List;
```

`import` 이후 작성되는 경로는 Fully Quialfied Class Name 이어야하며 이는 해당하는 패키지명 + 클래스명입니다.

- `java.util.`(패키지명) + `List` (클래스명)

## 기본 패키지

`java.lang` 패키지의 내용은 자바코드를 작성하는데 필요한 기본적인 클래스(`Object`, `System`)들이 포함되어 있어 `import` 를 명시하지 않아도 사용할 수 있습니다.

다른 `java` 키워드로 시작되는 패키지에는 자바에서 제공하는 여러 기본적인 패키지들이 포함되어 있으며 `javax` 로 시작하는 패키지는 자바 확장 패키지라고 합니다.

```java
import java.util.Arrays;
import javax.swing.*;
```

위와 같이 와일드카드(`*`)를 사용하면 패키지 내의 모든 클래스르 `import` 할 수 있습니다. **하지만 이렇게 할 경우 해당 패키지 내에 직접 선언된 클래스만 `import`  되며 하위 패키지(디렉토리)에 작성된 클래스는 `import` 되지 않습니다.**

```java
import java.*;
// 아무런 클래스도 import 할 수 없음.
```

---

# 접근 제어자

자바에서는 클래스 및 속성에 접근 제어자를 작성하여 다른 클래스에서의 참조 가능 수준을 지정할 수 있습니다.

1. `public` 
    - 프로젝트 내에 어떠한 클래스에서도 접근 가능
2. `protected` 
    - 같은 패키지 혹은 상속 관계(`implements`, `extends` ) 에서 참조 가능
3. `default` 
    - 같은 패키지 내에서만 참조 가능
    - 이 때 `default` 키워드는 명시적으로 작성하는 것이 아닌 아무것도 작성하지 않는 것
4. `private`
    - 같은 클래스 내에서만 참조 가능

이 때 클래스 간의 구분은 `.java` 파일과는 상관 없이 클래스 선언 블록에 따라 구분합니다. 따라서 같은 파일 내의 다른 클래스라도 `private` 키워드로 선언된 속성에 접근할 수 없습니다.

이너클래스로 작성된다면 같은 클래스 내에 위치하는 것이므로 서로간 접근 제어자의 관계 없이 접근할 수 있습니다.

```java
public class MyClass{
	private static class Node {
		int val;
		Node next;
	}
	private static class NodeHead {
		Node head;
		public NodeHead() {
			head = new Node();
			head.val = 1;
		}
	}
	public static void main(String[] args) {
		Node node = new Node();
		node.val = 0;
	}
}
//모두 접근 가능
```

# 클래스 패스

클래스 패스란 JVM이 자바 바이트 코드 (`.class` )파일을 실행할 때 클래스로더에서 런타임에 포함할 바이트 코드의 경로를 말합니다.

`java` 명령어로 JVM을 실행시킬 때 기본적으로 해당하는 파일 파라미터의 경로를 클래스 패스로 사용합니다.

여러 IDE에서 JRE의 경로를 지정하면 해당 JRE 경로에 맞게 클래스패스를 지정해주어서 직접 지정하지 않아도 되지만 터미널을 통해 직접 `.class` 파일을 수행시킨다면 클래스 패스를 지정해서 JRE에 접근이 가능해야합니다.

## 환경변수 지정

`java` 명령어로 바이트코드를 수행할 때 기본적으로 참조할 클래스패스를 지정할 수 있습니다.

MacOs 에서는 다음과 같이 지정할 수 있습니다.

1. 편집기로 `.bash_profile` 파일 접근
    - `vi ~/.bash profile` (vim으로 접근)
2. JDK 홈경로 export
    - `export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_192.jdk/Contents/Home`
3. 홈 경로로 클래스 패스 지정
    - `export PATH=${PATH}:$JAVA_HOME/bin`

## 컴파일러 파라미터 옵션 지정

`.java` 파일을 컴파일할 때 `-classpath` 파라미터를 작성하여 원하는 클래스패스를 직접 지정할 수 있습니다. 

이 때 환병변수 지정하는 것처럼 해당 클래스가 존재하는 경로를 작성할 수 있으며 여러 경로가 존재한다면 `;` 구분자로 구분하여 작성합니다.
