"use-client"
import styled from 'styled-components'


export const StyledPost = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  .text{
    font-size: 0.8rem;
    color: #757575;
  }
  .box{
    display: flex;
    justify-content: center;
    align-items: center;
    
    border-radius: 8px;
    border: solid 2px #A9907E;
    box-shadow: 6px 6px #FFEAD2, -6px 6px #FFEAD2, -6px -6px #FFEAD2, 6px -6px #FFEAD2;
    /* border: solid 2px rgb(10, 149, 255);
    box-shadow: 6px 6px rgb(209, 235, 253), -6px 6px rgb(202, 235, 253), -6px -6px rgb(202, 235, 253), 6px -6px rgb(202, 235, 253); */
    margin-bottom: 8px;
    padding: 0;
  }
  .image {
    width: calc(74vw / 3);
    max-width: 256px;
    height: calc(74vw / 3);

    background-position: center;
    background-size: cover;
    border-radius: 5px;

    display: flex;
    justify-content: center;
    align-items: center;
  }

`