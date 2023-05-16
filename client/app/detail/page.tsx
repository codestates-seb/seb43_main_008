import Header from "../Header";
import { Comments } from "./Comments";
import { Slider } from "./Slider";
import { Vote } from "./Vote";

export default function Detail() {
  return (
    <>
      <Header backButton={true} textContent={"이름 어떻게 불러오지..?"} saveButton={false} />
      <Slider />
      <Vote />
      <Comments />
    </>
  );
}
