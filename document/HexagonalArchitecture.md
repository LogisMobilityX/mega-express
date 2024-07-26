# 헥사고날 아키텍처 (Hexagonal Architecture)

헥사고날 아키텍처는 소프트웨어 설계 원칙 중 하나로, 특히 유지 보수성과 확장성을 강조하는 아키텍처다. 이 아키텍처는 도메인 중심 설계(Domain-Driven Design, DDD)의 영향을 많이 받으며, 다양한 기술적 요구 사항을 충족하기 위해 사용된다. 헥사고날 아키텍처는 다음과 같은 핵심 개념을 포함한다.

### 핵심 개념

1. **도메인 계층(Domain Layer)**
    - **설명**: 비즈니스 로직과 도메인 규칙이 포함된 계층이다. 엔티티, 값 객체(Value Object), 도메인 서비스 등이 이 계층에 속한다.
    - **역할**: 비즈니스 규칙을 구현하며, 도메인 모델을 책임진다.

2. **애플리케이션 계층(Application Layer)**
    - **설명**: 도메인 객체를 사용하여 비즈니스 시나리오를 구현하는 계층이다. 주로 유스 케이스와 애플리케이션 서비스로 구성된다.
    - **역할**: 도메인 계층과 인프라스트럭처 계층 사이의 연결을 담당한다. 트랜잭션을 관리하고 도메인 객체를 조작하는 로직을 포함한다.

3. **인프라스트럭처 계층(Infrastructure Layer)**
    - **설명**: 데이터베이스, 메시징 시스템, 파일 시스템 등 외부 시스템과의 통신을 담당하는 계층이다.
    - **역할**: 기술적 세부 사항을 캡슐화하고, 도메인 계층과 애플리케이션 계층에서 사용하는 저장소 패턴, 외부 시스템 통신 등을 구현한다.

4. **인터페이스 계층(Interface Layer)**
    - **설명**: 사용자 인터페이스나 API를 통해 외부와 상호 작용하는 계층이다.
    - **역할**: 프레젠테이션 로직을 포함하며, 사용자가 시스템과 상호 작용할 수 있도록 한다. 웹 애플리케이션의 경우 컨트롤러, 뷰 등이 이 계층에 속한다.

### 특징 및 장점

- **유지 보수성**: 각 계층이 명확하게 분리되어 있어 변경 사항이 발생했을 때 영향 범위를 최소화할 수 있다.
- **확장성**: 새로운 기능 추가 시 특정 계층에 집중할 수 있어 확장이 용이하다.
- **테스트 용이성**: 각 계층이 독립적이기 때문에 유닛 테스트와 통합 테스트를 쉽게 수행할 수 있다.
- **재사용성**: 도메인 로직이 중심이 되기 때문에 다양한 인터페이스와 인프라를 통해 재사용할 수 있다.

### 구현 예시

1. **도메인 계층**
   ```java
   public class Order {
       private Long id;
       private List<OrderItem> items;

       public void addItem(OrderItem item) {
           // 비즈니스 로직
       }

       // 기타 도메인 로직
   }

2. **애플리케이션 계층 (Application Layer)**
- **설명**: 도메인 객체를 사용하여 비즈니스 시나리오를 구현하는 계층이다. 주로 유스 케이스와 애플리케이션 서비스로 구성된다.
- **역할**: 도메인 계층과 인프라스트럭처 계층 사이의 연결을 담당한다. 트랜잭션을 관리하고 도메인 객체를 조작하는 로직을 포함한다.

```java
public class OrderService {
    private OrderRepository orderRepository;

    public void createOrder(Order order) {
        // 유스 케이스 로직
        orderRepository.save(order);
    }
}
```

3. **인프라스트럭처 계층 (Infrastructure Layer)**
- **설명**: 데이터베이스, 메시징 시스템, 파일 시스템 등 외부 시스템과의 통신을 담당하는 계층이다.
- **역할**: 기술적 세부 사항을 캡슐화하고, 도메인 계층과 애플리케이션 계층에서 사용하는 저장소 패턴, 외부 시스템 통신 등을 구현한다.

```java
public class OrderRepositoryImpl implements OrderRepository {
    private EntityManager entityManager;

    public void save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
        } else {
            entityManager.merge(order);
        }
    }

    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public void delete(Order order) {
        if (entityManager.contains(order)) {
            entityManager.remove(order);
        } else {
            Order managedOrder = entityManager.find(Order.class, order.getId());
            if (managedOrder != null) {
                entityManager.remove(managedOrder);
            }
        }
    }
}
```
4. **인터페이스 계층 (Interface Layer)**
- **설명**: 사용자 인터페이스나 API를 통해 외부와 상호 작용하는 계층이다.
- **역할**: 프레젠테이션 로직을 포함하며, 사용자가 시스템과 상호 작용할 수 있도록 한다. 웹 애플리케이션의 경우 컨트롤러, 뷰 등이 이 계층에 속한다.

```java
@RestController
public class OrderController {
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return ResponseEntity.ok().build();
    }
}