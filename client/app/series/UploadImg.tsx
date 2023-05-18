// "use client";

// import Image from "next/image";
// import { ChangeEvent, useState } from "react";
// import styled from "styled-components";

// function PictureLabel() {
//   return (
//     <label
//       htmlFor="picture"
//       style={{
//         display: "flex",
//         alignItems: "center",
//         justifyContent: "center;",
//       }}
//     >
//       <Image
//         src="/add-pic.svg"
//         alt="사진 업로드 아이콘"
//         width="200"
//         height="200"
//         style={{ cursor: "pointer;" }}
//       />
//     </label>
//   );
// }

// export default function UploadImg() {
//   const [previewImage, setPreviewImage] = useState<string | null>(null);

//   const handleImageChange = (e: ChangeEvent<HTMLInputElement>) => {
//     const file = e.target.files[0];
//     if (file) {
//       setPreviewImage(URL.createObjectURL(file));
//     } else {
//       setPreviewImage(null);
//     }
//   };

//   return (
//     <>
//       <UploadImageContainer>
//         {previewImage && (
//           <Image
//             src={previewImage}
//             alt="업로드 된 이미지 미리보기"
//             width="300"
//             height="300"
//           />
//         )}
//         <PictureLabel />
//         <input
//           type="file"
//           accept="image/*"
//           id="picture"
//           style={{ display: "none" }}
//           onChange={handleImageChange}
//         />
//       </UploadImageContainer>
//     </>
//   );
// }

// const UploadImageContainer = styled.div`
//   background-color: white;
//   margin-top: 10px;
//   margin-bottom: 10px;
//   width: 100%;
//   height: 60vw;
//   display: flex;
//   align-items: center;
//   padding: 5px;
//   flex-direction: column;
//   border-radius: 10px;
//   border: 1px solid #9b9ba0;
// `;

"use client";

import Image from "next/image";
import { ChangeEvent, useState } from "react";
import styled from "styled-components";

function PictureLabel() {
  return (
    <label
      htmlFor="picture"
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center;",
      }}
    >
      <Image
        src="/add-pic.svg"
        alt="사진 업로드 아이콘"
        width="200"
        height="200"
        style={{ cursor: "pointer;" }}
      />
    </label>
  );
}
export default function UploadImg({ setPhoto }: { setPhoto: Function }) {
  const [previewImage, setPreviewImage] = useState<string | null>(null);
  const handleImageChange = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files[0];
    if (file) {
      setPreviewImage(URL.createObjectURL(file));
      setPhoto(true); // 이미지가 업로드되면 setPhoto를 호출하여 photo 상태를 true로 변경
    } else {
      setPreviewImage(null);
      setPhoto(false); // 이미지가 없다면 setPhoto를 호출하여 photo 상태를 false로 변경
    }
  };

  return (
    <>
      <UploadImageContainer>
        {previewImage && (
          <Image
            src={previewImage}
            alt="업로드 된 이미지 미리보기"
            width="300"
            height="300"
          />
        )}
        {!previewImage && <PictureLabel />}
        <input
          type="file"
          accept="image/*"
          id="picture"
          style={{ display: "none" }}
          onChange={handleImageChange}
        />
      </UploadImageContainer>
    </>
  );
}

const UploadImageContainer = styled.div`
  background-color: white;
  margin-top: 10px;
  margin-bottom: 10px;
  width: 100%;
  height: 60vw;
  display: flex;
  align-items: center;
  padding: 5px;
  flex-direction: column;
  border-radius: 10px;
  border: 1px solid #9b9ba0;
`;
