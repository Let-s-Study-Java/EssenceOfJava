# Enum(열거형)

열거형은 서로 연관된 상수를 편리하게 선언하기 위하여 사용합니다. 열거형은 값 관리 및 타입 관리까지 할 수 있으므로 논리적인 오류를 줄이는데 많은 도움이 됩니다.

## Enum을 사용하는 이유

일반 상수를 사용하여 관리하게 되면 다음과 같은 논리적 오류가 있을 수 있습니다.

```java
class Store{
    static final int FOOD = 1;
    static final int SHOES = 2;

    int category;
}

class Person{
    static final int CHILD = 1;
    static final int ADULT = 2;

    int age;
}

//...
Store store = new Store();
store.category = Store.FOOD;

Person person = new Person();
person.age = Person.CHILD;

//두 상수의 값은 1이므로 비교가 가능
//출력 : true
System.out.println(store.category == person.age);
```

위 코드에서 `category` 와 `age` 는 논리적으로 다른 값을 저장하지만 두 자료형이 모두 `int` 형 상수이므로 위와 같은 논리적 오류가 발생할 수 있습니다.

# Enum의 정의

Enum은 기본적으로 다음과 같이 정의할 수 있습니다.

```java
//Food enum 에서 CAKE, PIZZA, COOKIE 상수 선언
enum Food { CAKE, PIZZA, COOKIE }
```

# Enum은 클래스

Enum(열거형)은 `java.lang.Enum` 을 상속받는 클래스입니다. 따라서 각 상수는 인스턴스를 가지며, 내부에 메서드를 선언할 수 있습니다.

## Enum 참조

클래스 형태이기 때문에 `enum` 형의 레퍼런스 변수를 선언할 수 있으며, 인스턴스를 할당할 수 있습니다.

```java
Food food = Food.CAKE;
```

위 코드처럼 `Food` 형태의 레퍼런스 선언하고 상수를 할당할 수 있습니다.

`enum` 은 `static final` 로 선언한 것과 같이 상수들을 저장합니다. 만약 클래스로 정의한다면 다음과 같을 것입니다.

```java
class Food {
    static final Food CAKE = new Food("CAKE");
    static final Food PIZZA = new Food("PIZZA");
    static final Food COOKIE = new Food("COOKIE");

    private String name;

    public Food(String name){
      this.name = name;
    }
}
```

### 값 비교

`static final` 형태이기 때문에 `enum` 의 각 상수값들은 같은 주소를 참조하고 있습니다. 그래서 같은 `enum` 의 상수끼리는 `==` 비교가 가능합니다. 당연히 레퍼런스 형태이므로 `> , <` 와 같은 대소비교는 불가능합니다.

```java
Food food1 = Food.CAKE;
Food food2 = Food.CAKE;

//둘 다 Food.CAKE를 참조하므로 true 출력
System.out.println(food1 == food2);
//컴파일 에러
System.out.println(food1 > food2);
```

### switch - case

Enum 상수는 `switch - case` 문에서도 활용할 수 있습니다.

```java
switch (food) {
    case CAKE: //...
    case PIZZA: //...
    case COOKIE: //...
}
```

## java.lang.Enum

`java.lang.Enum` 을 상속받으므로 부모의 속성을 사용할 수 있습니다.

### oridnal

`java.lang.Enum`에서는 `ordinal` 이라는 속성을 제공합니다. 

`ordinal` 은 Enum 상수가 선언된 순서 값을 가지며 `ordinal()` 메서드를 제공하여 값을 받을 수 있습니다.

```java
//0
System.out.println(Food.CAKE.ordinal());
//1
System.out.println(Food.PIZZA.ordinal());
```

- `java.lang.Enum`

```java
public abstract class Enum<E extends Enum<E>>
        implements Comparable<E>, Serializable {

    //enum 상수 이름
    private final String name;
    //getter
    public final String name() {
          return name;
    }
    //ordinal
    private final int ordinal;
    //getter
    public final int ordinal() {
          return ordinal;
    }
    //생성자
    protected Enum(String name, int ordinal) {
          this.name = name;
          this.ordinal = ordinal;
    }
  //...
}
```

각 상수는 `ordinal()` , `name()` 메서드를 통해 값을 반환받을 수 있습니다.

<br/>

앞서 Enum을 클래스로 정의하였을 때 코드를 작성해보았는데, `java.lang.Enum` 에서는 위처럼 각 Enum 상수를 위한 생성자 및 필드를 제공합니다. 그에 따라서 각 값의 인스턴스를 생성할 수 있는 것입니다.

