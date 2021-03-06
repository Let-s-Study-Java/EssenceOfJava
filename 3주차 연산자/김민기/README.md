피연산자와 연산자에서 반환된 결과는 하나의 리터럴처럼 사용할 수 있습니다. 이는 일반 산술 연산 뿐아니라 대입 연산 등 모든 연산에서의 값을 다른 변수에 지정할 수 있다는 것을 말합니다.

# 연산 프로모션

기본 연산을 수행하는 피연산자의 데이터 타입이 다르다면, 더 큰 범위를 갖는 피연산자의 데이터형으로 프로모션(업캐스팅)되어 연산된 결과가 반환됩니다.

# 산술 연산자

산술연산자는 `boolean` 을 제외한 `primitive type` 간 기본 연산을 수행 결과를 반환합니다. 

`+, -, *, /, %` 와 같은 연산자가 있으며 모두 이항 연산자입니다.

## 더하기 연산자(`+`)

더하기(`+`) 는 양 옆의 두 피연산자를 더해준 결과를 반환합니다. 

### 문자열 더하기

기본형 타입이 아닌 변수는 더하기 연산이 불가능하지만 `String Class` 는 예외적으로 `+` 로 산술연산이 가능합니다. 어떤 연산식에 `String` 객체 혹은 `String` 리터럴이 포함된다면 해당 수식의 모든 값은 String으로 비교하여 반환됩니다.

연산 간 프로모션이 발생하여 기본형 변수는 값이 가르키고 있는 실제 값이 String으로 반환되어 연산되며 참조형 변수는 해당 객체의 `toString()` 메소드를 통해 반환된 문자열로 치환됩니다.

```java
Object o1 = new Object();
Object o2 = new Object();
System.out.println(o1 + o2);
// println 메소드는 기본적으로 레퍼런스의 toString을 호출하지만 객체 간 산술연산은 불가능하므로 에러가 발생합니다.

String str = "hello";
System.out.println(str + o1 + o2);
//hello o1.toString() o2.toString()
//산술연산식에 String이 각 레퍼런스는 toString()을 호출하여 반환된 String으로 연산합니다.

System.out.println(o1 + o2 + "");
//o1.toString() o2.toString()
//이 역시 연산식에 String이 포함되었으므로 연산이 가능합니다.
```

## 빼기 연산자(`-`)

빼기 연산자(`-`)는 왼쪽 피연산자에서 오른쪽 피연산자를 뺀 결과를 반환합니다.

## 곱하기 연산자(`*`)

곱하기 연산자(`*`)는 두 피연산자의 곱의 결과를 반환합니다.

## 나누기 연산자(`/`)

나누기 연산자(`/`)는 왼쪽 피연산자에서 오른쪽 피연산자를 나눈 결과를 반환합니다.

몫만 반환하는 것이 아니라 전체 결과를 반환하지만 두 피연산자가 모두 정수형이라면 결과도 정수형으로 반환되므로 몫만 반환받습니다.

반대로 두 피연산자 중 하나라도 실수형 값이 있다면, 타입 프로모션에 의해서 실수형 결과값이 반환됩니다.

### `0`  나누기 연산

자바에서는 정수형을 결과로 갖는 `0` 으로 나누는 연산을 허옹하지 않습니다. 이 때는 `ArithmeticException` 을 발생시킵니다.

실수를 결과로 갖는 `0` 으로 나누는 연산은 `infinity` 값을 갖게됩니다.

```java
//infinity
System.out.println(1.0/0);
//infinity
System.out.println(1/0.0);
//ArithmeticException
System.out.println(1/0);
```

## 나머지 연산자(`%`)

나머지 연산자(`%`)는 왼쪽 피연산자에서 오른쪽 피연산자를 나눴을 때의 나머지 값을 반환합니다. 

피연산자와 결과값에 모두 실수형을 가질 수 있지만 결과는 연산 프로모션에 의해 가장 큰 범위의 데이터형으로 반환됩니다.

```java
System.out.println(2 % 1.2);
System.out.println(2.4 % 1.2);
System.out.println(5 % 2);
/*
	출력
	0.8
	0.0
	1
*/
```

### `0`  나머지 연산

