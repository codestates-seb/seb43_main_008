import axios from "axios";

export function getMyData() {
  return axios.get(
    "http://ec2-3-34-28-12.ap-northeast-2.compute.amazonaws.com:8080/members/1"
  );
}