또 이를 고려한다면 처음에 클래스 형태로 작성된 코드는 다음과 같을 것입니다.

```java
class Food {
    static final Food CAKE = new Food("CAKE", 0);
    static final Food PIZZA = new Food("PIZZA", 1);
    static final Food COOKIE = new Food("COOKIE", 2);

    private String name;
    private int ordinal;

    public Food(String name, ordinal){
        this.name = name;
        this.ordinal = ordinal;
    }
}
```

### compareTo

위에서 `Comparable` 을 `implements` 하는 것을 확인할 수 있습니다. `compareTo` 를 구현하므로써 각 상수들을 비교할 수 있습니다.

이 때 비교대상이 되는 값은 `ordinal` 이므로 각 상수의 열거 순서에 따라 값 비교를 할 수 있습니다.

```java
@Override
public final int compareTo(E o) {
    Enum<?> other = (Enum<?>)o;
    Enum<E> self = this;
		//Enum 타입 비교
    if (self.getClass() != other.getClass() && 
        self.getDeclaringClass() != other.getDeclaringClass())
		//다른 타입일 시 예외 반환
        throw new ClassCastException();
		//ordinal로 크기 비교
    return self.ordinal - other.ordinal;
}
```

클래스 형태이기 때문에 `enum` 내에서 `compareTo` 를 오버라이딩하여 사용할 수도 있습니다.

### valueOf, values

상속된 `valueOf()` 메서드를 통해서 파라미터와 일치하는 Enum 상수의 레퍼런스를 얻을 수 있습니다.

```java
public static <T extends Enum<T>> T valueOf(Class<T> enumType,
                                              String name) {
    T result = enumType.enumConstantDirectory().get(name);
		//발견된 enum이 있다면 enum 반환
    if (result != null)
        return result;
		//매개변수가 null이라면 널포인터 예외 발생
    if (name == null)
        throw new NullPointerException("Name is null");
		//찾은 enum이 없다면 아규먼트오류 예외 발생
    throw new IllegalArgumentException(
        "No enum constant " + enumType.getCanonicalName() + "." + name);
}
```

또한 `Enum` 사용시 컴파일러가 자동으로 추가해주는 메서드가 있습니다.

- `static T valueOf(String name)`
    - 위 상속된 `valueOf` 를 오버로딩한 형태로 같은 동작을 하지만 `enumType` 을 매개변수로 넘겨줄 필요가 없습니다.
- `static T[] values()`
    - Enum 상수에 포함된 모든 값들을 Enum 배열 타입으로 반환받습니다. 반환받는 순서는 `ordinal` 값 순서입니다.

    ```java
    //Food[] 형 확인
    //출력 class [LFood;
    System.out.println(Food.values().getClass());

    //CAKE, PIZZA, COOKIE 출력
    for(Food f : Food.values()){
    	System.out.print(f);
    }
    ```

## Enum에 값 추가하기

enum을 사용할 때 각 상수에 `ordinal` 을 제외한 추가 값이 필요할 경우 있습니다. 이 때는 값을 추가하기 위해 Enum의 특성을 고려해야합니다.

`Food` 처럼 enum을 생성할 때 생성자를 통해서 값을 저장하는 것을 확인했습니다. 따라서 값을 추가한다면 아래처럼 생성자와 값을 저장할 필드가 필요합니다.

```java
enum Food {
    //다른 로직 작성한다면 선언 이후 ; 를 반드시 작성한다.
    CAKE(5000), PIZZA(20000), COOKIE(1500);

    private final int cost;
    Food(int cost){
        this.cost = cost;
    }

    public int getCost(){   return cost; }
}
```

위와 같이 사용자 정의 필드 및 메서드를 추가할 수 있습니다. 각 enum 상수를 생성하며 생성자를 호출, 그 후 값을 할당하는 형태로 동작시킵니다.

또 다른 값 추가를 위해 추가 생성자를 작성하여 구현할 수 있습니다.

```java
enum Food {
    CAKE(5000, 700L), PIZZA(20000, 2000L), COOKIE(1500, 200L);

    private final int cost;
    private final double calories;

    Food(int cost, double calories){
        this.cost = cost;
        this.calories = calories;
    }

	//...
}
```

이 때 생성자를 재작성했지만, `enum` 의 모든 생성자는 `private` 취급하여 외부에서 호출할 수 없습니다.

```java
//컴파일 에러
Food food = new Food(1);
```

## 추상 메서드 작성

`enum` 은 위와 같이 일반메서드를 작성할 수도 있지만 추상메서드를 작성하여 각 enum 상수별로 다른 동작을 할 수도 있습니다.

