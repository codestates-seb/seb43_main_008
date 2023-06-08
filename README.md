<div align="center">
  <h1>🌿쓰고 쓰고 또 쓰자 (쓰쓰또쓰)</h1>
</div>

<div align="center">
  <h3>쓰쓰또쓰와 함께 플라스틱을 재활용하고 기록해봐요.</h3>
  <img src="https://raw.githubusercontent.com/codestates-seb/seb43_main_008/main/client/public/images/qrCode.png" width="200px" height="200px">
</div>

<br/>

사용자가 물건이 “쉽게 버릴 수 있는 물건”이 아닌 “끝까지 제 쓸모를 다한 물건”이 될 수 있도록 도와줍니다.
함께 하며 서로가 서로의 원동력이 되도록 환경을 조성합니다.
저희 팀은 개인의 작은 실천과 동참으로 조금 더 깨끗한 세상을 만들 수 있다고 믿습니다.

-   쓰쓰또쓰는 사용자가 플라스틱 재사용 회차별 사진과 함께 글을 지속적으로 기록하는 일지 제공 서비스입니다.
-   재사용한 플라스틱의 졸업을 위해, 투표 기능을 제공합니다.
-   투표 결과에 따라 배지와 메달 보상으로 성취감을 느낄 수 있도록 만들었습니다.

<br/>

## 팀 소개

### Front-end