나머지 연산에서도 정수형을 결과로 갖는 `0` 으로 나누는 연산을 허옹하지 않으며`ArithmeticException` 을 발생시킵니다.

실수 간 연산에서도 `0` 으로 나눴을 때 나머지를 갖는 수는 없으므로 `NaN` 값을 갖게 됩니다.

```java
//NaN
System.out.println(1.0%0);
//NaN
System.out.println(1%0.0);
//ArithmeticException
System.out.println(1%0);
```

# 비트연산자

정수형 타입 연산에서 사용할 수 있는 연산으로, 해당 변수를 2진수로 표현한 값을 통해 연산합니다.

메모리 내 모든 값들은 2진수 형태로 저장하며, 저수준(low-level) 에서 연산을 수행하기 때문에 기본 산술 연산보다 속도가 빠릅니다.

## 비트 표현

정수 값은 데이터 맨 앞의 비트부호를 포함한 2진수 값으로 표현됩니다.

부호비트가 `1` 이면 음수를 나타냅니다. 같은 절댓값을 가진 양수와 음수는 2의 보수 관계를 가지며 다음과 같이 표현됩니다.

```java
(short) 2 == 0b1000_0010
(short) -2 == 0b0111_1110
```

## 이항 연산자

### OR(`|`) 연산

두 피연산의 각각 비트 값에 비트 OR 연산한 결과를 반환합니다. 즉 두 피연산자의 비트 자리에서 하나라도 `1` 이라면 해당 자리는 `1` 을 갖게됩니다.

- `0b0010 | 0b1011 == 0b1011`

### AND(`&`) 연산

두 피연산자의 각각 비트 값에 비트 AND 연산(둘 다  `1` 이라면 `1` )한 결과를 반환합니다. 

- `0b0010 & 0b1011 == 0b0010`

### XOR(`^`) 연산

두 피연산자의 각각 비트 값에 비트 XOR(배타적 논리합) 연산한 결과를 반환합니다.

XOR(배타적 논리합)은 비교하려는 두 수의 비트가 같으면 `0`, 다르면 `1` 을 갖습니다.

- `0b0010 ^ 0b1011 == 0b1001`

## 시프트 연산자

비트 시프트 연산자는 비트로 표현된 왼쪽 피연산자의 값을 오른쪽 피연산자가 갖는 정수값 만큼 이동 시키는 연산을 합니다.

### 왼쪽 시프트(`<<`)

왼쪽 피연산자의 비트를 오른쪽 피연산자(정수)만큼 왼쪽으로 이동시킵니다. 그 과정에서 **부호 비트를 포함한왼쪽의 비트들은 삭제되며** 오른쪽의 빈 비트 자리는 `0` 으로 채워집니다.

실제 값은 2진수의 자리수가 하나씩 증가하므로 `* 2`가 됩니다.

- `0b0010 << 2 == 0b1000`

```java
//20_000_000_000
int i = (int)Math.pow(10,9) * 2;
printBit(i);
int k = i << 1;
printBit(k);
int l = i * 2;
printBit(l);

//...
private static void printBit(int num) {
		System.out.println(num);
		for (int i = 31; ; i--) {
			System.out.print((num & 1 << i) != 0 ? 1 : 0);
			if (i == 0) {
				break;
			}
			if (i % 4 == 0) {
				System.out.print("_");
			}
		}
		System.out.println();
}

/*
출력 : 

2000000000
0111_0111_0011_0101_1001_0100_0000_0000
-294967296
1110_1110_0110_1011_0010_1000_0000_0000
-294967296
1110_1110_0110_1011_0010_1000_0000_0000

*/
```

위와 같이 비트이동 시킨 값과 *2 연산값의 비트를 각각 출력해보면 같은 값을 볼 수 있습니다. 

이 때 부호비트도 함께 이동하며 해당 변수의 표현 범위 내에서 *2와 같은 동작을합니다.

### 오른쪽 시프트(`>>`)

부호비트를 제외한 왼쪽 피연산자의 비트를 오른쪽 피연산자(정수)만큼 오른쪽으로 이동시킵니다. **오른쪽의 비트들은 삭제되며 왼**쪽의 빈 비트 자리는 원래 부호비트에 해당하는 값으로 채워집니다.

