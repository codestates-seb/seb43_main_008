import "./global.css";
import "./font.css";

import Provider from "./Provider";
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
        <meta name="msapplication-TileColor" content="#D4E8D8" />
        <meta name="theme-color" content="#D4E8D8" />
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1, user-scalable=no"
        />
      </head>
      <body>
        <Provider>
          <StyledComponentsRegistry>
            <div className="mainContainer">{children}</div>
          </StyledComponentsRegistry>
        </Provider>
      </body>
    </html>
  );
}
