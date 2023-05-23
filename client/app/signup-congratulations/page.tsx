import Header from "../Header";
import HomeButton from "./HomeButton";
import RegisterContext from "./RegisterContext";

export default function MemberEdit() {
  return (
    <div className="pageContainer">
      <Header
        backButton={false}
        textContent={"회원가입 축하"}
        voteButton={true}
      />
      <RegisterContext />
      <HomeButton />
    </div>
  );
}
