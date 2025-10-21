# 요구사항
## 선착순 100명에게 할인쿠포을 제공하는 이벤트를 진행하고자 함

### 요구사항
- 선착순 100명에게만 지급
- 101개 이상 지급되면 안됨
- 순간적으로 몰리는 트래픽을 버틸 수 있어야 함

### 선착순 100명에게만 지급하는 방법
1. 과정
   - ```java
       //service
       public void apply(Long userId) {

           long count = couponRepository.count();
           if (count > 100) {
               return;
           }
           couponRepository.save(new Coupon(userId));
       }
        ```
   - 위와 같이 DB에 입력된 쿠폰의 수량을 확인하고 100개 이하인 경우 새로운 쿠폰을 입력해 주는 방식으로 구현
2. 문제점
    - multi thread환경에서는 race condition이 발생함으로 100개 이상 발생할 가능성이 있다.
3. 해결방법
   - thread lock을 거는 방법
     - 비효율적임 - DB입력되기 전까지는 다른 쿠폰을 입력할 수 없음.
   - redis를 사용하여 해결
     - redis의 경우 single thread 기반임으로 thread safe함으로 해결 가능
4. 해결과정 (redis를 사용)
    - redis는 thread safe함으로 쿠폰 하나가 발급될때마다 redis의 count를 1개씩 증가시키는 것으로 적용
    - ```java
        //service
        public void apply(Long userId) {
            long count = couponCountRepository.increment();
            if (count > 100) {
                return;
            }
            couponRepository.save(new Coupon(userId));
        }

        //repository
        public Long increment() {
            return redisTemplate.opsForValue().increment("coupon_count");
        }
      ```
    - 위와 같이 redis의 coupon_count를 1씩 증가시키고, 발급될때마다 해당 카운트를 가져오는 것으로 해결가능.
5. 발생 가능한 문제점
   - 발행 가능한 쿠폰의 수량이 많으면 DB에 부하를 일으킴.

### DB에 부하를 고려하는 방법

- 생각을 해보면 우리는 일련의 과정을 한번에 진행 하는 경우 DB에 부하를 많이 발생할 가능성이 크다.
- DB의 경우 회원이나 주문과 같은 일련의 작업이 있을 가능성이 크기 때문이다.
#### kafka를 통한 해결방법
1. 전체과정
   1. kafka를 이용해 producer를 이용해 topic을 생성
   2. 생성된 topic을 consumer가 가져가는것으로 해결가능하다
2. 해결과정
   - ```java
       //repository
        @Component
        public class CouponCreateProducer {
        
            private final KafkaTemplate<String, Long> kafkaTemplate;
        
            public CouponCreateProducer(KafkaTemplate<String, Long> kafkaTemplate) {
                this.kafkaTemplate = kafkaTemplate;
            }
        
            public void create(Long userId) {
                kafkaTemplate.send("coupon_create", userId);
            }
        }

       //service
        public void apply(Long userId) {
            Long apply = appliedUserRepository.add(userId);
    
            if (apply != 1) {
                return;
            }
    
            long count = couponCountRepository.increment();
            if (count > 100) {
                return;
            }
            couponCreateProducer.create(userId);
        }
       ```

### 한명에게 하나의 쿠폰만 발행 하는 방법
#### redis의 set을 이용한 해결.
1. 전체과정
   - redis에서 set을 입력할때 입력을 성공하면 입력된 수량을 return한다.
   - 이말은 중복된 자료를 입력하면 0을 return하고, 그렇지 않는 경우 1을 return한다.
   - 결론적으로 id를 입력했을때 1이 아닌 경우를 return했을때 조건을 적용하면 1인 1개의 쿠폰을 발행할 수 있다.
2. 해결과정
    - ```java
      public void apply(Long userId) {
        Long apply = appliedUserRepository.add(userId);

        if (apply != 1) {
            return;
        }

        long count = couponCountRepository.increment();
        if (count > 100) {
            return;
        }
        couponCreateProducer.create(userId);
      }
      ```
