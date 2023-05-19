import Header from "../Header";
import RegisterForm from "./SignupForm";

export default function PhoneAuthPage() {
  return (
    <div className="pageContainer">
      <Header backButton={true} textContent={"회원가입"} secretButton={false} />
      <RegisterForm />
    </div>
  );
}
