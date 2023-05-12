"use-client"
import styled from 'styled-components'
export const DonePost = () => {
  return (<StyledActive>
    done
  </StyledActive>)
}

const StyledActive = styled.div`
  width: 30%;
  height: calc(80vw / 3);

  border-radius: 8px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
`