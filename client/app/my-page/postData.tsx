import { PostData } from "./type"


export const postData: PostData[] = [
  {
    id: 1, // 공개 상태로 아직 쓰는중
    title: "title",
    isPublic: true,
    isEditable: true,
    isActive: true,
    seriesStatus: "SERIES_ACTIVE",
    image: "https://t1.daumcdn.net/cfile/tistory/99EB114B5D5903C920",
  },
  {
    id: 2, // 비공개 상태로 아직 쓰는 중
    title: "title",
    isPublic: false,
    isEditable: true,
    isActive: true,
    seriesStatus: "SERIES_ACTIVE",
    image: "https://www.suwon.com/news/photo/201706/112733_58295_3216.jpg",
  },
  {
    id: 3, // 공개 상태로 1차 투표중
    title: "title",
    isPublic: true,
    isEditable: false,
    isActive: false,
    seriesStatus: "SERIES_SLEEP",
    voteCount: 1,
    image: "https://cdn.startuptoday.kr/news/photo/202205/43885_28909_5023.jpg",
  },
  {
    id: 4, // 공개 & 1차 투표 완료 & 명예 졸업
    title: "title",
    isPublic: true,
    isEditable: false, // 이거 왜 true ? 
    isActive: false,
    seriesStatus: "SERIES_QUIET",
    voteCount: 1,
    voteResult: true,
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD4F_4CpOe8GWC_Ur7OQjlRswi1evQzUmFiVwl6x6eiHZxlFKxWEhspr1wSb27HIBdKIs&usqp=CAU",
  },
  {
    id: 5, //공개 & 1차 투표 완료 & 명예 졸업 실패 (사용자가 게시글 확인 전)
    title: "title",
    isPublic: true,
    isEditable: false,
    isActive: false,
    seriesStatus: "SERIES_SLEEP", // 사용자가 확인 전까지는 sleep 유지
    voteCount: 1,
    voteResult: false,
    image: "https://cdn.startuptoday.kr/news/photo/202205/43885_28909_5023.jpg",
  },
  {
    // 📌 5번이랑 구분 안됨
    id: 7, // 공개 & 1차 투표 완료 &  그냥 졸업 선택 (사용자가 게시글 확인 후 그만 쓰기 선택)
    title: "title",
    isPublic: true,
    isEditable: false,
    isActive: false,
    seriesStatus: "SERIES_QUIET",
    voteCount: 1,
    voteResult: false,
    image: "https://cdn.startuptoday.kr/news/photo/202205/43885_28909_5023.jpg",
  },
  {
    id: 6, // 공개 & 1차 투표 완료 & 더 쓰기 선택 (사용자가 게시글 확인 후 더쓰기 선택)
    title: "title",
    isPublic: true,
    isEditable: true,
    isActive: true, // 졸업이 아니기 때문에 active는 활성화 상태
    seriesStatus: "SERIES_ACTIVE", //사용자가 더 쓴다고 선택시 해당 Status가 투표전으로 바뀜
    voteCount: 1,
    voteResult: false,
    image: "https://cdn.startuptoday.kr/news/photo/202205/43885_28909_5023.jpg",
  },

  {
    id: 8, // 공개 & 2차 투표중
    title: "title",
    isPublic: true,
    isEditable: false,
    isActive: true,
    seriesStatus: "SERIES_SLEEP",
    voteCount: 2,
    image: "https://cdn.startuptoday.kr/news/photo/202205/43885_28909_5023.jpg",
  },
  {
    id: 9, // 공개 & 2차 투표 완료 & 명예 졸업 (자동 졸업)
    title: "title",
    isPublic: true,
    isEditable: true, // 왜 true? 
    isActive: false,
    seriesStatus: "SERIES_QUIET",
    voteCount: 2,
    voteResult: true,
    image: "https://cdn.startuptoday.kr/news/photo/202205/43885_28909_5023.jpg",
  },
  {
    id: 10, // 공개 & 2차 투표 완료 & 그냥 졸업 (자동 졸업)
    title: "title",
    isPublic: true,
    isEditable: true, // 왜 true? 
    isActive: true,  // 왜 true? 
    seriesStatus: "SERIES_QUIET",
    voteCount: 2,
    voteResult: false,
    image: "https://cdn.startuptoday.kr/news/photo/202205/43885_28909_5023.jpg",
  },
]