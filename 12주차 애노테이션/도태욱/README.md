## 12. 애노테이션

목표 : 자바의 애노테이션을 학습한다.

### **학습할 것**

- 애노테이션 정의하는 방법
- **@retention**
- **@target**
- **@documented**
- 애노테이션 프로세서

---

### 1. **Annotation?**

- 애노테이션(annotation) : 주석, 메모
- 일반적인 주석( `//`, `/* */`)처럼 프로그램 자체에 영향을 미치지 않는다.
- 주석(comment)과 달리, 내부에 코드를 작성해 별도의 역할을 수행할 수 있다.
- **Annotation in Java**
    - 11주차에서 다룬 `enum`과 같이 JDK 1.5에서 새로 추가된 기능이다.
    - JDK에서 제공하는 **표준 애노테이션**은 주로 컴파일러에게 유용한 정보를 제공하는 역할을 한다.
    - 새로운 애노테이션을 정의하기 위해 사용할 수 있는 **메타 에노테이션** 또한 제공한다.

</br>

### 2. 표준 애노테이션

- Built-in Annotation. 자바에서 기본적으로 제공하는 애노테이션이다.
- **메타 애노테이션**의 종류
    - `@Target` : 애노테이션이 적용가능한 대상을 지정하는데 사용한다.
    - `@Documented` : 애노테이션 정보가 javadoc으로 작성된 문서에 포함되게 한다.
    - `@Inherited` : 애노테이션이 서브클래스에 상속되도록 한다.
    - `@Retention` : 애노테이션이 유지되는 범위를 지정하는데 사용한다.
    - `@Repeatable` : 애노테이션을 반복해서 적용할 수 있게 한다. (JDK1.8)
- 메타 애노테이션이 아닌 **표준 애노테이션**의 종류
    - `@Override` : 컴파일러에게 오버라이딩하는 메서드임을 알린다.
    - `@Deprecated` : 앞으로 사용하지 않을 것을 권장하는 대상에 붙인다.
    - `@SuppressWarnings` : 컴파일러의 특정 경고메시지가 나타나지 않게 해준다.
    - `@SafeVarargs` : 지네릭스 타입의 가변인자에 사용한다. (JDK 1.7)
    - `@FunctionalInterface` : 함수형 인터페이스라는 것을 알린다. (JDK 1.8)
    - `@Native` : native메서드에서 참조되는 상수 앞에 붙인다. (JDK 1.8)

</br>

### 3. java.lang.annotation.Annotation

- 모든 애노테이션의 조상격.
- Annotation은 애노테이션이 아닌 일반적인 인터페이스로 선언되어 있다.
- 상속이 허용되지 않기에 Annotation을 조상으로 지정할 수 없다.

    ```java
    // 에러. 허용되지 않는 표현이다.
    @interface AnnoTest extends Annotation
    ```

- 모든 애노테이션에 대해 equals(), hashCode(), toString() 메서드들을 호출할 수 있다.

    ```java
    // java.lang.annotation.Annotation
    package java.lang.annotation;

    public interface Annotation {
    	boolean equals (Object obj); 
    	int hashCode();
    	String toString();

    	// 애노테이션의 타입을 반환할 수 있다.
    	Class<? extends Annotation> annotationType();
    }
    ```

</br>

### 4. 애노테이션 정의 방법

```java
@interface 애노테이션명 {
	타입 요소명();  // 애노테이션의 요소를 선언한다.
	...
}
```

- 위와 같이 직접 애노테이션을 만들어 사용할 수 있다.
- 애노테이션 요소 선언 규칙
    - 요소의 타입으로는 **primitive type, String, Enum, 애노테이션, Class**만 허용된다.
    - 별도의 parameter를 허용하지 않는다.
    - 예외 선언이 되지 않는다.
    - 타입으로 제네릭을 사용할 수 없다.

    ```java
    @interface AnnoTest{
    	int count();  // O.사용가능한 올바른 표현이다.
    	int num = 10;  // O.상수를 선언할 수 있다.

    	String str(char a);  // X.매개변수 사용불가.
    	String choice() throws Exception; //X.예외 선언불가.
    	List<T> aList();  // X.타입 매개변수 사용불가.
    } 
    ```

- **마커 애노테이션 (Marker Annotation)**
    - 애노테이션의 요소를 하나도 정의하지 않은 애노테이션.
    - `Serializable`과 `Clonable` 인터페이스가 이에 해당한다.

</br>

### 5. 애노테이션 프로세서

> 소스코드 레벨에서, 컴파일 중 애노테이션을 읽는 동시에 새로운 소스코드를 생성하거나 기존 소스코드를 바꿀 수 있다.

추가로, 클래스나 별도의 리소스 파일들을 생성하도록 도와준다.

- 컴파일타임에 수행되기에 런타임에 소요비용이 들지 않는다.
- 사용 예시
    - `@Override`
    - Lombok (기존 코드를 변경한다.)
        - `@Getter`, `@Setter` 등의 애노테이션을 제공한다.
        - 표준적으로 작성해야 할 코드를 개발자 대신 생성한다.
    - AutoService (리소스 파일들을 생성한다.)