실제 값은 2진수의 자리수가 하나씩 감소하므로 `/ 2`가 됩니다.

- `0b0010 >> 1 == 0b0001`

### 부호 없는 오른쪽 시프트(`>>>`)

오른쪽 시프트 연산과 같으나, 부호비트를 모두 포함하여 이동시키는 연산입니다. 이동 시 빈 왼쪽의 자리는 `0` 으로 채워집니다.

 따라서 음수를 `>>>` 하게되면 반드시 양수 값을 갖게됩니다.

```java
int val1 = -16;
		int val2 = val1 >> 2;
		printBit(val2);
		int val3 = val1 >>> 2;
		printBit(val3);
//...

private static void printBit(int num) {
	System.out.println(num);
	for (int i = 31; ; i--) {
		System.out.print((num & 1 << i) != 0 ? 1 : 0);
		if (i == 0) {
			break;
		}
		if (i % 4 == 0) {
			System.out.print("_");
		}
	}
	System.out.println();
}

/*
출력 :

-4
1111_1111_1111_1111_1111_1111_1111_1100
1073741820
0011_1111_1111_1111_1111_1111_1111_1100

*/
```

## 단항 연산자

### NOT, 반전 (`~` ) 연산

`~` 연산은 단항 연산자로 해당 값이 갖고 있는 비트를 모두 반전 시키는 값을 반환합니다. 부호 비트를 포함해서 반전시킨다는 점을 유의해야합니다.

- `~0b0010 == 0b1101`

# 관계 연산자

두 피연산자의 값 비교 결과를 `boolean` 타입의 결과로 반환하는 연산자이며, 연산자 프로모션이 적용됩니다.

## `==` 연산자

두 피연산자의 값이 같으면 `true` , 다르면 `false` 를 반환합니다. 

`primitive` 값들은 직접 값을 비교하며, `reference`  형의 변수는 두 값의 해시코드를 비교한 값을 반환하게 됩니다.

## `!=` 연산자

두 피연산자의 값이 다르 `true` , 같으 `false` 를 반환합니다. `==` 의 결과값에 `!` 연산을 한값과 동일한 값을 갖습니다.

## `<, >` 연산자

두 피연산자의 대소 비교를 통해 `true` 혹은 `false` 를 반환하게 됩니다. 이 때 `boolean` 과 `reference` 형 값을 비교할 수 없습니다.

## `<=, >=` 연산자

두 피연산자의 대소 비교를 혹은 같음을 비교하여 `true` 혹은 `false` 를 반환하게 됩니다. 이 때 `boolean` 과 `reference` 형 값을 비교할 수 없습니다.

# 논리 연산자

논리 연산자는 두 `boolean` 형 피연산자의 값의 비교를 통해 `true` 혹은 `false` 를 반환합니다.

## AND( `&&` ), OR( `||` ) 연산자

`&&` 연산은 두 피연산자가 모두 `true` 면 `true` 아니면 `false` 를 반환합니다. `||` 연산자는 두 피연산자 중 하나라도 `true` 면 `true` , 아니면 `false` 를 반환합니다.

## 단락회로 평가

`**&&` 과 `||` 연산 수행 시 왼쪽 피연산자의 값에 따라서 오른쪽 피연산자를 확인하지 않고도 결과가 결정된다면 오른쪽 피연산자는 확인하지 않는 과정을 이야기합니다.**

`&&` 연산에서는 왼쪽 연산자가 `false` 면 오른쪽 연산자와 관계 없이 `false` 이므로 확인하지 않습니다.

`||` 연산은 왼쪽 연산자가 `true` 면 오른쪽 연산자과 관계 없이 `true` 입니다.

```java
//true 반환, 오른쪽 연산자 미수행
System.out.println(true || myMethod());
//false 반환, 오른쪽 연산자 미수행
System.out.println(false && myMethod());
```

## `&, |, ^` 연산자

`&, |, ^` 연산자들은 비트연산에서도 사용하지만 `boolean` 값을 통한 논리연산에도 사용됩니다.

`&` 와 `|` 는 각각 `&&` 와 `||` 연산과 동일한 결과를 반환하지만, 단락회로 평가가 사용되지 않습니다. 

