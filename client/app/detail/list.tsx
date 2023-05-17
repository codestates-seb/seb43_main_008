interface Data {
  id: number;
  title: string;
  image: string;
  usageCount: number;
}

// export default 키워드 다음에는 변수, 함수, 클래스 등의 선언식이 와야한다.
const list: Data[] = [
  {
    id: 1,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://post-phinf.pstatic.net/MjAxNzA0MDdfMTMx/MDAxNDkxNTQwMDQzMDky.iwrc6jB9B5E-wqRRw3BPd7YJJb0L1ML2npsWJmsBg1og.Ut_CQP9d2U-se2EfRQ2syv5s4zg9enFUrJh3mRumy7Ag.JPEG/%EB%B9%84%EB%8B%90%EB%B4%89%EC%A7%80_%EC%9E%AC%ED%99%9C%EC%9A%A9_3.jpg?type=w1200",
  },
  {
    id: 2,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://img.khan.co.kr/news/2022/12/22/news-p.v1.20221222.ce3d24a28f734a089433d64178bd3c7c_P1.jpg",
  },
  {
    id: 4,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://post-phinf.pstatic.net/MjAxNzA0MDdfMTMx/MDAxNDkxNTQwMDQzMDky.iwrc6jB9B5E-wqRRw3BPd7YJJb0L1ML2npsWJmsBg1og.Ut_CQP9d2U-se2EfRQ2syv5s4zg9enFUrJh3mRumy7Ag.JPEG/%EB%B9%84%EB%8B%90%EB%B4%89%EC%A7%80_%EC%9E%AC%ED%99%9C%EC%9A%A9_3.jpg?type=w1200",
  },
  {
    id: 5,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://img.khan.co.kr/news/2022/12/22/news-p.v1.20221222.ce3d24a28f734a089433d64178bd3c7c_P1.jpg",
  },
  {
    id: 6,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://post-phinf.pstatic.net/MjAxNzA0MDdfMTMx/MDAxNDkxNTQwMDQzMDky.iwrc6jB9B5E-wqRRw3BPd7YJJb0L1ML2npsWJmsBg1og.Ut_CQP9d2U-se2EfRQ2syv5s4zg9enFUrJh3mRumy7Ag.JPEG/%EB%B9%84%EB%8B%90%EB%B4%89%EC%A7%80_%EC%9E%AC%ED%99%9C%EC%9A%A9_3.jpg?type=w1200",
  },
  {
    id: 7,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://img.khan.co.kr/news/2022/12/22/news-p.v1.20221222.ce3d24a28f734a089433d64178bd3c7c_P1.jpg",
  },
  {
    id: 8,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://post-phinf.pstatic.net/MjAxNzA0MDdfMTMx/MDAxNDkxNTQwMDQzMDky.iwrc6jB9B5E-wqRRw3BPd7YJJb0L1ML2npsWJmsBg1og.Ut_CQP9d2U-se2EfRQ2syv5s4zg9enFUrJh3mRumy7Ag.JPEG/%EB%B9%84%EB%8B%90%EB%B4%89%EC%A7%80_%EC%9E%AC%ED%99%9C%EC%9A%A9_3.jpg?type=w1200",
  },
  {
    id: 9,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://post-phinf.pstatic.net/MjAxNzA0MDdfMTMx/MDAxNDkxNTQwMDQzMDky.iwrc6jB9B5E-wqRRw3BPd7YJJb0L1ML2npsWJmsBg1og.Ut_CQP9d2U-se2EfRQ2syv5s4zg9enFUrJh3mRumy7Ag.JPEG/%EB%B9%84%EB%8B%90%EB%B4%89%EC%A7%80_%EC%9E%AC%ED%99%9C%EC%9A%A9_3.jpg?type=w1200",
  },
  {
    id: 10,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://post-phinf.pstatic.net/MjAxNzA0MDdfMTMx/MDAxNDkxNTQwMDQzMDky.iwrc6jB9B5E-wqRRw3BPd7YJJb0L1ML2npsWJmsBg1og.Ut_CQP9d2U-se2EfRQ2syv5s4zg9enFUrJh3mRumy7Ag.JPEG/%EB%B9%84%EB%8B%90%EB%B4%89%EC%A7%80_%EC%9E%AC%ED%99%9C%EC%9A%A9_3.jpg?type=w1200",
  },
  {
    id: 11,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://post-phinf.pstatic.net/MjAxNzA0MDdfMTMx/MDAxNDkxNTQwMDQzMDky.iwrc6jB9B5E-wqRRw3BPd7YJJb0L1ML2npsWJmsBg1og.Ut_CQP9d2U-se2EfRQ2syv5s4zg9enFUrJh3mRumy7Ag.JPEG/%EB%B9%84%EB%8B%90%EB%B4%89%EC%A7%80_%EC%9E%AC%ED%99%9C%EC%9A%A9_3.jpg?type=w1200",
  },
  {
    id: 12,
    usageCount: 11,
    title: "봉다리 세바스찬",
    image:
      "https://post-phinf.pstatic.net/MjAxNzA0MDdfMTMx/MDAxNDkxNTQwMDQzMDky.iwrc6jB9B5E-wqRRw3BPd7YJJb0L1ML2npsWJmsBg1og.Ut_CQP9d2U-se2EfRQ2syv5s4zg9enFUrJh3mRumy7Ag.JPEG/%EB%B9%84%EB%8B%90%EB%B4%89%EC%A7%80_%EC%9E%AC%ED%99%9C%EC%9A%A9_3.jpg?type=w1200",
  },
];

export default list;
