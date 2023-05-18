import Header from "../Header";
import RegisterForm from "./RegisterForm";

export default function PhoneAuthPage() {
  return (
    <div className="page-container">
      <Header backButton={true} textContent={"회원가입"} saveButton={false} />

      <RegisterForm />
    </div>
  );
}