이 때는 Enum 상수 선언부에 추상 메서드를 구현해야합니다.

```java
enum Food {
    CAKE(5000, 700L) {
        double getSatisfaction(int hunger) {
            return getCalories() - hunger - getCost();
        }
    },
    PIZZA(20000, 2000L) {
        double getSatisfaction(int hunger) {
            return getCalories() - hunger - getCost();
        }
    },
    COOKIE(1500, 200L) {
        double getSatisfaction(int hunger) {
            return getCalories() - hunger - getCost();
        }
    };

    private final int cost;
    private final double calories;

    Food(int cost, double calories){
        this.cost = cost;
        this.calories = calories;
    }

    abstract double getSatisfaction(int hunger);

    public int getCost(){
        return cost;
    }

    public double getCalories() {
        return calories;
    }
}
```

# EnumSet

`java.util.EnumSet` 클래스는 `AbstractSet` 을 상속한 추상 클래스입니다. 

일반적인 `HashSet` 이나 `TreeSet` 이 아닌 `EnumSet` 을 사용하는 이유는 다음과 같습니다.

1. 같은 `enum` 타입만 매개변수로 사용하기 때문에 `null` 을 가질 수 없고 가질 필요가 없다.
    - `EnumSet` 제네릭은 `Enum`의 상속(enum 포함) 가능하며 다른 형태의 값은 저장할 수 없으므로 타입이 보장됨

    ```java
    public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E>
        implements Cloneable, java.io.Serializable
    ```

2. 연산이 빠르며 메모리 효율적
    - `HashSet` 같은 경우는 내부적으로 배열과 `hashcode` 를 통해 값을 저장하는데 반해 `long` 형 비트필드로 값을 저장하기 때문에 메모리를 더 적게 할당하며 연산속도가 더 빠름
3. 순서 보장
    - `ordinal` 값을 통해 순서를 보장한다.

◾️`EnumSet` 은 thread-safe 하지 않기 때문에 사용시 주의해야합니다.

## EnumSet 생성

`EnumSet` 은 추상 클래스이므로 객체 생성을 위해서 내부에 작성된 `of` 메서드들을 이용합니다.

모든 `of` 메서드들은 내부적으로 `noneOf(Class elementType)` 메서드를 통해 객체를 생성합니다.

### noneOf

비어있는 `EnumSet` 객체를 생성합니다.

```java
//클래스 타입(Enum 타입)에 맞게 비어있는 EnumSet 생성
public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
    Enum<?>[] universe = getUniverse(elementType);
    if (universe == null)
        throw new ClassCastException(elementType + " not an enum");

    if (universe.length <= 64)
        return new RegularEnumSet<>(elementType, universe);
    else
        return new JumboEnumSet<>(elementType, universe);
}
```

### allOf

해당 enum 타입의 모든 상수를 셋에 추가하여 반환합니다.

```java
		//enumSet을 생성 후 모든 값을 add
public static <E extends Enum<E>> EnumSet<E> allOf(Class<E> elementType) {
    EnumSet<E> result = noneOf(elementType);
    result.addAll();
    return result;
}
```

### copyOf

`EnumSet` 을 매개로 받아 복제된 `EnumSet` 을 생성합니다.

혹은 `Collections` 를 매개로 받아 이터레이터를 통해 복제된 `EnumSet` 을 생성합니다.

```java
public static <E extends Enum<E>> EnumSet<E> copyOf(EnumSet<E> s) {
    return s.clone();
}

public static <E extends Enum<E>> EnumSet<E> copyOf(Collection<E> c) {
    if (c instanceof EnumSet) {
        return ((EnumSet<E>)c).clone();
    } else {
        if (c.isEmpty())
            throw new IllegalArgumentException("Collection is empty");
        Iterator<E> i = c.iterator();
        E first = i.next();
        EnumSet<E> result = EnumSet.of(first);
        while (i.hasNext())
            result.add(i.next());
        return result;
    }
}
```

### Of

셋을 생성한 후에 enum 상수 아규먼트를 넣어 반환합니다.

```java
public static <E extends Enum<E>> EnumSet<E> of(E e) {
    EnumSet<E> result = noneOf(e.getDeclaringClass());
    result.add(e);
    return result;
}

public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2) {
    EnumSet<E> result = noneOf(e1.getDeclaringClass());
    result.add(e1);
    result.add(e2);
    return result;
}
//...
```

또한 `AbstractSet`, `AbstractCollection` 등을 상속받기 때문에 `iterator`나 `add()` , `remove()` 등의 메서드를 사용할 수 있습니다.
