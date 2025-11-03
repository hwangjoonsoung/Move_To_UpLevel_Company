# New Information I Learned
## 2025-10-09
### 식별관계와 비식별관계

#### 개요
- ERD cloud에서 booth table을 설계하는 도중에 식별관계를 설정하니 fee 테이블의 id와 fee_item 테이블의 id가 booth 테이블의 fk로 들어갔다
- 내가 설계한 DB 사전등록과 요금![2025_10_09.png](/source/2025_10_09.png)
 
- 이를 보고 내가 설계한 것이 비 식별관계인가 식별 관계인가에 대한 의문점을 가짐
#### 내용
- 내가 설계한 구조는 비 식별관계이며 현대적인 DB설계는 대부분 비 식별관계로 설계하는 것이 유리하다.
#### 식별관계와 비 식별관계의 차이
    
1. 식별관계
    - 식별관계의 경우 부모 테이블의 pk를 자식테이블에서 fk로 사용함과 동시에 특정할 수 있는 키를 사용하여 하나의 row를 찾을 수 있는 관계를 의미한다.
    - 예를 들어 자식테이블에서 pk로 찾으면 여러개의 row가 나올텐데, 특정키(sequence키, unique키 등등)를 사용하여 하나의 row를 찾아낼 수 있는 구조일때 식별관계가 성립니다.
    - 이때 자식 테이블에서 pk 유무의 여부는 상관 없다.
2. 비 식별관계
    - 부모 테이블의 pk를 자식테이블에 fk로 사용하는 단순 참조값일 뿐이다.
#### 결론
- 비 식별관계를 사용하되 N:N 관계에서는 식별관계를 사용하자.

## 2025-10-11
### 하나의 폼에서 두개 이상의 dto로 분리해서 받아야 하는 경우

#### 개요

- 부스 신청할때 하나의 폼에서 부스에 대한 정보와 부스에 참가하는 사람의 정보를 입력하는 항목이 필요할것으로 생각함.
- 결국 하나의 폼에서 특정 항목을 분리해서 json으로 만들어서 controller로 보내야 하는데. 이 부분을 어떻게 하면 좋을지 고민함.
- 처음에는 controller에서 받을때 @responseBody를 나눠서 받는 방법을 사용하면 안될까? 라는 생각을 했다.
```java
    @PostMapping("/booth/new")
    public void newBooth(@RequestBody BoothInfoDto boothInfoDto, @RequestBody StaffInfoDto staffInfoDto) {
    }
```
- 이건 불가능하다. request Body는 하나의 스트림으로 전달되기 때문에 그 내용을 읽어서 변환하는 과정은 한번만 수행할 수 있기때문.
#### 내용

- 하나의 dto로 모든 정보를 받는게 아닌 부모 dto와 자식 dto로 분리해서 받는다.
```java
@Setter @Getter @Builder
public class BoothNewRequestDto {

    @Valid
    private BoothInfoDto boothInfo;

    @Valid
    private List<StaffInfoDto> staffs;

}

@Setter @Getter @Builder
public class BoothInfoDto {
    private String companyName;

    private String ceoName;
    
    //...나머지 변수
}

@Getter @Setter @Builder
public class StaffInfoDto {

    private String name;

    private String affiliation;
    //...나머지 변수
}
```
#### DTO분리를 통하여 받기
- DTO를 분리해서 받으면 자식 DTO를 가져와서 entity를 만들어주고, persist하는 것으로 DB에 입력할 수 있다.
#### 그럼 front에서는 어떻게 날려야 할까?
- json으로 만들어서 날리면 된다.
```json
{
    "boothInfo":{
        "companyName":"테스트 회사명",
        "ceoName":"테스트 회장님 이름",
        "companyPhoneNumber":"010-1234-1234"
    },
    "staffs":[{
        "name":"참가자1",
        "affiliation":"소속1",
        "position":"직책1"
    },
    {
        "name":"참가자2",
        "affiliation":"소속2",
        "position":"직책2"
    }
    ]
}
```
#### 결론
- 하나의 form에서 두개 이상의 entity에 나눠서 입력해야 한다면, 각각 부모 DTO와 자식 DTO를 생성하여 @requestBody를 통해 한번에 받을 수 있도록 한다.

## 2025-10-12
### lombok에서 builder를 사용할 때 default로 선언되어 있지만 null로 build되는 이유
#### 개요
- booth service level에서 booth add를 하는 과정을 작업하는 중에 dto를 받아서 booth entity를 bulid해준다.
- 이때 생성한 연관관계 편의 메서드를 사용하는데 NPE가 발생했다.
- Booths entity에 있는 연관관계 편의 메서드
- ```java
    @Builder
    public class Booths {

        @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL , orphanRemoval = true)
        private List<Staffs> staffs = new ArrayList<>();
    
        //== 연관관계 편의 메서드 ==//
        public void addStaff(Staffs staffs) {
            this.getStaffs().add(staffs);
            staffs.setBooths(this);
        }
    }
  ```
  
