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

