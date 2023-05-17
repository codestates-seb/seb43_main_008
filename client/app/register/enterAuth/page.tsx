import Header from "../../Header";
import AuthNumberSubmit from "./AuthNumberSubmit";

export default function PhoneAuthPage() {
  return (
    <div className="pageContainer">
      <Header
        backButton={false}
        textContent={"인증번호 입력"}
        secretButton={false}
      />
      <AuthNumberSubmit />
    </div>
  );
}
