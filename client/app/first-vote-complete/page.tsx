import Header from "../Header";
import Navbar from "../Navbar";
import VoteResultContext from "./VoteResultContext";

export default function MemberEdit() {
  return (
    <div className="header-page-container">
      <Header backButton={true} textContent="투표 결과" voteButton={false} />
      <VoteResultContext />
      <Navbar />
    </div>
  );
}
