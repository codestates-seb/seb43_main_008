const badgeLists = [
  {
    src: "/badgeIcons/signUp.svg",
    alt: "가입 뱃지",
    mainText: "가입 축하 기념 뱃지 획득!",
    subText: "10%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: true,
  },
  {
    src: "/badgeIcons/dog.svg",
    alt: "강아지 뱃지",
    mainText: "재활용률 10%를 달성하셨네요!",
    subText: "20%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: true,
  },
  {
    src: "/badgeIcons/appreciation.svg",
    alt: "감사 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: true,
  },
  {
    src: "/badgeIcons/halloween.svg",
    alt: "할로윈 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: true,
  },
  {
    src: "/badgeIcons/beach.svg",
    alt: "해변 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: true,
  },
  {
    src: "/badgeIcons/blooming.svg",
    alt: "만개 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: false, // 기본값은 false
  },
  {
    src: "/badgeIcons/family.svg",
    alt: "가족 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: false,
  },
  {
    src: "/badgeIcons/shopping.svg",
    alt: "쇼핑 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: false,
  },
  {
    src: "/badgeIcons/wedding.svg",
    alt: "결혼 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: false,
  },
  {
    src: "/badgeIcons/cat.svg",
    alt: "고양이 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: false,
  },
  {
    src: "/badgeIcons/waves.svg",
    alt: "레져 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: false,
  },
  {
    src: "/badgeIcons/coffee.svg",
    alt: "커피 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    isAcquired: false,
  },
];

export default badgeLists;

// 기존에는 badgeLists를 직접 import해서 사용했지만, 이제는 부모 컴포넌트에서 API로 가져온 badgeList를 사용합

// 코드를 실행하려면 BadgeData 파일에 해당하는 데이터를 서버에서 받아와야 합니다. 이것은 실제 서버와 클라이언트 간에 데이터가 어떻게 교환되는지에 따라 달라짐
// badgeList는 일련의 뱃지 이미지에 대한 정보를 담은 객체의 배열이어야 함

// 각 객체는 이미지 소스 URL(src), 대체 텍스트(alt), 메인 텍스트(mainText), 부 텍스트(subText), 그리고 뱃지 획득 여부(isAcquired)를 포함해야 함
