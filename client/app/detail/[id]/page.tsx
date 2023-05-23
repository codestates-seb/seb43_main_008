import Header from "../../Header";
import { Comments } from "../Comments";
import { Slider } from "../Slider";
import { Vote } from "../Vote";

export default function Detail() {
  return (
    <>
      <Header backButton={true} textContent={true} voteButton={false} />
      <Slider />
      <Vote />
      <Comments />
    </>
  );
}
