import Header from "../errorPage/Header";
import HomeButton from "./HomeButton";
import RegisterContext from "./RegisterContext";

export default function MemberEdit() {
  return (
    <div className="pageContainer">
      <Header
        backButton={false}
        textContent={"회원가입 축하"}
        saveButton={false}
      />
      <RegisterContext />
      <HomeButton />
    </div>
  );
}
