import Header from "../Header";
import PhoneAuthSubmit from "./PhoneAuthSubmit";

export default function PhoneAuthPage() {
  return (
    <div className="pageContainer">
      <Header
        backButton={true}
        textContent={"휴대폰 인증"}
        saveButton={false}
      />
      <PhoneAuthSubmit />
    </div>
  );
}
