"use-client"
import styled from 'styled-components'

import { data } from "./data"


export const ActivePost = () => {
  return (
    <StyledActive>
      <div className="image" style={{ backgroundImage: `url(${data.image})` }} />
      <div className='text'> 세바스찬 </div>
    </StyledActive>
  )
}

const StyledActive = styled.div`
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
    border-radius: 8px;
    border: solid 2px #3f910c;
    box-shadow: 6px 6px #e4fabf, -6px 6px #e4fabf, 6px -6px #e4fabf, -6px -6px #e4fabf;

    width: calc(75vw / 3);
    max-width: 256px;
    height: calc(75vw / 3);
    margin-bottom: 8px;

    background-position: center;
    background-size: cover;
    border-radius: 5px;
  }
  @media screen and (min-width: 1024px) {
      width: 270px;
      height: 270px;
  }
`