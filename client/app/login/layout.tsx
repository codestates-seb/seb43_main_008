import Header from "../Header";

export default function LoginLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Header backButton={true} textContent={null} voteButton={true} />
      {children}
    </>
  );
}
