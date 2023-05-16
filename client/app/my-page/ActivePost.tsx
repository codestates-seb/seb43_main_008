"use-client"
import styled from 'styled-components'

import { data } from "./data"
import { StyledPost } from "./StyledPost"


export const ActivePost = () => {
  return (
    <StyledActive>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }} />
      </div>
      <div className='text'> 공백포함여덟글자 </div>

    </StyledActive>
  )
}

const StyledActive = styled(StyledPost)`

  .box {
    border-radius: 8px;
    border: solid 2px #3f910c;
    box-shadow: 6px 6px #e4fabf, -6px 6px #e4fabf, 6px -6px #e4fabf, -6px -6px #e4fabf;
  }
`