import "./global.css";
import "./font.css";

import StyledComponentsRegistry from "./registry";

export default async function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="ko">
      <head>
        <link rel="manifest" href="/manifest.json" />
        <link rel="icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="apple-touch-icon" href="/icons/icon-192x192.png" />
        <link
          rel="apple-touch-startup-image"
          href="/images/splashscreens.png"
        />
        <meta name="msapplication-TileColor" content="#ffffff" />
        <meta name="theme-color" content="#ffffff" />
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1, user-scalable=no"
        />
        {/* 타이틀 설정 */}
        <meta http-equiv="Title" content="쓰쓰또쓰" />
        {/* 오픈그래프 설정 */}
        <meta property="og:title" content="쓰쓰또쓰" />
        <meta property="og:description" content="쓰고 쓰고 또 쓰자 &lt;br/&gt; 플라스틱 재사용 과정을 공유하는 커뮤니티 서비스입니다." />
        <meta property="og:image" content="/Users/areumoh/Documents/projects/seb43_main_008_2/seb43_main_008/client/public/icons/icon-128x128.png" />
        {/* 검색 엔진 설정 */}
        <meta name="Keywords" content="쓰쓰또쓰" />
        <meta name="Description" content="쓰고 쓰고 또 쓰자. 플라스틱 재사용 과정을 공유하는 커뮤니티 서비스입니다." />
      </head>
      <body>
        <StyledComponentsRegistry>
          <div className="main-container">{children}</div>
        </StyledComponentsRegistry>
      </body>
    </html>
  );
}