|                                  <img src="https://cdn.discordapp.com/attachments/1101112392659767369/1113236228351991869/421b971f5aaff0b5.png" width="200px" height="200px">                                   |                           <img src="https://cdn.discordapp.com/attachments/1101112392659767369/1113236380366159994/a2125abea89bbbe1.png" width="200px" height="200px">                            |                 <img src="https://cdn.discordapp.com/attachments/1101112392659767369/1113237188902125608/32b8a4bccba3ad5f.png" width="200px" height="200px">                  |
| :-----------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------: |
|                                         [천지인(LEAD)<br/>(@jiin9999)](https://github.com/jiin9999)                                         |                                     [오아름<br/>(@Aroma-oh)](https://github.com/Aroma-oh)                                     |                         [정순현<br/>(@jungsoonhyun)](https://github.com/jungsoonhyun)                         |
| <p align="left">- 로그인 페이지<br/> - 회원가입 페이지<br/> - 회원 정보 수정 페이지<br/> - 회원가입 축하 페이지<br/> - 투표 완료 페이지</p> | <p align="left">- 북마크 페이지<br/> - 시리즈 상세 페이지</br> - 팔로우 페이지</br> - 메인 페이지</br> - 마이 페이지</br></p> | <p align="left">- 뱃지 페이지<br/> - 시리즈 작성페이지<br/> - 일지 작성 페이지</br>- 일지 상세페이지</br></p> |

### Back-end

| <img src="https://cdn.discordapp.com/attachments/1101112392659767369/1113236311986405406/0bf74084411d2309.png" width="200px" height="200px"> | <img src="https://cdn.discordapp.com/attachments/1101112392659767369/1113236110362034196/f9ec1fb7f9a35b2f.png" width="200px" height="200px"> | <img src="https://cdn.discordapp.com/attachments/1101112392659767369/1113236995800576120/aee0cf8f08b07ff1.png" width="200px" height="200px"> |
| :---------------------------------------------------------------------: | :--------------------------------------------------------------------------: | :-----------------------------------------------------------------------------: |
|        [진하늘(LEAD)<br/>(@mewluee)](https://github.com/mewluee)        |        [김대현<br/>(@NewfileDOTpy)](https://github.com/NewfileDOTpy)         |       [안윤아<br/>(@digital-hamster)](https://github.com/digital-hamster)       |
|               <p align="left">- 회원 도메인 CRUD</br> - 팔로우 도메인 CRUD</br> - OAuth2 로그인</br> - Spring Security + JWT</br> - 보안 </p>               |                 <p align="left">- 시리즈 도메인 CRUD</br> - 코멘트 도메인 CRUD</br> - 데이로그 도메인 CRUD</br> - CD/CI</br> - 이미지 파일 처리</br> - 이미지 파일 처리</p>       |                   <p align="left">- 뱃지 도메인 CRUD</br> - 북마크 도메인 CRUD</br> - 투표 도메인 CRUD</br> - Database Administrator</br> - NginX </p>                   |

## 시스템 아키텍처
![시스템아키텍쳐](https://github.com/codestates-seb/seb43_main_008/assets/119935602/ef5d1ff8-6c06-44ee-a0f5-947361afff48)


## ERD
<img width="1085" alt="ERD" src="https://github.com/codestates-seb/seb43_main_008/assets/119935602/149d42f7-fa6b-475f-bacf-8adbc707e77d">


## FE 기술 스택

-   Next.js
    -   SSR을 이용해 SEO(검색엔진 최적화)를 향상시켰습니다.
-   React
    -   컴포넌트 재사용과 상태에 따른 불필요한 리렌더링을 고려하며 구현하였습니다.
-   TypeScript
    -   동적 언어인 JS의 안정성을 보장하고, 원활한 협업을 위해 사용했습니다.
-   PWA
    -   PWA를 이용해 데스크탑, 안드로이드, IOS에서 다운로드하여 앱으로 사용할 수 있도록 구현하였고, 반응형 UI로 크로스 플랫폼을 지원할 수 있도록 했습니다.
    -   웹 푸시를 활용해 사용자가 네이티브 앱을 사용하는 것처럼 느낄 수 있도록 구현하였습니다.
-   ESLint & Prettier
    -   협업을 위해 코드 포매팅 기능에 ESLint를, 코드 스타일링에 Prettier를 적용했습니다.
-   Deploy
    -   Vercel을 이용해 https 배포했습니다.
    -   Github Action으로 CI/CD를 도입했습니다.

<br/>

## BE 기술 스택

-   Spring boot
    -   Java 기반의 웹 애플리케이션을 개발하기 위해 spring 프레임워크를 사용했습니다.
-   Spring Security
    -   사용자 인증과 권한 부여 그리고 보안 구성을 위해 사용했습니다.
-   MySQL
    -   사용자의 데이터를 담을 수 있는 데이터베이스 서버를 만들어 MySQL로 구현했습니다.
-   Postman
    -   REST API의 테스트를 위해 이용했습니다.
-   Linux
    -   원격 서버 가동을 위해 사용했습니다.
-   Gradle
    -   외부 라이브러리 및 프레임워크를 쉽게 적용하여 프로젝트를 빌드하기 위해 사용했습니다.
-   JPA
    -   DB를 로직 안에서 구현하기 위해 RDS를 이용하여 JPA로 구현했습니다.
-   Oauth2
    -   oauth2를 사용하여 사용자가 편리하게 회원가입과 로그인을 하도록 지원했습니다.
-   AWS Web Service
    -   EC2, CodeDeploy, S3 Bucket, RDS 를 통해 어플의 배포 및 관리를 하였습니다. 또한 IAM을 통해 협업 할 수 있었습니다.
-   JWT
    -   보안을 위해 사용자의 권한 정보를 담은 JWT를 발급해 리소스 접근에 제한을 두었습니다.
-   Github Action
    -   깃허브 의존성이 높고, 비용적 한계가 있는 프로젝트의 상황을 고려하여 CD/CI를 선정하였으며 Gighub push / merger 를 통해 간편하게 배포된 어플리케이션을 유지/ 보수 할 수 있도록 했습니다.
-   S3 imageBucket
    -   DB에 이미지 저장 시 가해지는 용량 및 부하를 줄이기 위하여 큰 스토리지용량을 지원하는 S3 imageBucket 사용했습니다.


<br/>

## 개발시 주안점

<details>
<summary><b>천지인</b></summary><br/>
  
- 프로젝트 초기 세팅: Next13의 실험적 기능인 app 디렉토리 폴더 구조를 이용해 프로젝트를 구축하였습니다.
- PWA: PC보다는 모바일에 집중한 사이트의 특성을 고려해 홈 화면에 추가하여 사용자가 네이티브 앱처럼 사용할 수 있도록 했습니다.
- 회원가입: React-hook-form을 사용해 컴포넌트 리렌더링 최적화를 진행했습니다.
- 회원정보 수정: 사용자가 프로필 이미지를 등록하면 미리보기가 렌더되고, 서버에 바로 저장될 수 있도록 구현하였습니다.
  
</details>

<details>
<summary><b>오아름</b></summary><br/>

  
- 메인 페이지
  
 ![](https://velog.velcdn.com/images/on002way/post/9b0073bf-e650-48de-8b53-4b709e4a542d/image.gif) 
  
 ✅ 비회원도 조회가 가능한 유일한 페이지! 메인 페이지는 모두가 조회가 가능한 유일한 페이지입니다. 
  따라서 개별 시리즈는 새로운 사용자의 궁금증을 유도하여 회원가입으로 연결하는 역할을 해야합니다.
  이를 위해 시리즈의 사진 영역을 넓게 잡아 시각적인 이목을 끌고자 했으며, “n번 사용”정보를 노출하였습니다. 
  (비닐봉지를 30번 사용한 시리즈라면 궁금하지 않을까요? 👀) 
  
✅ 사용자의 빠른 탐색을 위해 무한 스크롤을 적용했습니다.
많은 이미지 로딩이 필요한 메인페이지는 최초 렌더가 느리다는 단점이 있습니다. 
  이를 해결하기 위해 api 요청 1회당 12개의 데이터만 불러오는 무한 스크롤을 적용하였습니다.

- 시리즈 상세 페이지
  
![](https://velog.velcdn.com/images/on002way/post/3047f626-a0f2-4efa-bef2-cc6a7c47ae12/image.gif)
  
✅ 기능별로 컴포넌트를 분할했습니다. 
시리즈 상세페이지는 다양한 기능(데이로그 조회, 북마크, 투표, 댓글)이 들어가는 페이지입니다. 각 기능은 개별 api가 존재하기 때문에 불필요한 리렌더링 방지를 위해 컴포넌트를 분할 했습니다. 
  
✅ 사용자의 빠른 탐색을 위해 무한 스크롤을 적용했습니다.
데이로그도 많은 이미지 로딩이 필요한 페이지입니다. 따라서 무한 스크롤을 통해 단위 별로 데이터를 호출하여 빠른 로딩을 주고자 했습니다. 

- 마이 페이지
  
![](https://velog.velcdn.com/images/on002way/post/ecfbc362-e5f0-44f8-b079-8b0f92a8d97d/image.gif)
  
✅ 조건으로 UI를 구분하여 컴포넌트를 재사용했습니다. 
마이페이지는 "자신"의 페이지 접속의 경우와 "타인"의 페이지 접속의 경우 UI가 구분됩니다. UI의 글씨 혹은 연결 링크만 변경되기 때문에, 페이지를 나눠 중복 코드를 작성하기 보다는 조건문을 주어 다른 UI가 렌더되도록 하였습니다. 
  
✅ 다양한 시리즈 상태의 구분이 필요했습니다.
시리즈는 투표 결과에 따라 메달을 부여받을 수도, 재사용을 해야할 수도 있습니다. 이러한 상태가 피드의 UI상으로 구분되어 사용자가 즉각적으로 확인할 수 있도록 구현했습니다. 

  
</details>

<details>
<summary><b>정순현</b></summary><br/>
  
- 로그인 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
- 회원가입 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
- 회원 정보 수정 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
  
</details>

<details>
<summary><b>진하늘</b></summary><br/>
  
- 로그인 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
- 회원가입 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
- 회원 정보 수정 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
  
</details>

<details>
<summary><b>김대현</b></summary><br/>
  
- 로그인 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
- 회원가입 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
- 회원 정보 수정 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
  
</details>

<details>
<summary><b>안윤아</b></summary><br/>
  
- 로그인 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
- 회원가입 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
- 회원 정보 수정 페이지
  - 이런 이유로 이런 이런 기술을 적용해서 이렇게 만들어 보았습니다.
  
</details>
