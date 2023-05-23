import Header from "../Header"
import { Feed } from './Feed'
import styles from "./page.module.css"
import { Profile } from './Profile'

export default function MyPage() {

  return (
    <div className={`${styles.container}`}>
      <Header backButton={true} textContent={"마이페이지"} voteButton={false} />
      <Profile type="mine" />
      <Feed type="mine" />
    </div>
  )
}