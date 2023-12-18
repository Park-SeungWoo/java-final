# About Project

https://github.com/Park-SeungWoo/java-final

# Demonstrate Video

---

https://github.com/Park-SeungWoo/java-final/assets/54872527/0badb59c-f63a-46e7-86b2-169fc20b6f33

# Features

---

기본 프로젝트 구조에서 추가된 기능들에 관해서만 설명합니다.

### Main

> 간단한 로그인 로직 구현으로
id: admin
pw: admin
으로 로그인 해야 메뉴 이용 가능(help 제외)
>

### Change Background

> Main frame의 메뉴 중 file→New Background Image
파일을 선택해 main frame의 배경화면으로 변경
jpg, jpeg, png 파일을 선택한 경우에만 해당
>

### Game Menu

> 게임으로 크롬의 공룡게임과 유사하게 제작함
Enter: 게임 시작
Space Bar: 점프
ESC: pause
플레이어와 장애물의 색상은 프로그램에 젖의되어있는 컬러셋에서 랜덤으로 뽑아서 사용
장애물이 나타나는 주기: 마지막 장애물 나타난 후로부터 최소 350px 이후 + random(0 ~ 200)px
장애물의 높이: 기본 50px + random(0~20)px
장애물을 5개 지날 때마다 난이도 상승
최대 난이도는 5
>

### Developer Info

> 개발자에 대한 정보를 javafx의 webview를 통해 notion page link를 publish해 제공
>

### Project Info

> 프로젝트에 대한 정보를 javafx의 webview를 통해 notion page link를 publish해 제공
>

# Developments

---

### JavaFx

> **17.0.9v
minimum JDK version: 11**
웹뷰를 위해 library 추가 사용, 자바 버전이 최소 11이어야 함
>

### AbstractFrame

> 모든 frame에 공통적으로 사용되는 기능들을 모아 각 Frame 에서는 비즈니스 로직들에만 집중 할 수 있도록 함
>
- 각 frame에서 구현되어야하는 코드는 크게 4 부분으로 나눈 method를 재정의하여 구현하도록 함

  위 메서드들은 abstract frame class의 constructor에서 호출되어 frame에 적용됨

- frame들에서 공통적으로 사용될 수 있는 메서드를 구현해 둠
    - 좌표를 이용한 frame, component 배치를 위한 메서드

      각각 component가 dimension의 중심 또는 특정 frame, panel등 box의 중심에 위치할 수 있는 좌표값을 return 해줌

- Reflection을 이용한 frame.add(), setMenuBar() 자동화
    - 각 클래스에서 frame에 add되어야하는 component들은 필드에 private으로 선언하고 “someComp”의 Naming convention을 따라야함
    - 추가 되어야 하는 menu bar는 반드시 private으로 하고, “menuBar”로 이름을 지정해야함
- 공통적으로 모든 frame에 popup menu가 추가
    - close
        - 메인화면 이면 프로그램 종료
        - 나머지는 dispose()
    - program info
        - 프로젝트 소개 메뉴로 넘어감
    - developer info
        - 개발자 소개 메뉴로 넘어감
    - game 메뉴와 help 메뉴들에서는 사용 불가

### FontsNColors

> 공통적으로 사용될 수 있는 font 설정과 color를 static fields로 모아둔 class
>