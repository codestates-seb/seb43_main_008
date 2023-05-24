import Header from "../../Header"
import { Feed } from '../Feed'
import styles from "../page.module.css"
import { Profile } from '../Profile'

export default function MyPage() {


  return (
    <div className={`${styles.container}`}>
      <Header backButton={true} textContent={true} voteButton={false} />
      <Profile />
      <Feed />
    </div>
  )
}