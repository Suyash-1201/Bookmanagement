import React from 'react'
import '../css/nav.css'
import { useNavigate } from 'react-router-dom'

const Nav = () => {

    const nav=useNavigate();
  return (
    <div className='nav-main'>
      <button onClick={()=>nav("/list")}>Books</button>
      <button onClick={()=>nav("/borrow")}>Borrowers</button>
      <button onClick={()=>nav("/")}>user</button>
    </div>
  )
}

export default Nav
