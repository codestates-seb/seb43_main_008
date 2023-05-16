"use-client"
import styled from 'styled-components'

import { data } from "./data"
import { Medal } from './Medal'
import { StyledPost } from "./StyledPost"

interface Props {
  level?: number;
  voting?: boolean;
}

export const VotingPost: React.FC<Props> = ({ level, voting }) => {
  return (
    <StyledVoting>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }}> {voting ? <div className='voting'>투표중</div> : <div className='voting'>투표완료</div>}</div>
      </div>
      <div className='text'> 세바스찬 </div>
      {level ? <Medal /> : null}
    </StyledVoting>
  )
}

const StyledVoting = styled(StyledPost)`
  .image {    
    filter: grayscale(80%);
  }
  .voting{
    font-size: 4.5vw;
    color: white;
    text-shadow: #878787 3px 3px 5px, #878787 -3px 3px 5px, #878787 -3px -3px 5px, #878787 3px -3px 5px;
  }

`