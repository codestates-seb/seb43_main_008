import Header from "../errorPage/Header";

export default function LoginLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Header backButton={true} textContent={null} saveButton={false} />
      {children}
    </>
  );
}