#### lombok에서 제공하는 Builder를 사용하면 해당 필드는 초기화로 설정해 버리는 이슈
- Lombok에서 제공하는 builder는 해당 클레스에 있는 필드와 이름이 동일한 메서드를 생성하고 해당 메서드를 통해 필드 값을 세팅해주는 방식으로 동작한다.
- 이는 세팅되지 않는 값은 초기값(null, 0, false)으로 세팅된다는 것을 의미한다.

#### 대응방법
- ```java
    @Builder
    public class Booths {

        @Builder.Default
        @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL , orphanRemoval = true)
        private List<Staffs> staffs = new ArrayList<>();
    
        //== 연관관계 편의 메서드 ==//
        public void addStaff(Staffs staffs) {
            this.getStaffs().add(staffs);
            staffs.setBooths(this);
        }
    }
  ```
- @Builder.Default를 부여한다면 해당 필드는 선언되어 있는 값으로 초기화를 시킨다.
- 간단하게 해결 가능
#### 결론 
- Builder를 사용할때 초기화를 지정해야 하는 필드는 Builder.default를 적용하자.
## 2025-10-15
### 영속되어 있는 entity를 비영속 시켰을때 발생하는 문제
#### 개요
- booth edit을 하는 과정에서 항상 그랬듯이 변경감지를 통해 booth를 수정하려고 시도했다.
- 문제는 staff를 수정하려고 하면 영속상태를 풀어버리는 문제가 발생해서 해결책을 찾고 있었다.
- ```java
    //service
    public Long editBooth(Long id, BoothRequestDto boothRequestDto) {
        Booth booth = boothRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("부스 신청 내역이 없습니다."));
        boothRepository.deleteStaffWithEntity(booth);

        FeeItem feeItem = feeItemsRepository.findById(boothRequestDto.getBoothInfo().getFeeItemId()).orElseThrow(() -> new IllegalArgumentException("해당 금액이 없습니다"));
        booth.updateBooth(boothRequestDto, feeItem);

        return id;
    }
  
  //repository
  @Repository
    public interface BoothRepository extends JpaRepository<Booth, Long> {
    @Modifying(clearAutomatically = true)
    @Query("update Staff s set s.isDeleted = true where s.booth = :booth and s.isDeleted = false")
    void deleteStaffWithEntity(@Param("booth") Booth booth);
  }
  
  //booth entity
  public void updateBooth(BoothRequestDto boothRequestDto, FeeItem feeItem) {
        this.companyName= boothRequestDto.getBoothInfo().getCompanyName();
        this.ceoName= boothRequestDto.getBoothInfo().getCeoName();
        this.companyPhoneNumber= boothRequestDto.getBoothInfo().getCompanyPhoneNumber();
        this.boothCount= boothRequestDto.getBoothInfo().getBoothCount();
        this.boothIds= boothRequestDto.getBoothInfo().getBoothIds();
        this.managerName= boothRequestDto.getBoothInfo().getManagerName();
        this.managerAffiliations= boothRequestDto.getBoothInfo().getManagerAffiliations();
        this.managerPhoneNumber= boothRequestDto.getBoothInfo().getManagerPhoneNumber();
        this.managerEmail= boothRequestDto.getBoothInfo().getManagerEmail();
        this.price= boothRequestDto.getBoothInfo().getPrice();
        this.feeItem = feeItem;
        List<StaffInfoDto> staffs = boothRequestDto.getStaffs();
        this.getStaff().clear();

        staffs.forEach(staff -> {
            Staff newStaff = Staff.builder()
                    .affiliation(staff.getAffiliation())
                    .name(staff.getName())
                    .position(staff.getPosition())
                    .build();
            this.addStaff(newStaff);
        });
    }
  
  //staff entity
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booth_id")
  private Booth booth;
  ```
#### 원인
- 프로세스를 잘못 설계했다.
  1. id를 통해 booth가져오기
  2. 기존에 입력되어 있는 staff를 모두 delete = true로 변경 (영속되어 있는 entity를 비영속으로 변경)
  3. dto를 통해 받은 정보를 booth로 변경
  4. booth에소 getStaff를 하는 순간 에러 발생
- 4번에서 에러가 발생하는 이유는 fetch가 lazy로 되어 있는데 우리는 2번에 비영속 상태로 변경했기 때문이다.
- 당연히 사용을 하려고 하는 시점에서는 비영속상태임으로 exception이 발생한 것이다.

