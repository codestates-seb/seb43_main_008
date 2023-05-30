const badgeLists = [
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/signUp.svg",
    img: "/badgeIcons/1.png",
    alt: "가입 뱃지",
    mainText: "가입 축하 기념 뱃지 획득!",
    subText: "지금부터 뱃지를 획득해보세요~",
    isAcquired: true,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/dog.svg",
    img: "/badgeIcons/2.png",
    alt: "2뱃지",
    mainText: "재활용을 시작 하셨네요!",
    subText: "10%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: true,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/appreciation.svg",
    img: "/badgeIcons/3.png",
    alt: "3뱃지",
    mainText: "재활용률 10%를 달성하셨네요!",
    subText: "20%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: true,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/halloween.svg",
    img: "/badgeIcons/4.png",
    alt: "4뱃지",
    mainText: "재활용률 20%를 달성하셨네요!",
    subText: "30%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: true,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/beach.svg",
    img: "/badgeIcons/5.png",
    alt: "5뱃지",
    mainText: "재활용률 30%를 달성하셨네요!",
    subText: "40%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: true,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/blooming.svg",
    img: "/badgeIcons/6.png",
    alt: "6뱃지",
    mainText: "재활용률 40%를 달성하셨네요!",
    subText: "50%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: false, // 기본값은 false
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/family.svg",
    img: "/badgeIcons/7.png",
    alt: "7뱃지",
    mainText: "재활용률 50%를 달성하셨네요!",
    subText: "60%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: false,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/shopping.svg",
    img: "/badgeIcons/8.png",
    alt: "8뱃지",
    mainText: "재활용률 60%를 달성하셨네요!",
    subText: "70%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: false,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/wedding.svg",
    img: "/badgeIcons/9.png",
    alt: "9뱃지",
    mainText: "재활용률 70%를 달성하셨네요!",
    subText: "80%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: false,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/cat.svg",
    img: "/badgeIcons/10.png",
    alt: "10뱃지",
    mainText: "재활용률 80%를 달성하셨네요!",
    subText: "90%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: false,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/waves.svg",
    img: "/badgeIcons/11.png",
    alt: "11뱃지",
    mainText: "재활용률 90%를 달성하셨네요!",
    subText: "100%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: false,
  },
  {
    // img: "https://ssts-img.s3.ap-northeast-2.amazonaws.com/badge/coffee.svg",
    img: "/badgeIcons/12.png",
    alt: "12뱃지",
    mainText: "재활용률 100%를 달성하셨네요!",
    subText: "졸업을 축하합니다",
    isAcquired: false,
  },
];

export default badgeLists;

// 기존에는 badgeLists를 직접 import해서 사용했지만, 이제는 부모 컴포넌트에서 API로 가져온 badgeList를 사용합

// 코드를 실행하려면 BadgeData 파일에 해당하는 데이터를 서버에서 받아와야 합니다. 이것은 실제 서버와 클라이언트 간에 데이터가 어떻게 교환되는지에 따라 달라짐
// badgeList는 일련의 뱃지 이미지에 대한 정보를 담은 객체의 배열이어야 함

// 각 객체는 이미지 소스 URL(img), 대체 텍스트(alt), 메인 텍스트(mainText), 부 텍스트(subText), 그리고 뱃지 획득 여부(isAcquired)를 포함해야 함
