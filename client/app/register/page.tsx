import Header from "../Header";
import RegisterForm from "./RegisterForm";

export default function PhoneAuthPage() {
  return (
    <div className="pageContainer">
      <Header backButton={true} textContent={"회원가입"} secretButton={false} />
      <RegisterForm />
    </div>
  );
}
