"use-client"
import { useRouter } from "next/navigation";
import styled from 'styled-components'

import { StyledPost } from "./StyledPost"
import { PostData } from "./type"

export const ActivePost = ({ type, ...data }: PostData) => {

  const router = useRouter();
  const HandleMoveToDetail = (id: number, title: string) => {
    router.push(`/my-list`);
    sessionStorage.setItem("header", title);
    localStorage.setItem("plastic", id.toString())
  }

  return (
    <>
      <StyledActive onClick={type === "mine" ? () => HandleMoveToDetail(data.id, data.title) : null}>
        <div className='box'>
          <div className="image" style={{ backgroundImage: `url(${data.image})` }} />
        </div>
        <div className='text'>{data.title}</div>
      </StyledActive>

    </>
  );
}

const StyledActive = styled(StyledPost)`
  cursor: pointer;
  .box {
    border-radius: 8px;
    border: solid 2px #3f910c;
    box-shadow: 6px 6px #e4fabf, -6px 6px #e4fabf, 6px -6px #e4fabf, -6px -6px #e4fabf;
  }
`

