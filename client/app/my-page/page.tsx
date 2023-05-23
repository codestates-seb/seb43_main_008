import { Feed } from './Feed'
import styles from "./page.module.css"
import { Profile } from './Profile'
export default function MyPage() {

  return (
    <div className={`${styles.container}`}>
      <Profile type="follower" />
      <Feed />
    </div>
  )
}