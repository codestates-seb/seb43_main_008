import Header from "../Header";

export default function MemberEditLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Header
        backButton={true}
        textContent={"내 정보 변경"}
        saveButton={false}
      />
      {children}
    </>
  );
}
