"use-client"
import styled from 'styled-components'

import { data } from "./data"
import { Medal } from './Medal'

interface Props {
  levle?: number;
  voting?: boolean;
}
export const DonePost: React.FC<Props> = ({ levle, voting }) => {
  return (
    <StyledDone>
      <div className="image" style={{ backgroundImage: `url(${data.image})` }}> {voting ? <div className='voting'>투표중</div> : null}</div>
      <div className='text'> 세바스찬 </div>
      {levle ? <Medal /> : null}
    </StyledDone>
  )
}

const StyledDone = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .text{
    font-size: 0.8rem;
    color: #757575;
  }
  .image {
    filter: grayscale(80%);
    border-radius: 8px;
    box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;

    width: calc(75vw / 3);
    max-width: 256px;
    height: calc(75vw / 3);
    margin-bottom: 4px;

    background-position: center;
    background-size: cover;
    border-radius: 5px;

    display: flex;
    justify-content: center;
    align-items: center;
    .voting{
      font-size: 4.5vw;
      color: white;
      text-shadow: #878787 3px 3px 5px, #878787 -3px 3px 5px, #878787 -3px -3px 5px, #878787 3px -3px 5px;

    }
  }
  @media screen and (min-width: 1024px) {
      width: 270px;
      height: 270px;
      font-size: 46px;
  }
`