```java
//false
System.out.println(true ^ true);

//false
System.out.println(false ^ false);

//true
System.out.println(true ^ false);
```

# Instanceof

`instanceof` 연산자는 `reference` 값의 객체 타입 평가를 위해 사용됩니다.

왼쪽 피연산자의 레퍼런스가 가르키고 있는 실제 객체의 타입이 오른쪽 피연산자의 클래스 타입과 같다면 `true` 를 반환합니다.

해당 객체가 상속 관계일 경우 부모의 객체를 포함하고 있으므로 이 경우에도 `true` 값을 반환하는 비교를 할 수 있습니다.

```java
Object o = new Integer(1);

System.out.println(o instanceof Integer);
//true 반환
System.out.println(o instanceof Object);
//상속관계이므로 true

Object o2;
System.out.println(o instanceof Object);
//null을 포인팅하므로 false
```

# 대입 연산자(`=` )

대입 연산자는 왼쪽 피연산자에 오른쪽 피연산자의 값을 대입하는 연산을 수행합니다. 

기본형은 기본형끼리 연산이 가능하고 레퍼런스는 레퍼런스끼리 연산이 가능합니다. 이 때 **왼쪽 피연산자의 범위가 더 큰 경우의** **프로모션이 가능한 범위 내에서만 수행이 가능하며 그렇지 않다면 컴파일 에러를 발생시킵니다.**

## 산술 및 비트 대입 연산

대입 연산자 왼쪽에 산술연산자 및 비트연산자를 사용할 수 있습니다. 

이 때는 왼쪽 피연산자의 값과 오른쪽 피연산자의 값을 산술 및 비트연산한 결과가 다시 왼쪽피연산자에 대입되게 됩니다.

```java
int temp = 1;
//temp = temp * 2;
temp *= 2;
//temp = temp | 1;
temp |= 1;
//temp = temp << 2;
temp <<= 2;
```

 

# 삼항 연산자

삼항 연산자는 `boolean ? val1 : val2` 와 같은 형식으로 표현됩니다. 

첫 번째 피연산자의 `boolean` 값이 `true` 면 두 번째 피연산자 값 반환, `false` 면 세 번째 피연산자의 값이 반환됩니다.

삼항연산자의 우선순위는 매우 낮기 때문에, 연산 시 괄호를 적절하게 작성해야합니다.

```java
// 2와 8이 반환되는 것이 아닌 2 또는 3 +5가 적용
int a = isTrue() ? 2 : 3 + 5;
// ->
int b = isTrue() ? 2 : (3 + 5);
```

# 화살표 연산자(`→` , 람다식)

JVM 8에서 추가된 익명 클래스를 표현하는 방법인 람다식을 위한 연산자입니다.

인터페이스의 상속 관계에서 한번만 사용되고 버려지는 클래스를 새로 선언하기 위해 클래스 작성 및 객체를 구현하는 과정은 번거로웠으나 람다식 사용으로 간결해질 수 있습니다.

```java
Integer[] myArr = new Integer[3];

//익명 클래스 구현
Arrays.sort(myArr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

//람다식 사용
Arrays.sort(myArr, (o1, o2) -> o1 - o2);

```

이 때 람다식을 사용하려는 클래스 혹은 인터페이스는 `@FuncionalItererface` 어노테이션이 추가되어야 합니다.

람다식은 `()` 를 통해 매개변수들을 감싸고 `{}` 으로 코드블럭을 작성합니다. 

만약 코드불럭이 한 줄로 작성된다면 `{}` 를 생략할 수 있으며, 이 때 반환을 `return` 으로 식별하지 않고 상태값을 그대로 작성하면 그 값이 반환됩니다.

* 람다식에 대해서는 추후 학습하여 게시할 예정입니다.

# 연산자 우선순위

연산자는 수식 내에 우선순위로 수행 순서가 결정되며, 같은 레벨의 우선순위를 갖는다면 왼쪽에 있는 연산자부터 수행합니다.

![image](https://user-images.githubusercontent.com/49678555/119215676-21703180-bb0a-11eb-8ae1-0e00a132cc8c.png)