#### 해결방법
- ```java
  //repository
  @Repository
    public interface BoothRepository extends JpaRepository<Booth, Long> {
    @Modifying
    @Query("update Staff s set s.isDeleted = true where s.booth = :booth and s.isDeleted = false")
    void deleteStaffWithEntity(@Param("booth") Booth booth);
  }
  ```
- 영속상태를 유지할 수 있도록 변경하면 간단하게 해결 가능하다.
#### 결론
- proxy에 no Session이면 영속상태 여부를 확인하자.

## 2025-10-19
### spring에서 docker mysql에 연결할때 localhost(127.0.0.1)인 경우 발생하는 문제
#### 개요
- 선착순 이벤트 강의를 들으면서 docker를 설치하였음 
- image로 mysql를 가져오는 도중에 spring에 연결되어 있는 DB연결을 local이 아닌 docker의 mysql에 연결하는 것이 더 좋을것 같다는 생각을 함.
- 이때 localhost:3306이 연결이 안되는 것을 확인 하였음.
- ```properties
  spring:
    devtools:
      livereload:
        enabled: false
    application:
      name: conference
    datasource:
      url: jdbc:mysql://localhost:3306/conference?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true
      password: conference123!!
      username: conference
      driver-class-name: com.mysql.cj.jdbc.Driver
  ```
#### 원인 
- localhost(127.0.0.1)을 의미하는 대상의 차이로 발생한 문제
- spring에서 localhost를 사용하면 지금 사용하고 있는 local기기를 의미한다
- 따라서 DB계정을 생성할 때 특정 ip에만 입력할 수 있도록 하는 계정을 만들면 당연히 DB연결이 불가능하다.(access denied)
- ```sql
  create user 'conference'@'localhost' indentified by 'conference123!!';
  ```
- 즉 localhost는 local기기를 의미하며, docker image로 만들 mysql의 경우 localhost가 아니다.
#### 해결방법
- global한 방법 (개발단계에서 사용)
  - ```sql
      create user 'conference'@'%'
    ```
  - 위와 같이 모든 ip에 대해서 들어갈 수 있는 계정을 생성하는 방법
  - 이 방법은 개발 단계에서만 사용해야 한다.
- specified한 방법
  - ```sql
        create user 'conference'@'127,14.1.0' identified by 'passwrod'
    ```
  - 위와 같이 특정 ip에 대해서만 입장할 수 있는 계정을 생성해야 한다.
#### 결론
- localhost의 의미는 실행환경에 따라서 달라진다.
- docker는 컨테이너 환경이기 때문에 local로 접속할 수 없으며, 이를 해결하기 위해서는 mysql계정을 생성할때 모든 ip에 대해 접근할 수 있는 계정을 만들거나, 특정 ip를 적용하는 적으로 접근 가능하다.

## 2025-11-03
### flyway repair
#### 개요
- default data를 입력하는 과정에서 insert query를 작성하는데 오타발생으로 인해서 쿼리가 동작을 안함.
- 문제는 해당 쿼리가 정상적으로 동작해도 이미 flyway에서 막혀서 서버를 실행할 수 없는 문제가 있음
- 기존에 해결할때는 database를 drop한 후 다시 create를 하는 것으로 해결함
- 분명 저렇게 하는 것이 아닌텐데... 라는 생각을 하고 있었음
#### 원인
- 일단 오류가 발생하는 문제는 flyway의 마이크레이션 히스토리와 DB 스키마 상태의 불일치 때문이다.
#### 시도
1. select * from flyway_schema_history;
2. 가장 최근 버전 삭제
#### 실패
- 일단 flyway_schema_history를 삭제한다고 해서 문제가 해결되는건 아니다.
- flyway_schema_history는 성공여부를 나타내는 것이기 때문에 해당 히스토리를 지운다고 원인해결은 아니다.
#### 해결
- FlywayMigrationStrategy를 이용한 해결
- ```java
    @Configuration
    public class FlywayRepairRunner {

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
                flyway.repair();
                flyway.migrate();
            };
        }
    }
  ```
- server를 시작하면 flyway를 repair하고 migration을 다시 하는 것으로 한다.
- 이렇게 하면 flyway설정에서 지정한 directory에 있는 version이 다시 쓰여진다.
- 물론, 기존에 이상없는건 넘어감.
#### 결론
- flyway를 해결하는 가장 간단한 방법은 gradle에서 ./gradlew flywayRepair이다.
- 하지만 local에서 실행하는 상황에서는 어쩔수 없이 configuration을 통해 진행할 수 밖에 없다.
- repair이 동작하는 방식은 다음과 같다.
  1. DB와 파일 리스트를 확인
  2. 일하지 않는 row를 파악해서 다시 생성
  3. migration 동작
- 따라서 DB의 row를 삭제한다고 해서 해결할 수 있는 건 아니다.
