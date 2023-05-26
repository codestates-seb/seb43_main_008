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

        {/* 오픈그래프 설정 */}
        <meta property="og:title" content="쓰쓰또쓰" />
        <meta property="og:description" content="쓰고 쓰고 또 쓰자 &lt;br/&gt; 플라스틱 재사용 과정을 공유하는 서비스입니다." />
        <meta property="og:image" content="../public/icons/icon-128x128.png" />

      </head>
      <body>
        <StyledComponentsRegistry>
          <div className="main-container">{children}</div>
        </StyledComponentsRegistry>
      </body>
    </html>
  );
}
