# 에러 - Throwable

자바에서 에러는 크게 `error` 와 `exception` 으로 구분할 수 있습니다. 이 에러들은 모두 프로그램 실행 중 문제가 발생하면 생성되며 `Throwable` 클래스를 상속받는 클래스들입니다. `Throwable` 역시 클래스이므로 `Object` 의 자손입니다.

## Error

`error` 는 프로그램의 비정상적인 종료를 일으키는 `StackOverFlow` (스택 메모리 초과), `OutOfMemory` (힙 메모리 초과) 등의 치명적인 에러들입니다. `error` 는 시스템 레벨에서 발생하여 메모리 공간 등의 환경을 수정하거나 코드의 로직을 변경시켜줘야하는 에러입니다.

## Exception(예외)

이와 달리 `exception` 은 앱 레벨에서 발생되는 에러들이며 프로그램이 무결해도 발생할 수 있습니다. `exception` 은 개발자가 예외 발생 시 처리방법을 지정함으로써 발생 후에 프로그램의 비정상 종료를 막을 수 있습니다.

![image](https://user-images.githubusercontent.com/49678555/124084571-13f38300-da8a-11eb-9655-69af8a873370.png)

# Exception

`Exception` 클래스의 자손들인 예외들은 크게 `RuntimeException` 과 그 외의 예외들 두가지로 분류할 수 있습니다.

## RuntimeException(Unchecked Exception)

`RuntimeException` 은 컴파일 이후 실행 중(런타임)에 발생할 수 있는 `Exception` 들이며 컴파일 시 컴파일리가 예외 발생에 대한 처리 유무를 확인하지 않습니다.

- `NullPointerException` (null을 참조하는 레퍼런스의 객체에 대한 접근), `ArrayIndexOutOfBoundsException` (할당된 범위 의외의 배열 인덱스 접근),`ClassCastException` (클래스 간 잘못된 형변환) 등의 `Exception`

`RuntimeException` 은 대부분 개발 중 버그로 인해 발생합니다. 따라서 디버깅을 통해 해결할 수 있는 경우가 많으며, 필요 시에 예외처리를 진행합니다.

## Other Exceptions(Checked Exception)

Other Exception 은 `RuntimeException` 의 하위 예외들을 제외한 예외를 말합니다.

- `IOException`, `SQLException`, `ClassNotFoundException` 등

이 예외들을 발생시키는 클래스, 메서드들은 반드시 해당 예외에 대한 처리를 해야합니다. 처리하지 않은 경우 컴파일 에러가 발생합니다. 

 이 예외들은 프로그램이 무결하더라도 사용자에 의해 발생될 수 있는 예외들입니다. 대부분 예측 가능한 예외가 발생하므로 예외 상황에 맞게 핸들링해주어야 합니다.

# 예외 처리(Exception Handling)

프로그램 동작 시 발생한 예외에 의해서 프로그램이 비정상 종료를 하지 않게 하려면 해당 예외에 대한 처리를 해주어야 합니다. 예외를 처리하게되면 비정상 종료를 막고 방법에 따라 프로그램의 동작을 계속해서 수행할 수 있게 합니다.

예외가 처리되지 않으면(Uncaught exception) JVM의 예외처리기(UncaughtExceptionHandler)가 받아서 예외 원인을 화면에 출력합니다.

하지만 예외발생 시 예외 객체를 만들고 예외 내에 메시지를 포함한 내용을 넣어야 하므로 성능 소요가 큽니다. 따라서 단순 반환 등의 이유로 예외를 생성해서 `throw` 하지 않는 것이 권장됩니다.

## 예외 직접 처리

### try - catch

`try - catch` 문은 `try` 문 수행 중 예외가 발생했을 시 발생한 라인부터 수행을 중지, `catch()` 에 해당하는 `Exception` 파라미터가 정의되어 있을 경우 `catch`  문을 수행하는 역할을 합니다. 만약 `catch()` 파라미터에 해당하는 예외가 없을 경우 처리되지 못합니다.

`try` 는 다른 `try` 블록 내에 선언될 수 있으며 `catch` 블록은 한 `try` 블록에 여러 개를 붙일 수 있습니다.

### finally

`finally` 블록은 `try` 문 수행 후 `catch` 블럭의 수행과 관련없이 반드시 수행되는 구문입니다. 보통 `close` 해야하는 객체들 같이 반드시 수행해야 하는 과정을 담습니다. `finally` 문은 `try - catch` 와 다르게 반드시 작성할 필요는 없습니다.

```java
try{
      try{

      } catch (RuntimeException e){

      } catch (Exception e){

      } finally {

      }
} catch (Exception e){
      try{

      } catch (Exception e){

      }
} finally {
      try{

      } catch (Exception e){

      }
}
```

### 멀티 catch 블럭

JVM 1.7 부터 `catch` 블럭에 여러 파라미터를 동시에 받을 수 있습니다. 이 때 `|` 로 예외 타입을 구분하며 명시합니다.

```java
...
catch( NullPointerException | ArrayOutOfBoundsException e){
...
}
```

단 이렇게 멀티 캐치 블럭에 포함되는 각 예외들은 상속관계가 없어야 합니다. 즉 어느 한쪽의 `instance` 로 포함되면 안됩니다.

### try - with - resource( AutoCloseable)

`try - catch` 의 예외 발생 유무와 관계없이 자원을 사용한 경우는 `close` 메서드를 호출해줄 필요가 있습니다. 이 때 `finally` 문을 사용하는데 만약 반환하려는 값이 `IOStream` 등의 예외 발생 가능한 자원이라면 `finally` 내에 다시 `try` 를 작성해야합니다.

자바에서는 JDK 1.7 부터 `try` 문에 인자를 참조할 수 있게 되었는데, 이렇게 작성할 경우 `try - catch` 문을 빠져나가게 되면 `close()` 메서드를 호출하여 자원을 반납합니다.

단 이렇게 사용하려면 `try` 문에 작성된 클래스는 `AutoCloseable` 을 구한현 객체여야 합니다.

```java
//AutoCloseable classes,,
try(FileInputStream fis = new FileInputStream("score.dat")
			;DataInputStream dis = new DataInputStream(fis)){
	//...
} catch(,,,) { ...}
```

`AutoCloseable` 은 예외를 반환하기 때문에 `close` 동작에서 예외를 발생시킬 수 있습니다. 그렇다면 예외가 발생하여 `close` 하는 과정인데, 그 과정에서 다시 예외가 발생하면 어떻게 될까요 ?

이 때는 실제 예외를 출력하고, `close` 의 예외는 `suppressed` 키워드와 함께 출력됩니다.

### Exception 검사

`catch` 문을 수행하면서 `intanceof` 연산자로 파라미터와 발생된 예외의 형을 검사합니다. 상속 관계인 예외도 함께 검사가 되므로 모든 예외의 `Exception` 으로 파라미터를 정의했다면 모든 예외를 받아 처리할 수 있습니다.

따라서 `catch` 체인으로 예외를 처리할 때, 슈퍼클래스 예외를 상위에 선언하게 되면 하위에 선언된 서브클래스 예외는 참조되지 않으므로 주의해서 작성해야합니다.

### printStackTrace(), getMessage()

두 메서드는 `Exception` 클래스에 정의되어 있어, 상속하는 모든 예외들에 존재합니다. 

`printStackTrace()` 는 해당 예외가 생성 됐을 때 메서드 호출 스택을 출력합니다. `getMessage()` 는 해당 예외 객체에 저장된 메시지를 반환합니다.ㅐ

## 예외 반환하기

### throw

`throw` 예외 키워드로 예외를 발생시킬 수 있습니다. 해당 구문이 수행되면 메서드는 종료되고 호출한 메서드에 `throw` 키워드와 작성된 예외와 같은 클래스의 예외가 발생됩니다. 

```java
public void myMethod(int val){
	//...
	if(val != 0){
		throw new RuntimeException("예외 !");
	}
}
```

위와 같이 로직 내에서 조건을 만족하지 못한 경우 예외를 `throw` 하여 처리할 수 있습니다.

### throws

메서드 선언부에 `throws 예외1, 예외2 ,,,`를 작성하면 작성된 예외형에 맞는 예외들은 핸들링하지 않아도 됩니다. 그리고 호출한 메서드는 `throws` 에 작성된 예외에 대한 핸들링(checkedException인 경우)을 해야합니다.

```java
//IOException에 대한 처리를 하지 않고 throw
public void main(String[] args) throws IOException){
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
}
```

### 예외 되던지기

메서드 내에서 발생한 예외를 `try` 문으로 감싸고 `catch` 블럭에서 발생한 예외가 아닌 다른 예외를 새로 생성하여 되던지는 구조를 말합니다.

```java
try{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String str = br.readLine();
} catch(IOException e){
	throw new RuntimeException("예외 발생 !");
}
```

위의 예제는 `CheckedException` 인 `IOException` 을 `catch` 블럭으로 받아서 `RuntimeException` 으로 처리해주기 때문에 호출 메서드에서 예외를 반드시 처리할 필요가 없습니다.

위와 같이 다른 종류의 예외를 던지기 때문에 로직별로 예외를 따로 처리하거나 한 번에 묶어서 처리할 수 있습니다. 

```java
private void checkUser(String userId){
//...
	try {
		//...
	} catch {SQLException e} {
		throw new UserIdNotFoundException("정보 처리 중 오류 발생! " );
	}
//...
}
```

### 예외 반환 주의 사항

`throw` 및 `throws` 는 예외를 처리하는 것이 아닌 반환하는 것으로 모든 호출 스택의 메서드에서 처리하지 않고 반환한다면 결국 `main` 메서드에 도달 한 후 비정상 종료가 될 것입니다. `throw` 및 `throws` 는 같은 예외를 메서드별로 따로 작성하지 않아 중복코드를 줄이기 위해 사용됩니다. 따라서 해당 키워드를 사용 후에 반드시 상위 호출 스택에서 예외를 처리해야합니다.

- 예외처리 객체를 만들어서 메시지를 받아야하므로 많은 성능 소요 → 필요한 경우에만 예외를 처리
- 직접처리 - try~catch
    - 일회성 예외처리
- 선언적 예외 처리 - throws
    - 예외 처리에 대한 다형성 제공
        - 응용프래그램에 맞게 처리할 수 있도록 메소드가 호출된 자리로 위임
    - 예외 발생 시에도 프로그램이 동작하게 하기 위함
        - 예외 발생 시 바로 메서드를 끝내고 호출자에게 throws 반환 - return 효과이므로 메서드 반환 불필요
    - 한 프로그램 내에서는 예외처리하는 방법이 같도록 모든 메서드에서 예외처리하지 않고 한 곳으로 전달해서 한꺼번에 예외처리

# 사용자 정의 예외

`Exception` 혹은 하위 클래스를 상속받은 클래스를 정의하여 사용자 정의 예외를 만들 수 있습니다.

사용자 정의 예외는 어떤 비즈니스 로직(서비스)에 대해서 일괄적으로 예외처리를 할 수 있도록 만들어 로직에 집중할 수 있도록 합니다.

```java
public class UserException extends RuntimeException{
	public UserException(String msg){
		super(msg);
	}
}
```

사용자 정의 예외를 만들 때 상속받는 `Exception` 클래스에 따라 동작을 구분시킬 수 있습니다. 

예를 들어 `RuntimeException` 의 하위 예외는 컴파일 단계에서 예외처리가 필수가 아니고 `Exception` 의 하위 예외는 CheckedException 처럼 핸들링해야합니다.

- 사용자 정의 예외 활용

```java
class UserException extends RuntimeException{
	public UserException(String msg){
		super(msg);
	}
}

class SearchException extends RuntimeException{
	public SearchException(String msg){
		super(msg);
	}
}

//..
public void myService(String param){
	try{
		switch(param){
			case "login" :
				login();
				break;
			case "register" :
				register();
				break;
			case "search" :
				search();
				break;
		}
	} catch(UserException e){
		e.printStackTrace();
		UserExceptionHandler(e);
	} catch(SearchException e){
		e.printStackTrace();
		SearchExceptionHandler(e);
	}
}

private void login(){
	try{
	//..
	} catch(Exception e){
		throw new UserException("로그인 예외");
	}
}

private void register(){
	try{
	//..
	} catch(Exception e){
		throw new UserException("등록 예외");
	}
}

private void search(){
	try{
	//..
	} catch(Exception e){
		throw new SearchException("검색 예외");
	}
}
```
