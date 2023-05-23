// import axios from "axios";

// const sendData = async () => {
//   try {
//     const result = await axios.post(
//       `http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080/series?public=${isPublic}`,
//       { title: title },
//       {
//         headers: {
//           "Content-Type": "application/json",
//           Authorization:
//             "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjI2LCJzdWIiOiJhbDAwZmhhQG5hdmVyLmNvbSIsImlhdCI6MTY4NDU4NDIxOSwiZXhwIjoxNjg0NTg2MDE5fQ.KUxmnc--qVV3ic9fudNTSTzamamXtXBNQjaHYSCEdbmsa5ijKKkVJVhMKUy_M-EL",
//         },
//       }
//     );
//     console.log(result.data.data.id);
//     localStorage.setItem("plastic", result.data.data.id);
//   } catch (err) {
//     console.log(err);
//     return false;
//   }
//   return true;
// };
